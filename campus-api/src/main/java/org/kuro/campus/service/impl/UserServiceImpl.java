package org.kuro.campus.service.impl;

import com.aliyun.oss.ServiceException;
import com.aliyuncs.exceptions.ClientException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.kuro.campus.auth.JWTToken;
import org.kuro.campus.handler.BusinessException;
import org.kuro.campus.mapper.UserMapper;
import org.kuro.campus.mapper.UserRoleMapper;
import org.kuro.campus.model.bean.ActiveUser;
import org.kuro.campus.model.entity.Permission;
import org.kuro.campus.model.entity.Role;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.entity.UserRole;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.UserService;
import org.kuro.campus.utils.CurrentUser;
import org.kuro.campus.utils.JWTUtils;
import org.kuro.campus.utils.NumberUtils;
import org.kuro.campus.utils.SmsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 22:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsListener smsListener;

    private static final String key_prefix = "user: verify";

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public Result register(User user) {
        User checkUsername = userMapper.loadUserByUsername(user.getUsername());
        if (checkUsername != null) {
            return Result.error(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(new Date());
        user.setAvatar(getRandomImg());
        userMapper.insertSelective(user);
        // 为用户分配默认角色
        UserRole userRole = new UserRole();
        userRole.setRoleId(2);
        userRole.setUserId(user.getId());
        userRoleMapper.insertSelective(userRole);
        return Result.ok(ResultCode.REGISTER_SUCCESS);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.loadUserByUsername(username);
    }

    /**
     * 查询用户角色
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> findRolesById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new ServiceException(ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        return userMapper.getRolesById(id);
    }

    /**
     * 根据用户角色查询用户的权限
     *
     * @param roles
     * @return
     */
    @Override
    public List<Permission> findPermissionByRoles(List<Role> roles) {
        Integer[] roleIds = new Integer[roles.size()];
        roles.forEach(role -> {
            roleIds[0] = role.getId();
        });
        return userMapper.findPermissionByRoles(roleIds[0]);
    }

    @Override
    public Result login(User user, HttpServletRequest request) {
        User userByUsername = userMapper.loadUserByUsername(user.getUsername());
        if (userByUsername == null) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        boolean result = passwordEncoder.matches(user.getPassword(), userByUsername.getPassword());
        if (!result) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        String token = JWTUtils.sign(userByUsername.getUsername(), userByUsername.getPassword());
        JWTToken jwtToken = new JWTToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("user", activeUser);
            return Result.ok(ResultCode.LOGIN_SUCCESS).data(map);
        } catch (AuthenticationException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result adminLogin(User user, HttpServletRequest request) {
        User userByUsername = userMapper.loadUserByUsername(user.getUsername());
        if (userByUsername == null) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        boolean result = passwordEncoder.matches(user.getPassword(), userByUsername.getPassword());
        if (!result) {
            return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        }
        // 获取用户角色
        List<Role> roles = userMapper.getRolesById(userByUsername.getId());
        for (Role role : roles) {
            if (role.getId() == 1) {
                String token = JWTUtils.sign(userByUsername.getUsername(), userByUsername.getPassword());
                JWTToken jwtToken = new JWTToken(token);
                try {
                    SecurityUtils.getSubject().login(jwtToken);
                    ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", token);
                    map.put("user", activeUser);
                    return Result.ok(ResultCode.LOGIN_SUCCESS).data(map);
                } catch (AuthenticationException e) {
                    throw new ServiceException(e.getMessage());
                }
            }
        }
        return Result.error(ResultCode.USER_CREDENTIALS_ERROR);
    }

    /**
     * 发送验证码，1为注册，2为改密
     * @param phone
     * @param type
     * @return
     */
    @Override
    public Result sendCode(String phone, Integer type) throws ClientException {
        if (StringUtils.isBlank(phone)) {
            return Result.error(ResultCode.PARAM_IS_BLANK);
        }

        // 生成6位随机验证码
        String code = NumberUtils.generateCode(6);
        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        if (type == 1) {
            // 注册
            smsListener.sendSms(msg, "SMS_200692839");
        } else {
            // 改密
            smsListener.sendSms(msg, "SMS_206563694");
        }
        // 把验证码保存到redis
        redisTemplate.opsForValue().set(key_prefix + phone, code, 5, TimeUnit.MINUTES);
        return Result.ok(ResultCode.PHONE_CODE_SEND);
    }

    /**
     * 修改信息
     * @param user
     * @return
     */
    @Override
    public Integer setting(User user, String code) {
        String redisCode = (String) redisTemplate.opsForValue().get(key_prefix + user.getPhone());
        if (!StringUtils.equals(code, redisCode)) {
            throw new BusinessException(ResultCode.CODE_ERROR.getCode(), ResultCode.CODE_ERROR.getMessage());
        }
        user.setId(CurrentUser.getCurrentUser().getId());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    // 随机头像
    private static final String[] headImg = {
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/category/26.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/1.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/2.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/3.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/4.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/5.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/6.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/7.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/8.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/9.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/11.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/12.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/13.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/14.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/15.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/16.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/17.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/18.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/19.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/20.jpg",
            "https://kuro-campus.oss-cn-shenzhen.aliyuncs.com/random-avatar/21.jpg",
    };

    private String getRandomImg() {
        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}

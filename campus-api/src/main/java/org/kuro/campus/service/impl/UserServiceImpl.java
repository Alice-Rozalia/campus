package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.UserMapper;
import org.kuro.campus.mapper.UserRoleMapper;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.entity.UserRole;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

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

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Transactional
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

    // 随机头像
    private static final String [] headImg = {
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

    private String getRandomImg(){
        int size =  headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}

package org.kuro.campus.service;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import org.kuro.campus.model.entity.Permission;
import org.kuro.campus.model.entity.Role;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 22:00
 */
public interface UserService {

    /**
     * 注册
     * @param user
     * @return
     */
    Result register(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 查询用户角色
     * @param id
     * @return
     */
    List<Role> findRolesById(Integer id);

    /**
     * 根据用户角色查询用户的权限
     * @param roles
     * @return
     */
    List<Permission> findPermissionByRoles(List<Role> roles);

    Result login(User user, HttpServletRequest request);

    User findUserById(Integer id);

    Result adminLogin(User user, HttpServletRequest request);

    /**
     * 发送验证码，1为注册，2为改密
     * @param phone
     * @param type
     * @return
     */
    Result sendCode(String phone, Integer type) throws ClientException;

    /**
     * 修改信息
     * @param user
     * @return
     */
    Integer setting(User user, String code);

    PageResult<User> getUserList(Integer page, Integer limit);
}

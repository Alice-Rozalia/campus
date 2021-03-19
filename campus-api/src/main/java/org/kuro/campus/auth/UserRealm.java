package org.kuro.campus.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.kuro.campus.model.bean.ActiveUser;
import org.kuro.campus.model.entity.Permission;
import org.kuro.campus.model.entity.Role;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.UserService;
import org.kuro.campus.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if (!activeUser.getUser().getAdmin()) {
            authorizationInfo.addStringPermission("*:*");
        } else {
            List<String> permissions = new ArrayList<>(activeUser.getPermissions());
            List<Role> roleList = activeUser.getRoles();
            //授权角色
            if (!CollectionUtils.isEmpty(roleList)) {
                for (Role role : roleList) {
                    authorizationInfo.addRole(role.getName());
                }
            }
            //授权权限
            if (!CollectionUtils.isEmpty(permissions)) {
                for (String permission : permissions) {
                    if (permission != null && !"".equals(permission)) {
                        authorizationInfo.addStringPermission(permission);
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtils.getUsername(token);

        if (username == null) {
            throw new AuthenticationException(ResultCode.USER_NOT_LOGIN.getMessage());
        }

        User userBean = userService.findUserByUsername(username);

        if (userBean == null) {
            throw new AccountException(ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        if (JWTUtils.isExpire(token)) {
            throw new AuthenticationException(ResultCode.USER_TOKEN_EXPIRED.getMessage());
        }
        if (!userBean.getEnabled()) {
            throw new LockedAccountException(ResultCode.USER_ACCOUNT_DISABLE.getMessage());
        }

        // 如果验证通过，获取用户的角色
        List<Role> roles = userService.findRolesById(userBean.getId());
        // 查询用户的所有菜单(包括了菜单和按钮)
        List<Permission> permissions = userService.findPermissionByRoles(roles);

        Set<String> perms = new HashSet<>();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (Permission permission : permissions) {
                String per = permission.getPerms();
                perms.add(per);
            }
        }
        //过滤出url,和用户的权限
        ActiveUser activeUser = new ActiveUser();
        activeUser.setRoles(roles);
        activeUser.setUser(userBean);
        activeUser.setPermissions(perms);
        return new SimpleAuthenticationInfo(activeUser, token, getName());
    }
}

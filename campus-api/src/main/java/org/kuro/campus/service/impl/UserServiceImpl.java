package org.kuro.campus.service.impl;

import org.kuro.campus.handler.BusinessException;
import org.kuro.campus.mapper.UserMapper;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 22:00
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(), ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        user.setRoles(userMapper.getRolesById(user.getId()));
        return user;
    }
}

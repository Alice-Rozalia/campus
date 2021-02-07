package org.kuro.campus.service.impl;

import org.kuro.campus.handler.BusinessException;
import org.kuro.campus.mapper.UserMapper;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 23:18
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户%s不存在", s));
        }
        user.setRoles(userMapper.getRolesById(user.getId()));
        return user;
    }
}

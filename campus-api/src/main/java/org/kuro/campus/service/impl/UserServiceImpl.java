package org.kuro.campus.service.impl;

import org.kuro.campus.mapper.UserMapper;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 22:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public Integer register(User user) {
        return userMapper.insertSelective(user);
    }
}

package org.kuro.campus.service;

import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.Result;

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
}

package org.kuro.campus.utils;

import org.kuro.campus.model.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/10 12:42
 */
public class CurrentUser {

    public static User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

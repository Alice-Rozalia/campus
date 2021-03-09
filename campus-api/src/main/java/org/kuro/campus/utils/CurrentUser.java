package org.kuro.campus.utils;

import org.apache.shiro.SecurityUtils;
import org.kuro.campus.model.bean.ActiveUser;
import org.kuro.campus.model.entity.User;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/10 12:42
 */
public class CurrentUser {

    public static User getCurrentUser() {
        if (SecurityUtils.getSubject().getPrincipal() == null) {
            return null;
        }
        return ((ActiveUser) SecurityUtils.getSubject().getPrincipal()).getUser();
    }
}

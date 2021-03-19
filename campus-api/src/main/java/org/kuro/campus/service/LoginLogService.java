package org.kuro.campus.service;

import org.kuro.campus.model.entity.LoginLog;
import org.kuro.campus.model.page.PageResult;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/17 11:00
 */
public interface LoginLogService {

    /**
     * 分页查询登录日志
     * @param page
     * @param limit
     * @return
     */
    PageResult<LoginLog> findLoginLog(Integer page, Integer limit);
}

package org.kuro.campus.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.kuro.campus.mapper.LoginLogMapper;
import org.kuro.campus.model.entity.LoginLog;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/17 11:01
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 分页查询登录日志
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageResult<LoginLog> findLoginLog(Integer page, Integer limit) {
        if (page != null && limit != null) {
            PageHelper.startPage(page, limit);
        }
        List<LoginLog> loginLogs = loginLogMapper.selectAll();
        PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogs);
        return new PageResult<>(Math.toIntExact(pageInfo.getTotal()), loginLogs);
    }
}

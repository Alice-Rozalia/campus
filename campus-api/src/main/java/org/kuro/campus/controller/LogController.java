package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.LoginLog;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/3/17 11:06
 */
@RestController
@RequestMapping("api/v1")
@Api(value = "系统管理模块", tags = "日志管理")
public class LogController {

    @Autowired
    private LoginLogService loginLogService;

    @RequiresPermissions({"log:login"})
    @GetMapping("/pri/log/login")
    @ApiOperation(value = "登录日志", notes = "分页查询所有的登录日志，不传参数则查询所有")
    public Result findLoginLogs(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        PageResult<LoginLog> loginLogs = loginLogService.findLoginLog(page, rows);
        return Result.ok().data("loginLogs", loginLogs);
    }
}

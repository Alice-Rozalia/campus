package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 19:01
 */
@RestController
@Api(value = "系统管理模块", tags = "特殊处理")
public class LoginController {

    @GetMapping("/login")
    @ApiOperation(value = "尚未登录", notes = "用户未登录访问授权接口时，重定向到该路由")
    public Result login() {
        return Result.error(ResultCode.USER_NOT_LOGIN);
    }

}

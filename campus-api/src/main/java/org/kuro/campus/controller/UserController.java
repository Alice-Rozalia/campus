package org.kuro.campus.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/2/5 22:58
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/pub/user/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.ok(ResultCode.ADD_SUCCESS);
    }
}

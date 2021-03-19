package org.kuro.campus.controller;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.page.PageResult;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.kuro.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    public Result register(@RequestBody @Valid User user) {
        return userService.register(user);
    }

    @PostMapping("/pub/user/login")
    @ApiOperation(value = "登录授权", notes = "登录授权接口，登录成功返回token令牌")
    public Result login(@RequestBody @Valid User user, HttpServletRequest request) {
        return userService.login(user, request);
    }

    @PostMapping("/pub/user/admin_login")
    @ApiOperation(value = "后台登录", notes = "后台登录接口，根据用户的账号和角色进行判断")
    public Result adminLogin(@RequestBody @Valid User user, HttpServletRequest request) {
        return userService.adminLogin(user, request);
    }

    @RequiresPermissions({"user:code"})
    @GetMapping("/pri/user/code")
    @ApiOperation(value = "验证码", notes = "根据邮箱发送验证码，1为注册验证码，其余为改密验证码")
    public Result sendCode(
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "type", defaultValue = "1") Integer type
    ) throws ClientException {
        return this.userService.sendCode(phone, type);
    }

    @RequiresPermissions({"user:setting"})
    @PutMapping("pri/user/setting/{code}")
    @ApiOperation(value = "修改信息", notes = "修改用户的地址和手机号码")
    public Result setting(@RequestBody User user, @PathVariable("code") String code) {
        userService.setting(user, code);
        return Result.ok(ResultCode.UPDATE_SUCCESS);
    }

    @RequiresPermissions({"user:list"})
    @GetMapping("pri/user/list")
    @ApiOperation(value = "用户列表", notes = "获取所有的用户")
    public Result getUserList(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        PageResult<User> list = userService.getUserList(page, limit);
        return Result.ok().data("users", list);
    }
}

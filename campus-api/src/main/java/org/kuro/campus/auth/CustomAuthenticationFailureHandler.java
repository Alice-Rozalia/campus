package org.kuro.campus.auth;

import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败的处理方案
 *
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 16:41
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends JSONAuthentication implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
            throws IOException, ServletException {
        // 对抛出的异常进行判断,判断到底是什么原因导致的登录失败
        Result result = null;
        if (e instanceof AccountExpiredException) {
            // 账号过期
            result = Result.error(ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            result = Result.error(ResultCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            result = Result.error(ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            // 账号不可用
            result = Result.error(ResultCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            // 账号锁定
            result = Result.error(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            // 用户不存在
            result = Result.error(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else {
            // 其他错误
            result = Result.error(ResultCode.COMMON_FAIL);
        }
        this.WriteJson(req, res, result);
    }
}

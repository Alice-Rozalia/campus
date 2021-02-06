package org.kuro.campus.auth;

import org.kuro.campus.model.entity.User;
import org.kuro.campus.model.response.Result;
import org.kuro.campus.model.response.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功的处理方案
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 16:37
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication)
            throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Result result = Result.ok(ResultCode.LOGIN_SUCCESS).data("user", user);
        this.WriteJson(req, res, result);
    }
}

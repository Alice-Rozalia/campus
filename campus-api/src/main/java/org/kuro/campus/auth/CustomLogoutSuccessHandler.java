package org.kuro.campus.auth;

import org.kuro.campus.model.response.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 16:45
 */
@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler extends JSONAuthentication implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication)
            throws IOException, ServletException {

        this.WriteJson(req, res, Result.ok().message("注销成功！"));

    }
}

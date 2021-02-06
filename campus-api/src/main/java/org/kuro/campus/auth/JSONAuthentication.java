package org.kuro.campus.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 封装输出JSON格式的类
 * @Author: 白鸟亦悲否？
 * @Date: 2020/12/23 16:47
 */
public class JSONAuthentication {

    protected void WriteJson(HttpServletRequest req, HttpServletResponse res, Object data)
            throws IOException, ServletException {
        // 这里很重要，否则页面获取不到正常的JSON数据集
        res.setContentType("application/json;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Method", "POST,GET");
        // 输出JSON
        PrintWriter out = res.getWriter();
        out.write(new ObjectMapper().writeValueAsString(data));
        out.flush();
        out.close();
    }

}

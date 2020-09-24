package com.fjjxpjy.controller;


import com.fjjxpjy.pojo.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
public class BaseController extends HttpServlet {
    // 当前会话session
    public HttpSession session;

    // 当前登录的用户
    public User loginUser = null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {

        // 当前登录用户
        loginUser = (User) session.getAttribute("loginSession");
        //获取请求的URI /user/login
        String uri = request.getRequestURI();

        // 从最后一个/开始截取 login
        uri = uri.substring(uri.lastIndexOf("/") + 1);

        // 具体的servlet
        Class<?> targetClass = this.getClass();

        System.out.println(this);
        try {
            // 通过截取uri(方法名),反射出对应的方法
            Method method = targetClass.getDeclaredMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            // 传入参数并执行方法
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

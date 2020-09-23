package com.fjjxpjy.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
@WebServlet("/user/*")
public class UserController extends BaseController {

    /**
     * @description 登录
     * @author fangjj
     * @date 2020/9/22
     * @param request, response]
     * @return void
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("login");
    }


}

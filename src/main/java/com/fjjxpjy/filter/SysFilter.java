package com.fjjxpjy.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjjxpjy.pojo.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;


/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
@WebFilter("/*")
public class SysFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //解决汉字乱码问题
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();


        //规定登录只能进这个，然后进行cookie的判断
        if (requestURI.endsWith("/") ||
                requestURI.endsWith("/index.jsp")

        ) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if (name.equals("loginCookie")) {
                        String value = cookie.getValue();
                        //先解码
                        String logindecode = URLDecoder.decode(value, "UTF-8");
                        //在解析json字符串~
                        User user = new ObjectMapper().readValue(logindecode, new TypeReference<User>() {
                        });
                        //解析完后放到session中
                        session.setAttribute("loginSession", user);
                        session.setMaxInactiveInterval(60 * 30);

                        //搞定后跳转到首页
                        request.setAttribute("loginUser",user);
                        request.getRequestDispatcher("/html/comom/home.jsp").forward(request, response);
                        chain.doFilter(request, response);
                        return;

                    }
                }
            }

        } else if (requestURI.endsWith("/menu/listAll") || requestURI.endsWith("/user/login") ||
        requestURI.endsWith("/img/getCode")) {


        }
        else {
            //如果不是放行的路径，将重定向回登录界面
            //重定向回登录界面，要判断是否有session
            Object loginSession = session.getAttribute("loginSession");
            if (loginSession == null) {
                response.sendRedirect("/index.jsp");
                return;
            }
        }

        request.setAttribute("loginUser",session.getAttribute("loginSession"));
        chain.doFilter(request, response);

    }
}

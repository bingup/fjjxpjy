package com.fjjxpjy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjjxpjy.pojo.Dept;
import com.fjjxpjy.pojo.User;
import com.fjjxpjy.service.DpetService;
import com.fjjxpjy.service.UserService;
import com.fjjxpjy.utils.Page;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
@WebServlet("/user/*")
public class UserController extends BaseController {
    private UserService userService = new UserService();
    private DpetService dpetService = new DpetService();

    /**
     * @return void
     * @description 登录
     * @author fangjj
     * @date 2020/9/22
     * @params [request, response]
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");   //一定要与表单的名字相同
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        ObjectMapper om = new ObjectMapper();


        User loginUser = userService.checkUser(username, password);

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 30);
        if (loginUser != null) {

            session.setAttribute("loginSession", loginUser);

            //是否要免密登录
            if ("1".equals(remember)) {
                String str = om.writeValueAsString(loginUser);

                Cookie cookie = new Cookie("loginCookie", URLEncoder.encode(str, "UTF-8"));
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }


            request.getRequestDispatcher("/html/comom/home.jsp").forward(request, response);

        } else {
            response.sendRedirect("/index.jsp");
        }

    }

    /**
     * @description 用户查询
     * @param [request, response]
     * @return void
     * @author fangjj
     * @date 2020/9/23
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        if (username == null) {
            username = "";
        }
        String pageCurrent = request.getParameter("pageCurrent");
        Page<User> page = new Page<>();
        Integer count = userService.count(username);
        page.setCount(count);
        if (pageCurrent != null && !(pageCurrent.equals(""))) {
            page.setPageCurrent(Integer.valueOf(pageCurrent));
        }
        List<User> list = userService.findAll(username, page);
        page.setData(list);
        request.setAttribute("username", username);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/html/user/list.jsp").forward(request, response);

    }

    /**
     * @description 删除
     * @return void
     * @author fangjj
     * @date 2020/9/23
     * @params [request, response]
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        userService.delete(Integer.valueOf(id));
        String pageCurrent = request.getParameter("pageCurrent");
        Page<User> page = new Page<>();
        Integer count = userService.count(username);
        page.setCount(count);
        if (pageCurrent != null && !(pageCurrent.equals(""))) {
            page.setPageCurrent(Integer.valueOf(pageCurrent));
        }
        List<User> list = userService.findAll(username, page);
        page.setData(list);
        request.setAttribute("username", username);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/html/user/list.jsp").forward(request, response);
    }

    /**
     * @description  update更新的过渡
     * @author fangjj
     * @date 2020/9/23
     * @params [request, response]
     * @return void
     */
    public void toupdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        User user = userService.findUser(Integer.valueOf(id));
        List<Dept> list = dpetService.findAll();
        request.setAttribute("list",list);
        request.setAttribute("user",user);
        request.getRequestDispatcher("/html/user/update.jsp").forward(request, response);
    }

    /**
     * @description user更新数据
     * @author fangjj
     * @date 2020/9/23
     * @params [request, response]
     * @return void
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        //遍历set集合
        for (Map.Entry<String, String[]> entry : entries) {
            System.out.println(entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "<br>");
        }

        //创建User对象
        User user = new User();
        //使用BeanUtils工具将表单中所有的数据封装成User对象
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.update(user);

        String username = user.getUsername();
        String pageCurrent = request.getParameter("pageCurrent");
        Page<User> page = new Page<>();
        Integer count = userService.count(username);
        page.setCount(count);
        if (pageCurrent != null && !(pageCurrent.equals(""))) {
            page.setPageCurrent(Integer.valueOf(pageCurrent));
        }
        List<User> list = userService.findAll(username, page);
        page.setData(list);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/html/user/list.jsp").forward(request, response);
    }

    /**
     * @description  user添加
     * @author fangjj
     * @date 2020/9/23
     * @params [request, response]
     * @return void
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        //遍历set集合
        for (Map.Entry<String, String[]> entry : entries) {
            System.out.println(entry.getKey() + "=" + Arrays.toString(entry.getValue()) );
        }

        //创建User对象
        User user = new User();
        //使用BeanUtils工具将表单中所有的数据封装成User对象
        try {
           BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.add(user);
        String username = user.getUsername();
        String pageCurrent = request.getParameter("pageCurrent");
        Page<User> page = new Page<>();
        Integer count = userService.count(username);
        page.setCount(count);
        if (pageCurrent != null && !(pageCurrent.equals(""))) {
            page.setPageCurrent(Integer.valueOf(pageCurrent));
        }
        List<User> list = userService.findAll(username, page);
        page.setData(list);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/html/user/list.jsp").forward(request, response);

    }
}

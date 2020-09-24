package com.fjjxpjy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjjxpjy.pojo.Dept;
import com.fjjxpjy.service.DpetService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/23
 * @description
 */
@WebServlet("/dept/*")
public class DeptController extends BaseController {
    private DpetService dpetService = new DpetService();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper om = new ObjectMapper();
        List<Dept> list = dpetService.findAll();
        String str = om.writeValueAsString(list);
        response.getWriter().write(str);
    }


}

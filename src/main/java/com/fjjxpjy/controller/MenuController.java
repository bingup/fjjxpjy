package com.fjjxpjy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjjxpjy.pojo.Menu;
import com.fjjxpjy.service.MenuService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fangjj
 * @date 2020/9/22
 * @description
 */
@WebServlet("/menu/*")
public class MenuController extends BaseController {
    private MenuService menuService = new MenuService();

    public void listAll(HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper om = new ObjectMapper();
        List<Menu> list = menuService.listAll();
        List<Menu> parent;
        List<Menu> son;
        parent = list.stream().filter((n) -> {
            return n.getType().equals("1");
        }).collect(Collectors.toList());
        son = list.stream().filter((n) -> {
            return n.getType().equals("2");
        }).collect(Collectors.toList());
        Map<String,List<Menu>> map = new HashMap<>();
        map.put("parent",parent);
        map.put("son",son);
        try {
            String str = om.writeValueAsString(map);
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

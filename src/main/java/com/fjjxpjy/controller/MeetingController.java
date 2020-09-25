package com.fjjxpjy.controller;

import com.fjjxpjy.pojo.Meeting;
import com.fjjxpjy.pojo.User;
import com.fjjxpjy.service.MeetingService;
import com.fjjxpjy.utils.Page;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fangjj
 * @date 2020/9/25
 * @description
 */
@WebServlet("/meet/*")
public class MeetingController extends BaseController {
    private MeetingService meetingService = new MeetingService();

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
            Set<Map.Entry<String, String[]>> entries = map.entrySet();
            //遍历set集合
            for (Map.Entry<String, String[]> entry : entries) {
                    System.out.println(entry.getKey() + "=" + Arrays.toString(entry.getValue()));
                }

            //创建meeting对象
            Meeting meeting = new Meeting();
            //使用BeanUtils工具将表单中所有的数据封装成User对象
            try {
                    BeanUtils.populate(meeting, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            meetingService.add(meeting);

        }


    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        if (title == null) {
            title = "";
        }
        String pageCurrent = request.getParameter("pageCurrent");

        Page<Meeting> page = new Page<>();
        Integer count = meetingService.count(title);
        page.setCount(count);
        if (pageCurrent != null && !(pageCurrent.equals(""))) {
            page.setPageCurrent(Integer.valueOf(pageCurrent));
        }
        List<Meeting> list = meetingService.findAll(title,page);
        page.setData(list);
        request.setAttribute("title", title);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/html/meeting/list.jsp").forward(request, response);
    }

    }



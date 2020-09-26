package com.fjjxpjy.controller;

import com.fjjxpjy.enums.MeetingStatusEnum;
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
import java.lang.reflect.Array;
import java.util.*;

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
        List<Meeting> list = meetingService.findAll(title, page);
        page.setData(list);
        request.setAttribute("title", title);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/html/meeting/list.jsp").forward(request, response);
    }

    /**
     * @return void
     * @description 回显每个会议的信息，例如参加的人数，还有别的信息
     * @author fangjj
     * @date 2020/9/25
     * @params [request, response]
     */
    public void getMeetingById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();
        String id = request.getParameter("id");
        Meeting meeting = meetingService.getMeetingById(Integer.valueOf(id));
        //查询出当前用户的id
        Integer userId = loginUser.getId();
        //根据meeting得到当前参加用户的集合
        String makeUser = meeting.getMakeUser();
        makeUser = makeUser.replace("[", "");
        makeUser = makeUser.replace("]", "");
        String[] split = makeUser.split(",");
        ArrayList<String> list = new ArrayList(Arrays.asList(split));
        //应该到的人数
        Integer should = list.size();
        //实际到的人数
        List<Integer> joinListUseId = meetingService.getJoinListUseId(id);
        Integer realCount = joinListUseId.size();
        //将数据放到map中然后传给前端
        map.put("should", should.toString());
        map.put("realCount", realCount.toString());
        //是否需要参加会议
        //flag 判断该人是否能参加该会议~
        if (list.contains(userId.toString()) && meeting.getStatus() == MeetingStatusEnum.NO_START.getValue()) {
            //需要参加会议 之前还要判断该会议的状态是否能够参加或者取消参加
            if (joinListUseId.contains(userId)) {
                //显示参加按钮
                map.put("flag", "2");
            } else {
                map.put("flag", "3");
            }
        } else {
            //不需要参加会议
            map.put("flag", "1");
            if (meeting.getStatus() ==MeetingStatusEnum.RUNNING.getValue() ){
                map.put("msg","会议已经开始了，不能参加了");
            }else {
                map.put("msg","会议已经结束了，你还参加个栏子");
            }

        }
        request.setAttribute("map", map);
        request.setAttribute("meeting", meeting);
        request.getRequestDispatcher("/html/meeting/detail.jsp").forward(request, response);

    }

    /**
     * @return void
     * @description 参加会议~
     * @author fangjj
     * @date 2020/9/26
     * @params [request, response]
     */
    public void joinMeeting(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        meetingService.joinMeeting(Integer.valueOf(id), loginUser.getId());


        request.setAttribute("id", id);
        request.getRequestDispatcher("/meet/getMeetingById").forward(request, response);
    }

    /**
     * @return void
     * @description 退出会议
     * @author fangjj
     * @date 2020/9/26
     * @params [request, response]
     */
    public void exitMeeting(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        meetingService.exitMeeting(loginUser.getId());

        request.setAttribute("id", id);
        request.getRequestDispatcher("/meet/getMeetingById").forward(request, response);
    }
}



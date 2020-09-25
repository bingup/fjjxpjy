package com.fjjxpjy.service;

import com.fjjxpjy.dao.MeetingDao;
import com.fjjxpjy.enums.MeetingStatusEnum;
import com.fjjxpjy.pojo.Meeting;
import com.fjjxpjy.utils.DateUtil;
import com.fjjxpjy.utils.Page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author fangjj
 * @date 2020/9/25
 * @description
 */
public class MeetingService {
    private MeetingDao meetingDao = new MeetingDao();

    public void add(Meeting meeting) {

        meeting.setMakeUser(Arrays.toString(meeting.getMakeUsers()));
        meeting.setPublishDate(DateUtil.getNow());
        meeting.setStatus(MeetingStatusEnum.NO_START.getValue());
        meetingDao.add(meeting);
    }

    public Integer count(String title) {
        return meetingDao.count(title);
    }

    public List<Meeting> findAll(String title, Page<Meeting> page) {
        return meetingDao.findAll(title, page);
    }

    public void updateStatusTimer() {
        //更新所有的status
        //先查询所有的meeting，在根据时间来比较
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Meeting> meetingList = meetingDao.queryAll();
        for (Meeting meeting : meetingList) {
            try {
                long startTime = sdf.parse(meeting.getStartTime()).getTime();//11
                long endTime = sdf.parse(meeting.getEndTime()).getTime();//12
                long now = sdf.parse(DateUtil.getNow()).getTime();

                if(now < startTime) {
                    //未开始
                } else if (startTime <= now && now < endTime){
                    meeting.setStatus(MeetingStatusEnum.RUNNING.getValue());
                    System.out.println("会议修改了状态"+meeting.getId());
                    meetingDao.updateStatusById(meeting);
                }else{
                    meeting.setStatus(MeetingStatusEnum.END.getValue());
                    System.out.println("会议修改成了终止的状态"+meeting.getId());
                    meetingDao.updateStatusById(meeting);
                }


            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }
}

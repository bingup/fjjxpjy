package com.fjjxpjy.task;

import com.fjjxpjy.service.MeetingService;

import java.util.TimerTask;

/**
 * @author fangjj
 * @date 2020/9/25
 * @description
 */
public class MeetingTask extends TimerTask {

    private boolean isRunning = false;

    //定时任务执行体
    private MeetingService meetingService = new MeetingService();

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            meetingService.updateStatusTimer();
            isRunning = false;
        }
    }
}

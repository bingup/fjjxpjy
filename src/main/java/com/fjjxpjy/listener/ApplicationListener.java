package com.fjjxpjy.listener;

import com.fjjxpjy.task.MeetingTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;

/**
 * @author fangjj
 * @date 2020/9/25
 * @description
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer();
        MeetingTask myTask = new MeetingTask();
        //一分钟 1000*60
        timer.schedule(myTask, new Date(), 1000*60*10);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

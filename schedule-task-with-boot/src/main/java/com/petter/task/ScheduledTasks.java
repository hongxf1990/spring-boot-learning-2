package com.petter.task;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定义一个Task
 * @author hongxf
 * @since 2017-09-12 14:47
 */
@Component
public class ScheduledTasks {

    public void execute() {
        //执行任务
        System.out.println("execute, date=" + new Date());
    }
}

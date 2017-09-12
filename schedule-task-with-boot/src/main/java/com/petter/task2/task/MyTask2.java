package com.petter.task2.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hongxf
 * @since 2017-09-12 15:26
 */
@Service
public class MyTask2 extends BaseTask {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("MyTask2.execute(), date=" + new Date());
    }

    @Override
    public String cronExpression() {
        return "0/10 * * * * ?";
    }

    @Override
    public String jobName() {
        return "job2";
    }

    @Override
    public String jobGroupName() {
        return "group1";
    }

    @Override
    public String triggerName() {
        return "trigger2";
    }

    @Override
    public String triggerGroupName() {
        return "group1";
    }
}

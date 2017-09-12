package com.petter.task2.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 不再使用这个
 * @author hongxf
 * @since 2017-09-12 15:25
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello Job, date = " + new Date());
    }
}

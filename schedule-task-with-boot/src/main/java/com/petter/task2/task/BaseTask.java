package com.petter.task2.task;

import org.quartz.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 使用一个基类来实现多个task
 * @author hongxf
 * @since 2017-09-12 15:55
 */
public abstract class BaseTask implements Job {

    @Resource
    private Scheduler scheduler;

    @PostConstruct //等同于init-method
    public void init() {
        //需要定义JobDetail
        JobDetail jobDetail = JobBuilder.newJob(this.getClass())
                .withIdentity(jobName(), jobGroupName()).build();

        //定义Trigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName(), triggerGroupName())
                .withSchedule(cronScheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public abstract String cronExpression();

    public abstract String jobName();

    public abstract String jobGroupName();

    public abstract String triggerName();

    public abstract String triggerGroupName();
}

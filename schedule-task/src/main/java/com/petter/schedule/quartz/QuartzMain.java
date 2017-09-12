package com.petter.schedule.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author hongxf
 * @since 2017-06-19 15:04
 */
public class QuartzMain {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        /*
         * 在 Quartz 中，scheduler 由 scheduler 工厂创建：DirectSchedulerFactory 或者 StdSchedulerFactory。
         * 第二种工厂 StdSchedulerFactory 使用较多，
         * 因为 DirectSchedulerFactory 使用起来不够方便，需要作许多详细的手工编码设置。
         */
        // 获取Scheduler实例，并且启动任务调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        System.out.println("scheduler.start");

        System.out.println(scheduler.getSchedulerName());

        //具体任务，使用JobBuilder.newJob进行创建
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1", "group1").build();

        //触发时间点. (每5秒执行1次.)
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();
        //TriggerBuilder.newTrigger创建trigger类
        //四种类型的 trigger：SimpleTrigger，CronTirgger，DateIntervalTrigger，和 NthIncludedDayTrigger
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1").startNow()
                .withSchedule(simpleScheduleBuilder).build();

        // 交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail,trigger);

        //睡眠20秒.
        TimeUnit.SECONDS.sleep(20);
        scheduler.shutdown();//关闭定时任务调度器.
        System.out.println("scheduler.shutdown");
    }
}

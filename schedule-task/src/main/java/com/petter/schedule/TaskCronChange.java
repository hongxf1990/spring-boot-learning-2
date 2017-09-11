package com.petter.schedule;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 定时任务动态修改cron参数
 * @author hongxf
 * @since 2017-06-01 13:52
 */
@RestController
@EnableScheduling
public class TaskCronChange implements SchedulingConfigurer {

    private String cron = "0/5 * * * * *"; //每5秒执行一下定时任务

    @RequestMapping("/changeCron")
    public String changeCron() {
        cron = "0/10 * * * * *"; //每10秒执行一下定时任务
        return "changeCron";
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = () -> {
            //任务逻辑代码部分.
            System.out.println("TaskCronChange task is running ... " + new Date());
        };

        Trigger trigger = triggerContext -> {
            //任务触发，可修改任务的执行周期.
            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        };
        //两个参数，一个Runnable,一个是Trigger，在Runnable中执行业务逻辑代码，在Trigger修改定时任务的执行周期。
        taskRegistrar.addTriggerTask(task, trigger);
    }

}

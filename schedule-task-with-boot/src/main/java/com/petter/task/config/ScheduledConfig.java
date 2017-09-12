package com.petter.task.config;

import com.petter.task.ScheduledTasks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 不实用，写的太死
 * quartz配置类
 * @author hongxf
 * @since 2017-09-12 14:49
 */
@Configuration
public class ScheduledConfig {

    /**
     * 定义jobDetail
     * @param scheduledTasks
     * @return
     */
    @Bean
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTasks scheduledTasks) {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        //设置对应的job对象
        bean.setTargetObject(scheduledTasks);
        //设置scheduledTasks对应的方法名
        bean.setTargetMethod("execute");
        return bean;
    }

    /**
     * 定义Trigger
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression("0/5 * * ? * *"); //每5秒执行一次
        return trigger;
    }


    /**
     * 定义Scheduler
     * @param cronTriggerFactoryBean
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        return schedulerFactoryBean;
    }
}

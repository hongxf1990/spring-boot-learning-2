package com.petter.task2.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;

/**
 * 这里方式比较实用，可以定义多个job
 * quartz的配置类
 * 1、SchedulerFactoryBean
 * 2、Scheduler类
 * @author hongxf
 * @since 2017-09-12 15:22
 */
@Configuration
public class QuartzConfig {

    @Resource
    private SpringBeanJobFactory springBeanJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //把job交给spring管理，这样job就能使用Spring产生的Bean了
        schedulerFactoryBean.setJobFactory(springBeanJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

}

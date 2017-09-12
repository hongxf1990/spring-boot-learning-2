package com.petter.task2.task;

import com.petter.task2.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author hongxf
 * @since 2017-09-12 15:26
 */
@Service
public class MyTask extends BaseTask {

    /**
     * 为什么这helloService是null，Spring没有正常注入
     * -------------------------------------------------
     * Quartz中的job是由Quartz框架【动态】创建的（通过配置该job的className，通过反射进行动态创建）
     * 所以在job中使用spring bean的话，是无法进行使用的（Job的类不是一个Spring Bean）
     *
     * 需要解决建立的job交给Spring进行管理
     *--------------------------------------------------
     * 使用AutowireCapableBeanFactory进行自动注入job
     */
    @Resource
    private HelloService helloService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("MyTask.execute(), date=" + new Date());
        helloService.hello();
    }

    @Override
    public String cronExpression() {
        return "0/5 * * * * ?";
    }

    @Override
    public String jobName() {
        return "job1";
    }

    @Override
    public String jobGroupName() {
        return "group1";
    }

    @Override
    public String triggerName() {
        return "trigger1";
    }

    @Override
    public String triggerGroupName() {
        return "group1";
    }
}

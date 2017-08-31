package com.petter.web;

import com.petter.task.Task1;
import com.petter.task.Task2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@RestController
public class HelloWorldController {

    @Resource
    private Task1 task1;
    @Resource
    private Task2 task2;

    //同步调用
    @RequestMapping("/task1")
    public String task1() throws Exception{
        task1.doTaskOne();
        task1.doTaskTwo();
        task1.doTaskThree();
        return "task1";
    }

    //异步调用
    //三个任务几乎同时进行
    @RequestMapping("/task2")
    public String task2() throws Exception{
        task2.doTaskOne();
        task2.doTaskTwo();
        task2.doTaskThree();
        return "task2";
    }
}

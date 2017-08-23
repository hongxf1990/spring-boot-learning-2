package com.petter.web;

import com.petter.entity.Demo;
import com.petter.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@RestController
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Resource
    private DemoService demoService;

    @RequestMapping("/hello-world")
    public String hello(){
        logger.info("test hello world");
        return "Hello world2!";
    }

    /**
     * 测试保存数据方法.
     * @return
     */
    @RequestMapping("/save-demo")
    public String save(){
        Demo d = new Demo();
        d.setName("hongxf");
        d.setTelephoneNumber("158620166122");
        demoService.saveDemo(d);
        return "保存成功";
    }

    @GetMapping("/get-demo")
    public Demo getDemoById() {
        return demoService.getById(1L);
    }
}

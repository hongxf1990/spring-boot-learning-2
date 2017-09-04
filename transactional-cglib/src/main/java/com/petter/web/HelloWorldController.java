package com.petter.web;

import com.petter.entity.Demo;
import com.petter.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //注入错误
    //@Resource
    //private TestService testService;

    @RequestMapping("/save-demo")
    public String hello(){
        Demo demo = new Demo();
        demo.setName("hongxf");
        demo.setTelephoneNumber("1232423");
        demoService.insertDemo(demo);
        return "ok";
    }

    //@RequestMapping("/hello")
    //public String hello2() {
    //    testService.sayHello();
    //    return "ok";
    //}

}

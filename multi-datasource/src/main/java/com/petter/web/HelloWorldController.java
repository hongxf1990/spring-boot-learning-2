package com.petter.web;

import com.petter.entity.primary.Demo;
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

    @Resource
    private DemoService demoService;

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello-world")
    public Demo hello(){
        logger.info("test hello world");
        return demoService.getById(1L);
    }

}

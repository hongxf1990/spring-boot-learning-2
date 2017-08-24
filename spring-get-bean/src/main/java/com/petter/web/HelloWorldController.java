package com.petter.web;

import com.petter.service.DemoService;
import com.petter.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@RestController
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello-world")
    public String hello(){
        DemoService demoService = (DemoService) SpringUtil.getBean("demoService");
        if (demoService != null) {
            logger.info("test hello world");
        }
        return "Hello world2!";
    }

}

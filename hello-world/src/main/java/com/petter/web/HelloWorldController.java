package com.petter.web;

import com.petter.bean.DemoBean;
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
        logger.info("test hello world");
        return "Hello world!";
    }

    //默认使用JackSon进行Json解析
    @RequestMapping("/demo")
    public DemoBean getDemo() {
        DemoBean demoBean = new DemoBean();
        demoBean.setId(1L);
        demoBean.setName("hongxf");
        return demoBean;
    }
}

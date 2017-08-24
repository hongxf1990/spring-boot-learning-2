package com.petter.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@Controller
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/")
    public ModelAndView hello(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "hongxf");
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/hello-freemarker")
    public ModelAndView helloHtml2() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "hongxf");
        mav.setViewName("hello");
        return mav;
    }
}

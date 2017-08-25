package com.petter.web;

import com.petter.entity.Demo;
import com.petter.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @since 2017-02-10 23:58
 */
@RequestMapping(value = "/demo")
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping("/test")
    public String test(){
        Demo loaded = demoService.findById(1L);
        System.out.println("loaded = " + loaded);
        Demo cached = demoService.findById(1L);
        System.out.println("cached = " + cached);
        loaded = demoService.findById(2L);
        System.out.println("loaded2 = " + loaded);
        return "ok";
    }


    @RequestMapping("/delete")
    public String delete(long id){
        demoService.deleteFromCache(id);
        return "ok";
    }

    //测试redis是否可用
    @RequestMapping("/test1")
    public String test1(){
        demoService.test();
        return "ok";
    }
}

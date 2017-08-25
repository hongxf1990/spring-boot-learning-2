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

        //存入两条数据.  
        Demo demo = new Demo();
        demo.setName("张三");
        demo.setPwd("123456");
        Demo demo2 = demoService.save(demo);

        //不走缓存.  
        System.out.println(demoService.findById(demo2.getId()));
        //走缓存.  
        System.out.println(demoService.findById(demo2.getId()));

        demo = new Demo();
        demo.setName("李四");
        demo.setPwd("123456");
        Demo demo3 = demoService.save(demo);

        //不走缓存.  
        System.out.println(demoService.findById(demo3.getId()));
        //走缓存.  
        System.out.println(demoService.findById(demo3.getId()));

        System.out.println("============修改数据=====================");
        //修改数据.  
        Demo updated = new Demo();
        updated.setName("李四-updated");
        updated.setPwd("123456");
        updated.setId(demo3.getId());
        demoService.update(updated);

        //走缓存.
        System.out.println(demoService.findById(updated.getId()));

        return "ok";
    }
}

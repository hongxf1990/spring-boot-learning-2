package com.petter.web;

import com.github.pagehelper.PageHelper;
import com.petter.entity.Demo;
import com.petter.entity.Demo2;
import com.petter.service.Demo2Service;
import com.petter.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@RestController
public class HelloWorldController {


    @Resource
    private DemoService demoService;
    @Resource
    private Demo2Service demo2Service;

    @RequestMapping("/hello-save")
    public Demo save(){
        Demo demo = new Demo();
        demo.setName("张三");
        demoService.save(demo);
        return demo;
    }

    @RequestMapping("/hello-save-with-xml")
    public Demo saveWithXML(){
        Demo demo = new Demo();
        demo.setName("李四");
        demoService.saveWithXML(demo);
        return demo;
    }

    @RequestMapping("/hello-world")
    public List<Demo> hello(
            String name
    ){
        PageHelper.startPage(1, 2);
        return demoService.likeName(name + "%");
    }

    @RequestMapping("/hello")
    public Demo hello2(
            Long id
    ) {
        return demoService.getById(id);
    }

    @RequestMapping("/hello3")
    public List<Demo2> hello3(
            String name,
            String email
    ) {
        Demo2 demo2 = new Demo2();
        demo2.setName(name);
        demo2.setEmail(email);
        PageHelper.startPage(1, 2);
        return demo2Service.getByDemo(demo2);
    }

    @RequestMapping("/hello4")
    public Demo2 hello4() {
        Demo2 demo2 = new Demo2();
        demo2.setName("洪学分");
        demo2Service.saveDemo(demo2);
        return demo2;
    }
}

package com.petter.web;

import com.github.pagehelper.PageHelper;
import com.petter.entity.Demo;
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

    @RequestMapping("/hello-world")
    public List<Demo> hello(
            String name
    ){
        PageHelper.startPage(1, 1);
        return demoService.likeName(name + "%");
    }

}

package com.petter.web;

import com.petter.entity.Demo;
import com.petter.repository.DemoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 两种操作方式
 * 1、继承MongoRepository，基本操作
 * 2、使用MongoTemplate，复杂操作
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@RestController
public class HelloWorldController {

    @Resource
    private DemoRepository demoRepository;
    @Resource
    private MongoTemplate mongoTemplate;

    @RequestMapping("/save")
    public String save(){
        Demo demoInfo = new Demo();
        demoInfo.setName("张三");
        demoInfo.setTelephoneNumber("1312323424");
        demoRepository.save(demoInfo);

        demoInfo = new Demo();
        demoInfo.setName("李四");
        demoInfo.setTelephoneNumber("2342323443");
        demoRepository.save(demoInfo);

        return "ok";
    }

    @RequestMapping("/find")
    public List<Demo> find(){
        return demoRepository.findAll();
    }

    @RequestMapping("/find-by-name")
    public Demo findByName(){
        return demoRepository.findByName("张三");
    }


    @RequestMapping("find2")
    public List<Demo> find2(){
        Query query = new Query();
        query.skip(5).limit(1);
        return mongoTemplate.find(query,Demo.class);
    }
}

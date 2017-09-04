package com.petter.service;

import com.petter.entity.Demo;
import com.petter.repository.DemoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 如果不使用基于接口的Service，那么直接使用事务也是可以的
 * @author hongxf
 * @since 2017-09-04 15:54
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Transactional
    public void insertDemo(Demo demo) {
        demoRepository.save(demo);
        //测试异常时候是否回滚，可以实现事务
        //int a = 10 /0;
    }
}

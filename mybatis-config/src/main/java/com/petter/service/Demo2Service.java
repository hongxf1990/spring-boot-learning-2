package com.petter.service;

import com.petter.entity.Demo2;
import com.petter.mapper.Demo2Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hongxf
 * @since 2017-09-14 11:39
 */
@Service
public class Demo2Service {

    @Resource
    private Demo2Mapper demo2Mapper;

    public List<Demo2> getByDemo(Demo2 demo) {
        return demo2Mapper.selectByDemo(demo);
    }

    @Transactional
    public void saveDemo(Demo2 demo2) {
        demo2Mapper.save(demo2);
    }
}

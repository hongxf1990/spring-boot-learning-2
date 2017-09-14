package com.petter.service;

import com.petter.entity.Demo;
import com.petter.mapper.DemoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hongxf
 * @since 2017-08-30 9:06
 */
@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> likeName(String name) {
        return demoMapper.likeName(name);
    }

    public Demo getById(long id) {
        return demoMapper.getById(id);
    }

    @Transactional
    public void save(Demo demo){
        demoMapper.save(demo);
    }

    @Transactional
    public void saveWithXML(Demo demo){
        demoMapper.saveDemo(demo);
    }
}

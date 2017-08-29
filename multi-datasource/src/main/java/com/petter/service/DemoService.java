package com.petter.service;

import com.petter.entity.primary.Demo;
import com.petter.entity.primary.DemoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-29 14:38
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    public Demo getById(Long id) {
        return demoRepository.findOne(id);
    }
}

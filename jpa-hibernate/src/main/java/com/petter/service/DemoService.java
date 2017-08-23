package com.petter.service;

import com.petter.entity.Demo;
import com.petter.repository.DemoRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-23 15:16
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void saveDemo(Demo demo) {
        demoRepository.save(demo);
    }

    public Demo getById(Long id) {
        String sql = "select * from demo where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Demo.class), id);
    }
}

package com.petter.task2.service;

import org.springframework.stereotype.Service;

/**
 * spring中的普通Service，要在job中使用
 * @author hongxf
 * @since 2017-09-12 15:51
 */
@Service
public class HelloService {

    public void hello() {
        System.out.println("hello Service!");
    }
}

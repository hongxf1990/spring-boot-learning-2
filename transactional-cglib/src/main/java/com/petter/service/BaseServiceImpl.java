package com.petter.service;

import javax.transaction.Transactional;

/**
 * @author hongxf
 * @since 2017-09-04 16:28
 */
public class BaseServiceImpl implements BaseService {

    @Transactional
    @Override
    public void sayHello() {
        System.out.println("BaseService.sayHello");
    }
}

package com.petter.repository;

import com.petter.entity.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoRepository定义了基本的CRUD操作
 * @author hongxf
 * @since 2017-09-06 15:53
 */
public interface DemoRepository extends MongoRepository<Demo, String> {

    Demo findByName(String name);

}

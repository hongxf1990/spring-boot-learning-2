package com.petter.repository;

import com.petter.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository是最底层的实现，最好使用这个，另外还有CrudRepository
 * @author Administrator
 * @since 2017-02-10 23:52
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {

}

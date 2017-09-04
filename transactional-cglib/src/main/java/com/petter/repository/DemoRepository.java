package com.petter.repository;

import com.petter.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongxf
 * @since 2017-09-04 15:53
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {
}

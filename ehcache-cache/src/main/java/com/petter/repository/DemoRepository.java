package com.petter.repository;

import com.petter.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongxf
 * @since 2017-08-24 16:49
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {
}

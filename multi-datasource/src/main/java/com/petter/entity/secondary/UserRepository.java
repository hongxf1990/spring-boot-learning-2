package com.petter.entity.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hongxf
 * @since 2017-08-29 14:22
 */
public interface UserRepository extends JpaRepository<User, Long> {
}

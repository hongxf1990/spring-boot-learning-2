package com.petter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.petter.mapper")
@SpringBootApplication
public class MybatisConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisConfigApplication.class, args);
	}
}

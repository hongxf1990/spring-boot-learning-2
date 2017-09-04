package com.petter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务支持
public class TransactionalCglibApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionalCglibApplication.class, args);
	}
}

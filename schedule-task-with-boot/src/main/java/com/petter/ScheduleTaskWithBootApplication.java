package com.petter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ScheduleTaskWithBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleTaskWithBootApplication.class, args);
	}
}

package com.petter;

import com.petter.properties.BlogProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({BlogProperties.class})
public class MultiEnvironmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiEnvironmentApplication.class, args);
	}
}

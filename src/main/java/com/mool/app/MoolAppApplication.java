package com.mool.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoolAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoolAppApplication.class, args);
	}

}

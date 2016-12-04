package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
public class DemoApplication {

	public DemoApplication() {
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

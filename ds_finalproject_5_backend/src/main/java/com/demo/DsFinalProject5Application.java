package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.demo" })
public class DsFinalProject5Application {

	public static void main(String[] args) {		
		SpringApplication.run(DsFinalProject5Application.class, args);
		
	}

}

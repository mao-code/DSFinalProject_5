package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.demo" })
public class DsFinalProject5Application {

	public static void main(String[] args) {		
		SpringApplication.run(DsFinalProject5Application.class, args);
		
	}

	@Bean
	public WebMvcConfigurer corsMappingConfigurer() {
	   return new WebMvcConfigurer() {
	       @Override
	       public void addCorsMappings(CorsRegistry registry) {
	    	   registry.addMapping("/**"); //允許跨網域請求的來源
	       }
	   };
	}
}

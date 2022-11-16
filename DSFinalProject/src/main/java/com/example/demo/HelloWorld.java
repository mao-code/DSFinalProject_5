package com.example.demo;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {


	@GetMapping("/test/{text}")
	public ResponseEntity<Object> test(@PathVariable("text") String text) {
		return ResponseEntity.ok().body(
			new Object() {
				public final int code=200; 
				public final String msg="Test: "+text;
		});
	}
	
	@RequestMapping("/")
	public String hello(){
		return "Hey, Spring Boot 的 Hello World ! ";
	}

}



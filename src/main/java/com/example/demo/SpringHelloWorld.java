package com.example.demo;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class SpringHelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@GetMapping("/test/{text}")
	public ResponseEntity<Object> test(@PathVariable("text") String text)
	{
		return ResponseEntity.ok().body(new Object() {
			public final int code = 200;
			public final boolean success = true;
			public final String message = "Test: "+text;
		});
	}

}
package com.musma.springbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbookApplication {

	public static void main(String[] args) {
		System.out.println("hello word");
		SpringApplication.run(SpringbookApplication.class, args);
	}

}

package com.example.springcache;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class SpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheApplication.class, args);
	}
}

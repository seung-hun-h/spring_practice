package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import memory.MemoryController;
import memory.MemoryFinder;

@Configuration
public class MemoryConfig {
	@Bean
	public MemoryFinder memoryFinder() {
		return new MemoryFinder();
	}

	@Bean
	public MemoryController memoryController() {
		return new MemoryController(memoryFinder());
	}
}

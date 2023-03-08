package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import memory.MemoryCondition;
import memory.MemoryController;
import memory.MemoryFinder;

@Conditional(MemoryCondition.class)
@Configuration
public class MemoryConfig {
	@Bean
	public MemoryController memoryController() {
		return new MemoryController(memoryFinder());
	}

	@Bean
	public MemoryFinder memoryFinder() {
		return new MemoryFinder();
	}
}

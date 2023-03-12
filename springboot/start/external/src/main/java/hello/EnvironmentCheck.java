package hello;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EnvironmentCheck {
	private final Environment environment;

	public EnvironmentCheck(Environment environment) {
		this.environment = environment;
	}

	@PostConstruct
	public void init() {
		String url = environment.getProperty("url");
		String password = environment.getProperty("password");
		String username = environment.getProperty("username");

		log.info("url = {}", url);
		log.info("password = {}", password);
		log.info("username = {}", username);
	}
}

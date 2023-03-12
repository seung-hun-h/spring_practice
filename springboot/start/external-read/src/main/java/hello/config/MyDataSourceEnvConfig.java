package hello.config;

import java.time.Duration;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import hello.datasource.MyDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MyDataSourceEnvConfig {
	private final Environment environment;

	public MyDataSourceEnvConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	MyDataSource myDataSource() {
		String url = environment.getProperty("my.datasource.url");
		String username = environment.getProperty("my.datasource.username");
		String password = environment.getProperty("my.datasource.password");
		Integer maxConnection = environment.getProperty("my.datasource.etc.max-connection", Integer.class);
		Duration timeout = environment.getProperty("my.datasource.etc.timeout", Duration.class);
		List<String> options = environment.getProperty("my.datasource.etc.options", List.class);

		return new MyDataSource(url, username, password, maxConnection, timeout, options);
	}
}

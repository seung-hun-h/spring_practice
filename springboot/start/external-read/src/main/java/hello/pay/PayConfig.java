package hello.pay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class PayConfig {
	@Bean
	@Profile("default")
	public LocalPayClient localPayClient() {
		return new LocalPayClient();
	}

	@Bean
	@Profile("prod")
	public ProdPayClient prodPayClient() {
		return new ProdPayClient();
	}
}

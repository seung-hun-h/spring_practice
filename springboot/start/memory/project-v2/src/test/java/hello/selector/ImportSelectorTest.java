package hello.selector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class ImportSelectorTest {
	@Test
	void staticConfig() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(StaticConfig.class);
		applicationContext.refresh();
		HelloBean bean = applicationContext.getBean(HelloBean.class);
		Assertions.assertNotNull(bean);
	}

	@Test
	void selectorConfig() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(SelectorConfig.class);
		applicationContext.refresh();
		HelloBean bean = applicationContext.getBean(HelloBean.class);
		Assertions.assertNotNull(bean);
	}


	@Import(HelloConfig.class)
	@Configuration
	public static class StaticConfig {
	}

	@Import(HelloSelector.class)
	@Configuration
	public static class SelectorConfig{}
}

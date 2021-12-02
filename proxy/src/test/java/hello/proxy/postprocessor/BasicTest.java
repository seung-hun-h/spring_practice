package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BasicTest {

  @Test
  void basicConfig() {
    ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);

    // A는 빈으로 등록
    A a = context.getBean("beanA", A.class);
    a.helloA();

    // B는 빈으로 등록되지 않는다
    Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> {
      context.getBean(B.class);
    });
  }

  @Slf4j
  static class BasicConfig {

    @Bean(name = "beanA")
    public A a() {
      return new A();
    }

  }

  @Slf4j
  static class A {
    public void helloA() {
      log.info("hello A");
    }
  }

  @Slf4j
  static class B {
    public void helloB() {
      log.info("hello B");
    }
  }
}

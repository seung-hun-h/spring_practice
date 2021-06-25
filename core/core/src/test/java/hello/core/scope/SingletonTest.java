package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        ac.close();

        assertThat(bean1).isSameAs(bean2);
    }


    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        void close() {
            System.out.println("SingletonBean.close");
        }
    }

}

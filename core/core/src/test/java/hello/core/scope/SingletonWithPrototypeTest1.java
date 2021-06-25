package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);

        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    static class ClientBean {
//        private final ObjectProvider<PrototypeBean> objectProvider;
//
//        @Autowired
//        public ClientBean(ObjectProvider<PrototypeBean> objectProvider) {
//            this.objectProvider = objectProvider;
//        }
        private final Provider<PrototypeBean> objectProvider;

        @Autowired
        public ClientBean(Provider<PrototypeBean> objectProvider) {
            this.objectProvider = objectProvider;
        }

        public int logic() {
//            PrototypeBean prototypeBean = objectProvider.getObject();
            PrototypeBean prototypeBean = objectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}

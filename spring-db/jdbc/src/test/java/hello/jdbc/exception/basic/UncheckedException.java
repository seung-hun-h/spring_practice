package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedException {

    @Test
    void callCatch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void callThrow() {
        Service service = new Service();

        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyUnCheckedException.class);
    }

    static class MyUnCheckedException extends RuntimeException {
        public MyUnCheckedException(String message) {
            super(message);
        }
    }

    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUnCheckedException e) {
                log.info("예외 발생, message = {}", e.getMessage(), e);
            }
        }

        public void callThrow() {
            repository.call();
        }
    }

    static class Repository {
        public void call() {
            throw new MyUnCheckedException("ex");
        }
    }

}


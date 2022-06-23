package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedException {

    Service service = new Service();

    @Test
    void callCatch() {
        service.callCatch();
    }

    @Test
    void callThrows() {
        assertThatThrownBy(() -> service.callThrows())
                .isInstanceOf(MyCheckedException.class);
    }

    static class MyCheckedException extends Exception{
        public MyCheckedException(String message) {
            super(message);
        }
    }

    static class Service {
        public final Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }
        }

        public void callThrows() throws MyCheckedException {
            repository.call();
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");

        }
    }
}

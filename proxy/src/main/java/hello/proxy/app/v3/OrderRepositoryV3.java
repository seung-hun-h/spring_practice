package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        if (Objects.equals(itemId, "ex")) {
            throw new IllegalStateException("예외 발생!");
        }

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

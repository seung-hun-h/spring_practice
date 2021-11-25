package hello.proxy.app.v1;

import java.util.Objects;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {
    @Override
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

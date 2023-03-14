package hello.order.v4;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Timed("my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {
	private final AtomicInteger stocks = new AtomicInteger(100);
	@Override
	public void order() {
		log.info("order");
		stocks.decrementAndGet();
		sleep(500);
	}
	@Override
	public void cancel() {
		log.info("cancel");
		stocks.incrementAndGet();
		sleep(200);
	}

	@Override
	public AtomicInteger stock() {
		return stocks;
	}
	private static void sleep(int millis) {
		try {
			Thread.sleep(millis + ThreadLocalRandom.current().nextInt(200));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

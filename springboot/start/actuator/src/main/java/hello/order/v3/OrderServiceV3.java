package hello.order.v3;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderServiceV3 implements OrderService {
	private final MeterRegistry registry;
	private final AtomicInteger stocks = new AtomicInteger(100);

	@Override
	public void order() {
		Timer timer = Timer.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "order")
			.description("order")
			.register(registry);

		timer.record(() -> {
			log.info("order");
			stocks.decrementAndGet();
			sleep(500);
		});
	}

	@Override
	public void cancel() {
		Timer timer = Timer.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "cancel")
			.description("order")
			.register(registry);
		timer.record(() -> {
			log.info("cancel");
			stocks.incrementAndGet();
			sleep(200);
		});
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

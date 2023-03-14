package hello.order.v1;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class OrderServiceV1 implements OrderService {
	private final MeterRegistry registry;
	private final AtomicInteger stocks = new AtomicInteger(100);

	@Override
	public void order() {
		log.info("order");
		stocks.decrementAndGet();

		Counter.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "order")
			.description("order")
			.register(registry)
			.increment();
	}

	@Override
	public void cancel() {
		log.info("cancel");
		stocks.incrementAndGet();

		Counter.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "cancel")
			.description("order")
			.register(registry)
			.increment();
	}

	@Override
	public AtomicInteger stock() {
		return stocks;
	}
}

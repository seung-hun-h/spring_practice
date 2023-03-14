package hello.order.v2;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceV2 implements OrderService {
	private final AtomicInteger stocks = new AtomicInteger(100);

	@Counted("my.order")
	@Override
	public void order() {
		log.info("order");
		stocks.decrementAndGet();
	}

	@Counted("my.order")
	@Override
	public void cancel() {
		log.info("cancel");
		stocks.incrementAndGet();
	}

	@Override
	public AtomicInteger stock() {
		return stocks;
	}
}

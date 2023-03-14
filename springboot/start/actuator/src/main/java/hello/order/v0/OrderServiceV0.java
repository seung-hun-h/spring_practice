package hello.order.v0;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceV0 implements OrderService {
	private final AtomicInteger stocks = new AtomicInteger(100);

	@Override
	public void order() {
		log.info("order");
		stocks.decrementAndGet();
	}

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

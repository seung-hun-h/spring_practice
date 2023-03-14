package hello.order.gauge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
public class StockConfigV1 {
	@Bean
	public MyStockGauge myStockGauge(OrderService orderService, MeterRegistry meterRegistry) {
		return new MyStockGauge(orderService, meterRegistry);
	}

	@RequiredArgsConstructor
	@Slf4j
	private static class MyStockGauge {
		final OrderService orderService;
		final MeterRegistry meterRegistry;

		@PostConstruct
		void init() {
			Gauge.builder("my.stock", orderService, service -> {
				log.info("stock gauge call");
				return service.stock().get();
			}).register(meterRegistry);
		}
	}
}

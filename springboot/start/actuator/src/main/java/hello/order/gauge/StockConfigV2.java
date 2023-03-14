package hello.order.gauge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import jakarta.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StockConfigV2 {
	@Bean
	public MeterBinder meterBinder(OrderService orderService) {
		return registry -> {
			Gauge.builder("my.stock", orderService, service -> {
				log.info("stock gauge call");
				return service.stock().get();
			});
		};
	}
}

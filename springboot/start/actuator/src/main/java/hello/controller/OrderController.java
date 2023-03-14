package hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.order.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
public class OrderController {
	private final OrderService orderService;


	@GetMapping("/order")
	public String order() {
		orderService.order();
		return "order";
	}

	@GetMapping("/cancel")
	public String cancel() {
		orderService.cancel();
		return "cancel";
	}

	@GetMapping("/stock")
	public int stock() {
		return orderService.stock().get();
	}
}

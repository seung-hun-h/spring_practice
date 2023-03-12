package hello.pay;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final PayClient payClient;

	public void order(int money) {
		payClient.pay(money);
	}
}

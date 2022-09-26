package Item19.constructor;

import java.time.Instant;

public class Sub extends Super {
	private final Instant instant;

	public Sub() {
		this.instant = Instant.now();
	}

	@Override
	public void overrideMe() {
		System.out.println("instant = " + instant);
	}

	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.overrideMe();
	}
}

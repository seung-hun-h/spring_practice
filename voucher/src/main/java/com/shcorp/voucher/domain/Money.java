package com.shcorp.voucher.domain;

public record Money(int amount) {
	public static final Money ZERO = new Money(0);
	private static final int MIN_AMOUNT = 0;
	private static final int MAX_AMOUNT = 1_000_000;
	public Money {
		if (amount < MIN_AMOUNT) {
			throw new IllegalArgumentException("amounts cannot be less than " + MIN_AMOUNT + "current amount: " + amount);
		}
		if (amount > MAX_AMOUNT) {
			throw new IllegalArgumentException("amounts cannot be greater than " + MAX_AMOUNT + "current amount: " + amount);
		}
	}

	Money plus(Money other) {
		return new Money(amount + other.amount);
	}

	Money minus(Money other) {
		return new Money(amount - other.amount);
	}

	Money multiply(Money other) {
		return new Money(amount * other.amount);
	}
}

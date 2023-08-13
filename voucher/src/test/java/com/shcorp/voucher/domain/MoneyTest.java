package com.shcorp.voucher.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shcorp.voucher.voucher.domain.Money;

class MoneyTest {

	@Test
	void testCreateMoney() {
		Money money = new Money(100);
		assertEquals(100, money.amount());
	}

	@Test
	void testCreateMoneyWithNegativeAmount() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Money(-100);
		});
	}

	@Test
	void testCreateMoneyWithOverMaxAmount() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Money(1000001);
		});
	}

	@Test
	void testPlus() {
		Money money = new Money(100);
		Money money2 = new Money(200);
		Money result = money.plus(money2);
		assertEquals(300, result.amount());
	}

	@Test
	void testMinus() {
		Money money = new Money(100);
		Money money2 = new Money(200);
		Money result = money2.minus(money);
		assertEquals(100, result.amount());
	}

	@Test
	void testMultiply() {
		Money money = new Money(100);
		Money money2 = new Money(200);
		Money result = money.multiply(money2);
		assertEquals(20000, result.amount());
	}

	@Test
	void testMultiplyOverMaxAmount() {
		Money money = new Money(100);
		Money money2 = new Money(10000);
		assertThrows(IllegalArgumentException.class, () -> {
			money.multiply(money2);
		});
	}
}
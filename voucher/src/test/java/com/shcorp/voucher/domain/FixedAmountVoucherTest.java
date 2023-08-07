package com.shcorp.voucher.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FixedAmountVoucherTest {
	@Test
	void testApplyDiscount() {
		// given
		FixedAmountVoucher fixedAmountVoucher = new FixedAmountVoucher("code", new Money(100));
		Money money = new Money(1000);

		// when
		Money result = fixedAmountVoucher.applyDiscount(money);

		// then
		assertEquals("code", fixedAmountVoucher.getCode());
		assertEquals(900, result.amount());
	}

	@Test
	void testApplyDiscountWhenDiscountAmountIsGreaterThanMoneyAmount() {
		// given
		FixedAmountVoucher fixedAmountVoucher = new FixedAmountVoucher("code", new Money(1000));
		Money money = new Money(100);

		// when & then
		assertThrows(IllegalArgumentException.class, () -> {
			fixedAmountVoucher.applyDiscount(money);
		});
	}
}
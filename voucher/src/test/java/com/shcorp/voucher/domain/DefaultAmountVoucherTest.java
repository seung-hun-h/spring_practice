package com.shcorp.voucher.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DefaultAmountVoucherTest {
	@Test
	void testApplyDiscount() {
		// given
		DefaultAmountVoucher defaultAmountVoucher = new DefaultAmountVoucher();
		Money money = new Money(100);

		// when
		Money result = defaultAmountVoucher.applyDiscount(money);

		// then
		assertEquals(money, result);
	}
}
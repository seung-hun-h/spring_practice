package com.shcorp.voucher.domain;

import java.util.Objects;

public interface Voucher {

	static Voucher from(String code, VoucherType voucherType, int amount) {
		Objects.requireNonNull(code, "code cannot be null");
		Objects.requireNonNull(voucherType, "voucherType cannot be null");

		return switch (voucherType) {
			case PERCENT -> new PercentAmountVoucher(code, amount);
			case FIXED -> new FixedAmountVoucher(code, new Money(amount));
			default -> new DefaultAmountVoucher(code);
		};
	}

	Money applyDiscount(Money money);

	VoucherType getType();

	int getAmount();

	String getCode();
}

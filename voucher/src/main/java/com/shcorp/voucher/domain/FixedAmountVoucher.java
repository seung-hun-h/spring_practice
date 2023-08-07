package com.shcorp.voucher.domain;

import java.util.Objects;

class FixedAmountVoucher implements Voucher {
	private final Money amount;

	FixedAmountVoucher(Money amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		this.amount = amount;
	}

	@Override
	public Money applyDiscount(Money money) {
		return money.minus(amount);
	}

	@Override
	public VoucherType getType() {
		return VoucherType.FIXED;
	}

	@Override
	public int getAmount() {
		return amount.amount();
	}
}

package com.shcorp.voucher.domain;

import java.util.Objects;

class FixedAmountVoucher implements Voucher {
	private final String code;
	private final Money amount;

	FixedAmountVoucher(String code, Money amount) {
		Objects.requireNonNull(amount, "amount cannot be null");
		this.code = code;
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

	@Override
	public String getCode() {
		return code;
	}
}

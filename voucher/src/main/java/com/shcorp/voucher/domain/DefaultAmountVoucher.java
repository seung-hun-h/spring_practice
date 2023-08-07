package com.shcorp.voucher.domain;

import java.util.Objects;

class DefaultAmountVoucher implements Voucher {
	private final String code;

	DefaultAmountVoucher(String code) {
		this.code = code;
	}

	@Override
	public Money applyDiscount(Money money) {
		return money.minus(Money.ZERO);
	}

	@Override
	public VoucherType getType() {
		return VoucherType.DEFAULT;
	}

	@Override
	public int getAmount() {
		return Money.ZERO.amount();
	}

	@Override
	public String getCode() {
		return null;
	}
}

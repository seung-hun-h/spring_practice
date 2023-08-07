package com.shcorp.voucher.domain;

class PercentAmountVoucher implements Voucher {
	private final int percent;

	PercentAmountVoucher(int percent) {
		//validate percent
		if (percent < 0) {
			throw new IllegalArgumentException("percent cannot be less than 0");
		}
		if (percent > 100) {
			throw new IllegalArgumentException("percent cannot be greater than 100");
		}
		this.percent = percent;
	}

	@Override
	public Money applyDiscount(Money money) {
		return money.minus(new Money(money.amount() * (percent / 100)));
	}

	@Override
	public VoucherType getType() {
		return VoucherType.PERCENT;
	}

	@Override
	public int getAmount() {
		return percent;
	}
}

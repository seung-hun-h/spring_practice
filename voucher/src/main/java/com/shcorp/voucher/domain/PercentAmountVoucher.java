package com.shcorp.voucher.domain;

class PercentAmountVoucher implements Voucher {
	private final String code;
	private final int percent;

	PercentAmountVoucher(String code, int percent) {
		//validate percent
		if (percent < 0) {
			throw new IllegalArgumentException("percent cannot be less than 0");
		}
		if (percent > 100) {
			throw new IllegalArgumentException("percent cannot be greater than 100");
		}
		this.code = code;
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

	@Override
	public String getCode() {
		return code;
	}
}

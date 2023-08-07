package com.shcorp.voucher.domain;

class DefaultAmountVoucher implements Voucher {
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
}

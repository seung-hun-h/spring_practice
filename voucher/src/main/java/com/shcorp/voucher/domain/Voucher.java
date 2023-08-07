package com.shcorp.voucher.domain;

public interface Voucher {
	Money applyDiscount(Money money);
	VoucherType getType();
	int getAmount();
}

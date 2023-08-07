package com.shcorp.voucher.domain;

interface Voucher {
	Money applyDiscount(Money money);
	VoucherType getType();
	int getAmount();
}

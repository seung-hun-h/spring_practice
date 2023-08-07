package com.shcorp.voucher.adapter.out.persistence.memory;

import java.util.UUID;

import com.shcorp.voucher.domain.VoucherType;

class InMemoryVoucherEntity {
	private final UUID id;
	private final VoucherType voucherType;
	private final int amount;

	public InMemoryVoucherEntity(UUID uuid, VoucherType voucherType, int amount) {
		this.id = uuid;
		this.voucherType = voucherType;
		this.amount = amount;
	}

	public UUID getId() {
		return id;
	}

	public VoucherType getVoucherType() {
		return voucherType;
	}

	public int getAmount() {
		return amount;
	}
}

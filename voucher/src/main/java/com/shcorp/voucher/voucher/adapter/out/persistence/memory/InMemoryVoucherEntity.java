package com.shcorp.voucher.voucher.adapter.out.persistence.memory;

import java.util.UUID;

import com.shcorp.voucher.voucher.domain.VoucherType;

class InMemoryVoucherEntity {
	private final UUID id;
	private final String voucherCode;
	private final VoucherType voucherType;
	private final int amount;

	public InMemoryVoucherEntity(UUID uuid, String voucherCode, VoucherType voucherType, int amount) {
		this.id = uuid;
		this.voucherCode = voucherCode;
		this.voucherType = voucherType;
		this.amount = amount;
	}

	public UUID getId() {
		return id;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public VoucherType getVoucherType() {
		return voucherType;
	}

	public int getAmount() {
		return amount;
	}
}

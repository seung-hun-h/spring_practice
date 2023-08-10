package com.shcorp.voucher.adapter.in.web;

import com.shcorp.voucher.domain.Voucher;

public class CreateVoucherResponse{
	private final VoucherResponse voucher;

	public CreateVoucherResponse(Voucher voucher) {
		this.voucher = new VoucherResponse(voucher.getCode(), voucher.getType().toString().toLowerCase(), voucher.getAmount());
	}

	public record VoucherResponse(
		String code,
		String type,
		int amount
	) {
	}

	public VoucherResponse getVoucher() {
		return voucher;
	}
}

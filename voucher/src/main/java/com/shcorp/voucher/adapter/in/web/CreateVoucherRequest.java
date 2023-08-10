package com.shcorp.voucher.adapter.in.web;

import com.shcorp.voucher.application.port.in.CreatVoucherCommand;
import com.shcorp.voucher.domain.VoucherType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateVoucherRequest(
	@NotNull
	VoucherType voucherType,
	@Min(0) @Max(1_000_000)
	int amount
) {

	CreatVoucherCommand toCreateVoucherCommand() {
		return new CreatVoucherCommand(
			this.voucherType,
			this.amount
		);
	}
}

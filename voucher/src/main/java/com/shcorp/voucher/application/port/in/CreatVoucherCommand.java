package com.shcorp.voucher.application.port.in;

import com.shcorp.voucher.domain.VoucherType;

public record CreatVoucherCommand(
	VoucherType voucherType,
	int amount
) {
}

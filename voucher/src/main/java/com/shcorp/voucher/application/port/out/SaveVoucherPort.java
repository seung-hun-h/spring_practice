package com.shcorp.voucher.application.port.out;

import com.shcorp.voucher.domain.VoucherType;

public interface SaveVoucherPort {
	void saveVoucher(VoucherType voucherType, int amount);
}

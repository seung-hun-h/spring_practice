package com.shcorp.voucher.voucher.application.port.out;

import com.shcorp.voucher.voucher.domain.Voucher;

public interface SaveVoucherPort {
	void saveVoucher(Voucher voucher);
}

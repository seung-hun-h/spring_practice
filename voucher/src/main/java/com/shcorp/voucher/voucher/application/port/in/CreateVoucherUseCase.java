package com.shcorp.voucher.voucher.application.port.in;

import com.shcorp.voucher.voucher.domain.Voucher;

public interface CreateVoucherUseCase {
	Voucher createVoucher(CreatVoucherCommand creatVoucherCommand);
}

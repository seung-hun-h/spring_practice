package com.shcorp.voucher.application.port.in;

import com.shcorp.voucher.domain.Voucher;

public interface CreateVoucherUseCase {
	Voucher createVoucher(CreatVoucherCommand creatVoucherCommand);
}

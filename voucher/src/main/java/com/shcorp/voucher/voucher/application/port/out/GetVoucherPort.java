package com.shcorp.voucher.voucher.application.port.out;

import java.util.List;

import com.shcorp.voucher.voucher.domain.Voucher;

public interface GetVoucherPort {
	List<Voucher> getVouchers();
}

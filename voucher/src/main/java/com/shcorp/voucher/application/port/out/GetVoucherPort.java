package com.shcorp.voucher.application.port.out;

import java.util.List;

import com.shcorp.voucher.domain.Voucher;

public interface GetVoucherPort {
	List<Voucher> getVouchers();
}

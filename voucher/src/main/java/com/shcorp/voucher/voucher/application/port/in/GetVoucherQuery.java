package com.shcorp.voucher.voucher.application.port.in;

import java.util.List;

import com.shcorp.voucher.voucher.domain.Voucher;

public interface GetVoucherQuery {
	List<Voucher> getVouchers();
}

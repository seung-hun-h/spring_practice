package com.shcorp.voucher.application.port.in;

import java.util.List;

import com.shcorp.voucher.domain.Voucher;

public interface GetVoucherQuery {
	List<Voucher> getVouchers();
}

package com.shcorp.voucher.application.port.out;

import java.util.List;

import com.shcorp.voucher.application.port.out.model.GetVoucherResponse;

public interface GetVoucherService {
	List<GetVoucherResponse> getVouchers();
}

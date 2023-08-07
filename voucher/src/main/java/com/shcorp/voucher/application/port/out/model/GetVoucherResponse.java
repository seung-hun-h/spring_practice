package com.shcorp.voucher.application.port.out.model;

import com.shcorp.voucher.domain.VoucherType;

public record GetVoucherResponse(String voucherCode, VoucherType voucherType, int amount) {
}

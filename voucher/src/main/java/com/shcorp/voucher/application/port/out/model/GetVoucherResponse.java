package com.shcorp.voucher.application.port.out.model;

import com.shcorp.voucher.domain.VoucherType;

public record GetVoucherResponse(String id, VoucherType voucherType, int amount) {
}

package com.shcorp.voucher.adapter.out.persistence.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.shcorp.voucher.application.port.out.model.GetVoucherResponse;
import com.shcorp.voucher.application.port.out.GetVoucherUseCase;
import com.shcorp.voucher.application.port.out.SaveVoucherPort;
import com.shcorp.voucher.domain.VoucherType;

@Repository
public class InMemoryVoucherRepository implements GetVoucherUseCase, SaveVoucherPort {
	private static final List<InMemoryVoucherEntity> vouchers = new ArrayList<>();

	@Override
	public List<GetVoucherResponse> getVouchers() {
		return vouchers.stream()
			.map(inMemoryVoucherEntity -> new GetVoucherResponse(inMemoryVoucherEntity.getId().toString(), inMemoryVoucherEntity.getVoucherType(), inMemoryVoucherEntity.getAmount()))
			.toList();
	}

	@Override
	public void saveVoucher(VoucherType voucherType, int amount) {
		InMemoryVoucherEntity voucherEntity = new InMemoryVoucherEntity(UUID.randomUUID(), voucherType, amount);
		vouchers.add(voucherEntity);
	}
}

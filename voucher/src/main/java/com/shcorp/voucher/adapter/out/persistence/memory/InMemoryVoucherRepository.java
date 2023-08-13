package com.shcorp.voucher.adapter.out.persistence.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.shcorp.voucher.application.port.out.GetVoucherPort;
import com.shcorp.voucher.application.port.out.SaveVoucherPort;
import com.shcorp.voucher.domain.Voucher;

@Repository
public class InMemoryVoucherRepository implements GetVoucherPort, SaveVoucherPort {
	private static final List<InMemoryVoucherEntity> vouchers = new ArrayList<>();

	@Override
	public List<Voucher> getVouchers() {
		return vouchers.stream()
			.map(inMemoryVoucherEntity -> Voucher.from(inMemoryVoucherEntity.getVoucherCode(), inMemoryVoucherEntity.getVoucherType(), inMemoryVoucherEntity.getAmount()))
			.toList();
	}

	@Override
	public void saveVoucher(Voucher voucher) {
		InMemoryVoucherEntity voucherEntity = new InMemoryVoucherEntity(UUID.randomUUID(), voucher.getCode(), voucher.getType(), voucher.getAmount());
		vouchers.add(voucherEntity);
	}
}

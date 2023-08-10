package com.shcorp.voucher.application.service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcorp.voucher.application.port.in.CreatVoucherCommand;
import com.shcorp.voucher.application.port.in.CreateVoucherUseCase;
import com.shcorp.voucher.application.port.in.GetVoucherQuery;
import com.shcorp.voucher.application.port.out.GetVoucherPort;
import com.shcorp.voucher.application.port.out.SaveVoucherPort;
import com.shcorp.voucher.domain.Voucher;
import com.shcorp.voucher.domain.VoucherType;

@Service
public class VoucherService implements CreateVoucherUseCase, GetVoucherQuery {
	private final SaveVoucherPort saveVoucherPort;
	private final GetVoucherPort getVoucherPort;

	public VoucherService(SaveVoucherPort saveVoucherPort, GetVoucherPort getVoucherPort) {
		this.saveVoucherPort = saveVoucherPort;
		this.getVoucherPort = getVoucherPort;
	}

	@Transactional
	@Override
	public Voucher createVoucher(CreatVoucherCommand creatVoucherCommand) {
		Voucher voucher = Voucher.from(generateCode(creatVoucherCommand.getVoucherType()), creatVoucherCommand.getVoucherType(), creatVoucherCommand.getAmount());
		saveVoucherPort.saveVoucher(voucher);
		return voucher;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Voucher> getVouchers() {
		return getVoucherPort.getVouchers();
	}

	private String generateCode(VoucherType voucherType) {
		return voucherType.toString().toLowerCase(Locale.ROOT)+ UUID.randomUUID().toString().replace("-", "");
	}
}

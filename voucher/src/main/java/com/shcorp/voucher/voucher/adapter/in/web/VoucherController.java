package com.shcorp.voucher.voucher.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shcorp.voucher.voucher.application.port.in.CreateVoucherUseCase;
import com.shcorp.voucher.voucher.application.port.in.GetVoucherQuery;
import com.shcorp.voucher.voucher.domain.Voucher;
import jakarta.validation.Valid;

@RequestMapping("/api/v1/vouchers")
@RestController
public class VoucherController {
	private final CreateVoucherUseCase createVoucherUseCase;
	private final GetVoucherQuery getVoucherQuery;

	public VoucherController(CreateVoucherUseCase createVoucherUseCase, GetVoucherQuery getVoucherQuery) {
		this.createVoucherUseCase = createVoucherUseCase;
		this.getVoucherQuery = getVoucherQuery;
	}

	@PostMapping
	public ResponseEntity<Voucher> createVoucher(@Valid @RequestBody CreateVoucherRequest createVoucherRequest) {
		Voucher voucher = createVoucherUseCase.createVoucher(createVoucherRequest.toCreateVoucherCommand());
		return ResponseEntity.ok()
			.body(voucher);
	}
	
	@GetMapping
	public ResponseEntity<List<Voucher>> getVouchers() {
		return ResponseEntity.ok().body(getVoucherQuery.getVouchers());
	}
}

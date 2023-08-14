package com.shcorp.voucher.user.adapter.in.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
	@Email
	@NotBlank
	String email,
	@NotBlank
	String password,
	@NotBlank
	String nickname
) {
}

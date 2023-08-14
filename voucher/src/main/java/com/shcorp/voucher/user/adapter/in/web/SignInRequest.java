package com.shcorp.voucher.user.adapter.in.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
	@NotBlank
	@Email
	String email,
	@NotBlank
	String password
) {
}

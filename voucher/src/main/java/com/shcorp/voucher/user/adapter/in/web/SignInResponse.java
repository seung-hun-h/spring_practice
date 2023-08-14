package com.shcorp.voucher.user.adapter.in.web;

import java.time.LocalDateTime;

import com.shcorp.voucher.user.domain.AuthenticationStatus;

record SignInResponse(
	String email,
	String nickname,
	LocalDateTime updatedAt,
	LocalDateTime signedInAt,
	AuthenticationStatus authenticationStatus
) {
}

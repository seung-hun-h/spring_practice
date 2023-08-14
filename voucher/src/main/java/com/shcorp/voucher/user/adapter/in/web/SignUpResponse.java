package com.shcorp.voucher.user.adapter.in.web;

import java.time.LocalDateTime;

public record SignUpResponse(
	String email,
	String nickname,
	LocalDateTime createdAt
) {
}

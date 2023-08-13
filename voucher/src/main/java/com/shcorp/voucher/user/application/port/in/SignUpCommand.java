package com.shcorp.voucher.user.application.port.in;

import java.time.LocalDateTime;

public record SignUpCommand(
	String email,
	String nickname,
	String password,
	LocalDateTime requestAt
) {
}

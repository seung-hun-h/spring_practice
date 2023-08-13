package com.shcorp.voucher.user.application.port.in;

import java.time.LocalDateTime;

import com.shcorp.voucher.user.domain.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpCommand extends SelfValidating<SignUpCommand> {
	@Email
	@NotBlank
	private final String email;
	@NotBlank
	private final String nickname;
	@NotBlank
	private final String password;
	@NotNull
	private final LocalDateTime requestAt;

	public SignUpCommand(String email, String nickname, String password, LocalDateTime requestAt) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.requestAt = requestAt;
		this.validateSelf();
	}
}

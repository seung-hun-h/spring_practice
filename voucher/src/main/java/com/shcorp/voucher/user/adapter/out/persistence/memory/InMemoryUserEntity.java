package com.shcorp.voucher.user.adapter.out.persistence.memory;

import java.time.LocalDateTime;
import java.util.UUID;

import com.shcorp.voucher.user.domain.AuthenticationStatus;

public class InMemoryUserEntity {
	private UUID id;
	private String email;
	private String password;
	private String nickname;
	private LocalDateTime updatedAt;
	private LocalDateTime signedInAt;
	private AuthenticationStatus authenticationStatus;

	public InMemoryUserEntity(
		UUID id,
		String email,
		String password,
		String nickname,
		LocalDateTime updatedAt,
		LocalDateTime signedInAt,
		AuthenticationStatus authenticationStatus
	) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.updatedAt = updatedAt;
		this.signedInAt = signedInAt;
		this.authenticationStatus = authenticationStatus;
	}

	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public LocalDateTime getSignedInAt() {
		return signedInAt;
	}

	public AuthenticationStatus getAuthenticationStatus() {
		return authenticationStatus;
	}
}

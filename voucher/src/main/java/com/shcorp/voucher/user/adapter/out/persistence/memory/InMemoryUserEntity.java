package com.shcorp.voucher.user.adapter.out.persistence.memory;

import java.time.LocalDateTime;
import java.util.UUID;

import com.shcorp.voucher.user.domain.AuthenticationStatus;

class InMemoryUserEntity {
	private final UUID id;
	private final String email;
	private final String password;
	private final String nickname;
	private final LocalDateTime updatedAt;
	private final LocalDateTime signedInAt;
	private final AuthenticationStatus authenticationStatus;

	InMemoryUserEntity(
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

	UUID getId() {
		return id;
	}

	String getEmail() {
		return email;
	}

	String getNickname() {
		return nickname;
	}

	LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	AuthenticationStatus getAuthenticationStatus() {
		return authenticationStatus;
	}

	public String getPassword() {
		return password;
	}

	public LocalDateTime getSignedInAt() {
		return signedInAt;
	}
}

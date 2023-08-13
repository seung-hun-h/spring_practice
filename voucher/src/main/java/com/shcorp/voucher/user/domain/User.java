package com.shcorp.voucher.user.domain;

import java.time.LocalDateTime;

public class User {
	private final String email;
	private final String password;
	private String nickname;
	private LocalDateTime updatedAt;
	private LocalDateTime signedInAt;
	private AuthenticationStatus authenticationStatus;

	public User(String email, String password, String nickname, LocalDateTime updatedAt, AuthenticationStatus authenticationStatus) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.updatedAt = updatedAt;
		this.authenticationStatus = authenticationStatus;
	}

	public void changeNickname(String nickname) {
		this.nickname = nickname;
	}

	public void signIn(String password, LocalDateTime signedInAt) {
		if (authenticationStatus == AuthenticationStatus.SIGNED_IN) {
			throw new IllegalStateException("already signed in");
		}

		if (!this.password.equals(password)) {
			throw new IllegalStateException("Bad Credential!");
		}

		this.authenticationStatus = AuthenticationStatus.SIGNED_IN;
		this.signedInAt = signedInAt;
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

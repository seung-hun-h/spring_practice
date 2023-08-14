package com.shcorp.voucher.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcorp.voucher.user.application.port.in.SignInCommand;
import com.shcorp.voucher.user.application.port.in.SignInUseCase;
import com.shcorp.voucher.user.application.port.in.SignUpCommand;
import com.shcorp.voucher.user.application.port.in.SignUpUseCase;
import com.shcorp.voucher.user.application.port.out.GetUserPort;
import com.shcorp.voucher.user.application.port.out.SaveUserPort;
import com.shcorp.voucher.user.domain.AuthenticationStatus;
import com.shcorp.voucher.user.domain.User;

@Service
class AuthenticationService implements SignUpUseCase, SignInUseCase {
	private final SaveUserPort saveUserPort;
	private final GetUserPort getUserPort;
	private final PasswordEncoder passwordEncoder;

	AuthenticationService(
		SaveUserPort saveUserPort,
		GetUserPort getUserPort,
		PasswordEncoder passwordEncoder
	) {
		this.saveUserPort = saveUserPort;
		this.getUserPort = getUserPort;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	@Override
	public void signUp(SignUpCommand signUpCommand) {
		User user = new User(
			signUpCommand.getEmail(),
			passwordEncoder.encode(signUpCommand.getPassword()),
			signUpCommand.getNickname(),
			signUpCommand.getRequestAt(),
			AuthenticationStatus.SIGNED_OUT
		);

		saveUserPort.saveUser(user);
	}

	@Transactional
	@Override
	public User signIn(SignInCommand signInCommand) {
		User credential = getUserPort.getUser(signInCommand.getEmail())
			.filter(user -> passwordEncoder.matches(signInCommand.getPassword(), user.getPassword()))
			.orElseThrow(() -> new IllegalArgumentException("Bad Credential"));

		credential.signIn(signInCommand.getRequestAt());

		saveUserPort.saveUser(credential);

		return credential;
	}
}

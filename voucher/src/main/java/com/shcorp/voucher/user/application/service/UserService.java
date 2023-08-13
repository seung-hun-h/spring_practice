package com.shcorp.voucher.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shcorp.voucher.user.application.port.in.SignUpCommand;
import com.shcorp.voucher.user.application.port.in.SignUpUseCase;
import com.shcorp.voucher.user.application.port.out.SaveUserPort;
import com.shcorp.voucher.user.domain.AuthenticationStatus;
import com.shcorp.voucher.user.domain.User;

@Service
public class UserService implements SignUpUseCase {
	private final SaveUserPort saveUserPort;
	private final PasswordEncoder passwordEncoder;

	public UserService(SaveUserPort saveUserPort, PasswordEncoder passwordEncoder) {
		this.saveUserPort = saveUserPort;
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
}

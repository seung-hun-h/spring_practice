package com.shcorp.voucher.user.adapter.in.web;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shcorp.voucher.user.application.port.in.SignInCommand;
import com.shcorp.voucher.user.application.port.in.SignInUseCase;
import com.shcorp.voucher.user.application.port.in.SignUpCommand;
import com.shcorp.voucher.user.application.port.in.SignUpUseCase;
import com.shcorp.voucher.user.domain.User;
import jakarta.validation.Valid;

@RequestMapping("/api/v1/users")
@RestController
class AuthenticationController {
	private final SignInUseCase signInUseCase;
	private final SignUpUseCase signUpUseCase;

	AuthenticationController(SignInUseCase signInUseCase, SignUpUseCase signUpUseCase) {
		this.signInUseCase = signInUseCase;
		this.signUpUseCase = signUpUseCase;
	}

	@PostMapping
	ResponseEntity<SignUpResponse> signUp(@Valid SignUpRequest signUpRequest) {
		SignUpCommand signUpCommand = new SignUpCommand(
			signUpRequest.email(),
			signUpRequest.nickname(),
			signUpRequest.password(),
			LocalDateTime.now()
		);

		signUpUseCase.signUp(signUpCommand);

		return ResponseEntity.ok()
			.body(new SignUpResponse(
			signUpCommand.getEmail(),
			signUpCommand.getNickname(),
			signUpCommand.getRequestAt()
		));
	}

	@PostMapping("/sign-in")
	ResponseEntity<SignInResponse> signIn(@Valid SignInRequest signInRequest) {
		SignInCommand signInCommand = new SignInCommand(
			signInRequest.email(),
			signInRequest.password(),
			LocalDateTime.now()
		);

		User user = signInUseCase.signIn(signInCommand);

		return ResponseEntity.ok()
			.body(new SignInResponse(
			user.getEmail(),
			user.getNickname(),
			user.getUpdatedAt(),
			user.getSignedInAt(),
			user.getAuthenticationStatus()
		));
	}

}

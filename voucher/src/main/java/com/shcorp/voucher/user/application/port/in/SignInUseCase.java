package com.shcorp.voucher.user.application.port.in;

import com.shcorp.voucher.user.domain.User;

public interface SignInUseCase {
	User signIn(SignInCommand signInCommand);
}

package com.shcorp.voucher.user.application.port.out;

import java.util.Optional;

import com.shcorp.voucher.user.domain.User;

public interface GetUserPort {
	Optional<User> getUser(String email);
}

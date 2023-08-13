package com.shcorp.voucher.user.application.port.out;

import com.shcorp.voucher.user.domain.User;

public interface SaveUserPort {
	void saveUser(User user);
}

package com.shcorp.voucher.user.application.service;

interface PasswordEncoder {
	String encode(CharSequence password);

	boolean matches(CharSequence password, CharSequence encryptedPassword);
}

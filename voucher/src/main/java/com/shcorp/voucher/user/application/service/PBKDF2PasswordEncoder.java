package com.shcorp.voucher.user.application.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class PBKDF2PasswordEncoder implements PasswordEncoder {
	public static final String ID = "$31$";
	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int SIZE = 128;
	private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");

	private final SecureRandom random;

	private final int cost;

	public PBKDF2PasswordEncoder(@Value("${password-encoder.pbkdf2.cost}") int cost) {
		validateCost(cost);
		this.cost = cost;
		this.random = new SecureRandom();
	}

	private static int iterations(int cost)
	{
		validateCost(cost);
		return 1 << cost;
	}

	private static void validateCost(int cost) {
		if ((cost < 0) || (cost > 30))
			throw new IllegalArgumentException("cost: " + cost);
	}

	@Override
	public String encode(CharSequence password) {
		Objects.requireNonNull(password, "password");

		byte[] salt = new byte[SIZE / 8];
		random.nextBytes(salt);
		byte[] dk = pbkdf2(password.toString().toCharArray(), salt, 1 << cost);
		byte[] hash = new byte[salt.length + dk.length];
		System.arraycopy(salt, 0, hash, 0, salt.length);
		System.arraycopy(dk, 0, hash, salt.length, dk.length);
		Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
		return ID + cost + '$' + enc.encodeToString(hash);
	}

	@Override
	public boolean matches(CharSequence password, CharSequence encryptedPassword) {
		Objects.requireNonNull(password, "password");
		Objects.requireNonNull(password, "encryptedPassword");

		Matcher m = layout.matcher(encryptedPassword);
		if (!m.matches())
			throw new IllegalArgumentException("Invalid token format");
		int iterations = iterations(Integer.parseInt(m.group(1)));
		byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
		byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
		byte[] check = pbkdf2(password.toString().toCharArray(), salt, iterations);
		int zero = 0;
		for (int idx = 0; idx < check.length; ++idx)
			zero |= hash[salt.length + idx] ^ check[idx];
		return zero == 0;
	}

	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
		KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			return f.generateSecret(spec).getEncoded();
		}
		catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
		}
		catch (InvalidKeySpecException ex) {
			throw new IllegalStateException("Invalid SecretKeyFactory", ex);
		}
	}
}

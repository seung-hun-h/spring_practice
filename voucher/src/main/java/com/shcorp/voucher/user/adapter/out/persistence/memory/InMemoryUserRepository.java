package com.shcorp.voucher.user.adapter.out.persistence.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.shcorp.voucher.user.application.port.out.SaveUserPort;
import com.shcorp.voucher.user.domain.User;

@Repository
public class InMemoryUserRepository implements SaveUserPort {
	private static final Map<UUID, InMemoryUserEntity> users = new ConcurrentHashMap<>();

	@Override
	public void saveUser(User user) {
		for (Map.Entry<UUID, InMemoryUserEntity> entityEntry : users.entrySet()) {
			if (entityEntry.getValue().getEmail().equals(user.getEmail())) {
				users.put(entityEntry.getKey(), new InMemoryUserEntity(
					entityEntry.getKey(),
					user.getEmail(),
					user.getPassword(),
					user.getNickname(),
					user.getUpdatedAt(),
					user.getSignedInAt(),
					user.getAuthenticationStatus()
				));
				return;
			}
		}

		InMemoryUserEntity inMemoryUserEntity = new InMemoryUserEntity(
			UUID.randomUUID(),
			user.getEmail(),
			user.getPassword(),
			user.getNickname(),
			user.getUpdatedAt(),
			user.getSignedInAt(),
			user.getAuthenticationStatus()
		);

		users.put(inMemoryUserEntity.getId(), inMemoryUserEntity);
	}
}

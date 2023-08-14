package com.shcorp.voucher.user.adapter.out.persistence.memory;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.shcorp.voucher.user.application.port.out.GetUserPort;
import com.shcorp.voucher.user.application.port.out.SaveUserPort;
import com.shcorp.voucher.user.domain.User;

@Repository
class InMemoryUserRepository implements SaveUserPort, GetUserPort {
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

	@Override
	public Optional<User> getUser(String email) {
		for (Map.Entry<UUID, InMemoryUserEntity> entityEntry : users.entrySet()) {
			if (entityEntry.getValue().getEmail().equals(email)) {
				InMemoryUserEntity inMemoryUserEntity = entityEntry.getValue();
				return Optional.of(new User(
					inMemoryUserEntity.getEmail(),
					inMemoryUserEntity.getPassword(),
					inMemoryUserEntity.getNickname(),
					inMemoryUserEntity.getUpdatedAt(),
					inMemoryUserEntity.getSignedInAt(),
					inMemoryUserEntity.getAuthenticationStatus()
				));
			}
		}

		return Optional.empty();
	}
}

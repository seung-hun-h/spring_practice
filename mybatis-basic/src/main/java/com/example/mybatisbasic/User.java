package com.example.mybatisbasic;

import org.apache.ibatis.type.Alias;

@Alias("users")
public class User {
	private Long id;
	private String name;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

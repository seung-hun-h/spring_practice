package com.example.mybatisbasic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

	private final UserMapper userMapper;
	private final ApplicationContext applicationContext;

	User findById(Long id) {
		User user = userMapper.getUser(id);
		log.info(String.format("user = %s", user));
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			log.info(String.format("beanName = %s", beanName));
		}
		return user;
	}
}

package com.kh.spring.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.spring.user.controller.UserController;
import com.kh.spring.user.model.service.UserService;
import com.kh.spring.user.model.service.UserServiceImpl;
/*
 * 빈 등록 설정 클래스
 * 자식 @Bean 어노테이션 처리 가능 
 */
@Configuration
public class UserConfig {
	/*
	 * @Bean : 리턴 객체를 빈으로 등록 
	 * - id : 메소드 명 
	 * - class : 리턴 타입
	 */
	@Bean 
	public UserController userController() {
		return new UserController(userService());
	}
	@Bean 
	public UserService userService() {
		return new UserServiceImpl();
	}
}

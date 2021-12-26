package com.kh.spring.tv.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kh.spring.tv.user.controller.UserController;
import com.kh.spring.tv.user.model.service.UserService;
import com.kh.spring.tv.user.model.service.UserServiceImpl;

/*
 * 빈 등록 설정 클래스 
 * 자식 @Bean 어노테이션 처리가 가능 
 */
@Configuration 
public class UserConfig {

	@Bean
	// class : UserController, id : userController 
	public UserController userController() {
		return new UserController(userService()); // =ref 
	}
	/*
	 * @Bean 리턴 객체를 빈으로 등록 
	 * - id : 메소드명 (userService)
	 * - class : 리턴타입 (UserServiceImpl)
	 */
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}

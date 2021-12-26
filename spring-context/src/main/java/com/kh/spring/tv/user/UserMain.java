package com.kh.spring.tv.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kh.spring.tv.user.config.UserConfig;
import com.kh.spring.tv.user.controller.UserController;

public class UserMain {
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		System.out.println("======================spring Container초기화 완료========================");
		
		UserController controller = context.getBean(UserController.class);
		controller.selectOneUser(8);
		
	}
}

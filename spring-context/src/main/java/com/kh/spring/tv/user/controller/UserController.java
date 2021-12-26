package com.kh.spring.tv.user.controller;

import com.kh.spring.tv.user.model.service.UserService;

public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	public void selectOneUser(int no) {
		userService.selectOneUser(no);
	}
	
}

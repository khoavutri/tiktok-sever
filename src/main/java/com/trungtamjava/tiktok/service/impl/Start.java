package com.trungtamjava.tiktok.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.tiktok.model.UserDto;

import jakarta.annotation.PostConstruct;

@Service
public class Start {
	@Autowired
	UserServiceImpl userServiceImpl;
	
	private String startUserName = "1";
	private String startPassWord = "123456";
	private String startGmail = "khoavutri@gmail.com";
	private String startName = "khoa";
	@PostConstruct
	public void initUser() {
		if (userServiceImpl.countAll()>0) {
			return;
		}
		UserDto user = new UserDto();
		user.setUserName(startUserName);
		user.setPassWord(startPassWord);
		user.setGmail(startGmail);
		user.setRole("ADMIN");
		userServiceImpl.insert(user);
	}
}

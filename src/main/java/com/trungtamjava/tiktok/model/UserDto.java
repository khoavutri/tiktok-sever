package com.trungtamjava.tiktok.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private String userName;
	private String passWord;
	private String avatar;
	private String gmail;
	private String name;
	private boolean famous;
	private String bio;
	private String role;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = "Asia/Ho_chi_minh")
	private Date birthday;
	
}

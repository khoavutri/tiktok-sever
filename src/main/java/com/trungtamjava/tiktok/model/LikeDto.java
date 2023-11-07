package com.trungtamjava.tiktok.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LikeDto {
	private int id;
	private int userId;
	private int videoId;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = "Asia/Ho_chi_minh")
	private Date date;
}
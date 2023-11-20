package com.trungtamjava.tiktok.model;

import java.util.List;

import lombok.Data;

@Data
public class Profile {
	private UserDto user;
	private List<VideoDto>videos;
	private int trangthai;
	private int follower;
	private int dangFollow;
}

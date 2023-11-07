package com.trungtamjava.tiktok.model;

import java.util.List;

import lombok.Data;

@Data
public class Profile {
	private UserDto user;
	private List<VideoDto>videos;
	//0 chưa đăng nhâp, 1 chính bạn, 2 chưa follow,3 đã follow
	private int trangthai;
	private int follower;
	private int dangFollow;
}

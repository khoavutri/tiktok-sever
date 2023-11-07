package com.trungtamjava.tiktok.model;

import lombok.Data;

@Data
public class BaiViet {
	private UserDto user;
	private VideoDto video;
	private int likes;
	private int cmts;
	//0 chưa đăng nhập, 1 bai viet cua ban, 2 chua folow, 3 da folow
	private int trangthai;
	//0 chưa đăng nhập, 1 chưa like, 2 đã like
	private int daLike;
}

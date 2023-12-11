package com.trungtamjava.tiktok.model;

import lombok.Data;

@Data
public class CommentFull {
	private UserDto user;
	private VideoDto video;
	private CommentDto cmt;
	//0 chưa đăng nhập, 1 bai viet cua ban, 2 chua folow, 3 da folow
	private int trangthai;
	private int daLike;
}

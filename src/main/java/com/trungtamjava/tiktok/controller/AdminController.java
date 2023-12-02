package com.trungtamjava.tiktok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.tiktok.model.responeDto;
import com.trungtamjava.tiktok.service.impl.CommentServiceImpl;
import com.trungtamjava.tiktok.service.impl.LikeServiceImpl;
import com.trungtamjava.tiktok.service.impl.UserServiceImpl;
import com.trungtamjava.tiktok.service.impl.VideoServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	VideoServiceImpl videoServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	LikeServiceImpl likeServiceImpl;
	
	@Autowired
	CommentServiceImpl commentServiceImpl;
	@GetMapping("/test")
	public responeDto<String> test() {
		return responeDto.<String>builder().status(200).msg("hay").data("sai ngu").build();
	}
	@GetMapping("/countAll-videos")
	public responeDto<Long>countAllVideos(){
		try {
			long x = videoServiceImpl.countAll();
			return responeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return responeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/countAll-users")
	public responeDto<Long>countAllUsers(){
		try {
			long x = userServiceImpl.countAll();
			return responeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return responeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/countAll-cmts")
	public responeDto<Long>countAllCmts(){
		try {
			long x = commentServiceImpl.countAll();
			return responeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return responeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/countAll-likes")
	public responeDto<Long>countAllLikes(){
		try {
			long x = likeServiceImpl.countAll();
			return responeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return responeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
}

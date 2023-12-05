package com.trungtamjava.tiktok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.responeDto;
import com.trungtamjava.tiktok.service.impl.CommentServiceImpl;
import com.trungtamjava.tiktok.service.impl.LikeServiceImpl;
import com.trungtamjava.tiktok.service.impl.UserServiceImpl;
import com.trungtamjava.tiktok.service.impl.VideoServiceImpl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
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
	@PostMapping("/search-pageAll-atAdmin")
	public responeDto<List<UserDto>>searchPageAllAtAdmin(@RequestParam("keyword") String keyword,
			@RequestParam("currentPage") int currentPage,
			@RequestParam("size") int size){
		try {
			int xx = currentPage-1;
			List<UserDto> x = userServiceImpl.searchPageatAdmin(keyword,xx,size);
			return responeDto.<List<UserDto>>builder().status(200).msg("ok").data(x).build();
		
		} catch (Exception e) {
			return responeDto.<List<UserDto>>builder().status(400).msg("bad request").build();
		}
	}
	@PutMapping("Ban-Phuoc")
	public responeDto<Boolean>banPhuoc(@RequestParam("id") int id){
		try {
			UserDto userDto = userServiceImpl.SearchById(id);
			userDto.setFamous(!userDto.isFamous());
			userServiceImpl.update(userDto);
			return responeDto.<Boolean>builder().status(200).msg("ok").data(userDto.isFamous()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return responeDto.<Boolean>builder().status(400).msg("bad request").build();
		}
	}
	
	
}

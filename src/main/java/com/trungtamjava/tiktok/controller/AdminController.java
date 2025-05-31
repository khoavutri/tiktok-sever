package com.trungtamjava.tiktok.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.tiktok.model.BaiViet;
import com.trungtamjava.tiktok.model.CommentDto;
import com.trungtamjava.tiktok.model.CommentFull;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;
import com.trungtamjava.tiktok.model.ResponeDto;
import com.trungtamjava.tiktok.service.impl.CommentServiceImpl;
import com.trungtamjava.tiktok.service.impl.FolowerServiceImpl;
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
FolowerServiceImpl folowerServiceImpl;
	@Autowired
	CommentServiceImpl commentServiceImpl;
	@GetMapping("/test")
	public ResponeDto<String> test() {
		return ResponeDto.<String>builder().status(200).msg("hay").data("sai ngu").build();
	}
	@GetMapping("/countAll-videos")
	public ResponeDto<Long>countAllVideos(){
		try {
			long x = videoServiceImpl.countAll();
			return ResponeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return ResponeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/countAll-users")
	public ResponeDto<Long>countAllUsers(){
		try {
			long x = userServiceImpl.countAll();
			return ResponeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return ResponeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/countAll-cmts")
	public ResponeDto<Long>countAllCmts(){
		try {
			long x = commentServiceImpl.countAll();
			return ResponeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return ResponeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/countAll-likes")
	public ResponeDto<Long>countAllLikes(){
		try {
			long x = likeServiceImpl.countAll();
			return ResponeDto.<Long>builder().status(200).msg("ok").data(x).build();
		} catch (Exception e) {
			return ResponeDto.<Long>builder().status(400).msg("bad request").build();
		}
	}
	@PostMapping("/search-pageAll-atAdmin")
	public ResponeDto<List<UserDto>>searchPageAllAtAdmin(@RequestParam("keyword") String keyword,
			@RequestParam("currentPage") int currentPage,
			@RequestParam("size") int size){
		try {
			int xx = currentPage-1;
			List<UserDto> x = userServiceImpl.searchPageatAdmin(keyword,xx,size);
			return ResponeDto.<List<UserDto>>builder().status(200).msg("ok").data(x).build();
		
		} catch (Exception e) {
			return ResponeDto.<List<UserDto>>builder().status(400).msg("bad request").build();
		}
	}
	@PutMapping("/Ban-Phuoc")
	public ResponeDto<Boolean>banPhuoc(@RequestParam("id") int id){
		try {
			UserDto userDto = userServiceImpl.SearchById(id);
			userDto.setFamous(!userDto.isFamous());
			userServiceImpl.updateUserDetails(userDto.getUserName(), userDto.isFamous(), userDto.getRole());
			return ResponeDto.<Boolean>builder().status(200).msg("ok").data(userDto.isFamous()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponeDto.<Boolean>builder().status(400).msg("bad request").build();
		}
	}
	@PutMapping("/Dong-Dam")
	public ResponeDto<String>dongDam(@RequestParam("id") int id){
		try {
			UserDto userDto = userServiceImpl.SearchById(id);
			if (userDto.getRole().equals("ADMIN")) {
				userDto.setRole("USER");
			} else if (userDto.getRole().equals("USER")) {
				userDto.setRole("ADMIN");
			};
			
			userServiceImpl.updateUserDetails(userDto.getUserName(), userDto.isFamous(), userDto.getRole());
			return ResponeDto.<String>builder().status(200).msg("ok").data(userDto.getRole()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponeDto.<String>builder().status(400).msg("bad request").build();
		}
	}
	@DeleteMapping("/xoa-user")
	public ResponeDto<String>xoaUser(@RequestParam("id") int id){
		try {
			userServiceImpl.delete(id);
			return ResponeDto.<String>builder().status(200).msg("ok").data("success").build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponeDto.<String>builder().status(400).msg("bad request").build();
		}
	}
	@GetMapping("/SelectImgPostAll")
	public ResponeDto<List<BaiViet>> SelectImgPostAll(@RequestParam("page") int page,
			@RequestParam("size") int size
			){
		try {
			List<BaiViet>baiViets =  new ArrayList<BaiViet>();
			int x = page-1;
			List<VideoDto> videos = videoServiceImpl.SearchAllPage(x, size);

			for (VideoDto videoDto:videos) {
				BaiViet baiViet = new BaiViet();
				baiViet.setVideo(videoDto);
				UserDto user = userServiceImpl.SearchById(videoDto.getUserId());
				baiViet.setUser(user);
				baiViet.setLikes(likeServiceImpl.countByVideo(videoDto));
				baiViet.setCmts(commentServiceImpl.countByVideo(videoDto));
				
				baiViets.add(baiViet);
			}
			return ResponeDto.<List<BaiViet>>builder().status(200)
					.msg("success").data(baiViets).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<List<BaiViet>>builder().status(400)
					.msg("bad request").build();
		}
	}
	@GetMapping("/SearchAllCmts")
	public ResponeDto<List<CommentFull>>SearchAllCmtsByVideoId(@RequestParam("page") int page,
			@RequestParam("size") int size){
		try {
			int x = page-1;
			List<CommentDto>commentDtos = commentServiceImpl.SelectPageAll(x, size);
			List<CommentFull>commentFulls = new ArrayList<CommentFull>();

			for (CommentDto comment:commentDtos) {
				CommentFull full = new CommentFull();
				full.setCmt(comment);
				UserDto user = userServiceImpl.SearchById(comment.getUserId());
				full.setUser(user);
				VideoDto video = videoServiceImpl.SearchById(comment.getVideoId());
				full.setVideo(video);
				commentFulls.add(full);
			}
			return ResponeDto.<List<CommentFull>>builder().msg("OK").status(200).data(commentFulls).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<List<CommentFull>>builder().msg("not found").status(404).build();
		}
	}
	@DeleteMapping("/xoa-BaiViet")
	public ResponeDto<String>xoaBaiViet(@RequestParam("id") int id){
		try {
			videoServiceImpl.delete(id);
			return ResponeDto.<String>builder().status(200).msg("ok").data("success").build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponeDto.<String>builder().status(400).msg("bad request").build();
		}
	}
	@DeleteMapping("/xoa-Cmts")
	public ResponeDto<String>xoaCmts(@RequestParam("id") int id){
		try {
			commentServiceImpl.delete(id);
			return ResponeDto.<String>builder().status(200).msg("ok").data("success").build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponeDto.<String>builder().status(400).msg("bad request").build();
		}
	}
}

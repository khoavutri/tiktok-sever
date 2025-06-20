package com.trungtamjava.tiktok.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.tiktok.model.BaiViet;
import com.trungtamjava.tiktok.model.CommentDto;
import com.trungtamjava.tiktok.model.FolowerDto;
import com.trungtamjava.tiktok.model.LikeDto;
import com.trungtamjava.tiktok.model.Profile;
import com.trungtamjava.tiktok.model.UploadBaiViet;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;
import com.trungtamjava.tiktok.model.ResponeDto;
import com.trungtamjava.tiktok.service.impl.CommentServiceImpl;
import com.trungtamjava.tiktok.service.impl.FolowerServiceImpl;
import com.trungtamjava.tiktok.service.impl.LikeServiceImpl;
import com.trungtamjava.tiktok.service.impl.UserServiceImpl;
import com.trungtamjava.tiktok.service.impl.VideoServiceImpl;

import lombok.extern.slf4j.Slf4j;






@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	VideoServiceImpl videoServiceImpl;
	@Autowired
	FolowerServiceImpl folowerServiceImpl;
	@Autowired
	LikeServiceImpl likeServiceImpl;
	@Autowired
	CommentServiceImpl commentServiceImpl;
	@GetMapping("/test")
	public ResponeDto<String> test() {

		return ResponeDto.<String>builder().status(200).msg("ok").data("test").build();
	}
	/*private int id;
	private int userId;
	private int videoId;
	*/
	@PostMapping("/Like")
	public ResponeDto<LikeDto>Like(@RequestBody LikeDto like){
		try {
			
			return ResponeDto.<LikeDto>builder().msg("Created").status(201).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<LikeDto>builder().msg("bad request").status(400).build();
		}
	}
	@PostMapping("/Follow")
	public ResponeDto<FolowerDto>Follow(@RequestBody FolowerDto folowerDto){
		try {
			folowerServiceImpl.insert(folowerDto);
			return ResponeDto.<FolowerDto>builder().data(folowerDto).msg("success").status(201).build();
		} catch (Exception e) {
			return ResponeDto.<FolowerDto>builder().msg("bad request").status(400).build();
		}
		
	}
	@PostMapping("/UnFollow")
	public ResponeDto<FolowerDto>UnFollow(@RequestBody FolowerDto folowerDto){
		try {
			UserDto user1 = userServiceImpl.SearchById(folowerDto.getNguoiFolowId());
			UserDto user2 = userServiceImpl.SearchById(folowerDto.getDuocFolowId());
			FolowerDto dto = folowerServiceImpl.kiemtra(user1, user2);
			if (dto!=null) {
			folowerServiceImpl.deleteById(dto.getId());
			return ResponeDto.<FolowerDto>builder().data(folowerDto).msg("OK").status(200).build();
			} else {
				return ResponeDto.<FolowerDto>builder().msg("bad request").status(400).build();
			}
		} catch (Exception e) {
			return ResponeDto.<FolowerDto>builder().msg("bad request").status(400).build();
		}
		
	}
	@PostMapping("/UploadImgPost")
	public ResponeDto<String> UploadPostttt(@RequestBody UploadBaiViet baiViet ){
		try {
			VideoDto videoDto = new VideoDto();
			videoDto.setId(0);
			videoDto.setLink(baiViet.getLink());
			videoDto.setMota(baiViet.getMota());
			videoDto.setUserId(baiViet.getUserId());
			videoServiceImpl.insert(videoDto);
			return ResponeDto.<String>builder().msg("success").status(201).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<String>builder().status(400).msg("bad request").build();
		}
	}
	@DeleteMapping("/DeleteVideoById")
	public ResponeDto<VideoDto>DeleteVideoById(@RequestParam("VideoId") int id){
		try {
			videoServiceImpl.delete(id);
			return ResponeDto.<VideoDto>builder().status(200).msg("success").build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<VideoDto>builder().status(400).msg("bad request").build();
		}
		
	}
	@PostMapping("/LikeVideo")
	public ResponeDto<LikeDto>LikeVideo(@RequestBody LikeDto likeDto){
		try {
//			LikeDto likeDto = new LikeDto();
//			likeDto.setUserId(userId);
//			likeDto.setVideoId(videoId);
			likeServiceImpl.insert(likeDto);
			return ResponeDto.<LikeDto>builder().msg("created").status(201).data(null).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<LikeDto>builder().msg("bad request").status(400).build();
		}
	}
	@PostMapping("/UnLikeVideo")
	public ResponeDto<LikeDto>UnLikeVideo(@RequestBody LikeDto likeDto){
		try {
			LikeDto like = likeServiceImpl.selectByUserIdAndVideoId(likeDto.getUserId(), likeDto.getVideoId());
			likeServiceImpl.deleteById(like.getId());
			return ResponeDto.<LikeDto>builder().msg("Ok").status(200).data(null).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<LikeDto>builder().msg("bad request").status(400).build();
		}
	} 
	@GetMapping("/SeachAllFollowing")
	public ResponeDto<List<UserDto>>SeachAllFollowing(@RequestParam("userId")int userId){
		try {
			UserDto userDto = userServiceImpl.SearchById(userId);
			List<UserDto>userDtos = new ArrayList<UserDto>();
			List<FolowerDto>folowerDtos = folowerServiceImpl.SelectListByNguoiFolow(userDto);
			for (FolowerDto folowerDto:folowerDtos) {
				UserDto dto = userServiceImpl.SearchById(folowerDto.getDuocFolowId());
				userDtos.add(dto);
			}
			return ResponeDto.<List<UserDto>>builder().status(200).msg("OK").data(userDtos).build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<List<UserDto>>builder().status(400).msg("Bad Request").build();
		}
	}
	@PostMapping("/UpdateMyProfile")
	public ResponeDto<Profile>UpdateMyProfile(@RequestParam("userId") int userId,
			@RequestParam("avatar") String avatar,@RequestParam("userName") String userName,
			@RequestParam("name") String name,@RequestParam("bio") String bio){
		try {
			UserDto user = userServiceImpl.SearchById(userId);
			user.setAvatar(avatar);
			user.setUserName(userName);
			user.setName(name);
			user.setBio(bio);
			userServiceImpl.updateProfileByIddd(userId, avatar, userName, name, bio);
			return ResponeDto.<Profile>builder().msg("success").status(200).build();
		} catch (Exception e) {
			// TODO: handle exception'
			return ResponeDto.<Profile>builder().msg("bad request").status(400).build();
		}
	}
	@GetMapping("/SelectImgPostAllFollowing")
	public ResponeDto<List<BaiViet>> SelectImgPostAll(
			@RequestParam("userId") int userId){
		try {
			List<BaiViet>baiViets =  new ArrayList<BaiViet>();
			List<VideoDto> videos = new ArrayList<VideoDto>();
			List<FolowerDto> folowerDtos = 
				folowerServiceImpl.SelectListByNguoiFolow(userServiceImpl.SearchById(userId));
			for (FolowerDto folowerDto:folowerDtos) {
				List<VideoDto>dtos =
					videoServiceImpl.SearchListByUser(userServiceImpl.SearchById(folowerDto.getDuocFolowId()));
				for (VideoDto dto:dtos) {
					videos.add(dto);
				}
			}
			for (VideoDto videoDto:videos) {
				BaiViet baiViet = new BaiViet();
				baiViet.setVideo(videoDto);
				UserDto user = userServiceImpl.SearchById(videoDto.getUserId());
				baiViet.setUser(user);
				baiViet.setLikes(likeServiceImpl.countByVideo(videoDto));
				baiViet.setCmts(commentServiceImpl.countByVideo(videoDto));
				if (userId>0) {
					if (folowerServiceImpl.kiemtra(userServiceImpl.SearchById(userId),user)!=null) {
						baiViet.setTrangthai(3);
					} else if (userId==user.getId()) {
						baiViet.setTrangthai(1);
					} else baiViet.setTrangthai(2);
					
				} else baiViet.setTrangthai(0);
				if (userId>0) {
					if (likeServiceImpl.selectByUserIdAndVideoId(userId, videoDto.getId())!=null) {
						baiViet.setDaLike(2);
					} else {
						baiViet.setDaLike(1);
					}
				} else {
					baiViet.setDaLike(0);
				}
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
	@PostMapping("/CreateCmt")
	public ResponeDto<CommentDto> CreateCmt(@RequestBody CommentDto comment){
		try {
			commentServiceImpl.insert(comment);
			return ResponeDto.<CommentDto>builder().status(201).msg("Created").build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<CommentDto>builder().status(400).msg("bad request").build();
		}
	}
	@DeleteMapping("/DeleteCmt")
	public ResponeDto<CommentDto>DeleteCmt(@RequestParam("cmtId") int cmtId){
		try {
			commentServiceImpl.delete(cmtId);
			return ResponeDto.<CommentDto>builder().status(200).msg("OK").build();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponeDto.<CommentDto>builder().status(400).msg("bad request").build();
		}
	}
}

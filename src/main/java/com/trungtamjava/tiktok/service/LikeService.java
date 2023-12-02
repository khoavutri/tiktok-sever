package com.trungtamjava.tiktok.service;

import java.util.Date;
import java.util.List;

import com.trungtamjava.tiktok.model.LikeDto;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;

public interface LikeService {
	public List<LikeDto>SelectListByUser(UserDto userDto);
	public List<LikeDto>SelectPageByUser(UserDto userDto,int currentPage,int size);
	public List<LikeDto>SelectListByVideo(VideoDto videoDto);
	public List<LikeDto>SelectPageByVideo(VideoDto videoDto,int currentPage,int size);
	public List<LikeDto>SelectListByDate(Date date);
	public List<LikeDto>SelectPageByDate(Date date,int currentPage,int size);
	public List<LikeDto>SelectListAll();
	public List<LikeDto>SelectPageAll(int currentPage,int size);
	public int countByVideo(VideoDto videoDto);
	public int countByUser(UserDto userDto);
	public LikeDto SelectById(int id);
	public void insert(LikeDto likeDto);
	public void upDate(LikeDto likeDto);
	public void deleteById(int id);
	public LikeDto selectByUserIdAndVideoId(int userId,int videoId);
	public long countAll();
}

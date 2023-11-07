package com.trungtamjava.tiktok.service;

import java.util.Date;
import java.util.List;

import com.trungtamjava.tiktok.model.CommentDto;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;

public interface CommentService {
	public List<CommentDto> SelectListByDate(Date date);
	public List<CommentDto> SelectPageByDate(Date date,int currentPage,int size);
	public List<CommentDto> SelectListByContent(String content);
	public List<CommentDto> SelectPageByContent(String content,int currentPage,int size);
	public List<CommentDto> SelectListByVideo(VideoDto videoDto);
	public List<CommentDto> SelectPageByVideo(VideoDto videoDto,int currentPage,int size);
	public List<CommentDto> SelectListByUser(UserDto userDto);
	public List<CommentDto> SelectPageByUser(UserDto userDto,int currentPage,int size);
	public List<CommentDto> SelectListAll();
	public List<CommentDto> SelectPageAll(int currentPage,int size);
	public CommentDto SelectById(int id);
	public int countByUser(UserDto userDto);
	public int countByVideo(VideoDto videoDto);
	public void insert(CommentDto commentDto);
	public void upDate(CommentDto commentDto);
	public void delete(int id);
}

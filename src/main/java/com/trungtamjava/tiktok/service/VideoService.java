package com.trungtamjava.tiktok.service;

import java.util.List;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;

public interface VideoService {
	public VideoDto SearchById(int id);
	public List<VideoDto> SearchListByUser(UserDto userDto);
	public List<VideoDto> SearchPageByUser(UserDto userDto,int currentPage,int size);
	public List<VideoDto> SearchAllList();
	public List<VideoDto> SearchAllPage(int currentPage,int size);
	public List<VideoDto> SearchPageByMota(String mota,int currentPage,int size);
	public void insert(VideoDto videoDto);
	public void update(VideoDto videoDto);
	public void delete(int id);
	public long countAll();
}

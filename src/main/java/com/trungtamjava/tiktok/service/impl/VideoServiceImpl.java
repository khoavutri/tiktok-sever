package com.trungtamjava.tiktok.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trungtamjava.tiktok.dao.impl.UserDaoImpl;
import com.trungtamjava.tiktok.dao.impl.VideoDaoImpl;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;
import com.trungtamjava.tiktok.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	VideoDaoImpl videoDaoImpl;
	@Autowired
	UserDaoImpl userDaoImpl;

	@Override
	public VideoDto SearchById(int id) {
		// TODO Auto-generated method stub
		Video video = videoDaoImpl.SearchById(id);
		VideoDto videoDto = new VideoDto();
		videoDto.setId(id);
		videoDto.setLink(video.getLink());
		videoDto.setMota(video.getMota());
		videoDto.setView(video.getView());
		videoDto.setDate(video.getDate());
		videoDto.setUserId(video.getUser().getId());
		return videoDto;
	}

	@Override
	public List<VideoDto> SearchListByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchByUserName(userDto.getUserName());
		List<Video> videos = videoDaoImpl.SearchListByUser(user);
		List<VideoDto> videoDtos = new ArrayList<VideoDto>();
		for (Video video:videos) {
			VideoDto videoDto = new VideoDto();
			videoDto.setId(video.getId());
			videoDto.setLink(video.getLink());
			videoDto.setMota(video.getMota());
			videoDto.setView(video.getView());
			videoDto.setDate(video.getDate());
			videoDto.setUserId(video.getUser().getId());
			videoDtos.add(videoDto);
		}
		return videoDtos;
	}

	@Override
	public List<VideoDto> SearchPageByUser(UserDto userDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchByUserName(userDto.getUserName());
		List<Video> videos = videoDaoImpl.SearchPageByUser(user,currentPage,size).getContent();
		List<VideoDto> videoDtos = new ArrayList<VideoDto>();
		for (Video video:videos) {
			VideoDto videoDto = new VideoDto();
			videoDto.setId(video.getId());
			videoDto.setLink(video.getLink());
			videoDto.setMota(video.getMota());
			videoDto.setView(video.getView());
			videoDto.setDate(video.getDate());
			videoDto.setUserId(video.getUser().getId());
			videoDtos.add(videoDto);
		}
		return videoDtos;
	}

	@Override
	public List<VideoDto> SearchAllList() {
		// TODO Auto-generated method stub
		List<Video> videos = videoDaoImpl.SearchAllList();
		List<VideoDto> videoDtos = new ArrayList<VideoDto>();
		for (Video video:videos) {
			VideoDto videoDto = new VideoDto();
			videoDto.setId(video.getId());
			videoDto.setLink(video.getLink());
			videoDto.setMota(video.getMota());
			videoDto.setView(video.getView());
			videoDto.setDate(video.getDate());
			videoDto.setUserId(video.getUser().getId());
			videoDtos.add(videoDto);
		}
		return videoDtos;
	}

	@Override
	public List<VideoDto> SearchAllPage(int currentPage, int size) {
		// TODO Auto-generated method stub
		List<Video> videos = videoDaoImpl.SearchAllPage(currentPage,size).getContent();
		List<VideoDto> videoDtos = new ArrayList<VideoDto>();
		for (Video video:videos) {
			VideoDto videoDto = new VideoDto();
			videoDto.setId(video.getId());
			videoDto.setLink(video.getLink());
			videoDto.setMota(video.getMota());
			videoDto.setView(video.getView());
			videoDto.setDate(video.getDate());
			videoDto.setUserId(video.getUser().getId());
			videoDtos.add(videoDto);
		}
		return videoDtos;
	}

	@Override
	public List<VideoDto> SearchPageByMota(String mota, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<Video> videos = videoDaoImpl.SearchPageByMota(mota,currentPage,size).getContent();
		List<VideoDto> videoDtos = new ArrayList<VideoDto>();
		for (Video video:videos) {
			VideoDto videoDto = new VideoDto();
			videoDto.setId(video.getId());
			videoDto.setLink(video.getLink());
			videoDto.setMota(video.getMota());
			videoDto.setView(video.getView());
			videoDto.setDate(video.getDate());
			videoDto.setUserId(video.getUser().getId());
			videoDtos.add(videoDto);
		}
		return videoDtos;
	}



	@Override
	public void insert(VideoDto videoDto) {
		// TODO Auto-generated method stub
		User user= userDaoImpl.SearchById(videoDto.getUserId());
		Video video = new Video();
		video.setId(0);
		video.setLink(videoDto.getLink());
		video.setMota(videoDto.getMota());
		video.setView(0);
		video.setUser(user);
		videoDaoImpl.insert(video);
	}
	//id link mota view date userId
	@Override
	public void update(VideoDto videoDto) {
		// TODO Auto-generated method stub
		User user= userDaoImpl.SearchById(videoDto.getUserId());
		Video video = new Video();
		video.setId(videoDto.getId());
		video.setLink(videoDto.getLink());
		video.setMota(videoDto.getMota());
		video.setView(videoDto.getView());
		video.setDate(videoDto.getDate());
		video.setUser(user);
		videoDaoImpl.update(video);
	}

	@Override
	public void delete(int id) {
		videoDaoImpl.delete(id);
	}

	@Override
	public long countAll() {
		// TODO Auto-generated method stub
		return videoDaoImpl.countAll();
	}

}

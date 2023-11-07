package com.trungtamjava.tiktok.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.tiktok.dao.impl.LikeDaoImpl;
import com.trungtamjava.tiktok.dao.impl.UserDaoImpl;
import com.trungtamjava.tiktok.dao.impl.VideoDaoImpl;
import com.trungtamjava.tiktok.entity.Likee;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;
import com.trungtamjava.tiktok.model.LikeDto;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;
import com.trungtamjava.tiktok.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	LikeDaoImpl likeDaoImpl;
	@Autowired
	UserDaoImpl userDaoImpl;
	@Autowired
	VideoDaoImpl videoDaoImpl;
	@Override
	public List<LikeDto> SelectListByUser(UserDto userDto) {
		User user = userDaoImpl.SearchById(userDto.getId());
		List<Likee> likes = likeDaoImpl.SelectListByUser(user);
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectPageByUser(UserDto userDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<Likee> likes = 
				likeDaoImpl.SelectPageByUser(user, currentPage, size).getContent();
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectListByVideo(VideoDto videoDto) {
		// TODO Auto-generated method stub
		Video video = videoDaoImpl.SearchById(videoDto.getId());
		List<Likee> likes = likeDaoImpl.SelectListByVideo(video);
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectPageByVideo(VideoDto videoDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		Video video = videoDaoImpl.SearchById(videoDto.getId());
		List<Likee> likes = likeDaoImpl.SelectPageByVideo(video, currentPage, size).getContent();
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectListByDate(Date date) {
		// TODO Auto-generated method stub
		List<Likee> likes = likeDaoImpl.SelectListByDate(date);
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectPageByDate(Date date, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<Likee> likes = likeDaoImpl.SelectPageByDate(date, currentPage, size).getContent();
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();

		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectListAll() {
		// TODO Auto-generated method stub
		List<Likee> likes = likeDaoImpl.SelectListAll();
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public List<LikeDto> SelectPageAll(int currentPage, int size) {
		// TODO Auto-generated method stub
		List<Likee> likes = likeDaoImpl.SelectPageAll(currentPage, size).getContent();
		List<LikeDto>likeDtos = new ArrayList<LikeDto>();
		//id user video date
		for (Likee like:likes) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}

	@Override
	public int countByVideo(VideoDto videoDto) {
		// TODO Auto-generated method stub
		Video video = videoDaoImpl.SearchById(videoDto.getId());
		return likeDaoImpl.countByVideo(video);
	}

	@Override
	public int countByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		return likeDaoImpl.countByUser(user);
	}

	@Override
	public LikeDto SelectById(int id) {
		// TODO Auto-generated method stub
		Likee like = likeDaoImpl.SelectById(id);
		LikeDto likeDto = new LikeDto();
		likeDto.setId(like.getId());
		likeDto.setUserId(like.getUser().getId());
		likeDto.setVideoId(like.getVideo().getId());
		likeDto.setDate(like.getDate());
	
		return likeDto;
	}

	@Override
	public void insert(LikeDto likeDto) {
		//id user video date
		Likee like = new Likee();
		like.setId(0);
		like.setUser(userDaoImpl.SearchById(likeDto.getUserId()));
		like.setVideo(videoDaoImpl.SearchById(likeDto.getVideoId()));
		likeDaoImpl.insert(like);
	}

	@Override
	public void upDate(LikeDto likeDto) {
		// TODO Auto-generated method stub
		Likee like = new Likee();
		like.setId(likeDto.getId());
		like.setUser(userDaoImpl.SearchById(likeDto.getUserId()));
		like.setVideo(videoDaoImpl.SearchById(likeDto.getVideoId()));
		likeDaoImpl.upDate(like);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		likeDaoImpl.deleteById(id);
	}

	@Override
	public LikeDto selectByUserIdAndVideoId(int userId, int videoId) {
		// TODO Auto-generated method stub
		try {
			User user = userDaoImpl.SearchById(userId);
			Video video = videoDaoImpl.SearchById(videoId);
			Likee like = likeDaoImpl.selectByUserAndVideo(user, video);
			if (like!=null) {
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setUserId(like.getUser().getId());
			likeDto.setVideoId(like.getVideo().getId());
			likeDto.setDate(like.getDate());
			return likeDto;} else return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	
	}
	
}

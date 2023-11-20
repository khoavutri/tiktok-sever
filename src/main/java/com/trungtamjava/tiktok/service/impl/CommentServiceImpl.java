package com.trungtamjava.tiktok.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.tiktok.dao.impl.CommentDaoImpl;
import com.trungtamjava.tiktok.dao.impl.UserDaoImpl;
import com.trungtamjava.tiktok.dao.impl.VideoDaoImpl;
import com.trungtamjava.tiktok.entity.Comment;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;
import com.trungtamjava.tiktok.model.CommentDto;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;
import com.trungtamjava.tiktok.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDaoImpl commentDaoImpl;
	@Autowired
	VideoDaoImpl videoDaoImpl;
	@Autowired
	UserDaoImpl userDaoImpl;
	@Override
	public List<CommentDto> SelectListByDate(Date date) {
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = commentDaoImpl.SelectListByDate(date);
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectPageByDate(Date date, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = 
				commentDaoImpl.SelectPageByDate(date, currentPage, size).getContent();
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectListByContent(String content) {
		// TODO Auto-generated method stub
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = commentDaoImpl.SelectListByContent(content);
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectPageByContent(String content, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = 
				commentDaoImpl.SelectPageByContent(content, currentPage, size).getContent();
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectListByVideo(VideoDto videoDto) {
		// TODO Auto-generated method stub
		Video video = videoDaoImpl.SearchById(videoDto.getId());
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = commentDaoImpl.SelectListByVideo(video);
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		commentDtos.sort(new Comparator<CommentDto>() {
		    public int compare(CommentDto c1, CommentDto c2) {
		        return c2.getDate().compareTo(c1.getDate());
		    }
		});
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectPageByVideo(VideoDto videoDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		Video video = videoDaoImpl.SearchById(videoDto.getId());
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments =
				commentDaoImpl.SelectPageByVideo(video, currentPage, size).getContent();
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectListByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = commentDaoImpl.SelectListByUser(user);
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectPageByUser(UserDto userDto, int currentPage, int size) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = 
				commentDaoImpl.SelectPageByUser(user, currentPage, size).getContent();
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectListAll() {
		// TODO Auto-generated method stub
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = commentDaoImpl.SelectListAll();
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public List<CommentDto> SelectPageAll(int currentPage, int size) {
		// TODO Auto-generated method stub
		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = 
				commentDaoImpl.SelectPageAll(currentPage, size).getContent();
		for (Comment comment:comments) {
			CommentDto commentDto = new CommentDto();
			commentDto.setId(comment.getId());
			commentDto.setDate(comment.getDate());
			commentDto.setContent(comment.getContent());
			commentDto.setUserId(comment.getUser().getId());
			commentDto.setVideoId(comment.getVideo().getId());
			commentDtos.add(commentDto);
		}
		return commentDtos;
	}

	@Override
	public CommentDto SelectById(int id) {
		// TODO Auto-generated method stub
		Comment comment = commentDaoImpl.SelectById(id);
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setDate(comment.getDate());
		commentDto.setContent(comment.getContent());
		commentDto.setUserId(comment.getUser().getId());
		commentDto.setVideoId(comment.getVideo().getId());
		return commentDto;
	}

	@Override
	public int countByUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userDto.getId());
		return commentDaoImpl.countByUser(user);
	}

	@Override
	public int countByVideo(VideoDto videoDto) {
		Video video = videoDaoImpl.SearchById(videoDto.getId());
		return commentDaoImpl.countByVideo(video);
	}

	@Override
	public void insert(CommentDto commentDto) {
		//id date content videoId userId
		Comment comment = new Comment();
		comment.setId(0);
		comment.setContent(commentDto.getContent());
		comment.setVideo(videoDaoImpl.SearchById(commentDto.getVideoId()));
		comment.setUser(userDaoImpl.SearchById(commentDto.getUserId()));
		commentDaoImpl.insert(comment);
	}

	@Override
	public void upDate(CommentDto commentDto) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setContent(commentDto.getContent());
		comment.setVideo(videoDaoImpl.SearchById(commentDto.getVideoId()));
		comment.setUser(userDaoImpl.SearchById(commentDto.getUserId()));
		commentDaoImpl.upDate(comment);
	}

	@Override
	public void delete(int id) {
		commentDaoImpl.delete(id);	
	}

}

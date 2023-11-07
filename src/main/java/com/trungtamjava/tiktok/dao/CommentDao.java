package com.trungtamjava.tiktok.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trungtamjava.tiktok.entity.Comment;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;

public interface CommentDao extends JpaRepository<Comment, Integer>{
	//id date content video user
	List<Comment> findByDate(Date date);
	Page<Comment> findByDate(Date date,Pageable pageable);
	List<Comment> findByContent(String content);
	Page<Comment> findByContent(String content,Pageable pageable);
	List<Comment> findByVideo(Video video);
	Page<Comment> findByVideo(Video video,Pageable pageable);
	List<Comment> findByUser(User user);
	Page<Comment> findByUser(User user,Pageable pageable);
	Page<Comment> findAll(Pageable pageable);
	int countByVideo(Optional<Video> optional);
	int countByUser(Optional<User> optional);
}

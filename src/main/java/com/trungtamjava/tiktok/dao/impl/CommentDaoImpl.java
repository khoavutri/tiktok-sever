package com.trungtamjava.tiktok.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.trungtamjava.tiktok.dao.CommentDao;
import com.trungtamjava.tiktok.entity.Comment;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;

@Component
public class CommentDaoImpl {
	@Autowired
	CommentDao commentDao;
	public List<Comment> SelectListByDate(Date date){
		return commentDao.findByDate(date);
	}
	public Page<Comment> SelectPageByDate(Date date,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Comment> pages = commentDao.findByDate(date,pageRequest);
		return pages;
	}
	public List<Comment> SelectListByContent(String content){
		return commentDao.findByContent("%"+content+"%");
	}
	public Page<Comment> SelectPageByContent(String content,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Comment> pages = commentDao.findByContent("%"+content+"%",pageRequest);
		return pages;
	}
	public List<Comment> SelectListByVideo(Video video){
		return commentDao.findByVideo(video);
	}
	public Page<Comment> SelectPageByVideo(Video video,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Comment> pages = commentDao.findByVideo(video,pageRequest);
		return pages;
	}
	public List<Comment> SelectListByUser(User user){
		return commentDao.findByUser(user);
	}
	public Page<Comment> SelectPageByUser(User user,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Comment> pages = commentDao.findByUser(user,pageRequest);
		return pages;
	}
	public List<Comment> SelectListAll(){
		return commentDao.findAll();
	}
	public Page<Comment> SelectPageAll(int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Comment> pages = commentDao.findAll(pageRequest);
		return pages;
	}
	public Comment SelectById(int id) {
		return commentDao.findById(id).orElse(null);
	}
	public int countByUser(User user) {
		Optional<User>optional = Optional.ofNullable(user);
		return commentDao.countByUser(optional);
	}
	public int countByVideo(Video video) {
		Optional<Video> optionalVideo = Optional.ofNullable(video);
		return commentDao.countByVideo(optionalVideo);
	}
	public void insert(Comment comment) {
		if (commentDao.findById(comment.getId()).orElse(null)==null) {
			commentDao.save(comment);
		}
	}
	public void upDate(Comment comment) {
		if (commentDao.findById(comment.getId()).orElse(null)!=null) {
			commentDao.save(comment);
		}
	}
	public void delete(int id) {
		commentDao.deleteById(id);
	}
	public long countAll() {
		return commentDao.count();
	}
}

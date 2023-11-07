package com.trungtamjava.tiktok.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.trungtamjava.tiktok.dao.LikeDao;
import com.trungtamjava.tiktok.entity.Likee;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;

@Component
public class LikeDaoImpl {
	@Autowired
	LikeDao likeDao;
	public List<Likee>SelectListByUser(User user){
		return likeDao.findByUser(user);
	}
	public Page<Likee>SelectPageByUser(User user,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Likee> pages = likeDao.findByUser(user, pageRequest);
		return pages;
	}
	public List<Likee>SelectListByVideo(Video video){
		return likeDao.findByVideo(video);
	}
	public Page<Likee>SelectPageByVideo(Video video,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Likee> pages = likeDao.findByVideo(video, pageRequest);
		return pages;
	}
	public List<Likee>SelectListByDate(Date date){
		return likeDao.findByDate(date);
	}
	public Page<Likee>SelectPageByDate(Date date,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Likee> pages = likeDao.findByDate(date, pageRequest);
		return pages;
	}
	public List<Likee>SelectListAll(){
		return likeDao.findAll();
	}
	public Page<Likee>SelectPageAll(int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Likee> pages = likeDao.findAll(pageRequest);
		return pages;
	}
	public int countByVideo(Video video) {
		Optional<Video> optionalVideo = Optional.ofNullable(video);
		return likeDao.countByVideo(optionalVideo);
	}
	public int countByUser(User user) {
		Optional<User> optional = Optional.ofNullable(user);
		return likeDao.countByUser(optional);
	}
	public Likee SelectById(int id) {
		return likeDao.findById(id).orElse(null);
	}
	public void insert(Likee likee) {
		if (likeDao.findById(likee.getId()).orElse(null)==null) {
			likeDao.save(likee);
		}
	}
	public void upDate(Likee likee) {
		if (likeDao.findById(likee.getId()).orElse(null)!=null) {
			likeDao.save(likee);
		}
	}
	public void deleteById(int id) {
		likeDao.deleteById(id);
	}
	public Likee selectByUserAndVideo(User user,Video video) {
		return likeDao.findByUserAndVideo(user, video);
	}
}

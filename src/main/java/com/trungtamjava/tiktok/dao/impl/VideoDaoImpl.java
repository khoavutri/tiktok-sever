package com.trungtamjava.tiktok.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.trungtamjava.tiktok.dao.VideoDao;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;

@Component
public class VideoDaoImpl {
	@Autowired
	VideoDao videoDao;
	public Video SearchById(int id) {
		return videoDao.findById(id).orElse(null);
	}
	public List<Video> SearchListByUser(User user){
		return videoDao.findByUser(user);
	}
	public Page<Video> SearchPageByUser(User user,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Video> pages = videoDao.findByUser(user,pageRequest);
		return pages;
	}
	public List<Video> SearchAllList(){
		return videoDao.findAll();
	}
	public Page<Video> SearchAllPage(int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Video> pages = videoDao.findAll(pageRequest);
		return pages;
	}
	public Page<Video> SearchPageByMota(String mota,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Video> pages = videoDao.findByMota("%"+mota+"%",pageRequest);
		return pages;
	}
	public void insert(Video video) {
		videoDao.save(video);
	}
	public void update(Video video) {
		videoDao.save(video);
	}
	public void delete(int id) {
		videoDao.deleteById(id);
	}
}

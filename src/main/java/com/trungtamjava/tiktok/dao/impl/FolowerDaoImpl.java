package com.trungtamjava.tiktok.dao.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.trungtamjava.tiktok.dao.FolowerDao;
import com.trungtamjava.tiktok.entity.Folower;
import com.trungtamjava.tiktok.entity.User;

@Component
public class FolowerDaoImpl {
	@Autowired
	FolowerDao folowerDao;
	public List<Folower>SelectListAll(){
		return folowerDao.findAll();
	}
	public Page<Folower>SelectPageAll(int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Folower> pages = folowerDao.findAll(pageRequest);
		return pages;
	}
	public List<Folower> SelectListByNguoiFolow(User user){
		return folowerDao.findByNguoiFolow(user);
	}
	public List<Folower> SelectListByDuocFolow(User user){
		return folowerDao.findByDuocFolow(user);
	}
	public Page<Folower>SelectPageByNguoiFolow(User user,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Folower> pages = folowerDao.findByNguoiFolow(user, pageRequest);
		return pages;
	}
	public Page<Folower>SelectPageByDuocFolow(User user,int currentPage,int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<Folower> pages = folowerDao.findByDuocFolow(user, pageRequest);
		return pages;
	}
	public int folower(User user) {
		Optional<User>optional = Optional.ofNullable(user);
		return folowerDao.countByDuocFolow(optional);
	}
	public int dangFolow(User user) {
		Optional<User>optional = Optional.ofNullable(user);
		return folowerDao.countByNguoiFolow(optional);
	}
	public Folower selectById(int id) {
		return folowerDao.findById(id).orElse(null);
	}
	public void insert(Folower folower) {
		if ((folowerDao.findById(folower.getId()).orElse(null))==null){
			folowerDao.save(folower);
		}
	}
	public void upDate(Folower folower) {
		if ((folowerDao.findById(folower.getId()).orElse(null))!=null){
			folowerDao.save(folower);
		}
	}
	public void deleteById(int id) {
		if ((folowerDao.findById(id).orElse(null))!=null) {
			folowerDao.deleteById(id);
		}
	}
	
	public Folower kt(User user1, User user2) {
		return folowerDao.findFollower(user1, user2);
	}
}

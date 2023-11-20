package com.trungtamjava.tiktok.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.trungtamjava.tiktok.dao.UserDao;
import com.trungtamjava.tiktok.entity.User;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDaoImpl {
	@Autowired
	UserDao userDao;
	
	public User SearchById(int id) {
		return userDao.findById(id).orElse(null);
	}
	
	public User SelectByUserNameAndPassWord(String userName,String passWord) {
		return userDao.searchByUserNameAndPassWord(userName, passWord);
	}
	public User SearchByUserName(String username) {
		return userDao.findByUserName(username);
	}
	public User SearchByGmail(String gmail) {
		return userDao.findByGmail(gmail);
	}
	
	public List<User> SearchByName(String name){
		return userDao.findByName("%"+name+"%");
	}
	public List<User> SearchByFamous(Boolean famous){
		return userDao.findByFamous(famous);
	}
	public List<User> SearchByDate(Date date){
		return userDao.findUsersByBirthday(date);
	}
	public void insert(User user) {
			userDao.save(user);
	}
	public void update(User user) {
			userDao.save(user);
	}
	public void delete(int id) {
		userDao.deleteById(id);
	}
	public Page<User> SearchPeopleByName(String name,int currentPage,
			int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<User> pages = userDao.searchPeopletByName("%"+name+"%", pageRequest);
		return pages;
	}
	 
	 public List<User> searchAllByKeyword(String keyword){
		 return userDao.searchAllByKeyword("%"+keyword+"%");
	 }
	 public Page<User> searchPageByKeyword(@Param("keyword") String keyword,int currentPage,
				int size){
		PageRequest pageRequest = PageRequest.of(currentPage, size);
		Page<User> pages = userDao.searchPageByKeyword("%"+keyword+"%", pageRequest);
			return pages;
	 }
}

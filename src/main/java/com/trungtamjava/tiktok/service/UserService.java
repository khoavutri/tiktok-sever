package com.trungtamjava.tiktok.service;

import java.util.Date;
import java.util.List;

import com.trungtamjava.tiktok.model.UserDto;

public interface UserService {
	public UserDto SearchById(int id);
	public UserDto SelectByUserNameAndPassWord(String userName,String passWord);
	public UserDto SearchByUserName(String username);
	public UserDto SearchByGmail(String gmail);
	public List<UserDto> SearchByName(String name);
	public List<UserDto> SearchByFamous(Boolean famous);
	public List<UserDto> SearchByDate(Date date);
	public void insert(UserDto userDto);
	public void update(UserDto userDto);
	public void delete(int id);
	public List<UserDto> SearchPeopleByName(String name,int currentPage,int size);
	public void updateProfileByIddd(int userId, String avatar,String userName,String name,String bio);
}
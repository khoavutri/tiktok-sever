package com.trungtamjava.tiktok.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trungtamjava.tiktok.dao.impl.UserDaoImpl;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.service.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService  {
	@Autowired
	UserDaoImpl userDaoImpl;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = SearchByUserName(username);
		if (user==null) {
			throw new UsernameNotFoundException("xác thực sai");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassWord(),authorities);
	}


	@Override
	public UserDto SearchById(int id) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(id);
		if (user!=null) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUserName(user.getUserName());
		userDto.setPassWord(user.getPassWord());
		userDto.setAvatar(user.getAvatar());
		userDto.setGmail(user.getGmail());
		userDto.setName(user.getName());
		userDto.setFamous(user.getFamous());
		userDto.setBio(user.getBio());
		userDto.setRole(user.getRole());
		userDto.setBirthday(user.getBirthday());
		return userDto;} else return null;
	}

	@Override
	public UserDto SelectByUserNameAndPassWord(String userName, String passWord) {
		User user = userDaoImpl.SelectByUserNameAndPassWord(userName, passWord);
		if (user!=null) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUserName(user.getUserName());
		userDto.setPassWord(user.getPassWord());
		userDto.setAvatar(user.getAvatar());
		userDto.setGmail(user.getGmail());
		userDto.setName(user.getName());
		userDto.setFamous(user.getFamous());
		userDto.setBio(user.getBio());
		userDto.setRole(user.getRole());
		userDto.setBirthday(user.getBirthday());
		return userDto;
		} else return null;
	}

	@Override
	public UserDto SearchByUserName(String username) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchByUserName(username);
		if (user!=null) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUserName(user.getUserName());
		userDto.setPassWord(user.getPassWord());
		userDto.setAvatar(user.getAvatar());
		userDto.setGmail(user.getGmail());
		userDto.setName(user.getName());
		userDto.setFamous(user.getFamous());
		userDto.setBio(user.getBio());
		userDto.setRole(user.getRole());
		userDto.setBirthday(user.getBirthday());
		return userDto;
		} else return null;
	}

	@Override
	public UserDto SearchByGmail(String gmail) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchByGmail(gmail);
		if (user!=null) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUserName(user.getUserName());
		userDto.setPassWord(user.getPassWord());
		userDto.setAvatar(user.getAvatar());
		userDto.setGmail(user.getGmail());
		userDto.setName(user.getName());
		userDto.setFamous(user.getFamous());
		userDto.setBio(user.getBio());
		userDto.setRole(user.getRole());
		userDto.setBirthday(user.getBirthday());
		return userDto;
		} else return null;
	}

	@Override
	public List<UserDto> SearchByName(String name) {
		// TODO Auto-generated method stub
		List<User> users = userDaoImpl.SearchByName(name);
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	}

	@Override
	public List<UserDto> SearchByFamous(Boolean famous) {
		// TODO Auto-generated method stub
		List<User> users = userDaoImpl.SearchByFamous(famous);
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	}

	@Override
	public List<UserDto> SearchByDate(Date date) {
		// TODO Auto-generated method stub
		List<User> users = userDaoImpl.SearchByDate(date);
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	}
	
	@Override
	public void insert(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(0);
		user.setUserName(userDto.getUserName());
		user.setPassWord(new BCryptPasswordEncoder().encode(userDto.getPassWord()));
		user.setAvatar("");
		user.setGmail(userDto.getGmail());
		user.setName(userDto.getName());
		user.setFamous(false);
		user.setBio("");
		if (!userDto.getRole().equals("ADMIN")) {
		user.setRole("USER");} else {user.setRole("ADMIN");};
		user.setBirthday(userDto.getBirthday());
		userDaoImpl.insert(user);
	}

	@Override
	public void update(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setUserName(userDto.getUserName());
		user.setPassWord(userDto.getPassWord());
		user.setAvatar(userDto.getAvatar());
		user.setGmail(userDto.getGmail());
		user.setName(userDto.getName());
		user.setFamous(userDto.isFamous());
		user.setBio(userDto.getBio());
		user.setRole(userDto.getRole());
		user.setBirthday(userDto.getBirthday());
		userDaoImpl.update(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDaoImpl.delete(id);
		
	}


	@Override
	public List<UserDto> SearchPeopleByName(String name, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<User> users = userDaoImpl.SearchPeopleByName(name,currentPage,size).getContent();
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	
	}


	@Override
	public void updateProfileByIddd(int userId, String avatar, String userName, String name, String bio) {
		// TODO Auto-generated method stub
		User user = userDaoImpl.SearchById(userId);
		user.setAvatar(avatar);
		user.setUserName(userName);
		user.setName(name);
		user.setBio(bio);
		userDaoImpl.update(user);
	}


	@Override
	public List<UserDto> searchAllByKeyword(String keyword) {
		List<User> users = userDaoImpl.searchAllByKeyword(keyword);
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	}


	@Override
	public List<UserDto> searchPageByKeyword(String keyword, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<User> users = userDaoImpl.searchPageByKeyword(keyword,currentPage,size).getContent();
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	}


	@Override
	public long countAll() {
		// TODO Auto-generated method stub
		return userDaoImpl.countAll();
	}


	@Override
	public List<UserDto> searchPageatAdmin(String keyword, int currentPage, int size) {
		List<User> users = userDaoImpl.searchPageatAdmin(keyword,currentPage,size).getContent();
		if (users!=null) {
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user: users) {
				UserDto userDto = new UserDto();
				userDto.setId(user.getId());
				userDto.setUserName(user.getUserName());
				userDto.setPassWord(user.getPassWord());
				userDto.setAvatar(user.getAvatar());
				userDto.setGmail(user.getGmail());
				userDto.setName(user.getName());
				userDto.setFamous(user.getFamous());
				userDto.setBio(user.getBio());
				userDto.setRole(user.getRole());
				userDto.setBirthday(user.getBirthday());
				userDtos.add(userDto);
			}
			return userDtos;
		} else return null;
	}


	@Override
	public void updateUserDetails(String username, Boolean famous, String role) {
		// TODO Auto-generated method stub
			 userDaoImpl.updateUserDetails(username, famous, role);
	}

}
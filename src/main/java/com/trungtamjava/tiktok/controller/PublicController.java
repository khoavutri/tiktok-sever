package com.trungtamjava.tiktok.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.tiktok.dao.LikeDao;
import com.trungtamjava.tiktok.dao.VideoDao;
import com.trungtamjava.tiktok.model.BaiViet;
import com.trungtamjava.tiktok.model.LikeDto;
import com.trungtamjava.tiktok.model.Profile;
import com.trungtamjava.tiktok.model.UserDto;
import com.trungtamjava.tiktok.model.VideoDto;
import com.trungtamjava.tiktok.model.responeDto;
import com.trungtamjava.tiktok.service.impl.CommentServiceImpl;
import com.trungtamjava.tiktok.service.impl.FolowerServiceImpl;
import com.trungtamjava.tiktok.service.impl.JwtTokenService;
import com.trungtamjava.tiktok.service.impl.LikeServiceImpl;
import com.trungtamjava.tiktok.service.impl.SendMail;
import com.trungtamjava.tiktok.service.impl.UserServiceImpl;
import com.trungtamjava.tiktok.service.impl.VideoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
	@Autowired
	SendMail sendMail;
	@Autowired
	UserServiceImpl userserviceImpl;
	@Autowired
	VideoServiceImpl videoServiceImpl;
	@Autowired
	LikeServiceImpl likeServiceImpl;
	@Autowired
	CommentServiceImpl commentServiceImpl;
	@Autowired
	FolowerServiceImpl folowerServiceImpl;
	
	
	@Autowired
	LikeDao likeDao;
	@Autowired
	VideoDao videoDao;
	@GetMapping("/test")
	public String test() {
	      
		return "this is my test";
	}

	@PostMapping("/SignUp")
	public responeDto<UserDto> signUp(@RequestParam("userName") String userName,
			@RequestParam("passWord") String passWord,
			@RequestParam("gmail") String gmail,
			@RequestParam("name") String name,
			@RequestParam("birthDay") String birthday) throws ParseException{
		String dateString = birthday;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
            Date date = dateFormat.parse(dateString);
            UserDto userDto = new UserDto();
    		userDto.setId(0);
    		userDto.setAvatar(null);
    		userDto.setFamous(false);
    		userDto.setRole("USER");
    		userDto.setBio(null);
    		userDto.setUserName(userName);
    		userDto.setPassWord(passWord);
    		userDto.setGmail(gmail);
    		userDto.setName(name);
    		userDto.setBirthday(date);
       
		if (userserviceImpl.SearchByUserName(userDto.getUserName())!=null) {
			return responeDto.<UserDto>builder().status(400).msg("userName existed").build();
		}
		if (userserviceImpl.SearchByGmail(userDto.getGmail())!=null) {
			return responeDto.<UserDto>builder().status(400).msg("gmail existed").build();
		}
		userserviceImpl.insert(userDto);
		return responeDto.<UserDto>builder().status(201).msg("created").data(userDto).build();
	}
	@PostMapping("/Login")
	public responeDto<UserDto>Login(@RequestParam("userName")String userName,
			@RequestParam("passWord")String passWord){
		UserDto a = userserviceImpl.SearchByUserName(userName);
		
		if (a==null) {return responeDto.<UserDto>builder().msg("not found").status(401).build();}
	else {
		boolean b=false;
		if (new BCryptPasswordEncoder().matches(passWord, a.getPassWord())) {
			b=true;
		}
		if (b) 
			return responeDto.<UserDto>builder().msg("success").status(200).data(a).build();
		else 
			return responeDto.<UserDto>builder().msg("wrong password").status(401).build();
		} 
	}
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenService jwtTokenService;
	@PostMapping("/AutoLogin")
	public responeDto<String> autologin(@RequestParam("username") String username,
			@RequestParam("password") String password){
		try {
			authenticationManager.authenticate(new 
					UsernamePasswordAuthenticationToken(username, password));
			
			
			return responeDto.<String>builder().status(200)
					.msg("success").data(jwtTokenService.creatToken(username)).build();
		} catch (Exception e) {
			// TODO: handle exception
		
			
		if (userserviceImpl.SearchByUserName(username)!=null) 
			return responeDto.<String>builder().msg("wrong password").status(401).build();
		else
			return responeDto.<String>builder().msg("not found").status(401).build();}
	}	
	
	@PostMapping("/AuthenticatioCode")
	public responeDto<String> AuthenticationCode(@RequestParam("gmail") String gmail) {
		String s = "";
		if (userserviceImpl.SearchByGmail(gmail)==null) {
			return responeDto.<String>builder().status(404).msg("not found gmail").build();
		}
		for (int i=0;i<6;i++) {
		Random random = new Random();
	    int randomNumber = random.nextInt(10);
	    s+=randomNumber;
	    }
		try {
			sendMail.sendMail(gmail, "Tiktok UI","Mã Xác thực của em là: "+s);
			return responeDto.<String>builder().msg("success").status(200).data(s).build();
		} catch (Exception e) {
			return responeDto.<String>builder().msg("failed").status(500).data("unable to send").build();
		}
		
	}
	@PostMapping("/SearchUserByGmail")
	public responeDto<UserDto> SearchUserByGmail(@RequestParam("gmail") String gmail){
		if (userserviceImpl.SearchByGmail(gmail)!=null) {
			return responeDto.<UserDto>builder().msg("success").status(200)
					.data(userserviceImpl.SearchByGmail(gmail)).build();
		} else {
			return responeDto.<UserDto>builder().msg("not found").status(404).build();
		}
	}
	@PutMapping("/UpdatePassWordByGmail")
	public responeDto<String>UpdatePassWordByGmail(@RequestParam("gmail") String gmail,
					@RequestParam("password") String password){
		try {
			UserDto userDto = userserviceImpl.SearchByGmail(gmail);
			userDto.setPassWord(password);
			userserviceImpl.update(userDto);
			return responeDto.<String>builder().status(200).msg("success").data(password).build();
		} catch (Exception e) {
			return responeDto.<String>builder().status(404).msg("failed").build();
		}
	}
	@GetMapping("/ResultFormLogin")
	public responeDto<List<UserDto>>ResultFormLogin(@RequestParam("name") String name){
		List<UserDto> lists = userserviceImpl.SearchPeopleByName(name, 0, 5);
		return responeDto.<List<UserDto>>builder().msg("Success").status(200).data(lists).build();
	}

	@GetMapping("/SelectImgPostAll")
	public responeDto<List<BaiViet>> SelectImgPostAll(@RequestParam("size") int size,
			@RequestParam("userId") int userId){
		try {
			List<BaiViet>baiViets =  new ArrayList<BaiViet>();
			List<VideoDto> videos = videoServiceImpl.SearchAllPage(0, size);

			for (VideoDto videoDto:videos) {
				BaiViet baiViet = new BaiViet();
				baiViet.setVideo(videoDto);
				UserDto user = userserviceImpl.SearchById(videoDto.getUserId());
				baiViet.setUser(user);
				baiViet.setLikes(likeServiceImpl.countByVideo(videoDto));
				baiViet.setCmts(commentServiceImpl.countByVideo(videoDto));
				if (userId>0) {
					if (folowerServiceImpl.kiemtra(userserviceImpl.SearchById(userId),user)!=null) {
						baiViet.setTrangthai(3);
					} else if (userId==user.getId()) {
						baiViet.setTrangthai(1);
					} else baiViet.setTrangthai(2);
					
				} else baiViet.setTrangthai(0);
				if (userId>0) {
					if (likeServiceImpl.selectByUserIdAndVideoId(userId, videoDto.getId())!=null) {
						baiViet.setDaLike(2);
					} else {
						baiViet.setDaLike(1);
					}
				} else {
					baiViet.setDaLike(0);
				}
				baiViets.add(baiViet);
			}
			
			
			return responeDto.<List<BaiViet>>builder().status(200)
					.msg("success").data(baiViets).build();
		} catch (Exception e) {
			// TODO: handle exception
			return responeDto.<List<BaiViet>>builder().status(400)
					.msg("bad request").build();
		}
	}
	@GetMapping("/SellectUserById")
	public responeDto<UserDto>SellectUserById(@RequestParam("id")int id){
		try {
			UserDto userDto = userserviceImpl.SearchById(id);
			return responeDto.<UserDto>builder().status(200).data(userDto).msg("success").build();
		} catch (Exception e) {
			// TODO: handle exception
			return responeDto.<UserDto>builder().status(404).msg("not found").build();
		}
	}
	@PostMapping("/SearchProfile")
	public responeDto<Profile>SearchProfile(@RequestParam("userId") int userId
			,@RequestParam("userName") String userName){
		try {
			Profile profile = new Profile();
			UserDto userDto = userserviceImpl.SearchByUserName(userName);
			profile.setUser(userDto);
			List<VideoDto>videoDtos = videoServiceImpl.SearchListByUser(userDto);
			profile.setVideos(videoDtos);
			int follower = folowerServiceImpl.folower(userDto);
			int dangFollow = folowerServiceImpl.dangFolow(userDto);
			profile.setDangFollow(dangFollow);
			profile.setFollower(follower);
			if (userId==0) profile.setTrangthai(0); else 
				if (userId == userDto.getId()) profile.setTrangthai(1); else {
				if (folowerServiceImpl.kiemtra(userserviceImpl.SearchById(userId), userDto)!=null) {
					profile.setTrangthai(3);
				} else profile.setTrangthai(2);
				}
			return responeDto.<Profile>builder().status(200).msg("success").data(profile).build();
		} catch (Exception e) {
			// TODO: handle exception
			return responeDto.<Profile>builder().status(400).msg("bad request").build();
		}
	}
	/*user;video;likes;cmts;trangthai;daLike;*/
	@GetMapping("/SelectRandomNotLogin")
	public responeDto<List<BaiViet>>SelectRandomNotLogin(@RequestParam("userId") int userId){
		try {
			List<BaiViet>baiViets = new ArrayList<BaiViet>();
			List<UserDto>users = userserviceImpl.SearchPeopleByName("",0 , 50);
			for (UserDto user:users) {
				List<VideoDto> videoDto = videoServiceImpl.SearchPageByUser(user, 0, 1);
				if (user.getId()!=userId && videoDto.size()>0) {
				BaiViet baiViet = new BaiViet();
				baiViet.setUser(user);
				baiViet.setVideo(videoDto.get(0));
				if (userId>0) {
					if (folowerServiceImpl.kiemtra(userserviceImpl.SearchById(userId),user)!=null) {
						baiViet.setTrangthai(3);
					} else if (userId==user.getId()) {
						baiViet.setTrangthai(1);
					} else {baiViet.setTrangthai(2);};	
				} else {baiViet.setTrangthai(0);};
				baiViets.add(baiViet);
				}
			}
			return responeDto.<List<BaiViet>>builder().status(200).msg("OK").data(baiViets).build();
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
			return responeDto.<List<BaiViet>>builder().status(400).msg("Bad request").build();
		}
	}
}
package com.trungtamjava.tiktok.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import com.trungtamjava.tiktok.entity.Likee;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;
//user video
public interface LikeDao extends JpaRepository<Likee, Integer> {
	List<Likee>findByUser(User user);
	Page<Likee>findByUser(User user,Pageable pageable);
	List<Likee>findByVideo(Video video);
	Page<Likee>findByVideo(Video video,Pageable pageable);
	List<Likee>findByDate(Date date);
	Page<Likee>findByDate(Date date,Pageable pageable);
	Page<Likee>findAll(Pageable pageable);
	int countByVideo(Optional<Video> optional);
	int countByUser(Optional<User> optional);
	Likee findByUserAndVideo(User user,Video video);
}

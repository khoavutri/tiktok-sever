package com.trungtamjava.tiktok.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import com.trungtamjava.tiktok.entity.User;
import com.trungtamjava.tiktok.entity.Video;

public interface VideoDao extends JpaRepository<Video, Integer> {
	List<Video> findByUser(User user);
	Page<Video> findByUser(User user,Pageable pageable);
	Page<Video> findByMota(String mota,Pageable pageable);
	Page<Video> findAll(Pageable pageable);
}

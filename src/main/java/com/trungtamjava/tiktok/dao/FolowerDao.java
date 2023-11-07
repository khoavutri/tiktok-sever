package com.trungtamjava.tiktok.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import com.trungtamjava.tiktok.entity.Folower;
import com.trungtamjava.tiktok.entity.User;

public interface FolowerDao extends JpaRepository<Folower, Integer>{
	List<Folower>findByNguoiFolow(User user);
	List<Folower>findByDuocFolow(User user);
	Page<Folower>findByNguoiFolow(User user,Pageable pageable);
	Page<Folower>findByDuocFolow(User user,Pageable pageable);
	int countByNguoiFolow(Optional<User> optional);
	int countByDuocFolow(Optional<User> optional);
	Page<Folower>findAll(Pageable pageable);
	
	@Query("SELECT f FROM Folower f WHERE f.nguoiFolow = :user1 AND f.duocFolow = :user2")
	Folower findFollower(@Param("user1") User user1, @Param("user2") User user2);
}

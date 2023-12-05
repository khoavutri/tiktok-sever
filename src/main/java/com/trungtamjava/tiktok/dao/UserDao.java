package com.trungtamjava.tiktok.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trungtamjava.tiktok.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.userName LIKE :s AND u.passWord LIKE :x")
	User searchByUserNameAndPassWord(@Param("s") String username,@Param("x")String password);
	
	User findByUserName(String userName);
	User findByGmail(String gmail);
	List<User> findByName(String name);
	List<User> findByFamous(Boolean famous);
	
	@Query("SELECT u FROM User u WHERE DAY(u.birthday) = DAY(:targetDate) AND MONTH(u.birthday) = MONTH(:targetDate)")
	List<User> findUsersByBirthday(@Param("targetDate") Date targetDate);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE :x")
	Page<User>searchPeopletByName(@Param("x") String s,Pageable pageable);
	
	 @Query("SELECT u FROM User u WHERE u.userName LIKE :keyword OR u.name LIKE :keyword OR u.bio LIKE :keyword")
	 List<User> searchAllByKeyword(@Param("keyword") String keyword);
	 @Query("SELECT u FROM User u WHERE u.userName LIKE :keyword OR u.name LIKE :keyword OR u.bio LIKE :keyword")
	 Page<User> searchPageByKeyword(@Param("keyword") String keyword,Pageable pageable);
	 
	 @Query("SELECT u FROM User u WHERE " +
		        "u.userName LIKE CONCAT('%', :keyword, '%') OR " +
		        "u.name LIKE CONCAT('%', :keyword, '%') OR " +
		        "CAST(u.id AS string) LIKE CONCAT('%', :keyword, '%') " +
		        "ORDER BY u.id")
		Page<User> searchPageAtAdmin(@Param("keyword") String keyword, Pageable pageable);


}

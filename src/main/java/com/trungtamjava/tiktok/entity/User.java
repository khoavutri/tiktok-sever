package com.trungtamjava.tiktok.entity;
import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String userName;
	
	private String passWord;
	

	private String avatar;
	
	private String gmail;
	
	private String name;
	
	private Boolean famous;
	
	private String bio;
	
	private String role;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = "Asia/Ho_chi_minh")
	private Date birthday;
	
	@OneToMany(mappedBy = "nguoiFolow",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Folower> folowing;
	
	@OneToMany(mappedBy = "duocFolow",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Folower> folower;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Video> videos;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Likee> likes;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Notifii> notifiis;


}
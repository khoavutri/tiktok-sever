package com.trungtamjava.tiktok.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String link;
	private String mota;
	private int view;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = "Asia/Ho_chi_minh")
	@CreatedDate
	private Date date;
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "video",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Comment> comments;
	@OneToMany(mappedBy = "video",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Likee> likes;
}
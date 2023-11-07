package com.trungtamjava.tiktok.entity;

import java.util.Date;


import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@LastModifiedDate
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy",timezone = "Asia/Ho_chi_minh")
	private Date date;
	private String content;
	@ManyToOne
	private Video video;
	@ManyToOne
	private User user;
}
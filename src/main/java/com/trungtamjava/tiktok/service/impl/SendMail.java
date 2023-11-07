package com.trungtamjava.tiktok.service.impl;


import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class SendMail {
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendMail(String to,String subject,String body) {
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,StandardCharsets.UTF_8.name());
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			//helper.setFrom("khoatest357@gmail.com");
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
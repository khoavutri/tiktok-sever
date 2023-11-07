package com.trungtamjava.tiktok.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.tiktok.model.responeDto;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/test")
	public responeDto<String> test() {
		return responeDto.<String>builder().status(200).msg("hay").data("sai ngu").build();
	}
}

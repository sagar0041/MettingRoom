package com.meetingroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.meetingroom.service.UserService;

public class TLController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/api/tl/all")
	public ResponseEntity<?> findAllUser() {
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
}

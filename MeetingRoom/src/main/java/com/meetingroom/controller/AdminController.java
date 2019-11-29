package com.meetingroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.service.UserService;

@RestController
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/api/admin/all")
	public ResponseEntity<?> findAllUser(){
		return ResponseEntity.ok(userService.findAllUsers());
	}

}

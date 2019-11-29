package com.meetingroom.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.meetingroom.jwt.JwtTokenProvider;
import com.meetingroom.model.Role;
import com.meetingroom.model.User;
import com.meetingroom.service.UserService;

@RestController
public class UserController {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/user/registration")
	public ResponseEntity<?> register(@RequestBody User user) {
		if (userService.findByEmail(user.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		user.setRole(Role.USER);
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/api/user/login")
	public ResponseEntity<?> login(Principal principal) {
		if (principal == null) {
			//This should be ok http status because this will be used for logout path.
			return ResponseEntity.ok(principal);
		}
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
		User user  = userService.findByEmail(authenticationToken.getName());
		user.setToken(jwtTokenProvider.generateToken(authenticationToken));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}

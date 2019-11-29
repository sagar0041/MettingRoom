package com.meetingroom.service;

import java.util.List;

import com.meetingroom.model.User;

public interface UserService {

	 User saveUser(User user);

	 User findByEmail(String email);

	 List<User> findAllUsers();

}

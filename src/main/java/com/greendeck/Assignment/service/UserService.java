package com.greendeck.Assignment.service;

import com.greendeck.Assignment.entities.User;

public interface UserService {

	public String createUser(User user);
	public boolean userExist(User user);
	public User getUser(String username);
}

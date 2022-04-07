package com.greendeck.Assignment.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.greendeck.Assignment.entities.User;

@Service
public class UserServiceImplementation implements UserService {

//	mapping will be username-->User
	HashMap<String, User> users = new HashMap<String, User>();

	
	
	
	public UserServiceImplementation() {
		super();
		// just inserting a dummy user to showcase how things will work
		users.put("dummy", new User("dummy", "dummy@user.com", "dummy", "user", "password"));
	}

	@Override
	public User getUser(String username) {
		return users.get(username);
	}

	@Override
	public String createUser(User user) {
		// We will create user only if the email and username are distinct
		if(users.get(user.getUsername()) != null)	return "Username already taken";
		
		for (Map.Entry<String, User> pair : users.entrySet()) {
			if(pair.getValue().getEmail().equals(user.getEmail()))
				return "Email ID is already taken";
		}
		users.put(
				user.getUsername(), 
				new User(
						user.getUsername(),
						user.getEmail(),
						user.getFirstName(), 
						user.getLastName(), 
						user.getPassword()
				)
		);
		return "User Profile Created...\n\tThanks for joining\n";
	}
	
	@Override
	public boolean userExist(User user) {
		User u = users.get(user.getUsername());
		return (u != null);
	}


}

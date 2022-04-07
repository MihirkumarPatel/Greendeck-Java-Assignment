package com.greendeck.Assignment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greendeck.Assignment.entities.User;
import com.greendeck.Assignment.service.UserService;

@RestController
public class Controller {

	@Autowired
	UserService userService;
	User currentUser;
	
	@GetMapping("/home")
	public String home() {
		return "This is home";
	}
	
	// we get the current user info
	@GetMapping("/user")
	public User user() {
		System.out.println("user here");
		return currentUser;
	}
	
	// To register a new user
	@PostMapping(path="/register")
	public String registerUser(@RequestBody User user) {
		return this.userService.createUser(user);
	}
	
	// To login (once registration is done)
	@PostMapping(path="/login")
	public String loginUser(@RequestBody User user) {
		
		currentUser = userService.getUser(user.getUsername());
		if(currentUser == null) { // Incorrect username...
			return "login failure";
		}else if(!currentUser.getPassword().equals(user.getPassword())) {			
			currentUser = null;
			return "Incorrect Password";
		}
		System.out.println(currentUser);
		return "logged in successfully";
	}
	
	// Get all images posted by current user
	@GetMapping(path="/images")
	public List<String> getImagesOfCurrentUser(){
		if(currentUser != null)	return currentUser.getImages();
		List<String> list = new ArrayList<>();
		list.add("Please Login First");
		return list;
	}
	
	//Post a new image on the current user's timeline
	@PostMapping(path="/images")
	public String postImage(@RequestBody String image) {
		//this.userService.addImage(imageName);
		if(currentUser == null)return "Please Login first";
		currentUser.addImage(image);
		return "success";
	}
	
	// show a list of followers of current user
	@GetMapping("/followers")
	public List<String> getFollowers() {
		if(this.currentUser == null)	return null;
		return this.currentUser.getFollowers();
	}
	
	// show a lsit of users (current User) follows
	@GetMapping("/following")
	public List<String> getFollowing() {
		return this.currentUser.getFollowing();
	}
	
	// The below API shows a list of users whom People I know follows
	// In other words this gives a list of people followed by the users whom I follow
	// 2nd degree connections...
	@GetMapping("/suggestions")
	public List<String> suggestions(){
		if(this.currentUser == null)	return null;
		List<String> list = new ArrayList<>();
		for(String username : this.currentUser.getFollowing()) {
			for(String name: this.userService.getUser(username).getFollowing()) {
				list.add(name);
			}
		}
		list = list.stream().distinct().collect(Collectors.toList());
		for(String name : currentUser.getFollowing())	list.remove(name);
		return list;
	}
	
	// Include user with the given username to my following list
	@PostMapping("/follow/{username}")
	public void follow(@PathVariable String username) {
		if(this.currentUser == null)	return;
		this.currentUser.follow(username);
	}
	
	// Remove user with the given username to my following list
	@PostMapping("/unfollow/{username}")
	public void unfollow(@PathVariable String username) {
		if(this.currentUser == null)	return;
		this.currentUser.unfollow(username);
	}
	
	// logout 
	@GetMapping("/logout")
	public String logout() {
		currentUser = null;
		return "logged out successfully";
	}
}

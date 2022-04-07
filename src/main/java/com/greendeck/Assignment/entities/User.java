package com.greendeck.Assignment.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private List<String> followers; // list of usernames that follows me
	private List<String> following; // list of usernames that I follow
	private List<String> images; // list of images posted by me

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String email, String firstName, String lastName, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.images = new ArrayList<>();
		this.followers = new ArrayList<>();
		this.following = new ArrayList<>();
	}

	public User(String username, String firstName, String lastName, List<String> followers, List<String> following,
			List<String> photos) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.followers = followers;
		this.following = following;
		this.images = photos;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

	public List<String> getFollowing() {
		return following;
	}

	public void setFollowing(List<String> following) {
		this.following = following;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", followers=" + followers + ", following=" + following + ", photos=" + photos + "]";
//	}

	public void addImage(String photo) {
		this.images.add(photo);
	}

	public void removeImage(String photo) {
		this.images.remove(photo);
	}

	// 2 methods to follow
	public void follow(User user) {
		this.following.add(user.username);
	}
	
	public void follow(String username) {
		this.following.add(username);
	}

	// 2 methods to unfollow
	public void unfollow(User user) {
		this.following.remove(user.username);
	}
	
	public void unfollow(String username) {
		this.following.remove(username);
	}

	public void removeFollower(User user) {
		this.followers.remove(user.username);
	}
}

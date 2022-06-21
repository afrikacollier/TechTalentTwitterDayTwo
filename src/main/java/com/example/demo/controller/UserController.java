package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Tweet;
import com.example.demo.models.UserProfile;
import com.example.demo.service.TweetService;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TweetService tweetService;
	
	//We are going to have an endpoint called /users
	//and /users/{username} will show us the profile of a user
	@GetMapping(value = "/users/{username}")
	public String getUser(@PathVariable(value="username") String username, Model model) {
		UserProfile user = userService.findByUsername(username);
		List<Tweet> tweets = tweetService.findAllByUser(user);
		model.addAttribute("tweetList", tweets);
		model.addAttribute("user", user);
		return "user";
		
	}
}

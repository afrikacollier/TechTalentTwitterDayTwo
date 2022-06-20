package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Tweet;
import com.example.demo.models.UserProfile;
import com.example.demo.service.TweetService;
import com.example.demo.service.UserService;

@Controller
public class TweetController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TweetService tweetService;
	
	@GetMapping(value= {"/tweets", "/"})
	public String getFeed(Model model) {
		List<Tweet> tweets = tweetService.findAll();
		model.addAttribute("tweetList", tweets);
		return "feed";
	}
	
	@GetMapping(value="/tweets/new")
	public String getTweet(Model model) {
		model.addAttribute("tweet", new Tweet());
		return "newTweet";
	}
	
	@PostMapping(value = "/tweets")
	public String submitTweetForm(@Valid Tweet tweet, BindingResult bindingResult, Model model) {
		UserProfile user=userService.getLoggedInUser();
		if (!bindingResult.hasErrors()) {
			tweet.setUser(user);
			tweetService.save(tweet);
			model.addAttribute("successMessage", "tweet successfully created");
			model.addAttribute("tweet", new Tweet());
		}
		return "newTweet";
	}
}

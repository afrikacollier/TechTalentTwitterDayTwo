package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Tweet;
import com.example.demo.models.UserProfile;
import com.example.demo.repository.TweetRepository;

@Service
public class TweetService {
	@Autowired
	private TweetRepository tweetRepository;
	
	public List<Tweet> findAll() {
		List<Tweet> tweets = tweetRepository.findAllByOrderByCreatedAtDesc();
		return tweets;
	}
	
	public List<Tweet> findAllByUser(UserProfile user) {
		List<Tweet> tweets = tweetRepository.findAllByUserOrderByCreatedAtDesc(user);
		return tweets;
	}
	
	public List<Tweet> findAll(List<UserProfile> users) {
		List<Tweet> tweets = tweetRepository.findAllByUserInOrderByCreatedAtDesc(users);
		return tweets;
	}
	
	public void save(Tweet tweet) {
		tweetRepository.save(tweet);
	}
}

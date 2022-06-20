package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Tweet;
import com.example.demo.models.UserProfile;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
	List<Tweet> findAllByOrderByCreatedAtDesc();
	List<Tweet> findAllByUserOrderByCreatedAtDesc(UserProfile user);
	List<Tweet> findAllByUserInOrderByCreatedAtDesc(List<UserProfile> users);
}

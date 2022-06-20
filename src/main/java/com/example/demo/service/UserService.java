package com.example.demo.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.UserProfile;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository,
					   RoleRepository roleRepository,
					   BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository=userRepository;
		this.roleRepository=roleRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	public UserProfile findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<UserProfile> findAll(){
		return userRepository.findAll();
	}
	
	public void save(UserProfile user) {
		userRepository.save(user);
	}
	//one way hash for password it's encrypted
	public UserProfile saveNewUser(UserProfile user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		UserProfile newUser=userRepository.save(user); //when you save generates an id
		return newUser;
	}
	
	public UserProfile getLoggedInUser() {
		String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		return findByUsername(loggedInUsername);
	}
}
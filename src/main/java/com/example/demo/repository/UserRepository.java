package com.example.demo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.UserProfile;

//import com.example.demo.models.UserProfile;
//We create an interface rather than a class because we aren't
//actually going to create the repository...
//SPRING BOOT is. However, we need to specify to Springboot
//details about how we want our repository created and what query methods
//might be used on it...so we create an interface for Spring Boot
//to scan and analyze in order to create the real implemented
//UserRepository.


//In order for this to be a repository, I have to inherit 
//from Repository or one of its subclasses.
//We never inherit from Repository directly, but generally inherit 
//from one of its subclasses, in this case we are going to inherit 
//from CrudRepository

//CrudRepository takes two Generic types, the first one
// is the type of Object that will be stored in the repository...
// the second one the type of Object that is the primary key for
// type #1

@Repository //helps you do an automatic injection 
public interface UserRepository extends CrudRepository<UserProfile, Long> {

	//Inheriting from CrudRepository gives us a lot of methods already
	//including .save, findById....etc...
	
	//But we want to specify that one of the queries we want to do is 
	//to look up a User by userName
	//The name of this method is parsed by SpringBoot and must 
	//conform to a specific pattern and Springboot figures out
	//how to turn it into a query.
	
	UserProfile findByUsername(String username);
	
	@Override //Springboot does conversion of iterable for us go back to service
	List<UserProfile> findAll();
}

package com.example.demo.models;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//We need to tell Spring Boot that we want to store this in a
//Database...so we add the @Entity tag which tells the 
//JPA (Java Persistence API) that this is a class we want to have
//a database for
@Data //getters and setters
@AllArgsConstructor //creates exactly what it says
@NoArgsConstructor 	//creates exactly what it says
@Entity
public class UserProfile {
	
	//We need to create a constructor -- you need a no argument
	//constructor in order for Entities to work.
	
	//We actually need an empty constructor and one that sets up 
	//everything.
	
	//We need to create getters and setters.
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	//columns
	
	@Email(message="Please provide a valid email")
	@NotEmpty(message = "Please provide an email")
		private String email;
	
	@Length(min = 3, message = "Your username must have at least 3 characters")
	@Length(max = 15, message = "Your username cannot have more than 15 characters")
	@Pattern(regexp="[^\\s]+", message="Your username cannot contain spaces")
	private String username;
	
	@Length(min = 5, message = "Your password must have at least 5 characters")
	private String password;
	
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	
	private int active;
	
	@CreationTimestamp
	private Date createdAt;
	
	//every user has to have a role
	//in order to have access roles as a user
	//roles will be defined in a separate class
	//Set (Collections): list of items that are unordered
	//you can't have two of the same item in the list
	
	//We cannot store a collection of things in 
	//a database column.
	
	//Instead roles will be associated with User through
	//a database relationship...
	
	//We are going to make this relationship be a many to many
	//relationship between roles and users..
	
	//IE a user can have many roles
	// and each role can have many users associated.
	//In order to have a many to many relationship in a database
	//we create a separate table that lists what roles and users
	//are associated with each other.
	//Table: user_role
	//Columns: user_id, role_id
	//			1		1		#user_id 1 has role_id 1
	//it knows it's inside user so it will create a mapping to user_id's 
	//and roles
	//javax.persistence is a newer API than hibernate.annotations
	//have a choice choose javax.persistence
	//if a user or role disappears any entry that has that will delete (I'm guessing
	//that particular part of the mapping)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;
}
package com.spring.rest.webservice.restwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.webservice.restwebservices.user.Post;
import com.spring.rest.webservice.restwebservices.user.User;
import com.spring.rest.webservice.restwebservices.user.UserService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable Integer id) {
		
		EntityModel<User> user = EntityModel.of(userService.getUserById(id));
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllUser());
		user.add(linkBuilder.withRel("all_user"));
		return user;
		
	}
	
	
	@GetMapping("/users/{id}/posts")
	public List<Post> getPostByUser(@PathVariable Integer id){
		User user = userService.getUserById(id);
		return user.getPosts();
	}

	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}

package com.mvc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mvc.dto.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping
	public User create(@Valid @RequestBody User user,BindingResult errors){
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error -> System.out.println(error));
		}
		user.setId(1);
		System.out.println(user);
		return user;
	}

	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(@RequestParam String username){
		System.out.println(username);
		List<User> user = new ArrayList<User>();
		user.add(new User());
		user.add(new User());
		user.add(new User());
		return user;
	}
	
	//正则表达式只能接收数字
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getUserInfo(@PathVariable("id") String id){
		User user = new User();
		user.setUsername("tom");
		return user;
	}
}

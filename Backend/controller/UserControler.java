package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.LoginDto;
import com.library.dto.UserDto;
import com.library.model.UserModel;
import com.library.response.LoginResponse;
import com.library.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class UserControler {
	
	@Autowired
	UserService userService;
	
	@PostMapping("signup")
	public UserModel saveUser(@RequestBody UserModel user)
	{
		return userService.saveUser(user);
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginDto logindto)
	{
		LoginResponse response  = userService.userLogin(logindto);	
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/admin/login")
	public ResponseEntity<?> adminLogin(@RequestBody LoginDto logindto) {
		LoginResponse response = userService.adminLogin(logindto);
		return ResponseEntity.ok(response);
	}

}

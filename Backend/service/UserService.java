package com.library.service;

import java.util.List;
import com.library.dto.LoginDto;
import com.library.dto.UserDto;
import com.library.model.UserModel;
import com.library.response.LoginResponse;


public interface UserService {
	
	public UserModel saveUser(UserModel user);
	
	public LoginResponse userLogin(LoginDto logindto);
	
	public LoginResponse adminLogin(LoginDto logindto); 
}

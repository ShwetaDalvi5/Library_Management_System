package com.library.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.library.dto.LoginDto;
import com.library.model.UserModel;
import com.library.repository.UserRepository;
import com.library.response.LoginResponse;
import com.library.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
    public UserModel saveUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        } else if (user.getRole().equalsIgnoreCase("ADMIN")) {
            if (userRepository.existsByRole("ADMIN")) {
                throw new IllegalArgumentException("An admin already exists.");
            }
        }

        return userRepository.save(user);
    }

	private LoginResponse login(LoginDto logindto, String expectedRole) {
        UserModel user = userRepository.findByUsername(logindto.getUsername());
        if (user != null) {
            String userRole = user.getRole();
            if (userRole == null || userRole.isEmpty()) {
                userRole = "USER"; 
            }

            if (userRole.equalsIgnoreCase(expectedRole)) {
                String password = logindto.getPassword();
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return new LoginResponse("Login success", true);
                } else {
                    return new LoginResponse("Incorrect password", false);
                }
            } else {
                return new LoginResponse("Role mismatch", false);
            }
        } else {
            return new LoginResponse("Username not found", false);
        }
    }
	
	@Override
	public LoginResponse userLogin(LoginDto logindto) {
		return login(logindto, "USER");
	}


	@Override
	public LoginResponse adminLogin(LoginDto logindto) {
		return login(logindto, "ADMIN");
	}

}

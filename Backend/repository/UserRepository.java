package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.library.model.UserModel;


@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	public UserModel findByUsername(String username);
	
	public Optional<UserModel> findByUsernameAndPassword(String username, String password);
	
	boolean existsByRole(String role);
}

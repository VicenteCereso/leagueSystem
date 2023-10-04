package com.tech.sportsys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tech.sportsys.exception.ResourceNotFoundException;
import com.tech.sportsys.model.User;
import com.tech.sportsys.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void deleteUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser==null || optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("delete", "Id",id);
		}
		userRepository.deleteById(id);
	}
	
	public User updateUser(User user,Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser==null || optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("user", "Id",id);
		}
		optionalUser.get().setApellidos(user.getApellidos());
		optionalUser.get().setNombres(user.getNombres());
		optionalUser.get().setEmail(user.getEmail());
		return userRepository.save(optionalUser.get());
	}
	
}

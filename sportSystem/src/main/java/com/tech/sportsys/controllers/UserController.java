package com.tech.sportsys.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.sportsys.exception.BadRequestException;
import com.tech.sportsys.exception.ResourceNotFoundException;
import com.tech.sportsys.model.User;
import com.tech.sportsys.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("tech/users")
public class UserController {
	public record Greeting(long id, String content) { }
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> lstUser = userService.getAllUsers();
		if(lstUser==null || lstUser.isEmpty()) {
			throw new ResourceNotFoundException("users");
		}
		return ResponseEntity.ok(lstUser);
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<User> searchUserById(@PathVariable("id") Long id){
		Optional<User> user = null;
		try {
			user = userService.getUserById(id);
			if(user==null || user.isEmpty()) {
				throw new ResourceNotFoundException("user", "Id",id);
			}
		}catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		return ResponseEntity.ok(user.get());
	}
	
	@DeleteMapping("/delete")
	public void deleteUserById(@RequestBody @Valid User user){
		try {
			userService.deleteUser(user.getId());
		}catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody @Valid User user) {
		User updateEmployee = null;
		try {
			updateEmployee = userService.updateUser(user,id);
			if(updateEmployee==null) {
				throw new ResourceNotFoundException("edit", "Id",id);
			}
			
		}
		catch (DataAccessException e) {
			throw new BadRequestException(e.getMessage());
		}
		return ResponseEntity.ok(updateEmployee);
	}
}

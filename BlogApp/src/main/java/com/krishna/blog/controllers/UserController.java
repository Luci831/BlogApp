package com.krishna.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.blog.payloads.ApiResponse;
import com.krishna.blog.payloads.UserDto;
import com.krishna.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST- create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUser = this.userService.createUser(userDto);

		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	// PUT- update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		UserDto updatedUser = this.userService.updateUser(userDto, uid);

		return ResponseEntity.ok(updatedUser);
	}

	// GET- fetch user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer id) {
		UserDto userById = this.userService.getUserById(id);

		return ResponseEntity.ok(userById);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = this.userService.getAllUsers();

		return ResponseEntity.ok(users);
	}

	// DELETE- delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer userId) {

		this.userService.delete(userId);

		return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);

	}

}

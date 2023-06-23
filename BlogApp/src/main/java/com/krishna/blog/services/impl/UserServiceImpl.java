package com.krishna.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.blog.entities.User;
import com.krishna.blog.exceptions.ResourceNotFoundException;
import com.krishna.blog.payloads.UserDto;
import com.krishna.blog.repositories.UserRepo;
import com.krishna.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	//to convert obj of one type to another having same feilds
	//such as user->userDto
	//userDto->user

	@Override
	public UserDto createUser(UserDto dtoUser) {

		User user = this.dtoToUser(dtoUser);// conver dtoUser->User
		User savedUser = userRepo.save(user);

		return this.userToDtoUser(savedUser);
		// conver User->dtoUser
	}

	@Override
	public UserDto updateUser(UserDto dtoUser, Integer dtoUserId) {

		User user = this.userRepo.findById(dtoUserId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", dtoUserId));

		user.setName(dtoUser.getName());
		user.setPassword(dtoUser.getPassword());
		user.setEmail(dtoUser.getEmail());
		user.setAbout(dtoUser.getAbout());

		User updatedUser = this.userRepo.save(user);

		return this.userToDtoUser(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDtoUser(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

		List<UserDto> usersDto = users.stream().map(user -> userToDtoUser(user)).collect(Collectors.toList());

		return usersDto;
	}

	@Override
	public void delete(Integer userId) {

		User user=userRepo.findById(userId).
				orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto,User.class);
		//source->target conversion

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;

	}

	private UserDto userToDtoUser(User user) {

		UserDto userDto = modelMapper.map(user,UserDto.class);

//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());

		return userDto;

	}

}

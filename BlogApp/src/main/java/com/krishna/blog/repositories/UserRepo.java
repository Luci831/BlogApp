package com.krishna.blog.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.krishna.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	
	Optional<User> findByEmail(String email);
	// takes in class for which table is and type of Id used
    //provides all functionality for db operation
	
	
}

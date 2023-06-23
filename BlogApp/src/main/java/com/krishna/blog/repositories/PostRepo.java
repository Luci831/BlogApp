package com.krishna.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.krishna.blog.entities.Category;
import com.krishna.blog.entities.Post;
import com.krishna.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	//Searching 
	
	//Custom query
	//@Query("select p from Post p where p.title like :key ")
	//:key to pass dynamic value
	//List<Post> findByTitleContaining(@Param("key") String title);
	List<Post> findByTitleContaining(String title);
}

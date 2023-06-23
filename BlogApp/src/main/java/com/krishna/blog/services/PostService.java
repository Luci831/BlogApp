package com.krishna.blog.services;

import java.util.List;

import com.krishna.blog.entities.Post;
import com.krishna.blog.payloads.PostDto;
import com.krishna.blog.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//getById
	PostDto getPostById(Integer postId);
	
	//getAll
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	//delete
	void deletePost(Integer postId);
	
	//getallpost by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//getallposts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search among posts
	List<PostDto> searchPost(String keyword);
}

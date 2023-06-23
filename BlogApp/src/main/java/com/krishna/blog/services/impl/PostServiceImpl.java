package com.krishna.blog.services.impl;

import com.krishna.blog.entities.Category;
import com.krishna.blog.entities.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.krishna.blog.entities.Post;
import com.krishna.blog.exceptions.ResourceNotFoundException;
import com.krishna.blog.payloads.PostDto;
import com.krishna.blog.payloads.PostResponse;
import com.krishna.blog.repositories.CategoryRepo;
import com.krishna.blog.repositories.PostRepo;
import com.krishna.blog.repositories.UserRepo;
import com.krishna.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", categoryId));
		;
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date(0));
		post.setCategory(category);
		post.setUser(user);
		// as dto has only two props

		Post savedPost = postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post_id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		postRepo.save(post);

		return this.modelMapper.map(post, PostDto.class);

	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post findById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post_id", postId));

		return this.modelMapper.map(findById, PostDto.class);

	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		// passing pageable obj for sorting and pagination
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
		sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> allposts = pagePost.getContent();

		List<PostDto> posts = allposts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(posts);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post_id", postId));

		postRepo.delete(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category_Id", categoryId));

		List<Post> findByCategory = this.postRepo.findByCategory(category);

		List<PostDto> posts = findByCategory.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return posts;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User_id", userId));

		List<Post> findByUser = postRepo.findByUser(user);

		List<PostDto> posts = findByUser.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return posts;

	}

	@Override
	public List<PostDto> searchPost(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		     List<PostDto> postDto = posts.stream().map(post->modelMapper.map(post,PostDto.class))
		    		 .collect(Collectors.toList());
		return postDto;
	}

}

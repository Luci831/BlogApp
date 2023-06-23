package com.krishna.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import com.krishna.blog.config.AppConstant;
import com.krishna.blog.entities.Post;
import com.krishna.blog.payloads.ApiResponse;
import com.krishna.blog.payloads.PostDto;
import com.krishna.blog.payloads.PostResponse;
import com.krishna.blog.services.FileService;
import com.krishna.blog.services.PostService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	// get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}

	// get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
	}

	// getById
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getById(@PathVariable Integer postId) {
		PostDto postById = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	// getAll
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
		PostResponse posts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	// deletePost
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
		postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted succesfully", true), HttpStatus.OK);
	}

	// UpdatePost
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost = postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
	}

	// SearchByTitle
	@GetMapping("/posts/search/{key}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String key) {
		List<PostDto> searchPost = this.postService.searchPost(key);

		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);
	}

	// postImage upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto postById = this.postService.getPostById(postId);
		String uploadedImage = fileService.uploadImage(path, image);

		postById.setImageName(uploadedImage);

		PostDto updatedPost = postService.updatePost(postById, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// method to serve file
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {

		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}

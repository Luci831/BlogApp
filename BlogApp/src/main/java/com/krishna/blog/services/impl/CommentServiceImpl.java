package com.krishna.blog.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.blog.entities.Comment;
import com.krishna.blog.entities.Post;
import com.krishna.blog.exceptions.ResourceNotFoundException;
import com.krishna.blog.payloads.CommentDto;
import com.krishna.blog.repositories.CommentRepo;
import com.krishna.blog.repositories.PostRepo;
import com.krishna.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		Post post = postRepo.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("Post", "Post_Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
		
		
	}

	@Override
	public void deleteComment(Integer commentId) {

		 Comment comment = commentRepo.findById(commentId).orElseThrow(()->
		 new ResourceNotFoundException("Comment", "Comment_ID", commentId));
		 
		 commentRepo.delete(comment);
		 
	}

}

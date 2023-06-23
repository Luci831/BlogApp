package com.krishna.blog.services;

import com.krishna.blog.payloads.CommentDto;

public interface CommentService {
	
	//create Comment
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	//delete Comment
	
	void deleteComment(Integer commentId);
 
}

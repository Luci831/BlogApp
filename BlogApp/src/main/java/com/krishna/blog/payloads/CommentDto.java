package com.krishna.blog.payloads;

import com.krishna.blog.entities.Post;
import com.krishna.blog.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private int commentID;
	
	private String content;
	
}

package com.krishna.blog.payloads;

import java.sql.Date;

import com.krishna.blog.entities.Category;
import com.krishna.blog.entities.User;
import com.krishna.blog.entities.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	
	//dont need id as its paased
	private Integer id;
	
	private String title;
	
    private String content;
	
	private String imageName;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Date addedDate;
	
	private Set<CommentDto> comments=new HashSet<>();
	
	//cat id and user id we can fetch directly from url @PathVariable
}

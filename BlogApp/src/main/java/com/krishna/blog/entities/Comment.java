package com.krishna.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentID;
	
	private String content;
	
	@ManyToOne
	//generate foreign key in comment for user_id
	private Post post;
	
	
	
	
}

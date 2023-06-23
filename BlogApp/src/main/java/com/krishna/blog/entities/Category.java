package com.krishna.blog.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Setter
@Getter
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="Description")
	private String categoryDescription;
	
	@Column(name="Title")
	private String categoryTitle;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> posts=new ArrayList<>(); 
	
	//cascade means if parent is removed child is also removed
}

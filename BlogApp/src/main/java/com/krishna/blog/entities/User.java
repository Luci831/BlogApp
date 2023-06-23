package com.krishna.blog.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;
import java.util.stream.Collectors;



@Entity // table will be created with class name
@NoArgsConstructor
@Table(name = "users")
@Setter
@Getter // Lambok lib to get these via annotation
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // for auto increment of id values
	private int id;

	@Column(name = "user_name", nullable = false, length = 100)
	// for setting column properties in table
	private String name;

	private String email;

	private String password;

	private String about;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

	private List<Post> posts = new ArrayList<>();

	
}

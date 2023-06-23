package com.krishna.blog.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class CategoryDto {

	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=10)
	private String categoryDescription;
	
	@NotBlank
	@Size(min=4)
	private String categoryTitle;
}

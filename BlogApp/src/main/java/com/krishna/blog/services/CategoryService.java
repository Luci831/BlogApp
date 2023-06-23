package com.krishna.blog.services;

import java.util.List;

import com.krishna.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	
	void deleteCategory(Integer categoryId);
	
	//getByID
	CategoryDto getById(Integer categoryId);
	
	//getAll
	
	List<CategoryDto> getCategories();
}

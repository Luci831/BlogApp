package com.krishna.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.blog.payloads.ApiResponse;
import com.krishna.blog.payloads.CategoryDto;
import com.krishna.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//GetById
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getById(@PathVariable Integer categoryId)
	{
		CategoryDto cat = this.categoryService.getById(categoryId);
		
		return ResponseEntity.ok(cat);
	}
	//GetAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		 List<CategoryDto> categories = this.categoryService.getCategories();
		 
		 return ResponseEntity.ok(categories);
	}
	
	//DeleteById
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
		
	}
	 
	//Update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateById(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId)
	{
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<>(updateCategory,HttpStatus.CREATED);
	}
	
	//Post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}

}

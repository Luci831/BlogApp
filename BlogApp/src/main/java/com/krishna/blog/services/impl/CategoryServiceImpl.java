package com.krishna.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.blog.entities.Category;
import com.krishna.blog.exceptions.ResourceNotFoundException;
import com.krishna.blog.payloads.CategoryDto;
import com.krishna.blog.repositories.CategoryRepo;
import com.krishna.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category category = this.categoryDtoToCategory(categoryDto);

		Category savedCategory = this.categoryRepo.save(category);

		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
		
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCategory = this.categoryRepo.save(category);
		
		return this.categoryToCategoryDto(updatedCategory);
		
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getById(Integer categoryId) {

		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		
		return this.categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories=categoryRepo.findAll();
		
		List<CategoryDto> list = categories.stream()
          .map(category->categoryToCategoryDto(category))
          .collect(Collectors.toList());
		
		return list;
	}

	private Category categoryDtoToCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);

		return category;
	}

	private CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

		return categoryDto;
	}

}

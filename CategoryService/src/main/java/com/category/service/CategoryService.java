package com.category.service;

import java.util.List;

import com.category.dto.CategoryDTO;
import com.category.entity.Category;
import com.category.exception.CategoryNotFoundException;

public interface CategoryService {
	
	
	public CategoryDTO addCategory(Category category);
	
	public List<CategoryDTO> findAllCategories() throws CategoryNotFoundException;
	
	public String removeCategory(int categoryId)throws CategoryNotFoundException;
	
	public String updateCategory(int categoryId,CategoryDTO categoryDTO);
	
	public CategoryDTO seachCategoryByName(String name)throws CategoryNotFoundException ;
	
	public CategoryDTO searchCategoryById(int id) throws CategoryNotFoundException ;
	

}


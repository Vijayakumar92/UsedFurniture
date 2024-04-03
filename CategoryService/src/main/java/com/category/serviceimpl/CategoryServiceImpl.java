package com.category.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.category.dto.CategoryDTO;
import com.category.entity.Category;
import com.category.exception.CategoryNotFoundException;
import com.category.repository.CategoryRepository;
import com.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO addCategory(Category category) {
		categoryRepository.save(category);
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		
		return categoryDTO;

	}

	public String updateCategory(int categoryId, CategoryDTO categoryDTO) {
		try {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
		category.setCategoryName(categoryDTO.getCategoryName());

		categoryRepository.save(category);
		
		}
		catch(CategoryNotFoundException e)
		{
			System.out.println(e);
			return "Category not updated";
		}
		return "Updated Successfully";

	}

	public String removeCategory(int categoryId) throws CategoryNotFoundException {
		
		Category category = categoryRepository.findById(categoryId).get();
		
		if(category == null) {
			throw new CategoryNotFoundException("Category does not exist with the  given Id");
		}

		categoryRepository.delete(category);
		return "Deleted Successfully";
	}

	public CategoryDTO seachCategoryByName(String name) throws CategoryNotFoundException {

		Category category = categoryRepository.findByCategoryName(name);
		if(category == null) {
			throw new CategoryNotFoundException("Category Not Found by Name");
		}

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;
	}

	public CategoryDTO searchCategoryById(int id) throws CategoryNotFoundException {
		Category category = categoryRepository.findById(id).get();
		if(category == null) {
			throw new CategoryNotFoundException("Category Not Found by Id");
		}

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;

	}
	@Override
	public List<CategoryDTO> findAllCategories() throws CategoryNotFoundException {
		
		List<Category> categories = categoryRepository.findAll();
		
		if(categories.isEmpty()) {
			throw new CategoryNotFoundException("There are no categories");
		}

		List<CategoryDTO> categoryDTOs=new ArrayList<CategoryDTO>();
		for(Category category:categories)
		{
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTOs.add(categoryDTO);
		
		}

		return categoryDTOs;

	}
}

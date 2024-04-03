package com.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.category.dto.CategoryDTO;
import com.category.entity.Category;
import com.category.exception.CategoryNotFoundException;
import com.category.serviceimpl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*") // Frontend Connection
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@PostMapping("/add")
	public CategoryDTO addNewProduct(@RequestBody Category category) {

		return categoryServiceImpl.addCategory(category);
	}

	@PutMapping("/update/{category_id}")
	public String updateCategory(@PathVariable(value = "category_id") int id, @RequestBody CategoryDTO categoryDTO) {
		categoryServiceImpl.updateCategory(id, categoryDTO);
		return "Updated Successfully";
	}

	@DeleteMapping("/delete/{category_id}")
	public String deleteCategory(@PathVariable(value = "category_id") int id) throws CategoryNotFoundException {
		categoryServiceImpl.removeCategory(id);
		return " Deleted Successfully";
	}

	@GetMapping("/getbyname/{categoryName}")
	public CategoryDTO getCategoryByName(@PathVariable(value = "categoryName") String name) throws CategoryNotFoundException {
		return categoryServiceImpl.seachCategoryByName(name);
	}

	@GetMapping("/byid/{category_id}")
	public CategoryDTO getCategoryById(@PathVariable(value = "category_id") int id) throws CategoryNotFoundException {
		return categoryServiceImpl.searchCategoryById(id);
	}

	@GetMapping("/getall")
	public List<CategoryDTO> getAllCategory() throws CategoryNotFoundException {
		return categoryServiceImpl.findAllCategories();
	}
}

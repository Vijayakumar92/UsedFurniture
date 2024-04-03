package com.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.category.dto.CategoryDTO;


@FeignClient(name="Category_service", url="http://localhost:8084") 
public interface CategoryService {

	@GetMapping("/category/byid/{category_id}")
	CategoryDTO getCategoryById(@PathVariable(value = "category_id") int id);
	
	
}

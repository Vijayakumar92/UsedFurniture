package com.product.service;

import java.util.List;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;

public interface ProductService {
	
	
	public ProductDTO addProduct(Product product);
	
	public List<ProductDTO> findAll()throws ProductNotFoundException;
	
	public String updateProduct(int id,ProductDTO productDTO);
	
	public ProductDTO getProductById(int id) throws ProductNotFoundException ;
	
	public boolean deleteProduct(int id) throws ProductNotFoundException;
	
	public List<ProductDTO> getProductByBrand(String brandName) throws ProductNotFoundException;
	
	public List<ProductDTO> getProductsByCategoryId(int id) throws ProductNotFoundException;
	
	

}

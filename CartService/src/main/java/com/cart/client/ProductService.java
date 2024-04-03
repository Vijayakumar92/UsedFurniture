package com.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.dto.ProductDTO;




@FeignClient(name="Product-service",url="http://localhost:8084")
public interface ProductService {
	
	@GetMapping("/product/getbyid/{p_id}")
	ProductDTO getProductById(@PathVariable(value = "p_id") int id);

}

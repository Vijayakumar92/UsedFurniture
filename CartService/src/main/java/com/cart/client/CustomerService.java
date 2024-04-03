package com.cart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cart.dto.CustomerDTO;


@FeignClient(name="customer-service", url="http://localhost:8081")
public interface CustomerService {
	
	@GetMapping("/customer/byid/{id}")
	CustomerDTO getCustomerById(@PathVariable(value = "id") int id);

}

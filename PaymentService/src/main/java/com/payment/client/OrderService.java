package com.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.payment.dto.OrderDTO;

@FeignClient(name="order-service",url="http://localhost:8086")
public interface OrderService {

		@GetMapping("/order/find/{orderId}")
		public OrderDTO findById(@PathVariable(value = "orderId") int orderId);
		
	

}

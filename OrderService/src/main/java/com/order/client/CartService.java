package com.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.dto.CartDTO;

@FeignClient(name = "cart-service", url = "http://localhost:8085")
public interface CartService {

    @GetMapping("/cart/getcartbycustid/{customerId}")
    CartDTO getCartByCustomerId(@PathVariable("customerId") int customerId);
}


//@FeignClient(name="cart-service", url="http://localhost:8085")
//public interface CartService {
//	
//	@GetMapping("/cart/getCartByCustId/{customerId}")
//	public CartDTO getCartByCustomerId(int customerId) throws CartNotFoundException;
//
//}
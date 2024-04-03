package com.cart.service;

import com.cart.dto.CartDTO;
import com.cart.exception.CartNotFoundException;
import com.cart.exception.CustomerNotFoundException;
import com.cart.exception.ProductNotFoundException;

public interface CartService {
	
	public CartDTO addToCart(int custid,int productid)throws CustomerNotFoundException, ProductNotFoundException;

	public String deleteProduct(int custid, int prodid)throws CustomerNotFoundException,ProductNotFoundException,CartNotFoundException;
	
	public CartDTO findCartByCustomerId(int customerId)throws CartNotFoundException;

}
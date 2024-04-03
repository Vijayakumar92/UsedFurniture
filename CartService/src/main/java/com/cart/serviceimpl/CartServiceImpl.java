package com.cart.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cart.client.CustomerService;
import com.cart.client.ProductService;
import com.cart.dto.CartDTO;
import com.cart.dto.CustomerDTO;
import com.cart.dto.ProductDTO;
import com.cart.entity.Cart;
import com.cart.exception.CartNotFoundException;
import com.cart.exception.CustomerNotFoundException;
import com.cart.exception.ProductNotFoundException;
import com.cart.repository.CartRepository;
import com.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartRepository cartRepository;

	public CartDTO addToCart(int customerId, int productId) throws CustomerNotFoundException, ProductNotFoundException{
		
		
		CustomerDTO customer = customerService.getCustomerById(customerId);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer Not Found by the Id");
		}
		
		ProductDTO product = productService.getProductById(productId);
		
		if(product==null) {
			throw new ProductNotFoundException("Product Not Found by the Id");
		}
		
			
		Cart cart = cartRepository.findByCustomerId(customerId);
		
			if(cart==null) {
				cart = new Cart();
				cart.setCustomerId(customerId);
				cart.setProductIds(new ArrayList<>());
			}
			
			List<Integer> products =cart.getProductIds();
				products.add(productId);	
				cart.setProductIds(products);
				cart.setTotalPrice(product.getProductPrice()+cart.getTotalPrice());
				
				cart.setTotalQuantity(cart.getTotalQuantity() +1);
				cartRepository.save(cart);
	
			return mapToCartDTO(cart);
		
	}

	

	public String deleteProduct(int custid, int prodid) throws CustomerNotFoundException,ProductNotFoundException,CartNotFoundException {
		
		CustomerDTO customer = customerService.getCustomerById(custid);
		
		if(customer==null) {
			throw new CustomerNotFoundException("Customer Not Found by the Id");
		}
		
		Cart cart = cartRepository.findByCustomerId(custid);
		
		if(cart==null) {
			throw new CartNotFoundException("Cart Not Found by the Id");
		}
		
		List<Integer> products = cart.getProductIds();
		if(!products.isEmpty()) {
		
			products.remove(prodid);
			return "Product Deleted Successfully";
		}
		else
			throw new ProductNotFoundException("Cart is Empty");

	}


	public CartDTO findCartByCustomerId(int customerId) throws CartNotFoundException {
		
		Cart cart = cartRepository.findByCustomerId(customerId);
		
		if(cart==null) {
			throw new CartNotFoundException("Cart Not Found Exception");
		 }
			return mapToCartDTO(cart);
		 }
	
	public CartDTO mapToCartDTO(Cart cart) {
		
		CartDTO cartDTO = new CartDTO();
		
		cartDTO.setId(cart.getId());
		cartDTO.setCustomerId(cart.getCustomerId());
		cartDTO.setProductIds(cart.getProductIds());
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setTotalQuantity(cart.getTotalQuantity());
		
		return cartDTO;
	}



}
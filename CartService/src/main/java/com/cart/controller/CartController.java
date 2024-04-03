package com.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.dto.CartDTO;
import com.cart.exception.CartNotFoundException;
import com.cart.exception.CustomerNotFoundException;
import com.cart.exception.ProductNotFoundException;
import com.cart.serviceimpl.CartServiceImpl;

@RestController
@RequestMapping("/cart") // Base mapping for CartController
@CrossOrigin(origins = "*") // Frontend Connection
public class CartController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @PostMapping("/add/{customerId}/{productId}")
    public CartDTO addToCart(@PathVariable(value = "customerId") int customerId,
                             @PathVariable(value = "productId") int productId) throws CustomerNotFoundException, ProductNotFoundException {
        return cartServiceImpl.addToCart(customerId, productId);
    }

    @DeleteMapping("/deleteproducts/{customerId}/{productId}")
    public String deleteProductCart(@PathVariable(value = "customerId") int customerId,
                                    @PathVariable(value = "productId") int productId) throws CustomerNotFoundException, ProductNotFoundException, CartNotFoundException {

        return cartServiceImpl.deleteProduct(customerId, productId);
    }

    @GetMapping("/getcartbycustid/{customerId}") // Corrected endpoint mapping
    public CartDTO getCartByCustomerId(@PathVariable(value = "customerId") int customerId) throws CartNotFoundException {
        return cartServiceImpl.findCartByCustomerId(customerId);
    }
}

//
//@RestController
//@RequestMapping("/cart")
//@CrossOrigin(origins = "*") // Frontend Connection
//public class CartController {
//
//	@Autowired
//	CartServiceImpl cartServiceImpl;
//
//	@PostMapping("/add/{customerId}/{productId}")
//	public CartDTO addToCart(@PathVariable(value = "customerId") int customerId,
//			@PathVariable(value = "productId") int productId) throws CustomerNotFoundException, ProductNotFoundException {
//		return cartServiceImpl.addToCart(customerId, productId);
//	}
//
//	@DeleteMapping("/deleteProducts/{customerId}/{productId}")
//	public String deleteProductCart(@PathVariable(value = "customerId") int customerId,
//			@PathVariable(value = "productId") int productId) throws CustomerNotFoundException, ProductNotFoundException, CartNotFoundException {
//		
//		return cartServiceImpl.deleteProduct(customerId, productId);
//	}
//	
//	@GetMapping("/getCartByCustId/{customerId}")
//	public CartDTO getCartByCustomerId(@PathVariable(value = "customerId")int customerId) throws CartNotFoundException {
//		return cartServiceImpl.findCartByCustomerId(customerId);
//	}
//}

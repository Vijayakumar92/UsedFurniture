package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.OrderDTO;
import com.order.entity.Order;
import com.order.exception.CartNotFoundException;
import com.order.exception.OrderNotFoundException;
import com.order.serviceimpl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*") // Frontend Connection
public class OrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	@PostMapping("/placeorder/{custid}")
	public OrderDTO placeOrder(@PathVariable(value = "custid") int custid)throws CartNotFoundException {
		return orderServiceImpl.addOrders(custid);
	}

	@GetMapping("/getbyid/{orderId}")
	public OrderDTO findById(@PathVariable(value = "orderId") int orderId) throws OrderNotFoundException {
		return orderServiceImpl.getOrderById(orderId);
	}
	
	@GetMapping("/getall")
	public List<Order> FindAllOrders() throws OrderNotFoundException {
		return orderServiceImpl.getAllOrders();
	}

	@DeleteMapping("/delete/{order_id}")
	public String deleteOrder(@PathVariable(value = "order_id") int orderId) throws CartNotFoundException {
		return orderServiceImpl.deleteOrders(orderId);

	}

	@GetMapping("/getbycustid/{customer_Id}")
	public Order getOrderCustId(@PathVariable(value = "customer_Id") int custId) throws CartNotFoundException, OrderNotFoundException {
		return orderServiceImpl.getOrderByCustomerId(custId);
	}
}

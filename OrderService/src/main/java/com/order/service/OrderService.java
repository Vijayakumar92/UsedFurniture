package com.order.service;

import java.util.List;

import com.order.dto.OrderDTO;
import com.order.entity.Order;
import com.order.exception.CartNotFoundException;
import com.order.exception.OrderNotFoundException;

public interface OrderService {

	public OrderDTO addOrders(int custid)throws CartNotFoundException;

	public OrderDTO getOrderById(int id)throws OrderNotFoundException;
	
	public List<Order> getAllOrders()throws OrderNotFoundException;
	
	public String deleteOrders(int orderId)throws CartNotFoundException;

	public Order getOrderByCustomerId(int customerId)throws CartNotFoundException, OrderNotFoundException;
}
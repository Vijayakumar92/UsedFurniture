package com.order.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.client.CartService;
import com.order.dto.CartDTO;
import com.order.dto.OrderDTO;
import com.order.entity.Order;
import com.order.exception.CartNotFoundException;
import com.order.exception.OrderNotFoundException;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import com.order.util.Status;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderRepository orderRepository;

	public OrderDTO addOrders(int customerId) throws CartNotFoundException {

		
		
			CartDTO cartDTO = cartService.getCartByCustomerId(customerId);
			
			if(cartDTO==null) {
				throw new CartNotFoundException("Cart Not Assigned to this customer. Please Order Again");
			}
			
			Order order = new Order();
			
			order.setDate(LocalDateTime.now());
			order.setStatus(Status.PENDING);
			order.setCustomerId(customerId);
			order.setCartId(cartDTO.getId());
			order.setProductIds(cartDTO.getProductIds());
			order.setTotalPrice(cartDTO.getTotalPrice());
			order.setTotalQuantity(cartDTO.getTotalQuantity());

			orderRepository.save(order);
			
			
			
			return mapToOrderDTO( order);
			
	}

	public OrderDTO getOrderById(int id) throws OrderNotFoundException {

		Order order = orderRepository.findById(id).get();
		if(order==null) {
			throw new OrderNotFoundException("Order Not Found By the given Id");
		}
		return mapToOrderDTO( order);
	}

	private OrderDTO mapToOrderDTO(Order order) {
		
		OrderDTO orderDTO = new OrderDTO();
		
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setDate(order.getDate());
		orderDTO.setCustomerId(order.getCustomerId());
		orderDTO.setCartId(order.getCartId());
		orderDTO.setProductIds(order.getProductIds());
		orderDTO.setTotalQuantity(order.getTotalQuantity());
		orderDTO.setTotalPrice(order.getTotalPrice());
		orderDTO.setStatus(order.getStatus());
		
		return orderDTO;
		
	}
	public List<Order> getAllOrders() throws OrderNotFoundException{

		List<Order> orders = orderRepository.findAll();
		
		if(orders.isEmpty()) {
			throw new OrderNotFoundException("There are no Orders.");
		}
		else {
			return orders;
		}
	}

	public String deleteOrders(int orderId) throws CartNotFoundException {

		CartDTO cartDTO = cartService.getCartByCustomerId(orderId);
		if(cartDTO==null) {
			throw new CartNotFoundException("Cart Not Found");
		}

		cartDTO.getProductIds().clear();

		orderRepository.deleteById(orderId);
		
		return "Deleted Successfully";
	}

	public Order getOrderByCustomerId(int customerId) throws CartNotFoundException, OrderNotFoundException {

		CartDTO cartDTO = cartService.getCartByCustomerId(customerId);
		
		if(cartDTO==null) {
			throw new CartNotFoundException("Cart Not Found By the given Customer Id");
		}
		Order order = orderRepository.getOrderByCartId(cartDTO.getId());
		
		if(order==null) {
			throw new OrderNotFoundException("Cart Not Found By the given Customer Id");
		}

		return order;
	}

	

}


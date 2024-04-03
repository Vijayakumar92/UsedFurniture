package com.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.order.util.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private LocalDateTime date;
	
	private Status status;
	
	private int customerId;

	private int cartId;
	
	private List<Integer> productIds = new ArrayList<>();
	
	private double totalPrice;
	
	private int totalQuantity;

}

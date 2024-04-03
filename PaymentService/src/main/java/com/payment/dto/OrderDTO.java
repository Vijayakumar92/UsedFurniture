package com.payment.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private int orderId;
	
	private LocalDateTime date;
	
	private Status status;
	
	private int customerId;

	private int cartId;
	
	private List<Integer> productIds = new ArrayList<>();
	
	private double totalPrice;
	
	private int totalQuantity;

	

}
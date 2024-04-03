package com.cart.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {

	private int id;
	private List<Integer> productIds;
	private int customerId;
	private int totalQuantity;
	private double totalPrice;

}
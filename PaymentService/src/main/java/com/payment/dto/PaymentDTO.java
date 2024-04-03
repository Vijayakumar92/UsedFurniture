package com.payment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	
		private int paymentId;
	 
	    private double amount;
	    
	    private LocalDateTime paymentDate;
	    
	    private String status;
	    
	    private int orderId;
	 
}

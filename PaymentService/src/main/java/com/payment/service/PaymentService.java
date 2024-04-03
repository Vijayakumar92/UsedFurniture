package com.payment.service;

import com.payment.dto.PaymentDTO;
import com.payment.entity.Payment;
import com.payment.exception.OrderNotFoundException;
import com.payment.exception.PaymentException;

public interface PaymentService {
	
	
	 PaymentDTO makePayment(Payment payment)throws OrderNotFoundException;
	 
	 PaymentDTO getPaymentById(int paymentId) throws PaymentException ;

}
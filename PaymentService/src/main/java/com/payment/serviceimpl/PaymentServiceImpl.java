package com.payment.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.client.OrderService;
import com.payment.dto.OrderDTO;
import com.payment.dto.PaymentDTO;
import com.payment.entity.Payment;
import com.payment.exception.OrderNotFoundException;
import com.payment.exception.PaymentException;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	 @Autowired
	 private PaymentRepository paymentRepository;
	 @Autowired
	 private OrderService orderService;
	
	
	
	   @Override
	   public PaymentDTO makePayment(Payment payment) throws OrderNotFoundException {
		
		    PaymentDTO paymentDTO = new PaymentDTO();
		    
		    paymentDTO.setAmount(payment.getAmount());
		    paymentDTO.setPaymentDate(LocalDateTime.now());
	        payment.setStatus("Pending"); // Initial status

	        OrderDTO orderDTO = orderService.findById(payment.getOrderId());
	        
	        if(orderDTO==null) {
	        	throw new OrderNotFoundException("Order Not Found by the given Id");
	        }
	        
	        payment.setOrderId(orderDTO.getOrderId());
 
	        Payment savedPayment = paymentRepository.save(payment);
 
	        paymentDTO.setPaymentId(savedPayment.getPaymentId());
	        paymentDTO.setPaymentDate(savedPayment.getPaymentDate());
	        paymentDTO.setStatus(savedPayment.getStatus());
 
	        return paymentDTO;
	}
 
	@Override
	public PaymentDTO getPaymentById(int paymentId) throws PaymentException {
		Payment payment = paymentRepository.findById(paymentId).get();
		if(payment==null) {
			throw new PaymentException("Payment Not Found by the given Id");
		}
		
		return null;
	}
	
 
}

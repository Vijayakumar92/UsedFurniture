package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.dto.PaymentDTO;
import com.payment.entity.Payment;
import com.payment.exception.OrderNotFoundException;
import com.payment.exception.PaymentException;
import com.payment.serviceimpl.PaymentServiceImpl;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {
	
	
		 @Autowired
		 private  PaymentServiceImpl paymentServiceImpl;
	
		    
	
		    @PostMapping("/makepayment")
		    public PaymentDTO makePayment(@RequestBody Payment payment) throws OrderNotFoundException {
		    	
		        return paymentServiceImpl.makePayment(payment);
		    }
		    
		    @GetMapping("/getbyid/{payment_Id}")
		    public PaymentDTO getPaymentById(@PathVariable(value="payment_Id") int paymentId) throws PaymentException {
		    	return paymentServiceImpl.getPaymentById(paymentId);
		    }
	
	}
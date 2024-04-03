package com.paymenttest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payment.client.OrderService;
import com.payment.dto.OrderDTO;
import com.payment.dto.PaymentDTO;
import com.payment.entity.Payment;
import com.payment.exception.OrderNotFoundException;
import com.payment.exception.PaymentException;
import com.payment.repository.PaymentRepository;
import com.payment.serviceimpl.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private OrderDTO orderDTO;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
        payment.setPaymentId(1);
        payment.setAmount(100.0);
        payment.setOrderId(123);
        payment.setPaymentDate(LocalDateTime.now());

        orderDTO = new OrderDTO();
        orderDTO.setOrderId(123);
       
    }

    @Test
    public void testMakePayment() throws OrderNotFoundException {
        when(orderService.findById(123)).thenReturn(orderDTO);
        when(paymentRepository.save(payment)).thenReturn(payment);

        PaymentDTO paymentDTO = paymentService.makePayment(payment);

        assertEquals(paymentDTO.getPaymentId(), payment.getPaymentId());
        assertEquals(paymentDTO.getAmount(), payment.getAmount());
        assertEquals(paymentDTO.getPaymentDate(), payment.getPaymentDate());
        assertEquals(paymentDTO.getStatus(), payment.getStatus());
    }

    @Test
    public void testMakePayment_OrderNotFoundException() {
        when(orderService.findById(123)).thenReturn(null);

        assertThrows(OrderNotFoundException.class, () -> paymentService.makePayment(payment));
    }

    
}

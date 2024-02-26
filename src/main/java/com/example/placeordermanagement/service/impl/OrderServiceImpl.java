package com.example.placeordermanagement.service.impl;

import com.example.placeordermanagement.dto.OrderRequest;
import com.example.placeordermanagement.dto.OrderResponse;
import com.example.placeordermanagement.entity.Order;
import com.example.placeordermanagement.entity.Payment;
import com.example.placeordermanagement.exception.PaymentException;
import com.example.placeordermanagement.repository.OrderRepository;
import com.example.placeordermanagement.repository.PaymentRepository;
import com.example.placeordermanagement.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional(rollbackOn = PaymentException.class)
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support this payment");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);


        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;


    }
}

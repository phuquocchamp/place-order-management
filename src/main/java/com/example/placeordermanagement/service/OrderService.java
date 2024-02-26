package com.example.placeordermanagement.service;

import com.example.placeordermanagement.dto.OrderRequest;
import com.example.placeordermanagement.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}

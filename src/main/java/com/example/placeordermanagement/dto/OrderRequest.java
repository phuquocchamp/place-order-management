package com.example.placeordermanagement.dto;

import com.example.placeordermanagement.entity.Order;
import com.example.placeordermanagement.entity.Payment;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OrderRequest {
    private Order order;
    private Payment payment;
}

package com.ec.pizzaserve.assignment1.service;

import com.ec.pizzaserve.assignment1.entity.Order;

public interface OrderService {

    boolean isValid (Order oder);
    boolean isIdValid(long id);
}

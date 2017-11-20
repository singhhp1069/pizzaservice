package com.ec.pizzaserve.assignment1.service;

import com.ec.pizzaserve.assignment1.entity.Topping;

public interface ToppingService {

    boolean isValid (Topping topping);
    boolean isIdValid (long id);
}

package com.ec.pizzaserve.assignment1.service;

import com.ec.pizzaserve.assignment1.entity.Pizza;

public interface PizzaService {
    boolean isValid(Pizza pizza);
    boolean isIdValid (long id);
}

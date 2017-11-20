package com.ec.pizzaserve.assignment1.service;

import com.ec.pizzaserve.assignment1.entity.Pizza;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Override
    public boolean isValid(Pizza pizza) {
        return pizza!=null && pizza.getId()!=null && pizza.getName() !=null && pizza.getPrice()!=null && pizza.getSize()!=null;
    }

    @Override
    public boolean isIdValid(long id) {
        return id != 0;
    }
}

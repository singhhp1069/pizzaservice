package com.ec.pizzaserve.assignment1.service;

import com.ec.pizzaserve.assignment1.entity.Topping;
import org.springframework.stereotype.Service;

@Service
public class ToppingServiceImpl implements ToppingService {
    @Override
    public boolean isValid(Topping topping) {
        return topping!=null && topping.getId()!=null && topping.getName() !=null && topping.getPrice()!=null;
    }

    @Override
    public boolean isIdValid(long id) {
        return id != 0;
    }
}

package com.ec.pizzaserve.assignment1.entity.request;

import com.ec.pizzaserve.assignment1.entity.Topping;

public class AddNewToppingRequest extends Topping{


    private String name;

    private Float price;

    private Long pizzaId;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Float getPrice() {
        return price;
    }

    @Override
    public void setPrice(Float price) {
        this.price = price;
    }
}

package com.ec.pizzaserve.assignment1.entity.request;

import com.ec.pizzaserve.assignment1.entity.Pizza;
import com.ec.pizzaserve.assignment1.entity.Size;

public class AddNewPizzaRequest extends Pizza {


    private String name;

    private Size size;

    private Float price;

    @Override
    public String getName() { return name; }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
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

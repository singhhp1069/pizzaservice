package com.ec.pizzaserve.assignment1.entity.request;

import com.ec.pizzaserve.assignment1.entity.Order;
import com.ec.pizzaserve.assignment1.entity.OrderItem;
import com.ec.pizzaserve.assignment1.entity.Pizza;
import com.ec.pizzaserve.assignment1.repository.PizzaRepository;

import java.util.ArrayList;
import java.util.List;

public class AddNewOrderRequest extends Order{

    private Long id;

    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private Float totalPrice;

    private String recipient;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

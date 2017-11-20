package com.ec.pizzaserve.assignment1.service;

import com.ec.pizzaserve.assignment1.entity.Order;
import com.ec.pizzaserve.assignment1.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public boolean isValid(Order order) {
        return order !=null && order.getRecipient() != null && order.getTotalPrice() != null && isOrderItemValid(order.getOrderItems());
    }

    boolean isOrderItemValid(List<OrderItem> orderItem){
        for(int i=0 ; i < orderItem.size() ; i++)
            return orderItem.get(i) != null && orderItem.get(i).getQuantity() != 0 && orderItem.get(i).getPizzaId() != 0;
        return false;
    }

    @Override
    public boolean isIdValid(long id) {
        return id != 0;
    }
}

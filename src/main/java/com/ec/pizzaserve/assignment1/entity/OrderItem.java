package com.ec.pizzaserve.assignment1.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ec1_orderItem")
public class OrderItem implements Serializable{


    @Id
    @GeneratedValue
    private Long id;

    private Long pizzaId;

    private Long quantity;

    @ManyToOne
    private Order order;

    public OrderItem(Long pizzaId ,Long quantity){
           this.pizzaId = pizzaId;
           this.quantity = quantity;
    }

    protected OrderItem() {
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

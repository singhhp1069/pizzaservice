package com.ec.pizzaserve.assignment1.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ec1_pizza")
public class Pizza implements Serializable {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Size size;

    private Float price;


    protected Pizza() {
        //empty constructor required by JPA
    }

    public Pizza(String name, Size size , Float price){
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

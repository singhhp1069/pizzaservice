package com.ec.pizzaserve.assignment1.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ec1_topping")
public class Topping implements Serializable{

    @Id @GeneratedValue
    private Long id;

    private Long pizzaId;

    private String name;

    private Float price;


    protected Topping(){
        //empty constructor required by JPA
    }

    public Topping (long pizzaId,String name,Float price){
        this.pizzaId = pizzaId;
        this.name = name;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

package com.ec.pizzaserve.assignment1.repository;

import com.ec.pizzaserve.assignment1.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza,Long> {
    List<Pizza> findById(Long Id);
}

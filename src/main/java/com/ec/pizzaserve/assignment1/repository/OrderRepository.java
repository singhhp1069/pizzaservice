package com.ec.pizzaserve.assignment1.repository;

import com.ec.pizzaserve.assignment1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

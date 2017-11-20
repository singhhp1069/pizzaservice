package com.ec.pizzaserve.assignment1.repository;

import com.ec.pizzaserve.assignment1.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

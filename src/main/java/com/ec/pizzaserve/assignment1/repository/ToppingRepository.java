package com.ec.pizzaserve.assignment1.repository;

import com.ec.pizzaserve.assignment1.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ToppingRepository extends JpaRepository<Topping ,Long> {

     List<Topping> findByPizzaId(Long pizzaId);
     List<Topping> findAllByPizzaIdAndId(Long pizzaId,Long id);

}

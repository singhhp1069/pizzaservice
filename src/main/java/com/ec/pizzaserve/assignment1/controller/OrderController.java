package com.ec.pizzaserve.assignment1.controller;

import com.ec.pizzaserve.assignment1.entity.*;
import com.ec.pizzaserve.assignment1.entity.request.AddNewOrderRequest;
import com.ec.pizzaserve.assignment1.repository.OrderRepository;
import com.ec.pizzaserve.assignment1.repository.PizzaRepository;
import com.ec.pizzaserve.assignment1.repository.ToppingRepository;
import com.ec.pizzaserve.assignment1.service.MathsCalculate;
import com.ec.pizzaserve.assignment1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private OrderService orderService;

    public OrderController(OrderRepository orderRepository , PizzaRepository pizzaRepository , ToppingRepository toppingRepository){
        this.orderRepository=orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.toppingRepository = toppingRepository;
    }

    @RequestMapping(method = RequestMethod.GET , path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrders(){
        List<Order> orderList = orderRepository.findAll();
        List<Long> orderId = new ArrayList<Long>();
        if(orderList.isEmpty()){
            orderId.add(new Long(0));
            return new ResponseEntity<>(orderId, HttpStatus.NOT_FOUND);
        }else{
            for (int i = 0 ; i < orderList.size() ; i++){
                orderId.add(orderList.get(i).getId());
            }
            return new ResponseEntity<>(orderId, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET , path = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") long id){
        if(orderService.isIdValid(id)) {
            Order orderList = orderRepository.findOne(id);
            if (orderList == null) {
                return new ResponseEntity<Order>(orderList, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Order>(orderList, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<Order>((Order) null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody AddNewOrderRequest addNewOrderRequest){

        List<Float> finalTotalPrice = new ArrayList<Float>();
        if(orderService.isValid(addNewOrderRequest)) {
            for (int i = 0 ; i < addNewOrderRequest.getOrderItems().size(); i++){
                List<Pizza> pizzas = pizzaRepository.findById(addNewOrderRequest.getOrderItems().get(i).getPizzaId());
                List<Float> totalPrice = new ArrayList<Float>();
                    for (int j = 0 ; j <pizzas.size(); j++){
                        totalPrice.add(pizzas.get(j).getPrice());
                         if(pizzas.get(j).getSize()==Size.Standard){
                             totalPrice.add(5.00f);
                         }else if(pizzas.get(j).getSize()==Size.Large){
                             totalPrice.add(8.50f);
                         }
                    }
                    List<Topping> toppings = toppingRepository.findByPizzaId(addNewOrderRequest.getOrderItems().get(i).getPizzaId());
                    for (int j = 0 ; j <toppings.size(); j++) {
                        totalPrice.add(toppings.get(j).getPrice());
                    }
                    finalTotalPrice.add(MathsCalculate.totalSumOfList(totalPrice)* addNewOrderRequest.getOrderItems().get(i).getQuantity());
                }
            Order order = new Order(addNewOrderRequest.getRecipient(),MathsCalculate.totalSumOfList(finalTotalPrice),addNewOrderRequest.getOrderItems());
            orderRepository.save(order);
            HttpHeaders headers = new HttpHeaders();
            headers.add("location","/order/"+(order.getId()).toString());
            return new ResponseEntity<Order>( order,headers, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Order>(addNewOrderRequest, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE , path = "/order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") long id){
        if(orderService.isIdValid(id)) {
            Order orderList = orderRepository.findOne(id);
            if (orderRepository.exists(id)) {
                orderRepository.delete(id);
                return new ResponseEntity<Order>(orderList, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<Order>(orderList, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<Order>((Order) null, HttpStatus.BAD_REQUEST);
        }
    }
}

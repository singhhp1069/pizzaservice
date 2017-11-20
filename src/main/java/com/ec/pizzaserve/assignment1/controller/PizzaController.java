package com.ec.pizzaserve.assignment1.controller;

import com.ec.pizzaserve.assignment1.entity.Pizza;
import com.ec.pizzaserve.assignment1.entity.Topping;
import com.ec.pizzaserve.assignment1.entity.request.AddNewPizzaRequest;
import com.ec.pizzaserve.assignment1.entity.request.AddNewToppingRequest;
import com.ec.pizzaserve.assignment1.repository.PizzaRepository;
import com.ec.pizzaserve.assignment1.repository.ToppingRepository;
import com.ec.pizzaserve.assignment1.service.PizzaService;
import com.ec.pizzaserve.assignment1.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private ToppingService toppingService;

    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository=pizzaRepository;
    }

    @RequestMapping(method = RequestMethod.GET , path = "/pizza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPizzas(){
        List<Pizza> pizzaList = pizzaRepository.findAll();
        List<Long> pizzaId = new ArrayList<Long>();
        if(pizzaList.isEmpty()){
            pizzaId.add(new Long(0));
            return new ResponseEntity<>(pizzaId, HttpStatus.NOT_FOUND);
        }else{
            for (int i = 0 ; i < pizzaList.size() ; i++){
                pizzaId.add(pizzaList.get(i).getId());
            }
            return new ResponseEntity<>(pizzaId, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET , path = "/pizza/{pizzaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") long id){
        Pizza pizzaList = pizzaRepository.findOne(id);
        if(pizzaList==null){
            return new ResponseEntity<Pizza>(pizzaList, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Pizza>(pizzaList, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/pizza", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pizza> addPizza(@RequestBody AddNewPizzaRequest addNewPizzaRequest){
        if(pizzaService.isValid(addNewPizzaRequest)) {
            Pizza pizza = new Pizza(addNewPizzaRequest.getName(),addNewPizzaRequest.getSize(),addNewPizzaRequest.getPrice());
            pizzaRepository.save(pizza);
            HttpHeaders headers = new HttpHeaders();
            headers.add("location","/pizza/"+(pizza.getId()).toString());

            return new ResponseEntity<Pizza>(pizza,headers, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Pizza>(addNewPizzaRequest, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT , path = "/pizza/{pizzaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pizza> updatePizza(@PathVariable("pizzaId") long id ,@RequestBody AddNewPizzaRequest addNewPizzaRequest){
        if(pizzaService.isValid(addNewPizzaRequest)) {
            if (pizzaRepository.exists(id)) {
                Pizza pizza = pizzaRepository.findOne(id);
                pizza.setPrice(addNewPizzaRequest.getPrice());
                pizza.setSize(addNewPizzaRequest.getSize());
                pizza.setName(addNewPizzaRequest.getName());
                pizzaRepository.save(pizza);
                return new ResponseEntity<Pizza>(pizza, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<Pizza>(addNewPizzaRequest, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<Pizza>(addNewPizzaRequest, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.DELETE , path = "/pizza/{pizzaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pizza> deletePizza(@PathVariable("pizzaId") long id){
        Pizza pizzaList = pizzaRepository.findOne(id);
        if(pizzaService.isIdValid(id)) {
            if (pizzaRepository.exists(id)) {
                pizzaRepository.delete(id);
                return new ResponseEntity<Pizza>(pizzaList, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<Pizza>(pizzaList, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<Pizza>(pizzaList, HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.POST, path = "/pizza/{pizzaId}/topping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topping> createTopping(@PathVariable("pizzaId") long pizzaId,@RequestBody AddNewToppingRequest addNewToppingRequest){
        if(pizzaRepository.exists(pizzaId)) {
            if (toppingService.isValid(addNewToppingRequest)) {
                Topping topping = new Topping(pizzaId, addNewToppingRequest.getName(), addNewToppingRequest.getPrice());
                toppingRepository.save(topping);
                HttpHeaders headers = new HttpHeaders();
                headers.add("location","/pizza/"+pizzaId+"/topping/"+(topping.getId()).toString());

                return new ResponseEntity<Topping>(topping,headers, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Topping>(addNewToppingRequest, HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<Topping>(addNewToppingRequest, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET , path = "/pizza/{pizzaId}/topping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listToppings(@PathVariable("pizzaId") long pizzaId){
        List<Long> toppingId = new ArrayList<Long>();
        if(toppingService.isIdValid(pizzaId)) {
            if(pizzaRepository.exists(pizzaId)){
                List<Topping> toppingList = toppingRepository.findByPizzaId(pizzaId);
                if (toppingList.isEmpty()) {
                    toppingId.add(new Long(0));
                    return new ResponseEntity<>(toppingId, HttpStatus.BAD_REQUEST);
                } else {
                    for (int i = 0 ; i < toppingList.size() ; i++){
                        toppingId.add(toppingList.get(i).getId());
                    }
                    return new ResponseEntity<>(toppingId, HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>(toppingId, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>((List) null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET , path = "/pizza/{pizzaId}/topping/{toppingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getToppingById(@PathVariable("pizzaId") long pizzaId, @PathVariable("toppingId") long toppingId){
        List toppingList = toppingRepository.findAllByPizzaIdAndId(pizzaId,toppingId);
        if(toppingService.isIdValid(pizzaId) && toppingService.isIdValid(toppingId)) {
            if (toppingList.isEmpty()) {
                return new ResponseEntity<List>(toppingList, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List>(toppingList, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<List>((List) null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE , path = "/pizza/{pizzaId}/topping/{toppingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> deleteToppingById(@PathVariable("pizzaId") long pizzaId,@PathVariable("toppingId") long toppingId){
        if(toppingService.isIdValid(pizzaId) && toppingService.isIdValid(toppingId)) {
            List toppingList = toppingRepository.findAllByPizzaIdAndId(pizzaId,toppingId);
            if (toppingList.isEmpty()) {
                return new ResponseEntity<List>(toppingList, HttpStatus.NOT_FOUND);
            } else {
                toppingRepository.delete(toppingId);
                return new ResponseEntity<List>(toppingList, HttpStatus.NO_CONTENT);
            }
        }else{
            return new ResponseEntity<List>((List) null, HttpStatus.BAD_REQUEST);
        }
    }
}

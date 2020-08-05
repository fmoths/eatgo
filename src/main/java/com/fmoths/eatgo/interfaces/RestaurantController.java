package com.fmoths.eatgo.interfaces;

import com.fmoths.eatgo.domain.Restaurant;
import com.fmoths.eatgo.domain.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepositoryImpl restaurantRepositoryImpl;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        return restaurantRepositoryImpl.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id){
        return restaurantRepositoryImpl.findById(id);
    }
}

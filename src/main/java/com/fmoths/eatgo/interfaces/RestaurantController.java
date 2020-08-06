package com.fmoths.eatgo.interfaces;

import com.fmoths.eatgo.application.RestaurantService;
import com.fmoths.eatgo.domain.MenuItem;
import com.fmoths.eatgo.domain.MenuItemRepository;
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
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id){
        return restaurantService.getRestaurant(id);
    }
}

package com.fmoths.eatgo.interfaces;

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
    private RestaurantRepositoryImpl restaurantRepositoryImpl;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        return restaurantRepositoryImpl.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id){

        Restaurant restaurant = restaurantRepositoryImpl.findById(id);

        List <MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }
}

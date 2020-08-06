package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepositoryImpl;

    @Autowired
    MenuItemRepository menuItemRepositoryImpl;

    public RestaurantService(RestaurantRepository restaurantRepositoryImpl, MenuItemRepository menuItemRepositoryImpl) {
        this.restaurantRepositoryImpl = restaurantRepositoryImpl;
        this.menuItemRepositoryImpl = menuItemRepositoryImpl;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepositoryImpl.findById(id);
        List <MenuItem> menuItems = menuItemRepositoryImpl.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepositoryImpl.findAll();
    }
}

package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    RestaurantRepository restaurantRepositoryImpl;

    MenuItemRepository menuItemRepositoryImpl;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepositoryImpl, MenuItemRepository menuItemRepositoryImpl) {
        this.restaurantRepositoryImpl = restaurantRepositoryImpl;
        this.menuItemRepositoryImpl = menuItemRepositoryImpl;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepositoryImpl.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        List <MenuItem> menuItems = menuItemRepositoryImpl.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepositoryImpl.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepositoryImpl.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address){

        Restaurant restaurant = restaurantRepositoryImpl.findById(id).orElse(null);

        if(restaurant != null)
            restaurant.updateInformation(name, address);

        return  restaurant;
    }
}

package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    public RestaurantService restaurantService;
    public RestaurantRepository restaurantRepositoryImpl;
    public MenuItemRepository menuItemRepositoryImpl;

    @BeforeEach
    public void setup(){
        restaurantRepositoryImpl = new RestaurantRepositoryImpl();
        menuItemRepositoryImpl = new MenuItemRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepositoryImpl,menuItemRepositoryImpl);
    }


    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertEquals(1004L,restaurant.getId());

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertEquals(menuItem.getName(),"kimchi");
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertEquals(1004L,restaurant.getId());
    }

}
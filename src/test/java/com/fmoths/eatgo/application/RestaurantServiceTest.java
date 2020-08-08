package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {

    public RestaurantService restaurantService;

    @Mock
    public RestaurantRepository restaurantRepositoryImpl;

    @Mock
    public MenuItemRepository menuItemRepositoryImpl;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        restaurantService = new RestaurantService(
                restaurantRepositoryImpl,menuItemRepositoryImpl);
        mockRestauantRepository();
        mockMenuItemRepository();
    }

    public void mockRestauantRepository(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");
        restaurants.add(restaurant);

        given(restaurantRepositoryImpl.findAll()).willReturn(restaurants);
        given(restaurantRepositoryImpl.findById(1004L)).willReturn(restaurant);
    }

    public void mockMenuItemRepository(){
        List <MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = new MenuItem("kimchi");
        menuItems.add(menuItem);
        given(menuItemRepositoryImpl.findAllByRestaurantId(1004L)).willReturn(menuItems);
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
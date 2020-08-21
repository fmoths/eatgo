package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.MenuItem;
import com.fmoths.eatgo.domain.MenuItemRepository;
import com.fmoths.eatgo.domain.Restaurant;
import com.fmoths.eatgo.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        Restaurant restaurant = Restaurant.builder()
                                .id(1004L)
                                .name("Bob zip")
                                .address("Seoul")
                                .build();

        restaurants.add(restaurant);

        given(restaurantRepositoryImpl.findAll()).willReturn(restaurants);
        given(restaurantRepositoryImpl.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    public void mockMenuItemRepository(){
        List <MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = MenuItem.builder().name("kimchi").build();
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
    @Test
    public void addRestaurant(){

//        Restaurant saved = Restaurant.builder()
//                .id(1234L)
//                .name("ByRyong")
//                .address("Busan")
//                .build();

        given(restaurantRepositoryImpl.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("kdpa")
                .address("Busan")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertEquals(created.getId(),1234L);
    }

    @Test
    public void updateRestaurant(){

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        given(restaurantRepositoryImpl.findById(1004L)).willReturn(Optional.of(restaurant));


        restaurantService.updateRestaurant(1004L,"sul zip","Busan");

        assertEquals("sul zip",restaurant.getName());
        assertEquals("Busan",restaurant.getAddress());
    }
}
package com.fmoths.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryImplTest {

    @Test
    public void save(){
        RestaurantRepository restaurantRepository  = new RestaurantRepositoryImpl();

        int oldCnt = restaurantRepository.findAll().size();

        Restaurant restaurant = new Restaurant("BeRyong","Busan");
        restaurantRepository.save(restaurant);

        assertEquals(1234L,restaurant.getId());

        int newCnt = restaurantRepository.findAll().size();

        assertEquals(1,newCnt - oldCnt);
    }
}
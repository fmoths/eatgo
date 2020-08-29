package com.fmoths.eatgo.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTests {

    @Test
    public void creation(){
//        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .menuItems(new ArrayList<MenuItem>())
                .build();


        assertEquals("Bob zip",restaurant.getName());
        assertEquals("Seoul",restaurant.getAddress());
    }

    @Test
    public void information(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .menuItems(new ArrayList<MenuItem>())
                .build();
        assertEquals("Bob zip in Seoul",restaurant.getInfomation());
    }
}
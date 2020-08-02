package com.fmoths.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant("Bob zip");
        assertEquals("Bob zip",restaurant.getName());
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant("Bob zip","Seoul");
        assertEquals("Bob zip in Seoul",restaurant.getInfomation());
    }
}
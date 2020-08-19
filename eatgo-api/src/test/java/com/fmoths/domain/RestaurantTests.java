package com.fmoths.domain;

import com.fmoths.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        assertEquals("Bob zip",restaurant.getName());
        assertEquals("Seoul",restaurant.getAddress());
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip","Seoul");
        assertEquals("Bob zip in Seoul",restaurant.getInfomation());
    }
}
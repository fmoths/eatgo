package com.fmoths.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    private List <Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepository(){
        this.restaurants.add(new Restaurant(1004L,"Bob zip","Seoul"));
        this.restaurants.add(new Restaurant(2020L,"Cyber food","Seoul"));
    }

    public List<Restaurant> findAll() {
        return restaurants;
    }

    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

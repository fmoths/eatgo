package com.fmoths.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepositoryImpl implements MenuItemRepository{
    private List<MenuItem> menuItems = new ArrayList<>();

    MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("kimchi"));
    }


    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
        return menuItems;
    }
}

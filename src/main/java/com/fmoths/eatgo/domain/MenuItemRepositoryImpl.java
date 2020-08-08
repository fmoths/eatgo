package com.fmoths.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    private List <MenuItem> menuItems;

    public MenuItemRepositoryImpl(){
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("kimchi"));
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
        return menuItems;
    }
}

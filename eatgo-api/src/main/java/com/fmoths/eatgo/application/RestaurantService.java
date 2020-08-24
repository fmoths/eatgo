package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepositoryImpl;

    private final MenuItemRepository menuItemRepositoryImpl;

    private final ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepositoryImpl, MenuItemRepository menuItemRepositoryImpl, ReviewRepository reviewRepository) {
        this.restaurantRepositoryImpl = restaurantRepositoryImpl;
        this.menuItemRepositoryImpl = menuItemRepositoryImpl;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepositoryImpl.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
        List <MenuItem> menuItems = menuItemRepositoryImpl.findAllByRestaurantId(id);
        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);
        restaurant.setReviews(reviews);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepositoryImpl.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepositoryImpl.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address){

        Restaurant restaurant = restaurantRepositoryImpl.findById(id).orElse(null);

        if(restaurant != null)
            restaurant.updateInformation(name, address);

        return  restaurant;
    }
}

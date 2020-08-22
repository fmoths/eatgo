package com.fmoths.eatgo.interfaces;

import com.fmoths.eatgo.application.RestaurantService;
import com.fmoths.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id){
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resource)
            throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();
        Restaurant restaurant = Restaurant.builder().name(name).address(address).build();

        Restaurant restaurant1 = restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant1.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Restaurant restaurant){

        String name = restaurant.getName();
        String address = restaurant.getAddress();
        restaurantService.updateRestaurant(id,name,address);
        return "{}";
    }
}

package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RestaurantServiceTest {

    public RestaurantService restaurantService;

    @Mock
    public ReviewRepository reviewRepository;

    @Mock
    public RestaurantRepository restaurantRepositoryImpl;

    @Mock
    public MenuItemRepository menuItemRepositoryImpl;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        restaurantService = new RestaurantService(
                restaurantRepositoryImpl,menuItemRepositoryImpl, reviewRepository);
        mockRestauantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
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

    private void mockReviewRepository(){
        List <Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
            .name("kdpark")
            .score(1)
            .description("Bad")
            .build());

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepositoryImpl).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertEquals(1004L,restaurant.getId());

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertEquals(menuItem.getName(),"kimchi");

        Review review = restaurant.getReviews().get(0);

        assertEquals("Bad",review.getDescription());
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertEquals(1004L,restaurant.getId());
    }

    @Test
    public void getRestaurantWithNotExisted(){
        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getRestaurant(404L);
        });
    }

    @Test
    public void addRestaurant(){

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
package com.fmoths.eatgo.interfaces;

import com.fmoths.eatgo.application.RestaurantService;
import com.fmoths.eatgo.domain.MenuItem;
import com.fmoths.eatgo.domain.Restaurant;
import com.fmoths.eatgo.domain.RestaurantNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        restaurants.add(restaurant);
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("\"name\":\"Bob zip\""))
                )
                .andExpect(content()
                        .string(containsString("\"id\":1004"))
                );
    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant1 = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        MenuItem menuItem = MenuItem.builder().name("Kimchi").build();
        restaurant1.setMenuItems(Arrays.asList(menuItem));

        Restaurant restaurant2 = Restaurant.builder()
                                    .id(2020L)
                                    .name("Cyber food")
                                    .address("Seoul")
                                    .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004"))
                )
                .andExpect(content().string(
                    containsString("\"name\":\"Bob zip\""))
                )
                .andExpect(content().string(
                        containsString("Kimchi"))
                );

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020"))
                )
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\""))
                );
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }
    @Test
    public void createWithVaildData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location","/restaurants/1234"))
            .andExpect(content().string("{}"));

        //TODO:: verify 란?
        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1004")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Jokerbar\",\"address\":\"Busan\"}"))
            .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1004L,"Jokerbar","Busan");
    }
}
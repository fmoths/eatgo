package com.fmoths.eatgo.interfaces;

import com.fmoths.eatgo.application.RestaurantService;
import com.fmoths.eatgo.domain.MenuItemRepository;
import com.fmoths.eatgo.domain.MenuItemRepositoryImpl;
import com.fmoths.eatgo.domain.RestaurantRepository;
import com.fmoths.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    //TODO::스파이 빈 이란?
    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;

    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
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
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\""))
                )
                .andExpect(content().string(
                        containsString("\"id\":1004"))
                ).andExpect(content()
                .string(containsString("kimchi")));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber food\""))
                )
                .andExpect(content().string(
                        containsString("\"id\":2020"))
                );
    }
}
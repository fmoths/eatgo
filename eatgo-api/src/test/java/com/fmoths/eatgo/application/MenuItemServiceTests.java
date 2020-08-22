package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.MenuItem;
import com.fmoths.eatgo.domain.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MenuItemServiceTests {

    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        menuItems.add(MenuItem.builder()
            .name("Kimchi")
            .build());

        menuItems.add(MenuItem.builder()
                .name("gugbob")
                .build());

        menuItemService.bulkUpdate(1L,menuItems);

        verify(menuItemRepository, times(2)).save(any());
    }
}
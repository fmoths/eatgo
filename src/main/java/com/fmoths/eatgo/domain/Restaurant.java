package com.fmoths.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private String address;
    private Long id;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }

    public void setId(Long id){
        this.id = id;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public String getName() {
        return name;
    }

    public Long getId(){
        return id;
    }

    public String getAddress(){
        return address;
    }

    public String getInfomation(){
        return name + " in "+address;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}

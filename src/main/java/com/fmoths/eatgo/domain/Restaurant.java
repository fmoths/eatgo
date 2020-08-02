package com.fmoths.eatgo.domain;

public class Restaurant {
    String name;
    String address;
    public Restaurant(String name){
        this.name = name;
    }

    public Restaurant(String name, String address) {
    }

    public String getName() {
        return name;
    }
}

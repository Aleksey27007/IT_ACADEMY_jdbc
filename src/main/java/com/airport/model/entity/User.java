package com.airport.model.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String phone;
    private int roleId;

    public User(int id, String name, String phone, int roleId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.roleId = roleId;
    }
}

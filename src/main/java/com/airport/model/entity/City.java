package com.airport.model.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class City {
    private int id;
    private String name;
    private LocalDateTime time;
}

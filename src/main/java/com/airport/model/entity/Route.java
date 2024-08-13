package com.airport.model.entity;

import lombok.Data;

@Data
public class Route {
    private int id;
    private int arrivalId;
    private int departureId;
}

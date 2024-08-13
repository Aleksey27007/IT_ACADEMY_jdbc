package com.airport.model.entity;

import lombok.Data;

@Data
public class Ticket {
    private int id;
    private int userId;
    private int routeId;
    private boolean status;
    private int airplaneId;
}

package com.MotorDeport.model.route;

import java.io.Serializable;

public class Route implements Serializable{
    private String pointOfDeparture;
    private String pointOfDestination;
    private int routingNumber;

    public Route(String pointOfDeparture, String pointOfDestination, int routingNumber) {
        this.pointOfDeparture = pointOfDeparture;
        this.pointOfDestination = pointOfDestination;
        this.routingNumber = routingNumber;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public String getPointOfDestination() {
        return pointOfDestination;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    @Override
    public String toString() {
        return  "Маршрут №: " + routingNumber + "\n" +
                "Точка отправления: " + pointOfDeparture + " - " +
                "Точка прибытия: " + pointOfDestination +
                "\n-----------------------------------------------------------------------------------" +
                "-----------------------------------------------------------";
    }
}

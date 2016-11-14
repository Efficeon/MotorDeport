package com.MotorDeport.model.passengerCar;

import java.io.Serializable;

public class Sedan extends PassengerCar implements Serializable{
    //static final long serialVersionUID = 1847807168196659482L;
    public Sedan(String licensePlate, int numberOfPassengers, boolean technicalTrunk) {
        super(licensePlate, numberOfPassengers, technicalTrunk);
        type = "Седан";
    }
}

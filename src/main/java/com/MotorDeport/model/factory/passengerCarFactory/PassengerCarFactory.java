package com.MotorDeport.model.factory.passengerCarFactory;

import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.IOException;

public interface PassengerCarFactory {
    PassengerCar create(String licensePlate, int numberOfPassengers, boolean technicalTrunk) throws IOException, ClassNotFoundException;
}

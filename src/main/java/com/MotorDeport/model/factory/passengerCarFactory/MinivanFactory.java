package com.MotorDeport.model.factory.passengerCarFactory;

import com.MotorDeport.dao.passengerCarDao.MinivanDao;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.IOException;

public class MinivanFactory implements PassengerCarFactory{
    @Override
    public PassengerCar create(String licensePlate, int numberOfPassengers, boolean technicalTrunk) throws IOException, ClassNotFoundException {

        MinivanDao minivanDao = new MinivanDao();
        return minivanDao.createPassengerCar(licensePlate, numberOfPassengers, technicalTrunk);
    }
}

package com.MotorDeport.model.factory.passengerCarFactory;

import com.MotorDeport.dao.passengerCarDao.SedanDao;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.IOException;

public class SedanFactory implements PassengerCarFactory {
    @Override
    public PassengerCar create(String licensePlate, int numberOfPassengers, boolean technicalTrunk) throws IOException, ClassNotFoundException {

        SedanDao sedanDao = new SedanDao();
        return sedanDao.createPassengerCar(licensePlate, numberOfPassengers, technicalTrunk);
    }
}

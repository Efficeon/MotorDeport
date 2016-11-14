package com.MotorDeport.model.factory.passengerCarFactory;

import com.MotorDeport.dao.passengerCarDao.MicrocarDao;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.IOException;

public class MicrocarFactory implements PassengerCarFactory{
    @Override
    public PassengerCar create(String licensePlate, int numberOfPassengers, boolean technicalTrunk) throws IOException, ClassNotFoundException {

        MicrocarDao microcarDao = new MicrocarDao();
        return microcarDao.createPassengerCar(licensePlate, numberOfPassengers, technicalTrunk);
    }
}

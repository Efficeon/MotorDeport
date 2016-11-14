package com.MotorDeport.dao.passengerCarDao;

import com.MotorDeport.exception.ExceptionPassengerCarSelectionNotFound;
import com.MotorDeport.model.Car;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class PassengerCarDao implements Serializable{
    protected static Map<String, PassengerCar> passengerCars = new HashMap<>();

    public abstract PassengerCar createPassengerCar(String licensePlate,
                                           int numberOfPassengers,
                                           boolean technicalTrunk) throws IOException, ClassNotFoundException;

    public abstract void addToDatabasePassengerCar() throws IOException;

    public abstract Map<String, PassengerCar> getDatabasePassengerCar() throws IOException, ClassNotFoundException;

    public void showDatabasePassengerCar() throws IOException, ClassNotFoundException {
        if (getDatabasePassengerCar() != null) {
            for (Map.Entry passengerCar : getDatabasePassengerCar().entrySet()) {
                ConsoleHelper.writeMessage(passengerCar.getValue().toString());
            }
        }
    }

    public void modificationLorryDriver(PassengerCar passengerCar, Driver driver) throws IOException, ClassNotFoundException {
        PassengerCar passengerCarUpdate = passengerCars.get(passengerCar.getLicensePlate());
        passengerCarUpdate.setDriver(driver);
        passengerCars.replace(passengerCar.getLicensePlate(), passengerCarUpdate);
        addToDatabasePassengerCar();
        getDatabasePassengerCar();
    }

    public Car passengerCarSelection(int numberOfPassengers) throws IOException, ClassNotFoundException, ExceptionPassengerCarSelectionNotFound {
        getDatabasePassengerCar();
        Car passengerCarSelection = null;
        for (Map.Entry<String, PassengerCar> passengerCar : passengerCars.entrySet()){
            if(passengerCar.getValue().getNumberOfPassengers() >= numberOfPassengers &&
                    passengerCar.getValue().isTechnicalTrunk()==true){

                passengerCarSelection = passengerCar.getValue();
            }
            else throw new ExceptionPassengerCarSelectionNotFound();
        }
        return passengerCarSelection;
    }

    public abstract void addDriverToPassengerCar(Car car, Driver driver) throws IOException, ClassNotFoundException;
}

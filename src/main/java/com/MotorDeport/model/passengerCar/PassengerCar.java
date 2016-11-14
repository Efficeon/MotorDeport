package com.MotorDeport.model.passengerCar;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.employee.Driver;

import java.io.Serializable;

public abstract class PassengerCar implements Car, Serializable
{
    private String licensePlate;             //Автомобильные номера
    private int numberOfPassengers;          //Количество пасажиров
    private boolean technicalTrunk;          //Техническая диагностика

    protected String type;                   //Тип Автомобиля
    protected Driver driver;                 //Водитель

    protected PassengerCar(String licensePlate, int numberOfPassengers, boolean technicalTrunk) {
        this.licensePlate = licensePlate;
        this.numberOfPassengers = numberOfPassengers;
        this.technicalTrunk = technicalTrunk;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public boolean isTechnicalTrunk() {
        return technicalTrunk;
    }

    public void setTechnicalTrunk(boolean technicalTrunk) {
        this.technicalTrunk = technicalTrunk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        String nameDriver = "отсутствует";
        if (this.driver != null){
            nameDriver = driver.getName() + ", Номер паспорта: " +driver.getPassportID();
        }
        return  "Легковой автомобиль(" + this.type + "):\n" +
                "Автомобильные номера: " + this.licensePlate + "; " +
                "Количество пасажиров " + this.numberOfPassengers + "; " +
                "Техническая диагностика: " + this.technicalTrunk + ";\n" +
                "Закрепленный водитель: " + nameDriver +
                "\n-----------------------------------------------------------------------------------" +
                "-----------------------------------------------------------";
    }
}

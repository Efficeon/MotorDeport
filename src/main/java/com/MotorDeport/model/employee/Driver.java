package com.MotorDeport.model.employee;

import com.MotorDeport.model.Car;

import java.io.Serializable;

public class Driver implements Serializable{
    //static final long serialVersionUID = -2830046108133983977L;

    private final String name;
    private final String passportID;
    private Car car = null;

    public Driver(String name, String passportID) {
       this.name = name;
       this.passportID = passportID;
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        String carLicensePlate = "отсутствует";
        if (car != null){
            carLicensePlate = car.getLicensePlate();
        }

        return "Водитель: " +
                "Номер паспорта: " + this.passportID + "; " +
                "ФИО: " + this.getName() + ";\n" +
                "Автомобиль: " + carLicensePlate + "; " +
                "\n-----------------------------------------------------------------------------------" +
                "-----------------------------------------------------------";
    }
}

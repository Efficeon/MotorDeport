package com.MotorDeport.model.lorry;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.employee.Driver;

import java.io.Serializable;

public abstract class Lorry implements Serializable, Car {
    //static final long serialVersionUID = 5037585225066001448L;
    private String licensePlate;             //Автомобильные номера
    private double carryingCapacity;         //Грузоподъемность
    private double lengthOfBody;             //Длинна
    private double widthOfBody;              //Ширина
    private double heightOfBody;             //Высота
    private boolean technicalDiagnostics;    //Техническая диагностика

    protected Driver driver = null;

    protected Lorry(String licensePlate,
                    double carryingCapacity,
                    double lengthOfBody,
                    double widthOfBody,
                    double heightOfBody,
                    boolean technicalDiagnostics) {
        this.licensePlate = licensePlate;
        this.carryingCapacity = carryingCapacity;
        this.lengthOfBody = lengthOfBody;
        this.widthOfBody = widthOfBody;
        this.heightOfBody = heightOfBody;
        this.technicalDiagnostics = technicalDiagnostics;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public double getLengthOfBody() {
        return lengthOfBody;
    }

    public void setLengthOfBody(double lengthOfBody) {
        this.lengthOfBody = lengthOfBody;
    }

    public double getWidthOfBody() {
        return widthOfBody;
    }

    public void setWidthOfBody(double widthOfBody) {
        this.widthOfBody = widthOfBody;
    }

    public double getHeightOfBody() {
        return heightOfBody;
    }

    public void setHeightOfBody(double heightOfBody) {
        this.heightOfBody = heightOfBody;
    }

    public boolean isTechnicalDiagnostics() {
        return technicalDiagnostics;
    }

    public void setTechnicalDiagnostics(boolean technicalDiagnostics) {
        this.technicalDiagnostics = technicalDiagnostics;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}


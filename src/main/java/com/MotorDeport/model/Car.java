package com.MotorDeport.model;

import com.MotorDeport.model.employee.Driver;

public interface Car {
    public String getLicensePlate();
    public Driver getDriver();
    public void setDriver(Driver driver);
    public String toString();
}

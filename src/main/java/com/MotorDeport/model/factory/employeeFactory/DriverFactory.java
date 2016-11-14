package com.MotorDeport.model.factory.employeeFactory;

import com.MotorDeport.dao.employeeDao.DriverDao;
import com.MotorDeport.model.employee.Driver;

import java.io.IOException;

public class DriverFactory{
    public Driver create(String name, String passportID) throws IOException, ClassNotFoundException {
        DriverDao driverDao = new DriverDao();
        return driverDao.createDriver(name, passportID);
    }
}

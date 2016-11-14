package com.MotorDeport.model.factory.lorryFactory;

import com.MotorDeport.dao.lorryDao.RefrigeratorLorryDao;
import com.MotorDeport.model.lorry.Lorry;

import java.io.IOException;

public class RefrigeratorLorryFactory implements LorryFactory{
    @Override
    public Lorry createLorry(String licensePlate,
                             double carryingCapacity,
                             double lengthOfBody,
                             double widthOfBody,
                             double heightOfBody,
                             boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        RefrigeratorLorryDao refrigeratorLorryDao = new RefrigeratorLorryDao();
        return refrigeratorLorryDao.createRefrigeratorLorry(licensePlate, carryingCapacity, lengthOfBody, widthOfBody,
                   heightOfBody, technicalDiagnostics);
    }
}

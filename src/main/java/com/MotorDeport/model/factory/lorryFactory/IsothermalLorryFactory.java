package com.MotorDeport.model.factory.lorryFactory;

import com.MotorDeport.dao.lorryDao.IsothermalLorryDao;
import com.MotorDeport.model.lorry.Lorry;

import java.io.IOException;

public class IsothermalLorryFactory implements LorryFactory {
    @Override
    public Lorry createLorry(String licensePlate,
                             double carryingCapacity,
                             double lengthOfBody,
                             double widthOfBody,
                             double heightOfBody,
                             boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        IsothermalLorryDao isothermalLorryDao = new IsothermalLorryDao();
        return isothermalLorryDao.createIsothermalLorry(licensePlate, carryingCapacity, lengthOfBody, widthOfBody,
                   heightOfBody, technicalDiagnostics);
    }
}

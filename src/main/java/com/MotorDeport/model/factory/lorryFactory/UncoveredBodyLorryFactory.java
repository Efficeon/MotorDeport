package com.MotorDeport.model.factory.lorryFactory;

import com.MotorDeport.dao.lorryDao.UncoveredBodyLorryDao;
import com.MotorDeport.model.lorry.Lorry;

import java.io.IOException;

public class UncoveredBodyLorryFactory implements LorryFactory{
    @Override
    public Lorry createLorry(String licensePlate,
                             double carryingCapacity,
                             double lengthOfBody,
                             double widthOfBody,
                             double heightOfBody,
                             boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        UncoveredBodyLorryDao uncoveredBodyLorryDao = new UncoveredBodyLorryDao();
        return uncoveredBodyLorryDao.createUncoveredBodyLorry(licensePlate, carryingCapacity, lengthOfBody,
                widthOfBody, heightOfBody, technicalDiagnostics);
    }
}

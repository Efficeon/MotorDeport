package com.MotorDeport.model.factory.lorryFactory;

import com.MotorDeport.dao.lorryDao.CoveredBodyLorryDao;
import com.MotorDeport.model.lorry.Lorry;

import java.io.IOException;

public class CoveredBodyLorryFactory implements LorryFactory {
    @Override
    public Lorry createLorry(String licensePlate,
                             double carryingCapacity,
                             double lengthOfBody,
                             double widthOfBody,
                             double heightOfBody,
                             boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        CoveredBodyLorryDao coveredBodyLorryDao = new CoveredBodyLorryDao();
        return coveredBodyLorryDao.createCoveredBodyLorry(licensePlate, carryingCapacity, lengthOfBody,
                widthOfBody, heightOfBody, technicalDiagnostics);
    }
}

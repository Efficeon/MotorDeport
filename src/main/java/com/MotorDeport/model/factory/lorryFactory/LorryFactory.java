package com.MotorDeport.model.factory.lorryFactory;

import com.MotorDeport.model.lorry.Lorry;

import java.io.IOException;

public interface LorryFactory {
    Lorry createLorry(String licensePlate,
                      double carryingCapacity,
                      double lengthOfBody,
                      double widthOfBody,
                      double heightOfBody,
                      boolean technicalDiagnostics) throws IOException, ClassNotFoundException;
}

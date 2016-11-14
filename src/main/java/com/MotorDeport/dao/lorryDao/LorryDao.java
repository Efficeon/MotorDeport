package com.MotorDeport.dao.lorryDao;

import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.lorry.Lorry;

import java.io.IOException;

public interface LorryDao {
    public void addDriverToLorry(Lorry lorry, Driver driver) throws IOException, ClassNotFoundException;
    public Lorry lorrySelection(double lengthOfBody, double widthOfBody, double heightOfBody, int carryingCapacity) throws IOException, ClassNotFoundException, ExceptionLorrySelectionNotFound;
}

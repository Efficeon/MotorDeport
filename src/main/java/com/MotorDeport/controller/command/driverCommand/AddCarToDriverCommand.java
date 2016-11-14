package com.MotorDeport.controller.command.driverCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.employeeDao.DriverDao;
import com.MotorDeport.dao.lorryDao.*;
import com.MotorDeport.View.ConsoleHelper;

import java.io.IOException;

public class AddCarToDriverCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Крытый кузов; 2-Изотермический кузов;" +
                "3-Рефрижераторный кузов; 4-Открытый кузов): ");
        int typeBody = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Укажите номер автомобиля: ");
        String licensePlate = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Укажите номер паспорта водителя: ");
        String passportID = ConsoleHelper.readString();

        DriverDao driverDao = new DriverDao();

        switch (typeBody){
            case 1:
                CoveredBodyLorryDao coveredLorryDao = new CoveredBodyLorryDao();
                driverDao.addLorryToDriver(driverDao.gerDriver(passportID),
                                           coveredLorryDao.getCoveredBodyLorry(licensePlate));
                break;
            case 2:
                IsothermalLorryDao isothermalLorryDao = new IsothermalLorryDao();
                driverDao.addLorryToDriver(driverDao.gerDriver(passportID),
                                           isothermalLorryDao.getIsothermalLorry(licensePlate));
                break;
            case 3:
                RefrigeratorLorryDao refrigeratorLorryDao = new RefrigeratorLorryDao();
                driverDao.addLorryToDriver(driverDao.gerDriver(passportID),
                                           refrigeratorLorryDao.getRefrigeratorLorry(licensePlate));
                break;
            case 4:
                UncoveredBodyLorryDao uncoveredLorryDao = new UncoveredBodyLorryDao();
                driverDao.addLorryToDriver(driverDao.gerDriver(passportID),
                                           uncoveredLorryDao.getUncoveredBodyLorry(licensePlate));
        }

        ConsoleHelper.writeMessage("Автомобиль с номером " + licensePlate +" закреплен за водителем " +
        driverDao.gerDriver(passportID).getName());
    }
}

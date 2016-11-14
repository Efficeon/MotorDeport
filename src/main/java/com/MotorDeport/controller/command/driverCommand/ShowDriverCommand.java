package com.MotorDeport.controller.command.driverCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.employeeDao.DriverDao;
import com.MotorDeport.View.ConsoleHelper;

import java.io.IOException;

public class ShowDriverCommand implements Command{
    @Override
    public void execute() throws IOException {
        DriverDao driverDao = new DriverDao();
        ConsoleHelper.writeMessage("\nУкажите номер паспорта водителя: ");
        String passportID = ConsoleHelper.readString();
        try {
            ConsoleHelper.writeMessage(driverDao.gerDriver(passportID).toString());
        } catch (NullPointerException e) {
            System.out.println("\nВодитель с такими данными не найден!\n");
        }
    }
}

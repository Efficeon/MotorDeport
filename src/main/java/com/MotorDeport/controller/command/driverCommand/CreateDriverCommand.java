package com.MotorDeport.controller.command.driverCommand;

import com.MotorDeport.controller.command.Command;

import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.factory.employeeFactory.DriverFactory;

import java.io.IOException;

public class CreateDriverCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        DriverFactory driverFactory = new DriverFactory();

        ConsoleHelper.writeMessage("Введите инициалы водителя: ");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите номер паспорта водителя: ");
        String passportID = ConsoleHelper.readString();
        Driver driver = driverFactory.create(name , passportID);
        ConsoleHelper.writeMessage("Водитель: " + name + " " + passportID + " добавлен!\n");
    }
}

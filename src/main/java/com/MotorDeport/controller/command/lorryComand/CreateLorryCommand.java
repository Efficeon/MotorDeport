package com.MotorDeport.controller.command.lorryComand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.factory.lorryFactory.*;

import java.io.IOException;

public class CreateLorryCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип автомобиля:\n1 - Крытый кузов \n2 - Изотермический кузов" +
                "\n3 - Рефрижераторный кузов \n4 - Открытый кузов: ");
        int typeBody = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Укажите следующие характеристики автомобиля:");

        ConsoleHelper.writeMessage("Автомобильные номера: ");
        String licensePlate = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Длинна: ");
        double lengthOfBody = ConsoleHelper.readDouble();

        ConsoleHelper.writeMessage("Ширина: ");
        double widthOfBody = ConsoleHelper.readDouble();

        ConsoleHelper.writeMessage("Высота: ");
        double heightOfBody = ConsoleHelper.readDouble();

        ConsoleHelper.writeMessage("Грузоподъемность: ");
        double carryingCapacity = ConsoleHelper.readDouble();

        ConsoleHelper.writeMessage("Техническая диагностика (true/false): ");
        boolean technicalDiagnostics = ConsoleHelper.readBoolean();

        LorryFactory lorryFactory;
        switch (typeBody){
            case 1:
                lorryFactory = new CoveredBodyLorryFactory();
                lorryFactory.createLorry(licensePlate, carryingCapacity, lengthOfBody,
                        widthOfBody, heightOfBody, technicalDiagnostics);
                break;
            case 2:
                lorryFactory = new IsothermalLorryFactory();
                lorryFactory.createLorry(licensePlate, carryingCapacity, lengthOfBody,
                        widthOfBody, heightOfBody, technicalDiagnostics);
                break;
            case 3:
                lorryFactory = new RefrigeratorLorryFactory();
                lorryFactory.createLorry(licensePlate, carryingCapacity, lengthOfBody,
                        widthOfBody, heightOfBody, technicalDiagnostics);
                break;
            case 4:
                lorryFactory = new UncoveredBodyLorryFactory();
                lorryFactory.createLorry(licensePlate, carryingCapacity, lengthOfBody,
                        widthOfBody, heightOfBody, technicalDiagnostics);
        }

        ConsoleHelper.writeMessage("\nГрузовой автомобиль с номром " + licensePlate + " создан!\n");
    }
}

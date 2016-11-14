package com.MotorDeport.controller.command.passenderCarCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.factory.passengerCarFactory.*;

import java.io.IOException;

public class CreatePassCarCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Хэ́тчбек; 2-Микрокар; 3-Минивен; 4-Седан): ");
        int type = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Укажите следующие характеристики автомобиля:");

        ConsoleHelper.writeMessage("Автомобильные номера: ");
        String licensePlate = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Количество пасажиров: ");
        int numberOfPassengers = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Техническая диагностика (true/false): ");
        boolean technicalDiagnostics = ConsoleHelper.readBoolean();

        PassengerCarFactory passengerCarFactory = null;
        switch (type){
            case 1:
                passengerCarFactory = new HatchbackFactory();
                break;
            case 2:
                passengerCarFactory = new MicrocarFactory();
                break;
            case 3:
                passengerCarFactory = new MinivanFactory();
                break;
            case 4:
                passengerCarFactory = new SedanFactory();
        }
        passengerCarFactory.create(licensePlate, numberOfPassengers, technicalDiagnostics);
    }
}

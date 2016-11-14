package com.MotorDeport.controller.command.passenderCarCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.passengerCarDao.*;
import com.MotorDeport.View.ConsoleHelper;

import java.io.IOException;

public class ShowPassCarCommand implements Command {
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Хэ́тчбек; 2-Микрокар; 3-Минивен; 4-Седан): ");
        int typeBody = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Укажите автомобильный номер: ");
        String licensePlate = ConsoleHelper.readString();

        switch (typeBody){
            case 1:
                HatchbackDao hatchbackDao = new HatchbackDao();
                hatchbackDao.getHatchback(licensePlate);
                break;
            case 2:
                MicrocarDao microcarDao = new MicrocarDao();
                microcarDao.getMicrocar(licensePlate);
                break;
            case 3:
                MinivanDao minivanDao = new MinivanDao();
                minivanDao.getMinivan(licensePlate);
                break;
            case 4:
                SedanDao sedanDao = new SedanDao();
                sedanDao.getSedan(licensePlate);
        }
    }
}

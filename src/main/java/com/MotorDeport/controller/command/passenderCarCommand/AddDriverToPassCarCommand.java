package com.MotorDeport.controller.command.passenderCarCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.employeeDao.DriverDao;
import com.MotorDeport.dao.passengerCarDao.HatchbackDao;
import com.MotorDeport.dao.passengerCarDao.MicrocarDao;
import com.MotorDeport.dao.passengerCarDao.MinivanDao;
import com.MotorDeport.dao.passengerCarDao.SedanDao;
import com.MotorDeport.View.ConsoleHelper;

import java.io.IOException;

public class AddDriverToPassCarCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Хэ́тчбек; 2-Микрокар; 3-Минивен; 4-Седан): ");
        int typeBody = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Укажите номер автомобиля: ");
        String licensePlate = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Укажите номер паспорта водителя: ");
        String passportID = ConsoleHelper.readString();

        DriverDao driverDao = new DriverDao();

        switch (typeBody){
            case 1:
                HatchbackDao hatchbackDao = new HatchbackDao();
                hatchbackDao.addDriverToPassengerCar(hatchbackDao.getHatchback(licensePlate), driverDao.gerDriver(passportID));
                break;
            case 2:
                MicrocarDao microcarDao = new MicrocarDao();
                microcarDao.addDriverToPassengerCar(microcarDao.getMicrocar(licensePlate), driverDao.gerDriver(passportID));
                break;
            case 3:
                MinivanDao minivanDao = new MinivanDao();
                minivanDao.addDriverToPassengerCar(minivanDao.getMinivan(licensePlate), driverDao.gerDriver(passportID));
                break;
            case 4:
                SedanDao sedanDao = new SedanDao();
                sedanDao.addDriverToPassengerCar(sedanDao.getSedan(licensePlate), driverDao.gerDriver(passportID));
        }
    }
}

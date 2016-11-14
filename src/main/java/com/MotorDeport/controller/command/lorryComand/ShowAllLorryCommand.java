package com.MotorDeport.controller.command.lorryComand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.lorryDao.CoveredBodyLorryDao;
import com.MotorDeport.dao.lorryDao.IsothermalLorryDao;
import com.MotorDeport.dao.lorryDao.RefrigeratorLorryDao;
import com.MotorDeport.dao.lorryDao.UncoveredBodyLorryDao;
import com.MotorDeport.View.ConsoleHelper;

import java.io.IOException;

public class ShowAllLorryCommand implements Command {
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Крытый кузов; 2-Изотермический кузов;" +
                "3-Рефрижераторный кузов; 4-Открытый кузов): ");
        int typeBody = ConsoleHelper.readInt();

        switch (typeBody){
            case 1:
                CoveredBodyLorryDao coveredLorryDao = new CoveredBodyLorryDao();
                coveredLorryDao.showDatabaseCoveredBodyLorry();
                break;
            case 2:
                IsothermalLorryDao isothermalLorryDao = new IsothermalLorryDao();
                isothermalLorryDao.showDatabaseIsothermalLorry();
                break;
            case 3:
                RefrigeratorLorryDao refrigeratorLorryDao = new RefrigeratorLorryDao();
                refrigeratorLorryDao.showDatabaseRefrigeratorLorry();
                break;
            case 4:
                UncoveredBodyLorryDao uncoveredLorryDao = new UncoveredBodyLorryDao();
                uncoveredLorryDao.showDatabaseUncoveredBodyLorry();
        }
    }
}

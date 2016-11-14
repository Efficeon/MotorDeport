package com.MotorDeport.controller.command.driverCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.employeeDao.DriverDao;

import java.io.IOException;

public class ShowAllDriversCommand implements Command {
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        DriverDao driverDao = new DriverDao();
        driverDao.showDatabaseDriver();
    }
}

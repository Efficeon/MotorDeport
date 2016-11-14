package com.MotorDeport.controller.command.routeCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.routeDao.RouteDao;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.route.Route;

import java.io.IOException;

public class ShowRouteCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите номер маршрута: ");
        int routingNumber = ConsoleHelper.readInt();

        RouteDao routeDao = new RouteDao();
        Route route = routeDao.getRouteByNumber(routingNumber);
        ConsoleHelper.writeMessage(route.toString());
    }
}

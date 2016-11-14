package com.MotorDeport.controller.command.orderCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.orderDao.OrderForLorryDao;
import com.MotorDeport.dao.orderDao.OrderForPassengerCarDao;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.order.OrderForLorry;
import com.MotorDeport.model.order.OrderForPassengerCar;

import java.io.IOException;

public class ShowOrderCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип заявки (1-Грузовой перевозка; " +
                "2-Пассажирская перевозка;");
        int typeBody = ConsoleHelper.readInt();

        ConsoleHelper.writeMessage("Укажите номер заявки: ");
        int orderNumber = ConsoleHelper.readInt();

        if (typeBody==1){
            OrderForLorryDao orderForLorryDao = new OrderForLorryDao();
            OrderForLorry order = orderForLorryDao.getOrderForLorry(orderNumber);
            ConsoleHelper.writeMessage(order.toString());
        } else if (typeBody==2){
            OrderForPassengerCarDao orderForPassengerCarDao = new OrderForPassengerCarDao();
            OrderForPassengerCar order = orderForPassengerCarDao.getOrderForPassengerCar(orderNumber);
            ConsoleHelper.writeMessage(order.toString());
        }
    }
}

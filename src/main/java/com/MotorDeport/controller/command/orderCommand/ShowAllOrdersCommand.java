package com.MotorDeport.controller.command.orderCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.dao.orderDao.OrderForLorryDao;
import com.MotorDeport.dao.orderDao.OrderForPassengerCarDao;
import com.MotorDeport.View.ConsoleHelper;

import java.io.IOException;

public class ShowAllOrdersCommand implements Command{
    @Override
    public void execute() throws IOException, ClassNotFoundException {
        ConsoleHelper.writeMessage("Укажите тип заявки:\n 1 - Грузовой перевозка" +
                                    "\n 2 - Пассажирская перевозка");
        int typeBody = ConsoleHelper.readInt();

        if (typeBody==1){
            OrderForLorryDao orderForLorryDao = new OrderForLorryDao();
            orderForLorryDao.showDatabaseOrderForLorry();
        } else if (typeBody==2){
            OrderForPassengerCarDao orderForPassengerCarDao = new OrderForPassengerCarDao();
            orderForPassengerCarDao.showDatabaseOrderForLorry();
        }
    }
}

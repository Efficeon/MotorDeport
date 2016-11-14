package com.MotorDeport.controller.command.orderCommand;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.exception.ExceptionPassengerCarSelectionNotFound;
import com.MotorDeport.exception.ExceptionRouteSelectionNotFound;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.factory.orderForTransportFactory.OrderForLorryFactory;
import com.MotorDeport.model.factory.orderForTransportFactory.OrderForPassengerCarFactory;

import java.io.IOException;

public class CreateOrderCommand implements Command {
    @Override
    public void execute() throws IOException {
        ConsoleHelper.writeMessage("Укажите тип заявки (1-Грузовой перевозка; " +
                                   "2-Пассажирская перевозка;");
        int typeBody = ConsoleHelper.readInt();

        if (typeBody==1){
            ConsoleHelper.writeMessage("Укажите следующие характеристики груза: ");
            ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Крытый кузов; 2-Изотермический кузов; " +
                                      "3-Рефрижераторный кузов; 4-Открытый кузов): ");
            int typeTransport = ConsoleHelper.readInt();

            ConsoleHelper.writeMessage("Длинна: ");
            double lengthOfBody = ConsoleHelper.readDouble();

            ConsoleHelper.writeMessage("Ширина: ");
            double widthOfBody = ConsoleHelper.readDouble();

            ConsoleHelper.writeMessage("Высота: ");
            double heightOfBody = ConsoleHelper.readDouble();

            ConsoleHelper.writeMessage("Вес: ");
            int cargoWeight = ConsoleHelper.readInt();

            ConsoleHelper.writeMessage("Номер маршрута: ");
            int routeNumber = ConsoleHelper.readInt();

            OrderForLorryFactory orderForLorryFactory= new OrderForLorryFactory();
            try {
                orderForLorryFactory.create(typeTransport, lengthOfBody, widthOfBody, heightOfBody,
                                            cargoWeight, routeNumber);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ExceptionLorrySelectionNotFound exceptionLorrySelectionNotFound) {
                ConsoleHelper.writeMessage("Автомобиль с указанными характеристиками отсутствует.");
            } catch (ExceptionRouteSelectionNotFound exceptionRouteSelectionNotFound) {
                ConsoleHelper.writeMessage("Маршрут с указанными характеристиками отсутствует.");
            }
        } else if (typeBody==2){
            ConsoleHelper.writeMessage("Укажите следующие данные для перевозки: ");

            ConsoleHelper.writeMessage("Укажите тип автомобиля (1-Хэ́тчбек; 2-Микрокар; 3-Минивен; 4-Седан): ");
            int typeTransport = ConsoleHelper.readInt();

            ConsoleHelper.writeMessage("Укажите количество пассажиров: ");
            int numberOfPassengers = ConsoleHelper.readInt();

            ConsoleHelper.writeMessage("Укажите номер маршрута: ");
            int routeNumber = ConsoleHelper.readInt();

            OrderForPassengerCarFactory orderForPassCarFactory = new OrderForPassengerCarFactory();
            try {
                orderForPassCarFactory.create(typeTransport, numberOfPassengers, routeNumber);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ExceptionRouteSelectionNotFound exceptionRouteSelectionNotFound) {
                ConsoleHelper.writeMessage("Маршрут с указанными характеристиками отсутствует.");
            } catch (ExceptionPassengerCarSelectionNotFound exceptionPassengerCarSelectionNotFound) {
                ConsoleHelper.writeMessage("Автомобиль с указанными характеристиками отсутствует.");
            }
        }
    }
}

package com.MotorDeport.View;

import com.MotorDeport.controller.command.Command;
import com.MotorDeport.controller.command.driverCommand.AddCarToDriverCommand;
import com.MotorDeport.controller.command.driverCommand.CreateDriverCommand;
import com.MotorDeport.controller.command.driverCommand.ShowAllDriversCommand;
import com.MotorDeport.controller.command.driverCommand.ShowDriverCommand;
import com.MotorDeport.controller.command.lorryComand.AddDriverToLorryCommand;
import com.MotorDeport.controller.command.lorryComand.CreateLorryCommand;
import com.MotorDeport.controller.command.lorryComand.ShowAllLorryCommand;
import com.MotorDeport.controller.command.lorryComand.ShowLorryCommand;
import com.MotorDeport.controller.command.orderCommand.CreateOrderCommand;
import com.MotorDeport.controller.command.orderCommand.ShowAllOrdersCommand;
import com.MotorDeport.controller.command.orderCommand.ShowOrderCommand;
import com.MotorDeport.controller.command.passenderCarCommand.AddDriverToPassCarCommand;
import com.MotorDeport.controller.command.passenderCarCommand.CreatePassCarCommand;
import com.MotorDeport.controller.command.passenderCarCommand.ShowAllPassCarCommand;
import com.MotorDeport.controller.command.passenderCarCommand.ShowPassCarCommand;
import com.MotorDeport.controller.command.routeCommand.AddRouteCommand;
import com.MotorDeport.controller.command.routeCommand.ShowAllRouteCommand;
import com.MotorDeport.controller.command.routeCommand.ShowRouteCommand;

import java.io.IOException;

public class CommandExecutor {

    public void askOperation() throws IOException, ClassNotFoundException {
        while (true) {
            ConsoleHelper.writeMessage("Выберите раздел:  \n1 - Заявки на перевозку " + "\n" +
                    "2 - Маршруты\n" +
                    "3 - Водители\n" +
                    "4 - Грузовые автомобили\n" +
                    "5 - Легковые автомобили\n" +
                    "6 - Справка\n" +
                    "7 - Выход из программы");

            int operation = ConsoleHelper.readInt();
            int orderOperation;
            Command command;
            switch (operation) {
                case 1:
                    ConsoleHelper.writeMessage("\nВыберите задачу: \n1 - Создат заявку" +
                            "\n2 - Показать заявку по номеру" +
                            "\n3 - Показать все заявки" +
                            "\n4 - Выход в предыдущее меню");
                    orderOperation = ConsoleHelper.readInt();
                    switch (orderOperation) {
                        case 1:
                            command = new CreateOrderCommand();
                            command.execute();
                            break;
                        case 2:
                            command = new ShowOrderCommand();
                            command.execute();
                            break;
                        case 3:
                            command = new ShowAllOrdersCommand();
                            command.execute();
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    ConsoleHelper.writeMessage("\nВыберите задачу: \n1 - Создат маршрут" +
                            "\n2 - Показать маршрут по номеру" +
                            "\n3 - Показать все маршруты" +
                            "\n4 - Выход в предыдущее меню");
                    orderOperation = ConsoleHelper.readInt();
                    switch (orderOperation) {
                        case 1:
                            command = new AddRouteCommand();
                            command.execute();
                            break;
                        case 2:
                            command = new ShowRouteCommand();
                            command.execute();
                            break;
                        case 3:
                            command = new ShowAllRouteCommand();
                            command.execute();
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Выберите задачу: \n1 - Добавить водителя" +
                            "\n2 - Закрепить автомобиль за водителем" +
                            "\n3 - Показать данные водителя по номеру паспорта" +
                            "\n4 - Показать данные всех водителей" +
                            "\n5 - Выход в предыдущее меню");
                    orderOperation = ConsoleHelper.readInt();
                    switch (orderOperation) {
                        case 1:
                            command = new CreateDriverCommand();
                            command.execute();
                            break;
                        case 2:
                            command = new AddCarToDriverCommand();
                            command.execute();
                            break;
                        case 3:
                            command = new ShowDriverCommand();
                            command.execute();
                            break;
                        case 4:
                            command = new ShowAllDriversCommand();
                            command.execute();
                            break;
                        case 5:
                            break;
                    }
                break;
                case 4:
                    ConsoleHelper.writeMessage("Выберите задачу: \n1 - Добавить грузовой автомобиль" +
                            "\n2 - Закрепить водителя за грузовым автомобилем" +
                            "\n3 - Показать данные грузового автомобиля по автомобильному номеру" +
                            "\n4 - Показать данные всех грузовых автомобилей по типу" +
                            "\n5 - Выход в предыдущее меню");
                    orderOperation = ConsoleHelper.readInt();
                    switch (orderOperation) {
                        case 1:
                            command = new CreateLorryCommand();
                            command.execute();
                            break;
                        case 2:
                            command = new AddDriverToLorryCommand();
                            command.execute();
                            break;
                        case 3:
                            command = new ShowLorryCommand();
                            command.execute();
                            break;
                        case 4:
                            command = new ShowAllLorryCommand();
                            command.execute();
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Выберите задачу: \n1 - Добавить легковой автомобиль" +
                            "\n2 - Закрепить водителя за легковым автомобилем" +
                            "\n3 - Показать данные легкового автомобиля по автомобильному номеру" +
                            "\n4 - Показать данные всех легковых автомобилей по типу" +
                            "\n5 - Выход в предыдущее меню");
                    orderOperation = ConsoleHelper.readInt();
                    switch (orderOperation) {
                        case 1:
                            command = new CreatePassCarCommand();
                            command.execute();
                            break;
                        case 2:
                            command = new AddDriverToPassCarCommand();
                            command.execute();
                            break;
                        case 3:
                            command = new ShowPassCarCommand();
                            command.execute();
                            break;
                        case 4:
                            command = new ShowAllPassCarCommand();
                            command.execute();
                            break;
                        default:
                            break;
                    }
                    break;
                case 6:
                    break;
                case 7:
                    return;
            }
        }
    }
}


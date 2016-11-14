package com.MotorDeport.dao.orderDao;

import com.MotorDeport.dao.passengerCarDao.HatchbackDao;
import com.MotorDeport.dao.passengerCarDao.MicrocarDao;
import com.MotorDeport.dao.passengerCarDao.MinivanDao;
import com.MotorDeport.dao.passengerCarDao.SedanDao;
import com.MotorDeport.dao.routeDao.RouteDao;
import com.MotorDeport.exception.ExceptionPassengerCarSelectionNotFound;
import com.MotorDeport.exception.ExceptionRouteSelectionNotFound;
import com.MotorDeport.model.Car;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.order.OrderForPassengerCar;
import com.MotorDeport.model.route.Route;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderForPassengerCarDao {
    private static List<OrderForPassengerCar> ordersForPassengerCar = new ArrayList<>();

    public OrderForPassengerCar createOrderForPassengerCar(int typeTransport,int numberOfPassengers,
                                                           int routeNumber) throws IOException, ClassNotFoundException, ExceptionPassengerCarSelectionNotFound, ExceptionRouteSelectionNotFound {

        if (getDatabaseOrderForForPassengerCar()!=null){
            ordersForPassengerCar = getDatabaseOrderForForPassengerCar();
        }

        int orderNumber;
        if (ordersForPassengerCar.size() == 0){
            orderNumber = 1;
        }else {
            orderNumber = ordersForPassengerCar.size();
        }

        Car car= typePassengerCarSelection(typeTransport, numberOfPassengers);

        RouteDao routeDao = new RouteDao();
        Route route = routeDao.routeSelection(routeNumber);
        OrderForPassengerCar order = new OrderForPassengerCar(orderNumber, numberOfPassengers, car, route);
        ordersForPassengerCar.add(order);
        addToDatabaseOrderForPassengerCar();

        return order;
    }

    private Car typePassengerCarSelection(int typeTransport, int numberOfPassengers) throws ExceptionPassengerCarSelectionNotFound, IOException, ClassNotFoundException {
        Car car = null;
        switch (numberOfPassengers){
            case 1:
                HatchbackDao hatchbackDao = new HatchbackDao();
                car = hatchbackDao.passengerCarSelection(numberOfPassengers);
                break;
            case 2:
                MicrocarDao microcarDao = new MicrocarDao();
                car = microcarDao.passengerCarSelection(numberOfPassengers);
                break;
            case 3:
                MinivanDao minivanDao = new MinivanDao();
                car = minivanDao.passengerCarSelection(numberOfPassengers);
                break;
            case 4:
                SedanDao sedanDao = new SedanDao();
                car = sedanDao.passengerCarSelection(numberOfPassengers);
        }
        return car;
    }

    public void addToDatabaseOrderForPassengerCar() throws IOException {
        ObjectOutputStream orderOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/order/OrderForPassengerCar.dat"));
        orderOutputStream.writeObject(ordersForPassengerCar);
        orderOutputStream.close();
    }

    public List<OrderForPassengerCar> getDatabaseOrderForForPassengerCar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream orderInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/order/OrderForPassengerCar.dat"))){
            return  (List<OrderForPassengerCar>) orderInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"OrderForPassengerCar.dat\"\" (EOFException)");
            return null;
        }
    }

    public OrderForPassengerCar getOrderForPassengerCar(int orderNumber) throws IOException, ClassNotFoundException {
        ordersForPassengerCar = getDatabaseOrderForForPassengerCar();
        for (OrderForPassengerCar order : ordersForPassengerCar){
            if (order.getOrderNumber() == orderNumber) return order;
        }
        return null;
        //TODO //дописать исключение
    }

    public void showDatabaseOrderForLorry() throws IOException, ClassNotFoundException {
        if (getDatabaseOrderForForPassengerCar()!=null){
            for (OrderForPassengerCar order : ordersForPassengerCar){
                ConsoleHelper.writeMessage(order.toString());
            }
        }
    }
}

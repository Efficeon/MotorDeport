package com.MotorDeport.dao.orderDao;

import com.MotorDeport.dao.lorryDao.*;
import com.MotorDeport.dao.routeDao.RouteDao;
import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.exception.ExceptionRouteSelectionNotFound;
import com.MotorDeport.model.Car;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.order.OrderForLorry;
import com.MotorDeport.model.route.Route;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderForLorryDao implements Serializable{
    private List<OrderForLorry> ordersForLorry = new ArrayList<>();

    public OrderForLorry createOrderForLorry(int typeTransport,
                                             double lengthOfBody,
                                             double widthOfBody,
                                             double heightOfBody,
                                             int cargoWeight,
                                             int routeNumber) throws IOException, ClassNotFoundException, ExceptionLorrySelectionNotFound, ExceptionRouteSelectionNotFound {

        if (getDatabaseOrderForLorry()!=null){
            ordersForLorry = getDatabaseOrderForLorry();
        }

        int orderNumber;
        if (ordersForLorry.size() == 0){
            orderNumber = 1;
        }else {
            orderNumber = ordersForLorry.size();
        }

        Car car= typeLorrySelection(typeTransport, lengthOfBody, widthOfBody, heightOfBody, cargoWeight);

        RouteDao routeDao = new RouteDao();
        Route route = routeDao.routeSelection(routeNumber);
        OrderForLorry order = new OrderForLorry(orderNumber, lengthOfBody, widthOfBody, heightOfBody,
                                                cargoWeight, car, route);
        ordersForLorry.add(order);
        addToDatabaseOrderForLorry();

        return order;
    }

    private Car typeLorrySelection(int typeTransport, double lengthOfBody, double widthOfBody,
                                   double heightOfBody, int cargoWeight) throws ExceptionLorrySelectionNotFound, IOException, ClassNotFoundException {
        Car car = null;
        switch (typeTransport){
            case 1:
                CoveredBodyLorryDao coveredBodyLorryDao = new CoveredBodyLorryDao();
                car = coveredBodyLorryDao.lorrySelection(lengthOfBody, widthOfBody, heightOfBody, cargoWeight);
                break;
            case 2:
                IsothermalLorryDao isothermalLorryDao = new IsothermalLorryDao();
                car = isothermalLorryDao.lorrySelection(lengthOfBody, widthOfBody, heightOfBody, cargoWeight);
                break;
            case 3:
                RefrigeratorLorryDao refrigeratorLorryDao = new RefrigeratorLorryDao();
                car = refrigeratorLorryDao.lorrySelection(lengthOfBody, widthOfBody, heightOfBody, cargoWeight);
                break;
            case 4:
                UncoveredBodyLorryDao uncoveredBodyLorryDao = new UncoveredBodyLorryDao();
                car = uncoveredBodyLorryDao.lorrySelection(lengthOfBody, widthOfBody, heightOfBody, cargoWeight);
        }
        return car;
    }

    public void addToDatabaseOrderForLorry() throws IOException {
        ObjectOutputStream orderOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/order/OrderForLorry.dat"));
        orderOutputStream.writeObject(ordersForLorry);
        orderOutputStream.close();
    }

    public List<OrderForLorry> getDatabaseOrderForLorry() throws IOException, ClassNotFoundException {
        try (ObjectInputStream orderInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/order/OrderForLorry.dat"))){
            return  (List<OrderForLorry>) orderInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"ConvertedBodyLorry.dat\"\" (EOFException)");
            return null;
        }
    }

    public OrderForLorry getOrderForLorry(int orderNumber) throws IOException, ClassNotFoundException {
        ordersForLorry = getDatabaseOrderForLorry();
        for (OrderForLorry order : ordersForLorry){
            if (order.getOrderNumber() == orderNumber) return order;
        }
        return null;
        //TODO //дописать исключение
    }

    public void showDatabaseOrderForLorry() throws IOException, ClassNotFoundException {
        if (getDatabaseOrderForLorry()!=null){
            ordersForLorry = getDatabaseOrderForLorry();
            for (OrderForLorry order : ordersForLorry){
                ConsoleHelper.writeMessage(order.toString());
            }
        }
    }
}

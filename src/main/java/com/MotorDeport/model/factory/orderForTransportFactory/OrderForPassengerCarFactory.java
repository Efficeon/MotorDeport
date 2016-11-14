package com.MotorDeport.model.factory.orderForTransportFactory;

import com.MotorDeport.dao.orderDao.OrderForPassengerCarDao;
import com.MotorDeport.exception.ExceptionPassengerCarSelectionNotFound;
import com.MotorDeport.exception.ExceptionRouteSelectionNotFound;
import com.MotorDeport.model.order.OrderForPassengerCar;

import java.io.IOException;

public class OrderForPassengerCarFactory {
    public OrderForPassengerCar create(int typeTransport,int numberOfPassengers, int routeNumber) throws ClassNotFoundException, ExceptionRouteSelectionNotFound, ExceptionPassengerCarSelectionNotFound, IOException {
        OrderForPassengerCarDao orderForPassengerCarDao = new OrderForPassengerCarDao();
        OrderForPassengerCar orderForPassengerCar;
        return orderForPassengerCarDao.createOrderForPassengerCar(typeTransport, numberOfPassengers, routeNumber);
    }
}

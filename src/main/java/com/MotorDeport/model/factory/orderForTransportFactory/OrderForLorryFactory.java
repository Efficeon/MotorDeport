package com.MotorDeport.model.factory.orderForTransportFactory;

import com.MotorDeport.dao.orderDao.OrderForLorryDao;
import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.exception.ExceptionRouteSelectionNotFound;
import com.MotorDeport.model.order.OrderForLorry;

import java.io.IOException;

public class OrderForLorryFactory {
    public OrderForLorry create(int typeTransport,
                                double lengthOfBody,
                                double widthOfBody,
                                double heightOfBody,
                                int cargoWeight,
                                int routeNumber) throws ClassNotFoundException, ExceptionLorrySelectionNotFound, ExceptionRouteSelectionNotFound, IOException {

        OrderForLorryDao orderForLorryDao = new OrderForLorryDao();
        OrderForLorry orderForLorry;
        return orderForLorryDao.createOrderForLorry(typeTransport,lengthOfBody, widthOfBody, heightOfBody,
                                                    cargoWeight, routeNumber);
    }
}

package com.MotorDeport.model.order;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.route.Route;

import java.io.Serializable;

public class OrderForPassengerCar implements Serializable{
    private int orderNumber;              //Номер заявки
    private int numberOfPassengers;       //Количество пассажиров
    private Car car;                      //Автомобиль
    private Route route;                  //Маршрут

    public OrderForPassengerCar(int orderNumber, int numberOfPassengers, Car car, Route route) {
        this.orderNumber = orderNumber;
        this.numberOfPassengers = numberOfPassengers;
        this.car = car;
        this.route = route;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Заявка на пассажирскую перевозку: " + "\n" +
                "маршрут: " + route.getRoutingNumber() + ", "
                + route.getPointOfDeparture() + " - " + route.getPointOfDeparture() + "\n" +
                "Автомобиль: " + car.getLicensePlate() + ", водитель: " + car.getDriver().getName() + ", " +
                "паспорт: " + car.getDriver().getPassportID() + "\n" +
                "\n-----------------------------------------------------------------------------------" +
                "-----------------------------------------------------------";
    }
}

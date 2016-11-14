package com.MotorDeport.model.order;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.route.Route;

import java.io.Serializable;

public class OrderForLorry implements Serializable{
    private int orderNumber;         //Номер заявки
    private double lengthOfBody;        //Длинна груза
    private double widthOfBody;         //Ширина груза
    private double heightOfBody;        //Высота груза
    private int cargoWeight;         //Вес груза
    private Car car;                 //Автомобиль
    private Route route;             //Маршрут

    public OrderForLorry(int orderNumber,
                         double lengthOfBody,
                         double widthOfBody,
                         double heightOfBody,
                         int cargoWeight,
                         Car car,
                         Route route){
        this.orderNumber = orderNumber;
        this.lengthOfBody = lengthOfBody;
        this.widthOfBody = widthOfBody;
        this.heightOfBody = heightOfBody;
        this.cargoWeight = cargoWeight;
        this.car = car;
        this.route = route;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getLengthOfBody() {
        return lengthOfBody;
    }

    public void setLengthOfBody(double lengthOfBody) {
        this.lengthOfBody = lengthOfBody;
    }

    public double getWidthOfBody() {
        return widthOfBody;
    }

    public void setWidthOfBody(double widthOfBody) {
        this.widthOfBody = widthOfBody;
    }

    public double getHeightOfBody() {
        return heightOfBody;
    }

    public void setHeightOfBody(int heightOfBody) {
        this.heightOfBody = heightOfBody;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Car getCar() {
        return car;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
         return "Заявка на грузовую перевозку: " + "\n" +
                "маршрут: " + route.getRoutingNumber() + ", "
                + route.getPointOfDeparture() + " - " + route.getPointOfDestination() + "\n" +
                "Автомобиль: " + car.getLicensePlate() + ", водитель: " + car.getDriver().getName() + ", " +
                "паспорт: " + car.getDriver().getPassportID() + "\n" +
                "Характеристика груза:" + "\n" +
                "длинна: " + lengthOfBody + "м., ширина: " +  widthOfBody + "м., высота: " + heightOfBody +
                "м., вес: " + cargoWeight + "кг." +
                 "\n-----------------------------------------------------------------------------------" +
                 "-----------------------------------------------------------";
    }
}

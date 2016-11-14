package com.MotorDeport.dao.passengerCarDao;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.passengerCar.Minivan;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MinivanDao extends PassengerCarDao implements Serializable{
    @Override
    public PassengerCar createPassengerCar(String licensePlate,
                                           int numberOfPassengers,
                                           boolean technicalTrunk) throws IOException, ClassNotFoundException {
        PassengerCar minivan = null;

        if (getDatabasePassengerCar()!=null){
            passengerCars = getDatabasePassengerCar();
        }

        if (passengerCars.containsKey(licensePlate)){
            minivan = passengerCars.get(licensePlate);
        } else {
            minivan = new Minivan(licensePlate, numberOfPassengers, technicalTrunk);

            passengerCars.put(minivan.getLicensePlate(), minivan);
            addToDatabasePassengerCar();
        }
        return minivan;
    }

    public Minivan getMinivan(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabasePassengerCar()!=null){
            for (Map.Entry lorry : getDatabasePassengerCar().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (Minivan) lorry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void addDriverToPassengerCar(Car car, Driver driver) throws IOException, ClassNotFoundException {
        Minivan passengerCarUpdate = (Minivan) passengerCars.get(car.getLicensePlate());
        passengerCarUpdate.setDriver(driver);
        passengerCars.replace(car.getLicensePlate(), passengerCarUpdate);
        addToDatabasePassengerCar();
        getDatabasePassengerCar();
    }

    @Override
    public void addToDatabasePassengerCar() throws IOException {
        ObjectOutputStream passengerCarOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Minivan.dat"));
        passengerCarOutputStream.writeObject(passengerCars);
        passengerCarOutputStream.close();
    }

    @Override
    public Map<String, PassengerCar> getDatabasePassengerCar() throws ClassNotFoundException, IOException {
        try (ObjectInputStream passengerCarInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Minivan.dat"))){
            return  (HashMap<String, PassengerCar>) passengerCarInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"Minivan.dat\" (EOFException)");
            return null;
        }
    }
}

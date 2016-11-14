package com.MotorDeport.dao.passengerCarDao;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.passengerCar.PassengerCar;
import com.MotorDeport.model.passengerCar.Sedan;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SedanDao extends PassengerCarDao implements Serializable{
    @Override
    public PassengerCar createPassengerCar(String licensePlate,
                                           int numberOfPassengers,
                                           boolean technicalTrunk) throws IOException, ClassNotFoundException {
        PassengerCar sedan = null;

        if (getDatabasePassengerCar()!=null){
            passengerCars = getDatabasePassengerCar();
        }

        if (passengerCars.containsKey(licensePlate)){
            sedan = passengerCars.get(licensePlate);
        } else {
            sedan = new Sedan(licensePlate, numberOfPassengers, technicalTrunk);

            passengerCars.put(sedan.getLicensePlate(), sedan);
            addToDatabasePassengerCar();
        }
        return sedan;
    }

    public void addDriverToPassengerCar(Car car, Driver driver) throws IOException, ClassNotFoundException {
        Sedan passengerCarUpdate = (Sedan) passengerCars.get(car.getLicensePlate());
        passengerCarUpdate.setDriver(driver);
        passengerCars.replace(car.getLicensePlate(), passengerCarUpdate);
        addToDatabasePassengerCar();
        getDatabasePassengerCar();
    }

    public Sedan getSedan(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabasePassengerCar()!=null){
            for (Map.Entry lorry : getDatabasePassengerCar().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (Sedan) lorry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void addToDatabasePassengerCar() throws IOException {
        ObjectOutputStream passengerCarOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Sedan.dat"));
        passengerCarOutputStream.writeObject(passengerCars);
        passengerCarOutputStream.close();
    }

    @Override
    public Map<String, PassengerCar> getDatabasePassengerCar() throws ClassNotFoundException, IOException {
        try (ObjectInputStream passengerCarInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Sedan.dat"))){
            return  (HashMap<String, PassengerCar>) passengerCarInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"Sedan.dat\" (EOFException)");
            return null;
        }
    }
}

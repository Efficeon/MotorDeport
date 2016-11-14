package com.MotorDeport.dao.passengerCarDao;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.passengerCar.Hatchback;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HatchbackDao extends PassengerCarDao implements Serializable{
    @Override
    public PassengerCar createPassengerCar(String licensePlate,
                                           int numberOfPassengers,
                                           boolean technicalTrunk) throws IOException, ClassNotFoundException {
        PassengerCar hatchback = null;

        if (getDatabasePassengerCar()!=null){
            passengerCars = getDatabasePassengerCar();
        }

        if (passengerCars.containsKey(licensePlate)){
            hatchback = passengerCars.get(licensePlate);
        } else {
            hatchback = new Hatchback(licensePlate, numberOfPassengers, technicalTrunk);

            passengerCars.put(hatchback.getLicensePlate(), hatchback);
            addToDatabasePassengerCar();
        }
        return hatchback;
    }

    public Hatchback getHatchback(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabasePassengerCar()!=null){
            for (Map.Entry lorry : getDatabasePassengerCar().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (Hatchback) lorry.getValue();
                }
            }
        }
        return null;
    }

    public void addDriverToPassengerCar(Car car, Driver driver) throws IOException, ClassNotFoundException {
        Hatchback passengerCarUpdate = (Hatchback) passengerCars.get(car.getLicensePlate());
        passengerCarUpdate.setDriver(driver);
        passengerCars.replace(car.getLicensePlate(), passengerCarUpdate);
        addToDatabasePassengerCar();
        getDatabasePassengerCar();
    }

    @Override
    public void addToDatabasePassengerCar() throws IOException {
        ObjectOutputStream passengerCarOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Hatchback.dat"));
        passengerCarOutputStream.writeObject(passengerCars);
        passengerCarOutputStream.close();
    }

    @Override
    public Map<String, PassengerCar> getDatabasePassengerCar() throws ClassNotFoundException, IOException {
        try (ObjectInputStream passengerCarInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Hatchback.dat"))){
            return  (HashMap<String, PassengerCar>) passengerCarInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"Hatchback.dat\" (EOFException)");
            return null;
        }
    }
}


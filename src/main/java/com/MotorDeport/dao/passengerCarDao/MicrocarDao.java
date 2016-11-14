package com.MotorDeport.dao.passengerCarDao;

import com.MotorDeport.model.Car;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.passengerCar.Microcar;
import com.MotorDeport.model.passengerCar.PassengerCar;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MicrocarDao extends PassengerCarDao implements Serializable {
    @Override
    public PassengerCar createPassengerCar(String licensePlate,
                                           int numberOfPassengers,
                                           boolean technicalTrunk) throws IOException, ClassNotFoundException {
        PassengerCar microcar = null;

        if (getDatabasePassengerCar() != null) {
            passengerCars = getDatabasePassengerCar();
        }

        if (passengerCars.containsKey(licensePlate)) {
            microcar = passengerCars.get(licensePlate);
        } else {
            microcar = new Microcar(licensePlate, numberOfPassengers, technicalTrunk);

            passengerCars.put(microcar.getLicensePlate(), microcar);
            addToDatabasePassengerCar();
        }
        return microcar;
    }

    public void addDriverToPassengerCar(Car car, Driver driver) throws IOException, ClassNotFoundException {
        Microcar passengerCarUpdate = (Microcar) passengerCars.get(car.getLicensePlate());
        passengerCarUpdate.setDriver(driver);
        passengerCars.replace(car.getLicensePlate(), passengerCarUpdate);
        addToDatabasePassengerCar();
        getDatabasePassengerCar();
    }

    public Microcar getMicrocar(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabasePassengerCar()!=null){
            for (Map.Entry lorry : getDatabasePassengerCar().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (Microcar) lorry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void addToDatabasePassengerCar() throws IOException {
        ObjectOutputStream passengerCarOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Microcar.dat"));
        passengerCarOutputStream.writeObject(passengerCars);
        passengerCarOutputStream.close();
    }

    @Override
    public Map<String, PassengerCar> getDatabasePassengerCar() throws ClassNotFoundException, IOException {
        try (ObjectInputStream passengerCarInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/passengerCar/Microcar.dat"))) {
            return (HashMap<String, PassengerCar>) passengerCarInputStream.readObject();
        } catch (EOFException e) {
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"Microcar.dat\" (EOFException)");
            return null;
        }
    }
}

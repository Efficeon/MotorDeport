package com.MotorDeport.dao.employeeDao;

import com.MotorDeport.model.Car;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DriverDao implements Serializable{
    private Map<String, Driver> drivers = new HashMap<>();

    public Driver createDriver(String name, String passportID) throws IOException, ClassNotFoundException {
        Driver driver = null;

        if (getDatabaseDriver() != null) {
            drivers = getDatabaseDriver();
        }

        if (drivers.containsKey(name)) {
            driver = drivers.get(name);
        } else {
            driver = new Driver(name, passportID);

            drivers.put(driver.getPassportID(), driver);
            addToDatabaseDriver();
        }
        return driver;
    }

    public void addToDatabaseDriver() throws IOException {
        ObjectOutputStream driverOutputStream = new ObjectOutputStream(new FileOutputStream("./driver/Driver.dat"));
        driverOutputStream.writeObject(drivers);
        driverOutputStream.close();
    }

    public Map<String, Driver> getDatabaseDriver()throws IOException, ClassNotFoundException {
        try (ObjectInputStream driverInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/driver/Driver.dat"))) {
            return (HashMap<String, Driver>) driverInputStream.readObject();
        } catch (EOFException e) {
            return null;
        }
    }

    public void showDatabaseDriver() throws IOException, ClassNotFoundException {
        if (getDatabaseDriver() != null) {
            for (Map.Entry driver : getDatabaseDriver().entrySet()) {
                ConsoleHelper.writeMessage(driver.getValue().toString());
            }
        }
    }

    public Driver gerDriver(String passportID)  {
        try {
            if (getDatabaseDriver() != null) {
                for (Map.Entry driver : getDatabaseDriver().entrySet()) {
                    if(((Driver) driver.getValue()).getPassportID().equals(passportID)) {
                        return (Driver) driver.getValue();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ConsoleHelper.writeMessage("Запрашиваемые данные не найдены");
        }
        return null;
    }

    public void addLorryToDriver(Driver driver, Car car) throws IOException, ClassNotFoundException {
        drivers = getDatabaseDriver();
        Driver driverUpdate = drivers.get(driver.getPassportID());
        driverUpdate.setCar(car);
        drivers.replace(driver.getPassportID(), driverUpdate);
        addToDatabaseDriver();
    }
}


package com.MotorDeport.dao.lorryDao;

import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.lorry.Lorry;
import com.MotorDeport.model.lorry.RefrigeratorLorry;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RefrigeratorLorryDao implements LorryDao, Serializable {
    private static Map<String, RefrigeratorLorry> refrigeratorLorries = new HashMap<>();

    public RefrigeratorLorry createRefrigeratorLorry(String licensePlate, double carryingCapacity,
                                                  double lengthOfBody, double widthOfBody, double heightOfBody,
                                                  boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        RefrigeratorLorry lorry = null;

        if (getDatabaseRefrigeratorLorry()!=null){
            refrigeratorLorries = getDatabaseRefrigeratorLorry();
        }

        if (refrigeratorLorries.containsKey(licensePlate)){
            lorry = refrigeratorLorries.get(licensePlate);
        } else {
            lorry = new RefrigeratorLorry(licensePlate, carryingCapacity, lengthOfBody,
                    widthOfBody, heightOfBody, technicalDiagnostics);

            refrigeratorLorries.put(lorry.getLicensePlate(), lorry);
            addToDatabaseLorry();
        }
        return lorry;
    }

    public void addToDatabaseLorry() throws IOException {
        ObjectOutputStream lorryOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/RefrigeratorLorry.dat"));
        lorryOutputStream.writeObject(refrigeratorLorries);
        lorryOutputStream.close();
    }

    public Map<String, RefrigeratorLorry> getDatabaseRefrigeratorLorry() throws IOException, ClassNotFoundException {
        try (ObjectInputStream lorryInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/RefrigeratorLorry.dat"))){
            return  (HashMap<String, RefrigeratorLorry>) lorryInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"RefrigeratorLorry.dat\" (EOFException)");
            return null;
        }
    }

    public void showDatabaseRefrigeratorLorry() throws IOException, ClassNotFoundException {
        if (getDatabaseRefrigeratorLorry()!=null){
            for (Map.Entry lorry : getDatabaseRefrigeratorLorry().entrySet()){
                ConsoleHelper.writeMessage(lorry.getValue().toString());
            }
        }
    }

    public RefrigeratorLorry getRefrigeratorLorry(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabaseRefrigeratorLorry()!=null){
            for (Map.Entry lorry : getDatabaseRefrigeratorLorry().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (RefrigeratorLorry) lorry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void addDriverToLorry(Lorry lorry, Driver driver) throws IOException, ClassNotFoundException {
        RefrigeratorLorry lorryUpdate = refrigeratorLorries.get(lorry.getLicensePlate());
        lorryUpdate.setDriver(driver);
        refrigeratorLorries.replace(lorry.getLicensePlate(), lorryUpdate);
        addToDatabaseLorry();
        getDatabaseRefrigeratorLorry();
    }

    @Override
    public Lorry lorrySelection(double lengthOfBody, double widthOfBody, double heightOfBody, int carryingCapacity) throws IOException, ClassNotFoundException, ExceptionLorrySelectionNotFound {
        getDatabaseRefrigeratorLorry();
        Lorry lorrySelection = null;
        for (Map.Entry<String, RefrigeratorLorry> lorry : refrigeratorLorries.entrySet()){
            if(lorry.getValue().getLengthOfBody() > lengthOfBody ||
                    lorry.getValue().getWidthOfBody() > widthOfBody ||
                    lorry.getValue().getHeightOfBody() > heightOfBody){
                if (lorry.getValue().getCarryingCapacity() > carryingCapacity ||
                        (lorry.getValue().isTechnicalDiagnostics())){
                    lorrySelection = lorry.getValue();
                }
            } else throw new ExceptionLorrySelectionNotFound();
        }
        return lorrySelection;
    }
}


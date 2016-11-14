package com.MotorDeport.dao.lorryDao;

import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.lorry.Lorry;
import com.MotorDeport.model.lorry.UncoveredBodyLorry;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UncoveredBodyLorryDao implements LorryDao, Serializable {
    private static Map<String, UncoveredBodyLorry> uncoveredBodyLorries = new HashMap<>();

    public UncoveredBodyLorry createUncoveredBodyLorry(String licensePlate, double carryingCapacity,
                                                        double lengthOfBody, double widthOfBody, double heightOfBody,
                                                        boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        UncoveredBodyLorry lorry = null;

        if (getDatabaseUncoveredBodyLorry() != null) {
            uncoveredBodyLorries = getDatabaseUncoveredBodyLorry();
        }

        if (uncoveredBodyLorries.containsKey(licensePlate)) {
            lorry = uncoveredBodyLorries.get(licensePlate);
        } else {
            lorry = new UncoveredBodyLorry(licensePlate, carryingCapacity, lengthOfBody,
                    widthOfBody, heightOfBody, technicalDiagnostics);

            uncoveredBodyLorries.put(lorry.getLicensePlate(), lorry);
            addToDatabaseLorry();
        }
        return lorry;
    }

    public void addToDatabaseLorry() throws IOException {
        ObjectOutputStream lorryOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/UncoveredBodyLorry.dat"));
        lorryOutputStream.writeObject(uncoveredBodyLorries);
        lorryOutputStream.close();
    }

    public Map<String, UncoveredBodyLorry> getDatabaseUncoveredBodyLorry() throws IOException, ClassNotFoundException {
        try (ObjectInputStream lorryInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/UncoveredBodyLorry.dat"))) {
            return (HashMap<String, UncoveredBodyLorry>) lorryInputStream.readObject();
        } catch (EOFException e) {
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"UncoveredBodyLorry.dat\" (EOFException)");
            return null;
        }
    }

    public UncoveredBodyLorry getUncoveredBodyLorry(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabaseUncoveredBodyLorry()!=null){
            for (Map.Entry lorry : getDatabaseUncoveredBodyLorry().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (UncoveredBodyLorry) lorry.getValue();
                }
            }
        }
        return null;
    }

    public void showDatabaseUncoveredBodyLorry() throws IOException, ClassNotFoundException {
        if (getDatabaseUncoveredBodyLorry() != null) {
            for (Map.Entry lorry : getDatabaseUncoveredBodyLorry().entrySet()) {
                ConsoleHelper.writeMessage(lorry.getValue().toString());
            }
        }
    }

    @Override
    public void addDriverToLorry(Lorry lorry, Driver driver) throws IOException, ClassNotFoundException {
        UncoveredBodyLorry lorryUpdate = uncoveredBodyLorries.get(lorry.getLicensePlate());
        lorryUpdate.setDriver(driver);
        uncoveredBodyLorries.replace(lorry.getLicensePlate(), lorryUpdate);
        addToDatabaseLorry();
        getDatabaseUncoveredBodyLorry();
    }

    @Override
    public Lorry lorrySelection(double lengthOfBody, double widthOfBody, double heightOfBody, int carryingCapacity) throws IOException, ClassNotFoundException, ExceptionLorrySelectionNotFound {
        getDatabaseUncoveredBodyLorry();
        Lorry lorrySelection = null;
        for (Map.Entry<String, UncoveredBodyLorry> lorry : uncoveredBodyLorries.entrySet()){
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

package com.MotorDeport.dao.lorryDao;

import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.lorry.CoveredBodyLorry;
import com.MotorDeport.model.lorry.Lorry;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CoveredBodyLorryDao implements LorryDao, Serializable{
    private Map<String, CoveredBodyLorry> coveredBodyLorries = new HashMap<>();

    public CoveredBodyLorry createCoveredBodyLorry(String licensePlate, double carryingCapacity,
                              double lengthOfBody, double widthOfBody, double heightOfBody,
                              boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

        CoveredBodyLorry lorry = null;

        if (getDatabaseCoveredBodyLorry()!=null){
        coveredBodyLorries = getDatabaseCoveredBodyLorry();
        }

        if (coveredBodyLorries.containsKey(licensePlate)){
            lorry = coveredBodyLorries.get(licensePlate);
        } else {
            lorry = new CoveredBodyLorry(licensePlate, carryingCapacity, lengthOfBody,
                    widthOfBody, heightOfBody, technicalDiagnostics);

            coveredBodyLorries.put(lorry.getLicensePlate(), lorry);
            addToDatabaseLorry();
        }
        return lorry;
    }

    public void addToDatabaseLorry() throws IOException {
        ObjectOutputStream lorryOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/ConvertedBodyLorry.dat"));
        lorryOutputStream.writeObject(coveredBodyLorries);
        lorryOutputStream.close();
    }

    public Map<String, CoveredBodyLorry> getDatabaseCoveredBodyLorry() throws IOException, ClassNotFoundException {
        try (ObjectInputStream lorryInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/ConvertedBodyLorry.dat"))){
            return  (HashMap<String, CoveredBodyLorry>) lorryInputStream.readObject();
        } catch (EOFException e){
            //ConsoleHelper.writeMessage("Ошибка работы с файлом \"ConvertedBodyLorry.dat\"\" (EOFException)");
            return null;
        }
    }

    public CoveredBodyLorry getCoveredBodyLorry(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabaseCoveredBodyLorry()!=null){
            for (Map.Entry lorry : getDatabaseCoveredBodyLorry().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (CoveredBodyLorry) lorry.getValue();
                }
            }
        }
        return null;
    }

    public void showDatabaseCoveredBodyLorry() throws IOException, ClassNotFoundException {
        if (getDatabaseCoveredBodyLorry()!=null){
            for (Map.Entry lorry : getDatabaseCoveredBodyLorry().entrySet()){
                ConsoleHelper.writeMessage(lorry.getValue().toString());
            }
        }
    }

    @Override
    public void addDriverToLorry(Lorry lorry, Driver driver) throws IOException, ClassNotFoundException {
        coveredBodyLorries = getDatabaseCoveredBodyLorry();
        CoveredBodyLorry lorryUpdate = coveredBodyLorries.get(lorry.getLicensePlate());
        lorryUpdate.setDriver(driver);
        coveredBodyLorries.replace(lorry.getLicensePlate(), lorryUpdate);
        addToDatabaseLorry();
        getDatabaseCoveredBodyLorry();
    }

    public Lorry lorrySelection(double lengthOfBody, double widthOfBody, double heightOfBody, int carryingCapacity) throws IOException, ClassNotFoundException, ExceptionLorrySelectionNotFound {
        coveredBodyLorries = getDatabaseCoveredBodyLorry();
        Lorry lorrySelection = null;
        for (Map.Entry<String, CoveredBodyLorry> lorry : coveredBodyLorries.entrySet()){
            if(lorry.getValue().getLengthOfBody() >= lengthOfBody &&
               lorry.getValue().getWidthOfBody() >= widthOfBody &&
               lorry.getValue().getHeightOfBody() >= heightOfBody){
                   if (lorry.getValue().getCarryingCapacity() >= carryingCapacity &&
                      (lorry.getValue().isTechnicalDiagnostics())){
                        lorrySelection = lorry.getValue();
                       break;
                }
            } else throw new ExceptionLorrySelectionNotFound();
        }
        return lorrySelection;
    }
}

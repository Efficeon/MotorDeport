package com.MotorDeport.dao.lorryDao;

import com.MotorDeport.exception.ExceptionLorrySelectionNotFound;
import com.MotorDeport.View.ConsoleHelper;
import com.MotorDeport.model.employee.Driver;
import com.MotorDeport.model.lorry.IsothermalLorry;
import com.MotorDeport.model.lorry.Lorry;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IsothermalLorryDao implements LorryDao, Serializable{
        private static Map<String, IsothermalLorry> isothermalLorries = new HashMap<>();

        public IsothermalLorry createIsothermalLorry(String licensePlate, double carryingCapacity,
                                                        double lengthOfBody, double widthOfBody, double heightOfBody,
                                                        boolean technicalDiagnostics) throws IOException, ClassNotFoundException {

            IsothermalLorry lorry = null;

            if (getDatabaseIsothermalLorry()!=null){
                isothermalLorries = getDatabaseIsothermalLorry();
            }

            if (isothermalLorries.containsKey(licensePlate)){
                lorry = isothermalLorries.get(licensePlate);
            } else {
                lorry = new IsothermalLorry(licensePlate, carryingCapacity, lengthOfBody,
                        widthOfBody, heightOfBody, technicalDiagnostics);

                isothermalLorries.put(lorry.getLicensePlate(), lorry);
                addToDatabaseLorry();
            }
            return lorry;
        }

        public void addToDatabaseLorry() throws IOException {
            ObjectOutputStream lorryOutputStream = new ObjectOutputStream(new FileOutputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/IsothermalLorry.dat"));
            lorryOutputStream.writeObject(isothermalLorries);
            lorryOutputStream.close();
        }

        public Map<String, IsothermalLorry> getDatabaseIsothermalLorry() throws IOException, ClassNotFoundException {
            try (ObjectInputStream lorryInputStream = new ObjectInputStream(new FileInputStream("./src/com/JavaStudent/MotorDeport/MotorDeportDatabase/lorry/IsothermalLorry.dat"))){
                return  (HashMap<String, IsothermalLorry>) lorryInputStream.readObject();
            } catch (EOFException e){
                //ConsoleHelper.writeMessage("Ошибка работы с файлом \"IsothermalLorry.dat\"\" (EOFException)");
                return null;
            }
        }

        public void showDatabaseIsothermalLorry() throws IOException, ClassNotFoundException {
            if (getDatabaseIsothermalLorry()!=null){
                for (Map.Entry lorry : getDatabaseIsothermalLorry().entrySet()){
                    ConsoleHelper.writeMessage(lorry.getValue().toString());
                }
            }
        }

    @Override
    public void addDriverToLorry(Lorry lorry, Driver driver) throws IOException, ClassNotFoundException {
        IsothermalLorry lorryUpdate = isothermalLorries.get(lorry.getLicensePlate());
        lorryUpdate.setDriver(driver);
        isothermalLorries.replace(lorry.getLicensePlate(), lorryUpdate);
        addToDatabaseLorry();
        getDatabaseIsothermalLorry();
    }

    public IsothermalLorry getIsothermalLorry(String licensePlate) throws IOException, ClassNotFoundException {
        if (getDatabaseIsothermalLorry()!=null){
            for (Map.Entry lorry : getDatabaseIsothermalLorry().entrySet()){
                if (lorry.getKey().equals(licensePlate)){
                    return (IsothermalLorry) lorry.getValue();
                }
            }
        }
        return null;
    }

    /*public void addDriverToLorry(Lorry lorry, Driver driver) throws IOException, ClassNotFoundException {
        CoveredBodyLorry lorryUpdate = coveredBodyLorries.get(lorry.getLicensePlate());
        lorryUpdate.setDriver(driver);
        coveredBodyLorries.replace(lorry.getLicensePlate(), lorryUpdate);
        addToDatabaseLorry();
        getDatabaseCoveredBodyLorry();
    }*/

    @Override
    public Lorry lorrySelection(double lengthOfBody, double widthOfBody, double heightOfBody, int carryingCapacity) throws IOException, ClassNotFoundException, ExceptionLorrySelectionNotFound {
        getDatabaseIsothermalLorry();
        Lorry lorrySelection = null;
        for (Map.Entry<String, IsothermalLorry> lorry : isothermalLorries.entrySet()){
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


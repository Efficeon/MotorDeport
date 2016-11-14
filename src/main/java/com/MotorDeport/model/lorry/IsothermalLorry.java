package com.MotorDeport.model.lorry;

import java.io.Serializable;

public class IsothermalLorry extends Lorry implements Serializable{
    //static final long serialVersionUID = 5037585225066001448L;

    public IsothermalLorry(String licensePlate,
                           double carryingCapacity,
                           double lengthOfBody,
                           double widthOfBody,
                           double heightOfBody,
                           boolean technicalDiagnostics) {
        super(licensePlate, carryingCapacity, lengthOfBody, widthOfBody, heightOfBody, technicalDiagnostics);
    }

    @Override
    public String toString(){
        String nameDriver = "отсутствует";
        if (this.driver != null){
            nameDriver = driver.getName() + ", Номер паспорта: " +driver.getPassportID();
        }
        return  "Грузовой автомобиль(Изотермический кузов): " +
                "Автомобильные номера: " + this.getLicensePlate() + "; " +
                "Грузоподъемность: " + this.getCarryingCapacity() + "; " +
                "Длинна: " +this.getLengthOfBody() + "; " +
                "Ширина: " + this.getWidthOfBody() + "; " +
                "Высота " + this.getHeightOfBody() + ";\n" +
                "Техническая диагностика: " + this.isTechnicalDiagnostics() + ";\n" +
                "Закрепленный водитель: " + nameDriver +
                "\n-----------------------------------------------------------------------------------" +
                "-----------------------------------------------------------";
    }
}

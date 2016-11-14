package com.MotorDeport.model.lorry;

import java.io.Serializable;

public class UncoveredBodyLorry extends Lorry implements Serializable{
    //static final long serialVersionUID = 5037585225066001448L;
    public UncoveredBodyLorry(String licensePlate,
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
        return  "Грузовой автомобиль(Открытый кузов): " +
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

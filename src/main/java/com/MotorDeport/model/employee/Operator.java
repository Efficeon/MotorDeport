package com.MotorDeport.model.employee;

public class Operator{
    private final String name;
    private final String passportID;

    public Operator(String name, String passportID) {
        this.name = name;
        this.passportID = passportID;
    }

    public String getName() {
        return name;
    }

    public String getPassportID() { return passportID; }
}

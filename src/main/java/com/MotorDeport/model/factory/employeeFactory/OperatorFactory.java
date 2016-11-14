package com.MotorDeport.model.factory.employeeFactory;

import com.MotorDeport.dao.employeeDao.OperatorDao;
import com.MotorDeport.model.employee.Operator;

public class OperatorFactory{

    public Operator create(String name, String passportID) {
       return OperatorDao.createOperator(name, passportID);
    }
}

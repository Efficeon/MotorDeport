package com.MotorDeport.dao.employeeDao;

import com.MotorDeport.model.employee.Operator;

public class OperatorDao {

    public static Operator createOperator(String name, String passportID ) {
        return new Operator(name, passportID);
    }
}

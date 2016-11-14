package com.MotorDeport.controller.command;

import java.io.IOException;

public interface Command {
    void execute() throws IOException, ClassNotFoundException;
}

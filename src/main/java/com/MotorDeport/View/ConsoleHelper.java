package com.MotorDeport.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleHelper {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static int readInt() throws IOException {
        int number = 0;
        try {
            number = Integer.parseInt(bufferedReader.readLine());
        }catch (NumberFormatException e){
            ConsoleHelper.writeMessage("Введены некорректные данные. Повторите ввод.");
            readInt();
        }
        return number;
    }

    public static double readDouble() throws IOException {
        double number = 0;
        try {
            number = Double.parseDouble(bufferedReader.readLine());
        } catch (NumberFormatException e){
            ConsoleHelper.writeMessage("Введены некорректные данные. Повторите ввод");
            readDouble();
        }
        return number;
    }

    public static boolean readBoolean() throws IOException {
        return Boolean.parseBoolean(bufferedReader.readLine());
    }

    public static boolean checkWithRegExp(String Name) {
        Pattern p = Pattern.compile("^[1-6]$");
        Matcher m = p.matcher(Name);
        return m.matches();
    }
}

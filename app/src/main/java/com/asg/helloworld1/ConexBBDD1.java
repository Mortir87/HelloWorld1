package com.asg.helloworld1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexBBDD1 {
    private static final String URL = "jdbc:mysql://192.168.1.163:3306/YOAPRENDO"; //BBDD EJERCICIO
    private static final String USER = "root";
    private static final String PASSWORD = "aitor1234"; //password en docker MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

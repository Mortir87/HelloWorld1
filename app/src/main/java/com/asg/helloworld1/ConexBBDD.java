package com.asg.helloworld1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexBBDD {
    private static final String URL = "jdbc:mysql://10.0.2.2:3306/nuevabd1";
    private static final String USER = "root";
    private static final String PASSWORD = ""; //No uso contraseña en XAMP
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

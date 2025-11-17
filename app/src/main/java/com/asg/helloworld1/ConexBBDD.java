package com.asg.helloworld1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexBBDD {
    //private static final String URL = "jdbc:mysql://10.0.2.2:3306/nuevabd1"; //Xamp en local
    private static final String URL = "jdbc:mysql://192.168.1.163:3306/BaseDatos1"; //Mysql en docker
    private static final String USER = "root";
    //private static final String PASSWORD = ""; //No uso contraseña en XAMP
    private static final String PASSWORD = "aitor1234"; //password en docker

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

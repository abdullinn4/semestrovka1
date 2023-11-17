package com.example.semestrovka.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/semestrovka";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "NargaNargaNarga4";

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Соединение с Базой Данных установлено!");
        } catch (SQLException e) {
            System.out.println("Ошибка установления соединения с Базой Данных!");
            throw new RuntimeException(e);
        }
        return connection;
    }
}

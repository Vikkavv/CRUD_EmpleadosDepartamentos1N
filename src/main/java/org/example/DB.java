package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DB {
    private static final String URL = "jdbc:mariadb://localhost:3306/db_empleados";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection conn = null;

    private DB() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        if(conn == null) new DB();
        return conn;
    }

    public static void closeConnection() throws SQLException {
        conn.close();
        conn = null;
    }
}
package com.Restapidemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class Database {
    protected static Connection initializeDatabase()
        throws SQLException, ClassNotFoundException
    {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/akash?characterEncoding=latin1&allowPublicKeyRetrieval=true&useSSL=false";
        String dbUsername = "akash";
        String dbPassword = "Akash@4016";
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL , dbUsername, dbPassword);
        return con;
    }
}
package com.school.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnectionToDatabase(){

        Connection connection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MYSQL Driver registered");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlineschool?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "ama", "password");
        } catch (ClassNotFoundException e){
            System.out.println("Could not find a MySQL Driver");
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("Connection Failed...");
            e.printStackTrace();
        }

        if(connection != null){
            System.out.println("Connection to DB made");
        }

        return connection;
    }
}

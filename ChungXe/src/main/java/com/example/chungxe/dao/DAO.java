package com.example.chungxe.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection con;

    public DAO() {
        if (con == null) {
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/sqa?allowPublicKeyRetrieval=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "b18dcdt073");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
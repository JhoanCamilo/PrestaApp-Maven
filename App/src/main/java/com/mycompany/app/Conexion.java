package com.mycompany.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String Usuario = "Prestamos";
    String Clave = "";
    String URL = "jdbc:mysql://localhost:3306/pruebas";
    Connection con;
    Statement stmt;
    ResultSet rs;
    public void ValidarDriver(){
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch (ClassNotFoundException e) {
        }
    }
}

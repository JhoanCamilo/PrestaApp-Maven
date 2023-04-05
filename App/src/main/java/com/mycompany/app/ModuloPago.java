package com.mycompany.app;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ModuloPago {
    
        String Usuario = "Prestamos";
        String Clave = "";
        String URL = "jdbc:mysql://localhost:3306/pruebas";
        Connection con;
        Statement stmt;
        Conexion funciones = new Conexion();
    
        void pagar(JLabel CampoPagar, JLabel campoUltimoMes, JTextField campoPago, JLabel CampoNombre, JComboBox<String> CampoMesPago) {
                funciones.ValidarDriver();
                String nombre = CampoNombre.getText();
                String mes = (String) CampoMesPago.getSelectedItem();
                String fechaPago = DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDateTime.now());
                int cancelado = Integer.parseInt(CampoPagar.getText());
                int pagado = Integer.parseInt(campoPago.getText());
                int pago = cancelado - pagado;
                int meses = Integer.parseInt(campoUltimoMes.getText());

                if (meses > 0) {
                    meses = meses - 1;
                }

                String orden = "UPDATE clientes SET total_prestado = '" + pago +  "', mes_pagado = '" + mes + "', meses_debe = '" + meses + "', fecha_ultimo_pago = '" + fechaPago + "' WHERE nombre = '" + nombre + "'";
                System.out.println("Total prestado = " + pago + "\n mes = " + mes + "\n meses que debe = " + meses + "\n ultimo pago = " + fechaPago);
                try {
                    con = DriverManager.getConnection(URL, Usuario, Clave);
                    stmt = con.createStatement();
                    stmt.executeUpdate(orden);
                    JOptionPane.showMessageDialog(null, "Pago correctamente hecho","Pagar cuota", JOptionPane.INFORMATION_MESSAGE);
                    mesPago(campoPago, CampoNombre, CampoMesPago);
                    campoPago.setText("");
                } catch (SQLException e) { System.out.println(e);}
         }
        
        void mesPago(JTextField campoPago, JLabel CampoNombre, JComboBox<String> CampoMesPago){
            String mes = (String) CampoMesPago.getSelectedItem();
            String nombre = CampoNombre.getText();
            int pagado = Integer.parseInt(campoPago.getText());
            String orden = "UPDATE contabilidad SET " +  mes + " = " + pagado + " WHERE nombre = '" + nombre + "'";

                try {
                    con = DriverManager.getConnection(URL, Usuario, Clave);
                    stmt = con.createStatement();
                    stmt.executeUpdate(orden);
                } catch (SQLException e) { 
                    System.out.println(e);
                    e.printStackTrace();
                }
        }
}

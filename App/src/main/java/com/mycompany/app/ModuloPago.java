package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ModuloPago {
    
        String Usuario = "Prestamos";
        String Clave = "";
        String URL = "jdbc:mysql://localhost:3306/pruebas";
        Connection con;
        Statement stmt;
        ResultSet rs;
        Conexion funciones = new Conexion();
    
        void pagar(JLabel CampoPagar, JLabel campoMeses, JTextField campoPago, JLabel CampoNombre) {
                funciones.ValidarDriver();
                String nombre = CampoNombre.getText();
                int cancelado = Integer.parseInt(CampoPagar.getText());
                int pagado = Integer.parseInt(campoPago.getText());
                int pago = cancelado - pagado;
                int meses = Integer.parseInt(campoMeses.getText());

                if (meses > 0) {
                    meses = meses - 1;
                }

                String orden = "UPDATE clientes SET total_prestado = '" + pago + "', meses_debe = '" + meses + "' WHERE nombre = '" + nombre + "'";

                try {
                    con = DriverManager.getConnection(URL, Usuario, Clave);
                    stmt = con.createStatement();
                    stmt.executeUpdate(orden);
                    JOptionPane.showMessageDialog(null, "Pago correctamente hecho","Pagar cuota", JOptionPane.INFORMATION_MESSAGE);
                    campoPago.setText("");

                } catch (SQLException e) { e.printStackTrace();}
    }
}

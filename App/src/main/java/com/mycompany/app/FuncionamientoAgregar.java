package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FuncionamientoAgregar {
    String Usuario = "Prestamos";
    String Clave = "";
    String URL = "jdbc:mysql://localhost:3306/pruebas";
    Connection con;
    Statement stmt;
    ResultSet rs;
    JTextField codebtorName_1 = new JTextField();
    JTextField codebtorId_1 = new JTextField();
    JTextField codebtorDir_1 = new JTextField();
    JTextField codebtorTel_1 = new JTextField();
    JTextField codebtorTelTrab_1 = new JTextField();
    JTextField codebtorName_2 = new JTextField();
    JTextField codebtorId_2 = new JTextField();
    JTextField codebtorDir_2 = new JTextField();
    JTextField codebtorTel_2 = new JTextField();
    JTextField codebtorTelTrab_2 = new JTextField();
    JTextField codebtorName_3 = new JTextField();
    JTextField codebtorId_3 = new JTextField();
    JTextField codebtorDir_3 = new JTextField();
    JTextField codebtorTel_3 = new JTextField();
    JTextField codebtorTelTrab_3 = new JTextField();
    
    Conexion funciones = new Conexion();
    
    //Agregar cliente
    
    void addCo_debtor(){
        JLabel codebtor1 = new JLabel("Codeudor 1");
        codebtor1.setFont(new Font("Tahoma", Font.BOLD, 15));
        JLabel codebtor2 = new JLabel("Codeudor 2");
        codebtor2.setFont(new Font("Tahoma", Font.BOLD, 15));
        JLabel codebtor3 = new JLabel("Codeudor 3");
        codebtor3.setFont(new Font("Tahoma", Font.BOLD, 15));
        Object[] Codebtor_1 = {
            codebtor1,
            "Nombre codeudor 1:", codebtorName_1,
            "Cédula codeudor 1:", codebtorId_1, 
            "Dirección codeudor 1:", codebtorDir_1,
            "Telefono codeudor 1:", codebtorTel_1,
            "Tel trabajo codeudor 1:", codebtorTelTrab_1,
            
        };
        Object[] Codebtor_2 = {
            codebtor2,
            "Nombre codeudor 2:", codebtorName_2,
            "Cédula codeudor 2:", codebtorId_2, 
            "Dirección codeudor 2:", codebtorDir_2,
            "Telefono codeudor 2:", codebtorTel_2,
            "Tel trabajo codeudor 2:", codebtorTelTrab_2,
        };
        Object[] Codebtor_3 = {
            codebtor3,
            "Nombre codeudor 3:", codebtorName_3,
            "Cédula codeudor 3:", codebtorId_3, 
            "Dirección codeudor 3:", codebtorDir_3,
            "Telefono codeudor 3:", codebtorTel_3,
            "Tel trabajo codeudor 3:", codebtorTelTrab_3
        };
        JOptionPane.showMessageDialog(null, Codebtor_1, "Datos del codeudor 1", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, Codebtor_2, "Datos del codeudor 2", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, Codebtor_3, "Datos del codeudor 3", JOptionPane.INFORMATION_MESSAGE);
    }
    void llenarComboBox(JComboBox<String> Inversionistas) {
        funciones.ValidarDriver();
        String orden = "SELECT investor_name FROM inversionistas";
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            while (rs.next()) {
                String textList = rs.getString("investor_name");
                textList = textList.toLowerCase();
                Inversionistas.addItem(COnvertirMayus(textList));
            }

        } catch (SQLException e) {}
    }

    public String COnvertirMayus(String text) {
        char[] caracteres = text.toCharArray();
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        for (int i = 0; i < text.length() - 2; i++) // Es 'palabra'
        {
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') // Reemplazamos
            {
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }
        return new String(caracteres);
    }
    
    void addCustomer(JTextField campoNombre, JTextField campoCedula, JTextField campoDireccion, JTextField campoTelCasa, JTextField campoTrabajo, JTextField campoTelTrabajo, JTextField campoMonto, JTextField campoDiaPago, JTextField campoTasaInteres, JComboBox<String> inversionistas) {
        
        String customerName = campoNombre.getText();
        String customerId = campoCedula.getText();
        String customerDir = campoDireccion.getText();
        String customerPhone = campoTelCasa.getText();
        String customerWork = campoTrabajo.getText();
        String customerWorkPho = campoTelTrabajo.getText();
        String codebtorName = codebtorName_1.getText();
        String codebtorId = codebtorId_1.getText();
        String codebtorDir = codebtorDir_1.getText();
        String codebtorTel = codebtorTel_1.getText();
        String codebtorTelTrab = codebtorTelTrab_1.getText();
        String codebtorName2 = codebtorName_2.getText();
        String codebtorId2 = codebtorId_2.getText();
        String codebtorDir2 = codebtorDir_2.getText();
        String codebtorTel2 = codebtorTel_2.getText();
        String codebtorTelTrab2 = codebtorTelTrab_2.getText();
        String codebtorName3 = codebtorName_3.getText();
        String codebtorId3 = codebtorId_3.getText();
        String codebtorDir3 = codebtorDir_3.getText();
        String codebtorTel3 = codebtorTel_3.getText();
        String codebtorTelTrab3 = codebtorTelTrab_3.getText();
        int customerAmount = Integer.parseInt(campoMonto.getText());
        int customerPayDay = Integer.parseInt(campoDiaPago.getText());
        String customerRate = campoTasaInteres.getText();
        String investor = (String) inversionistas.getSelectedItem();
        String order = "INSERT INTO clientes (nombre, cedula, direccion, telefono, trabajo, telefono_trabajo, codeudor, cedula_codeudor, direccion_codeudor, telefono_codeudor, telefono_trabajo_codeudor,"
                + "codeudor_2, cedula_codeudor_2, direccion_codeudor_2, telefono_codeudor_2, telefono_trabajo_codeudor_2, codeudor_3, cedula_codeudor_3, direccion_codeudor_3, telefono_codeudor_3, telefono_trabajo_codeudor_3,"
                + "total_prestado, interes, total_interes, inversionista, dia_de_pago, meses_debe, estado, alterado) VALUES ('" + customerName + "','" + customerId + "','" + customerDir + "','" + customerPhone + "','" + customerWork + "','" + customerWorkPho + "','" + codebtorName + "','" + codebtorId + "','" + codebtorDir + "','" + codebtorTel + "','" + codebtorTelTrab + "','" + codebtorName2 + "','" + codebtorId2 + "','" + codebtorDir2 + "','" + codebtorTel2 + "','" + codebtorTelTrab2 + "','" + codebtorName3 + "','" + codebtorId3 + "','" + codebtorDir3 + "','" + codebtorTel3 + "','" + codebtorTelTrab3 + "','" + customerAmount + "','" + customerRate + "','" + 0 + "','" + investor + "','" + customerPayDay + "','" + 0 + "','ACTIVO', 'NO')";
        funciones.ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(order);
            JOptionPane.showMessageDialog(null,"Cliente agregado correctamente","Agregar cliente", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {e.printStackTrace();}
    }
    
    //Agregar inversionista
    
    void agregarInversionista(JTextField campoNombre) {
        String nombreInversionista = campoNombre.getText();
        String order = "INSERT INTO inversionistas (investor_name) VALUES ('" + nombreInversionista + "')";
        
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(order);
            JOptionPane.showMessageDialog(null,"Inversionista agregado correctamente","Agregar inversionista", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {e.printStackTrace();}
    }
}

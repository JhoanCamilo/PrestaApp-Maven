package com.mycompany.app;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FuncionamientoActualizar {
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
    
    void fillClientNames(JComboBox<String> CampoNombres) {
        String order = "SELECT nombre FROM clientes ORDER BY nombre";
        funciones.ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(order);

            while (rs.next()) {
                String textList = rs.getString("nombre");
                textList = textList.toLowerCase();
                CampoNombres.addItem(COnvertirMayus(textList));
            }

        } catch (SQLException e) {}
    }
    void fillInvestorCombo(JComboBox<String> CampoInvestor, String investor) {
        String order = "SELECT investor_name FROM inversionistas WHERE investor_name NOT LIKE '" + investor + "'";
        funciones.ValidarDriver();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(order);

            while (rs.next()) {
                String textList = rs.getString("investor_name");
                textList = textList.toLowerCase();
                CampoInvestor.addItem(COnvertirMayus(textList));
            }

        } catch (SQLException e) {e.printStackTrace();}
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

    void traerInfo(JComboBox<String> CampoNombres, JComboBox<String> CampoInvestor, JTextField campoDireccion, JTextField campoTelCasa, JTextField campoTrabajo, JTextField campoTelTrabajo, JTextField campoMonto, JTextField campoDiaPago, JTextField campoTasaInteres, JTextField campoEstado) {
        String cliente = (String) CampoNombres.getSelectedItem();
        String order = "SELECT direccion,telefono,trabajo,telefono_trabajo,total_prestado,dia_de_pago,interes,inversionista,estado FROM clientes WHERE nombre = '" + cliente +"'";
                
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(order);
            CampoInvestor.removeAllItems();
            if(rs.next()){
                String investor = rs.getString("inversionista");
                investor = investor.toLowerCase();
                CampoInvestor.addItem(COnvertirMayus(investor));
                campoDireccion.setText(rs.getString("direccion"));
                campoTelCasa.setText(rs.getString("telefono"));
                campoTrabajo.setText(rs.getString("trabajo"));
                campoTelTrabajo.setText(rs.getString("telefono_trabajo"));
                campoMonto.setText(rs.getString("total_prestado"));
                campoDiaPago.setText(rs.getString("dia_de_pago"));
                campoTasaInteres.setText(rs.getString("interes"));
                campoEstado.setText(rs.getString("estado").toUpperCase());
                
                fillInvestorCombo(CampoInvestor, investor);
            }
            
        } catch (SQLException e) {e.printStackTrace();}
    }
    void loadCodebtors(JComboBox<String> CampoNombres){
        String cliente = (String) CampoNombres.getSelectedItem();
        String order = "SELECT codeudor,cedula_codeudor,direccion_codeudor,telefono_codeudor,telefono_trabajo_codeudor,codeudor_2,cedula_codeudor_2,direccion_codeudor_2,telefono_codeudor_2,telefono_trabajo_codeudor_2,codeudor_3,cedula_codeudor_3,direccion_codeudor_3,telefono_codeudor_3,telefono_trabajo_codeudor_3 FROM clientes WHERE nombre = '" + cliente + "'";
                
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(order);
            
            if(rs.next()){
                codebtorName_1.setText(rs.getString("codeudor"));
                codebtorId_1.setText(rs.getString("cedula_codeudor"));
                codebtorDir_1.setText(rs.getString("direccion_codeudor"));
                codebtorTel_1.setText(rs.getString("telefono_codeudor"));
                codebtorTelTrab_1.setText(rs.getString("telefono_trabajo_codeudor"));
                codebtorName_2.setText(rs.getString("codeudor_2"));
                codebtorId_2.setText(rs.getString("cedula_codeudor_2"));
                codebtorDir_2.setText(rs.getString("direccion_codeudor_2"));
                codebtorTel_2.setText(rs.getString("telefono_codeudor_2"));
                codebtorTelTrab_2.setText(rs.getString("telefono_trabajo_codeudor_2"));
                codebtorName_3.setText(rs.getString("codeudor_3"));
                codebtorId_3.setText(rs.getString("cedula_codeudor_3"));
                codebtorDir_3.setText(rs.getString("direccion_codeudor_3"));
                codebtorTel_3.setText(rs.getString("telefono_codeudor_3"));
                codebtorTelTrab_3.setText(rs.getString("telefono_trabajo_codeudor_3"));
            }
            
        } catch (SQLException e) {e.printStackTrace();}
    }
    void getCodebtors(JComboBox<String> CampoNombres) {
        String cliente = (String) CampoNombres.getSelectedItem();
        String order = "SELECT codeudor,cedula_codeudor,direccion_codeudor,telefono_codeudor,telefono_trabajo_codeudor,codeudor_2,cedula_codeudor_2,direccion_codeudor_2,telefono_codeudor_2,telefono_trabajo_codeudor_2,codeudor_3,cedula_codeudor_3,direccion_codeudor_3,telefono_codeudor_3,telefono_trabajo_codeudor_3 FROM clientes WHERE nombre = '" + cliente + "'";
        
        JLabel codebtor1 = new JLabel("Codeudor 1");
        codebtor1.setFont(new Font("Tahoma", Font.BOLD, 15));
        JLabel codebtor2 = new JLabel("Codeudor 2");
        codebtor2.setFont(new Font("Tahoma", Font.BOLD, 15));
        JLabel codebtor3 = new JLabel("Codeudor 3");
        codebtor3.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(order);
            
            if(rs.next()){
                codebtorName_1.setText(rs.getString("codeudor"));
                codebtorId_1.setText(rs.getString("cedula_codeudor"));
                codebtorDir_1.setText(rs.getString("direccion_codeudor"));
                codebtorTel_1.setText(rs.getString("telefono_codeudor"));
                codebtorTelTrab_1.setText(rs.getString("telefono_trabajo_codeudor"));
                codebtorName_2.setText(rs.getString("codeudor_2"));
                codebtorId_2.setText(rs.getString("cedula_codeudor_2"));
                codebtorDir_2.setText(rs.getString("direccion_codeudor_2"));
                codebtorTel_2.setText(rs.getString("telefono_codeudor_2"));
                codebtorTelTrab_2.setText(rs.getString("telefono_trabajo_codeudor_2"));
                codebtorName_3.setText(rs.getString("codeudor_3"));
                codebtorId_3.setText(rs.getString("cedula_codeudor_3"));
                codebtorDir_3.setText(rs.getString("direccion_codeudor_3"));
                codebtorTel_3.setText(rs.getString("telefono_codeudor_3"));
                codebtorTelTrab_3.setText(rs.getString("telefono_trabajo_codeudor_3"));
            }
            
        } catch (SQLException e) {e.printStackTrace();}
        
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

    void actualizarDatos(JComboBox<String> CampoNombres, JComboBox<String> CampoInvestor, JTextField campoDireccion, JTextField campoTelCasa, JTextField campoTrabajo, JTextField campoTelTrabajo, JTextField campoMonto, JTextField campoDiaPago, JTextField campoTasaInteres, JTextField campoEstado) {
        String nombre = (String) CampoNombres.getSelectedItem();
        String inversionista = (String) CampoInvestor.getSelectedItem();
        String direccion = campoDireccion.getText();
        String telefonoCasa = campoTelCasa.getText();
        String trabajo = campoTrabajo.getText();
        String telefonoTrabajo = campoTelTrabajo.getText();
        int monto = Integer.parseInt(campoMonto.getText());
        int diaPago = Integer.parseInt(campoDiaPago.getText());
        String interes = campoTasaInteres.getText();
        String estado = campoEstado.getText();
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
        
        String order = "UPDATE clientes SET direccion = '" + direccion + "',telefono = '" + telefonoCasa +"',trabajo = '" + trabajo + "',telefono_trabajo = '" + telefonoTrabajo + 
                "',codeudor = '" + codebtorName + "',cedula_codeudor = '" + codebtorId + "',direccion_codeudor = '" + codebtorDir + "', telefono_codeudor = '" + codebtorTel + "', telefono_trabajo_codeudor = '" + codebtorTelTrab +
                "',codeudor_2 = '" + codebtorName2 + "',cedula_codeudor_2 = '" + codebtorId2 + "',direccion_codeudor_2 = '" + codebtorDir2 + "',telefono_codeudor_2 = '" + codebtorTel2 + "', telefono_trabajo_codeudor_2 = '" + codebtorTelTrab2 +
                "',codeudor_3 = '" + codebtorName3 + "',cedula_codeudor_3 = '" + codebtorId3 + "',direccion_codeudor_3 = '" + codebtorDir3 + "',telefono_codeudor_3 = '" + codebtorTel3 + "', telefono_trabajo_codeudor_3 = '" + codebtorTelTrab3 +
                "',total_prestado = '" + monto + "',interes = '" + interes + "',inversionista = '" + inversionista + "',dia_de_pago = '" + diaPago + "',estado = '" + estado + "' WHERE nombre = '" + nombre + "'";
        
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(order);
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente","Actualizar cliente", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {e.printStackTrace();}
    }
}

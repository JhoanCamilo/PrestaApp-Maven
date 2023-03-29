package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FuncionamientoEliminar {
    String Usuario = "Prestamos";
    String Clave = "";
    String URL = "jdbc:mysql://localhost:3306/pruebas";
    Connection con;
    Statement stmt;
    ResultSet rs;
    Conexion funciones = new Conexion();
    
    void fillCustomerComboBox(JComboBox<String> clientes, JComboBox<String> inversionista, JLabel campoTelefono, JLabel campoDeuda) {
        funciones.ValidarDriver();
        String investor = (String) inversionista.getSelectedItem();
        String orden = "SELECT nombre FROM clientes WHERE inversionista = '" + investor +"'";
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);
            
            clientes.removeAllItems();
            clientes.addItem("Seleccione el cliente");
            campoTelefono.setText("----------------------------------------------------");
            campoDeuda.setText("----------------------------------------------------");
            while (rs.next()) {
                String textList = rs.getString("nombre");
                textList = textList.toLowerCase();
                clientes.addItem(COnvertirMayus(textList));
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
    void traerDatos(JComboBox<String> clientes, JLabel campoTelefono, JLabel campoDeuda) {
        String cliente = (String) clientes.getSelectedItem();
        String order = "SELECT telefono, total_prestado FROM clientes WHERE nombre = '" + cliente +"'";
        
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(order);

            if(rs.next()){
                campoTelefono.setText(rs.getString("telefono"));
                campoDeuda.setText(rs.getString("total_prestado"));
            }
            
       } catch (SQLException e){}
    }
    void eliminarCliente(JComboBox<String> clientes , JLabel campoTelefono, JLabel campoDeuda) {
        String clientName = (String) clientes.getSelectedItem();
        String order = "DELETE FROM clientes WHERE nombre = '" + clientName + "'";
        
        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este cliente?", "Eliminar cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == 0) {
            try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                stmt.executeUpdate(order);
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente","Agregar cliente", JOptionPane.INFORMATION_MESSAGE);
                clientes.setSelectedIndex(0);
                campoTelefono.setText("----------------------------------------------------");
                campoDeuda.setText("----------------------------------------------------");
            } catch (SQLException e) {e.printStackTrace();}
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }
    
    //Eliminar inversionista
    void cargarInversionistas(JComboBox<String> inversionistas){
        String orden = "SELECT investor_name FROM inversionistas";
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            while (rs.next()) {
                String textList = rs.getString("investor_name");
                textList = textList.toLowerCase();
                inversionistas.addItem(COnvertirMayus(textList));
            }

        } catch (SQLException e) {}
    }
    
    void confirmEliminarInversionista(JComboBox<String> inversionistas){
        String seleccion = (String) inversionistas.getSelectedItem();
        String order = "DELETE clientes, inversionistas FROM clientes JOIN inversionistas ON clientes.inversionista = inversionistas.investor_name WHERE inversionistas.investor_name = '" + seleccion + "'";

        String negrita = "<html>Se eliminarán <b>TODOS</b> los clientes que tenga este inversionista</html>";
        JLabel alerta = new JLabel(negrita);
        int ask = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este inversionista?", "Eliminar inversionista", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (ask == 0) {
            int confirm = JOptionPane.showConfirmDialog(null, alerta.getText() + "\n" + "¿Desea continuar?", "Eliminar inversionista", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == 0) {
                try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                stmt.executeUpdate(order);
                JOptionPane.showMessageDialog(null, "Inversionista eliminado");
                } catch (SQLException e) {e.printStackTrace();}
            } else {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }
}

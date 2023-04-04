package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FuncionamientoPrincipal {

    String Usuario = "Prestamos";
    String Clave = "";
    String URL = "jdbc:mysql://localhost:3306/pruebas";
    Connection con;
    Statement stmt;
    ResultSet rs;
    Conexion funciones = new Conexion();

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

    void LLenarLista(JComboBox<String> Inversionistas, JList<String> ListaClientes) {
        String seleccion = (String) Inversionistas.getSelectedItem();
        Calendar fecha = Calendar.getInstance();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        seleccion = seleccion.toLowerCase();
        String orden = "SELECT nombre FROM clientes WHERE inversionista = '" + seleccion + "' " ;// AND dia_de_pago = " + dia;
        DefaultListModel<String> modelo = new DefaultListModel<String>();
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            while (rs.next()) {
                String clientecito = rs.getString("nombre");
                ListaClientes.setModel(modelo);
                modelo.addElement(clientecito.trim());
            }

        } catch (SQLException e) {}
    }
    void MostrarInfo(JList<String> ListaClientes, JLabel CampoNombre, JLabel CampoDocumento, JLabel CampoDireccion, JLabel CampoTelCasa, JLabel CampoTrabajo, JLabel CampoTelTrabajo, JLabel CampoPagar, JLabel campoMeses, JLabel CampoEstado, JLabel CampoIntereses) {
        
        String cliente = (String) ListaClientes.getSelectedValue();
        String orden = "SELECT nombre, cedula, direccion, telefono, trabajo, telefono_trabajo, total_prestado, total_interes, meses_debe, estado FROM clientes WHERE nombre = '" + cliente + "'";
        
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if(rs.next()){
                CampoNombre.setText(rs.getString("nombre"));
                CampoDocumento.setText(rs.getString("cedula"));
                CampoDireccion.setText(rs.getString("direccion"));
                CampoTelCasa.setText(rs.getString("telefono"));
                CampoTrabajo.setText(rs.getString("trabajo"));
                CampoTelTrabajo.setText(rs.getString("telefono_trabajo"));
                CampoPagar.setText(rs.getString("total_prestado"));
                campoMeses.setText(rs.getString("meses_debe"));
                CampoEstado.setText(rs.getString("estado"));
                CampoIntereses.setText(rs.getString("total_interes"));
            }
            
       } catch (SQLException e) {e.printStackTrace();}
    }
    void Codeudores(JList<String> ListaClientes){
        String cliente = (String) ListaClientes.getSelectedValue();
        String orden = "SELECT codeudor, cedula_codeudor, direccion_codeudor, telefono_codeudor, telefono_trabajo_codeudor, codeudor_2, cedula_codeudor_2, direccion_codeudor_2, telefono_codeudor_2, telefono_trabajo_codeudor_2, codeudor_3, cedula_codeudor_3, direccion_codeudor_3, telefono_codeudor_3, telefono_trabajo_codeudor_3 FROM clientes WHERE nombre = '" + cliente + "'";
        
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);

            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Codeudor 1: " + rs.getString("codeudor") + "\n" +
                                                    "Cédula codeudor 1: " + rs.getString("cedula_codeudor") + "\n" +
                                                    "Dirección codeudor 1: " + rs.getString("direccion_codeudor") + "\n" +
                                                    "Teléfono codeudor 1: " + rs.getString("telefono_codeudor") + "\n" +
                                                    "Tel trabajo codeudor 1: " + rs.getString("telefono_trabajo_codeudor") + "\n" +
                                                    "----------------------------------------" + "\n" +
                                                    "Codeudor 2: " + rs.getString("codeudor_2") + "\n" +
                                                    "Cédula codeudor 2: " + rs.getString("cedula_codeudor_2") + "\n" +
                                                    "Dirección codeudor 2: " + rs.getString("direccion_codeudor_2") + "\n" +
                                                    "Teléfono codeudor 2: " + rs.getString("telefono_codeudor_2") + "\n" +
                                                    "Tel trabajo codeudor 2: " + rs.getString("telefono_trabajo_codeudor_2") + "\n" +
                                                    "----------------------------------------" + "\n" +
                                                    "Codeudor 3: " + rs.getString("codeudor_3") + "\n" +
                                                    "Cédula codeudor 3: " + rs.getString("cedula_codeudor_3") + "\n" +
                                                    "Dirección codeudor 3: " + rs.getString("direccion_codeudor_3") + "\n" +
                                                    "Teléfono codeudor 3: " + rs.getString("telefono_codeudor_3") + "\n" +
                                                    "Tel trabajo codeudor 3: " + rs.getString("telefono_trabajo_codeudor_3"),
                        "Codeudores del cliente", JOptionPane.INFORMATION_MESSAGE);
            }
            
       } catch (SQLException e) {e.printStackTrace();}
    }
    void VerMesesAtrasados(){
        String orden = "SELECT nombre, telefono FROM clientes WHERE MESES_DEBE >= 2";
        DefaultTableModel modeloTabla = new DefaultTableModel();
        JTable table = new JTable(modeloTabla);
        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int CantColumnas = rsMd.getColumnCount();
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Teléfono");
            
            while(rs.next()){
                Object[] filas = new Object[CantColumnas];       

                for (int i = 0; i < CantColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(filas);                
            }                    
       } catch (SQLException e) {e.printStackTrace();}        
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new java.awt.Dimension(300, 100));
        //table.setFillsViewportHeight(true);
        
        if(table.getRowCount() > 0){
            JOptionPane.showMessageDialog(null, scrollPane, "CLIENTES QUE DEBEN 2 MESES O MÁS", JOptionPane.WARNING_MESSAGE);
        }        
    }
    
    void sumarMeses(String cliente, int mesesDebidos) {
        String orden = "UPDATE clientes SET meses_debe = '" + mesesDebidos + "', alterado = 'si'  WHERE nombre = '" + cliente + "'";
        System.out.println("Actualizando datos de " + cliente + ", Meses que debe: " + mesesDebidos);

        try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            stmt.executeUpdate(orden);
                     
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    void sumarIntereses(int dia, String nombre, int interesAcumulado){
        Connection cone;
        Statement stat;
        int diaPasado;
        if (dia == 1) {
            diaPasado = 30;
        } else {
            diaPasado = dia - 1;
        }
        String orden = "UPDATE clientes SET total_interes = " + interesAcumulado + " WHERE dia_de_pago = " + diaPasado + " AND nombre = '" + nombre + "'";
        System.out.println("Se sumaron intereses");
        try {
            cone = DriverManager.getConnection(URL, Usuario, Clave);
            stat = cone.createStatement();
            stat.executeUpdate(orden);
                     
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void reiniciar(int dia){
        Connection cone;
        Statement stat;
        int diaPasado = dia - 1;
        String orden = "UPDATE clientes SET alterado = 'no' WHERE dia_de_pago = " + diaPasado;

        try {
            cone = DriverManager.getConnection(URL, Usuario, Clave);
            stat = cone.createStatement();
            stat.executeUpdate(orden);
                     
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    void sumarMes(){
        Connection conn;
        Statement stamt;
        ResultSet ris;
        Calendar fecha = Calendar.getInstance();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        String query = "SELECT nombre, meses_debe, total_prestado,total_interes,interes FROM clientes WHERE dia_de_pago = " + dia + " AND estado = 'activo' AND alterado = 'no'";
        
        try {
            conn = DriverManager.getConnection(URL, Usuario, Clave);
            stamt = conn.createStatement();
            ris = stamt.executeQuery(query);

            while(ris.next()){
                String cliente = ris.getString("nombre");
                int mesesQueDebe = Integer.parseInt(ris.getString("meses_debe")) + 1;
                int faltaPagar = Integer.parseInt(ris.getString("total_prestado"));
                double interes = Double.parseDouble(ris.getString("interes"));
                int interesesTotal = Integer.parseInt(ris.getString("total_interes"));
                int interesAcumulado = (int) (interesesTotal + (faltaPagar*interes));
                sumarMeses(cliente, mesesQueDebe);
                sumarIntereses(dia, cliente, interesAcumulado);
                reiniciar(dia);
            }

         } catch (SQLException e) {System.out.println(e);}
    }
}

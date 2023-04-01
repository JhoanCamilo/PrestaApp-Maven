package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.tool.xml.html.ParaGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.JOptionPane;




public class GenerarDocumento {
        String Usuario = "Prestamos";
        String Clave = "";
        String URL = "jdbc:mysql://localhost:3306/pruebas";
        Connection con;
        Statement stmt;
        ResultSet rs;
        Conexion funciones = new Conexion();
        int sumatoria = 0;
        
        void balanceMesActual(){
            Month mes = LocalDate.now().getMonth();
            String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            String orden = "SELECT SUM(" + nombreMes + ") FROM contabilidad";
            
            
            try {
            con = DriverManager.getConnection(URL, Usuario, Clave);
            stmt = con.createStatement();
            rs = stmt.executeQuery(orden);
            
            sumatoria = Integer.parseInt(rs.getString("sum(" + nombreMes + ")"));
                                
            } catch (SQLException e) {e.printStackTrace();}
        }
        
        void GenerarReporte() {
            String orden = "SELECT nombre, total_prestado, dia_de_pago, estado FROM clientes";
            funciones.ValidarDriver();
            Document documento = new Document();
            try {
                String usuario = System.getProperty("user.home");
                String path = new File(usuario + "/Desktop").getCanonicalPath();
                String FILE_NAME = path + "/Reporte.pdf";
                PdfWriter.getInstance(documento, new FileOutputStream(new File(FILE_NAME)));
                documento.open();
                
                PdfPTable tabla = new PdfPTable(4);
                tabla.addCell("Cliente");
                tabla.addCell("Meses que debe");
                tabla.addCell("día de pago");
                tabla.addCell("estado");
                
                try {
                    con = DriverManager.getConnection(URL, Usuario, Clave);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(orden);
                    
                    if (rs.next()) {
                        do {                            
                            tabla.addCell(rs.getString(1));
                            tabla.addCell(rs.getString(2));
                            tabla.addCell(rs.getString(3));
                            tabla.addCell(rs.getString(4));
                        } while (rs.next());
                        documento.add(tabla);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Nada que mostrar");
                    }
                } catch (SQLException e) {
                    System.out.println("Error: " + e);
                }
                
                Paragraph balance = new Paragraph(sumatoria);
                documento.add(balance);
                
                documento.close();
                JOptionPane.showMessageDialog(null, "Documento correctamente creador");
                System.out.println("Archivo creado en " + path);
                
            } catch (DocumentException | FileNotFoundException e) {
                System.out.println("Error: " + e);
            }
            catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
}

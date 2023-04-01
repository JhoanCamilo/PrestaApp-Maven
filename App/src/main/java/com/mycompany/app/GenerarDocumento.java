package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
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
        String sumatoria;
        
        void balanceMesActual(){
            Month mes = LocalDate.now().getMonth();
            String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            String orden = "SELECT SUM(" + nombreMes + ") FROM contabilidad";
            
            
            try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery(orden);
            
                if (rs.next()) {
                    sumatoria = rs.getString(1);
                }
            
                System.out.println("Sumatoria del mes actual es: " + sumatoria);
                                
            } catch (SQLException e) {e.printStackTrace();}
        }
        void calcularInteresesl(){
            Month mes = LocalDate.now().getMonth();
            String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            String orden = "SELECT SUM(" + nombreMes + ") FROM contabilidad";
            
            
            try {
                con = DriverManager.getConnection(URL, Usuario, Clave);
                stmt = con.createStatement();
                rs = stmt.executeQuery(orden);
            
                if (rs.next()) {
                    sumatoria = rs.getString(1);
                }
            
                System.out.println("Sumatoria del mes actual es: " + sumatoria);
                                
            } catch (SQLException e) {e.printStackTrace();}
        }
        
        void GenerarReporte() {
            balanceMesActual();
            //String orden = "SELECT nombre, cedula, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre FROM contabilidad";
            String orden = "select clientes.nombre, clientes.cedula, contabilidad.enero, contabilidad.febrero, contabilidad.marzo, contabilidad.abril, contabilidad.mayo, contabilidad.junio, contabilidad.julio, contabilidad.agosto, contabilidad.septiembre, contabilidad.octubre, contabilidad.noviembre, contabilidad.diciembre from clientes join contabilidad on contabilidad.nombre = clientes.nombre where clientes.estado = 'activo'";
            Month mes = LocalDate.now().getMonth();
            String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            funciones.ValidarDriver();
            Rectangle pageSize = new Rectangle(1000f, 500f);
            Document documento = new Document(pageSize);
            try {
                String usuario = System.getProperty("user.home");
                String path = new File(usuario + "/Desktop").getCanonicalPath();
                String FILE_NAME = path + "/Reporte_" + nombreMes + ".pdf";
                PdfWriter.getInstance(documento, new FileOutputStream(new File(FILE_NAME)));
                documento.open();
                                
                PdfPTable tabla = new PdfPTable(14);
                float[] medidaCeldas = {1.20f, 0.45f, 0.25f, 0.30f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 0.40f, 0.35f, 0.35f, 0.35f};
                tabla.addCell("Cliente");
                tabla.addCell("Cédula");
                tabla.addCell("Enero");
                tabla.addCell("Febrero");
                tabla.addCell("Marzo");
                tabla.addCell("Abril");
                tabla.addCell("Mayo");
                tabla.addCell("Junio");
                tabla.addCell("Julio");
                tabla.addCell("Agosto");
                tabla.addCell("Septiembre");
                tabla.addCell("Octubre");
                tabla.addCell("Noviembre");
                tabla.addCell("Diciembre");
                tabla.setWidthPercentage(100);
                tabla.setWidths(medidaCeldas);
                
                try {
                    con = DriverManager.getConnection(URL, Usuario, Clave);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(orden);
                    Paragraph balance = new Paragraph("Balance para el mes de " +  nombreMes + ": " + sumatoria);
                    Paragraph notice = new Paragraph("Esta tabla solo muestra los clientes cuyo estado es ACTIVO.");
                    
                    if (rs.next()) {
                        do {                            
                            tabla.addCell(rs.getString("Nombre"));
                            tabla.addCell(rs.getString("Cedula"));
                            tabla.addCell(rs.getString("Enero"));
                            tabla.addCell(rs.getString("Febrero"));
                            tabla.addCell(rs.getString("Marzo"));
                            tabla.addCell(rs.getString("Abril"));
                            tabla.addCell(rs.getString("Mayo"));
                            tabla.addCell(rs.getString("Junio"));
                            tabla.addCell(rs.getString("Julio"));
                            tabla.addCell(rs.getString("Agosto"));
                            tabla.addCell(rs.getString("Septiembre"));
                            tabla.addCell(rs.getString("Octubre"));
                            tabla.addCell(rs.getString("Noviembre"));
                            tabla.addCell(rs.getString("Diciembre"));
                        } while (rs.next());
                        documento.add(tabla);
                        documento.add(notice);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Nada que mostrar");
                    }
                     documento.add(balance);
                } catch (SQLException e) {
                    System.out.println("Error: " + e);
                }
                
                documento.close();
                JOptionPane.showMessageDialog(null, "Documento correctamente creador \n Archivo creado en" + path);
                System.out.println("Archivo creado en " + path);
                
            } catch (DocumentException | FileNotFoundException e) {
                System.out.println("Error: " + e);
            }
            catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }
}

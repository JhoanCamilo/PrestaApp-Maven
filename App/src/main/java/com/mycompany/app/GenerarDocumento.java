package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;




public class GenerarDocumento {
        String Usuario = "Prestamos";
        String Clave = "";
        String URL = "jdbc:mysql://localhost:3306/pruebas";
        Conexion funciones = new Conexion();
        
        void GenerarReporte() {
            funciones.ValidarDriver();
            Connection conn;


        }
}

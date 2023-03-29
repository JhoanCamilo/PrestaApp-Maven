package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;*/




public class GenerarDocumento {
        String Usuario = "Prestamos";
        String Clave = "";
        String URL = "jdbc:mysql://localhost:3306/pruebas";
        Conexion funciones = new Conexion();
        
        void GenerarReporte() {
                 funciones.ValidarDriver();
                 Connection conn;
                   
                /*try {
                    conn = DriverManager.getConnection(URL, Usuario, Clave);
                    JasperReport reporte = null;
                    String path = "src\\Reportes\\report1.jasper";
                     try {
                         reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                         JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
                         JasperViewer vista = new JasperViewer(jprint, false);

                        vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        vista.setVisible(true);
                     } catch (JRException ex) {
                         Logger.getLogger(GenerarDocumento.class.getName()).log(Level.SEVERE, null, ex);
                     }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(GenerarDocumento.class.getName()).log(Level.SEVERE, null, ex);
                }*/
        }
}

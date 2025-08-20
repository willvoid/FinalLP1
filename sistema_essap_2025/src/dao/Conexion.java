package dao;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javax.swing.JOptionPane;

import vista.GUILogin;

public class Conexion {
	String url = "jdbc:postgresql://localhost:5432/lp1";
    String usuario = "postgres";
    String password = "123";
    GUILogin guilogin = new GUILogin();
    public Connection conectarBD() {
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            //Logger.getLogger(Conexion.class.getName()).log(Level.ERROR, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return conectar;
    }
}

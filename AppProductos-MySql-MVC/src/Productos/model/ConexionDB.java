package Productos.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
 
    Connection conexion;
    
    //parametros de conexion
    private final String URL = "jdbc:mysql://localhost:3306/tienda";
    private final String USER = "root";
    private final String PASSWORD = "AndrmattR24**";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public Connection getConnection(){
        conexion = null;
        
        try {
            Class.forName(DRIVER);
            conexion =  DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO,"CONEXION ESTABLECIDA");
            
        } catch (ClassNotFoundException|SQLException  ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
    }
        return conexion;
    }
    
    public void setDisconect(){
        try {
            conexion.close();
            Logger.getLogger(ConexionDB.class.getName()).log(Level.INFO,"CONEXION TERMINADA");
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexion {
    Connection conec;
        public Connection getConnection(){
            try {
                String myBaseDatos = "Jdbc:mysql://localhost:3306/sistema_ventas?serverTimezone=UTC";
                conec = DriverManager.getConnection(myBaseDatos, "root", "");
                return conec;
            } catch (SQLException e) {
                System.out.println(e.toString());
                } 
            return null;
                
            
        }
}

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductoDAO {
  
    Connection conec;
    conexion con = new conexion();
    PreparedStatement pre;
    ResultSet res;
    
  
   
   public boolean RegistrarProductos(Producto prod){
       String sql = "INSERT INTO producto (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";
       try {
           conec = con.getConnection();
           pre = conec.prepareStatement(sql);
           pre.setString(1, prod.getCodigo());
           pre.setString(2, prod.getNombre());
           pre.setString(3, prod.getProveedor());
           pre.setInt(4, prod.getStock());
           pre.setDouble(5, prod.getPrecio());
           pre.execute();
           return true;
        }catch (SQLException e){ 
           System.out.println(e.toString());
           return false;
       }finally {
            try {
                conec.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
       
}
   

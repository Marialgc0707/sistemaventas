package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    Connection conec;
    PreparedStatement pre;
    ResultSet res;
    conexion con = new conexion();
    
    public Login Log(String correo, String contraseña){
        Login x = new Login();
        String sql = "SELECT * FROM usuario WHERE correo = ? AND contraseña = ?";

        try {
            conec = con.getConnection();
            pre = conec.prepareStatement(sql);
            pre.setString(1, correo);
            pre.setString(2, contraseña);
            res = pre.executeQuery();
            if (res.next()) {
                x.setID(res.getInt("ID"));
                x.setNombre(res.getString("nombre"));
                x.setCorreo(res.getString("correo"));
                x.setContraseña(res.getString("contraseña"));
            }          
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return x;
    }    
}


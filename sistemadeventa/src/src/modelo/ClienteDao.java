/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuario
 */
public class ClienteDao {
    
    conexion con = new conexion();
    Connection conec;
    PreparedStatement pre;
    ResultSet res;
    
    
    public boolean RegistarCliente(Cliente cl){
        String sql = " INSERT INTO cliente (cedula, nombre, telefono, direccion, razon_social) VALUES (?,?,?,?,?)"; 
        try {
            conec = con.getConnection();
            pre = conec.prepareStatement (sql);
            pre.setString(1, cl.getCed());
            pre.setString(2, cl.getNombre());
            pre.setString(3, cl.getTelefono());
            pre.setString(4, cl.getDireccion());
            pre.setString(5, cl.getRazon());
            pre.execute();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
            
        }finally{
            try {
                conec.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarCliente(){
        List<Cliente> ListaCl = new ArrayList();
            String sql = "SELECT * FROM cliente";
            try {
                conec = con.getConnection();
                pre = conec.prepareStatement(sql);
                res = pre.executeQuery();
                while (res.next()) {                    
                    Cliente cl = new Cliente();
                    cl.setId(res.getInt("ID"));
                    cl.setCed(res.getString("cedula"));
                    cl.setNombre(res.getString("nombre"));
                    cl.setTelefono(res.getString("telefono"));
                    cl.setDireccion(res.getString("direccion"));
                    cl.setRazon(res.getString("razon_social"));
                    ListaCl.add(cl);
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        return ListaCl;
    }
    
    public boolean EliminarCliente(int id){
        String sql = "DELETE FROM cliente WHERE ID = ?";
        try {
            pre = conec.prepareStatement(sql);
            pre.setInt(1, id);
            pre.execute();
            return true; 
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally{
            try {
                conec.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ModificarCliente(Cliente cl){
        String sql = "UPDATE cliente SET cedula=?, nombre=?, telefono=?, direccion=?, razon_social=? WHERE ID=?";
        try {
            pre = conec.prepareStatement(sql);
            pre.setString(1, cl.getCed());
            pre.setString(2, cl.getNombre());
            pre.setString(3, cl.getTelefono());
            pre.setString(4, cl.getDireccion());
            pre.setString(5, cl.getRazon());
            pre.setInt(6, cl.getId());
            pre.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                conec.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
           
    }
    
}

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    Connection conec;
    conexion con = new conexion();
    PreparedStatement pre;
    ResultSet res;

    public boolean RegistrarProveedor(Proveedor pr) {
        String sql = "INSERT INTO proveedor (RIF, nombre, telefono, direccion, razon_social) VALUES (?,?,?,?,?) ";
        try {
            conec = con.getConnection();
            pre = conec.prepareStatement(sql);
            pre.setString(1, pr.getRif());
            pre.setString(2, pr.getNombre());
            pre.setString(3, pr.getTelefono());
            pre.setString(4, pr.getDireccion());
            pre.setString(5, pr.getRazon());
            pre.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                conec.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarProveedor() {
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            conec = con.getConnection();
            pre = conec.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Proveedor pr = new Proveedor();
                pr.setId(res.getInt("ID"));
                pr.setRif(res.getString("RIF"));
                pr.setNombre(res.getString("nombre"));
                pr.setTelefono(res.getString("telefono"));
                pr.setDireccion(res.getString("direccion"));
                pr.setRazon(res.getString("razon_social"));
                Listapr.add(pr);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }

    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE ID = ?";
        try {
            pre = conec.prepareStatement(sql);
            pre.setInt(1, id);
            pre.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                conec.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public boolean ModificarProveedor(Proveedor pr) {
        String sql = "UPDATE proveedor SET RIF=?, nombre=?, telefono=?, direccion=?, razon_social=? WHERE ID=?";
        try {
            conec = con.getConnection();
            pre = conec.prepareStatement(sql);
            pre.setString(1, pr.getRif());
            pre.setString(2, pr.getNombre());
            pre.setString(3, pr.getTelefono());
            pre.setString(4, pr.getDireccion());
            pre.setString(5, pr.getRazon());
            pre.setInt(6, pr.getId());
            pre.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                conec.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}

package Productos.repository;

import Productos.model.ConexionDB;
import Productos.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoRepository extends ConexionDB {

    public boolean save(Producto producto) {

         // preparamos la consulta
        PreparedStatement ps = null;

        // traemos la conexion
        Connection database = getConnection();
        
        // instruccion sql
        String sql = "INSERT INTO producto (codigo, nombre, precio, cantidad) VALUES (?,?,?,?)";

        try {
            ps = database.prepareStatement(sql);
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());

            // ejecutamos la consulta
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        } finally {
            try {
                database.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean update(Producto producto) {

         // preparamos la consulta
        PreparedStatement ps = null;

        // traemos la conexion
        Connection database = getConnection();
        
        //instruccion de Sql
        String sql = "UPDATE producto SET codigo=?, nombre=?, precio=?, cantidad=? WHERE id=?";
        
        try {
            
            ps = database.prepareStatement(sql);

            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setInt(5, producto.getId());

            //ejecucion de la consulta
            ps.execute();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        } finally {
            try {
                database.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean delete(Producto producto) {

         // preparamos la consulta
        PreparedStatement ps = null;

        // traemos la conexion
        Connection database = getConnection();
        
        //instruccion sql
        String sql = "DELETE FROM producto WHERE id=?";

        try {
            ps = database.prepareStatement(sql);

            ps.setInt(1, producto.getId());

            //ejecucion de la consulta
            ps.execute();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        } finally {
            try {
                database.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductoRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

     public boolean buscar(Producto pro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection database = getConnection();

        String sql = "SELECT * FROM producto WHERE codigo=? ";

        try {
            ps = database.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();

            if (rs.next()) {
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                database.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Usuario;
import aplicacion.TipoUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basesdatos
 */
public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario validarUsuario(String idUsuario, String clave) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement("select id_usuario, clave, nombre, direccion, email, tipo_usuario "
                    + "from usuario " + "where id_usuario = ? and clave = ?");
            stmUsuario.setString(1, idUsuario);
            stmUsuario.setString(2, clave);
            rsUsuario = stmUsuario.executeQuery();
            if (rsUsuario.next()) {
                resultado = new Usuario(rsUsuario.getString("id_usuario"), rsUsuario.getString("clave"),
                        rsUsuario.getString("nombre"), rsUsuario.getString("direccion"), rsUsuario.getString("email"),
                        TipoUsuario.valueOf(rsUsuario.getString("tipo_usuario")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public void borrarUsuario(String id) {
        Connection con;
        PreparedStatement stmLibro = null;

        con = super.getConexion();

        try {
            stmLibro = con.prepareStatement("delete from usuario where id_usuario = ?");
            stmLibro.setString(1, id);
            stmLibro.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmLibro.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void modificarUsuario(Usuario u) {
        Connection con;
        PreparedStatement stmLibro = null;

        con = super.getConexion();

        try {
            stmLibro = con.prepareStatement("update usuario " + "set clave=?, " + "    nombre=?, " + "    direccion=?, "
                    + "    email=?, " + "    tipo_usuario=? " + "where id_usuario=?");
            stmLibro.setString(1, u.getClave());
            stmLibro.setString(2, u.getNombre());
            stmLibro.setString(3, u.getDireccion());
            stmLibro.setString(4, u.getEmail());
            stmLibro.setString(5, u.getTipoUsuario().toString());
            stmLibro.setString(6, u.getIdUsuario());
            stmLibro.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stmLibro.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void insertarUsuario(Usuario u) {
        Connection con;
        PreparedStatement stat;

        con = super.getConexion();

        try {
            stat = con.prepareStatement(
                    "insert into usuario(clave, nombre, direccion, email, tipo_usuario)" + " values (?,?,?,?,?)");
            stat.setString(0, u.getClave());
            stat.setString(1, u.getNombre());
            stat.setString(2, u.getDireccion());
            stat.setString(3, u.getEmail());
            stat.setString(4, u.getTipoUsuario().toString());
            stat.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public List<Usuario> consultarUsuarios() {
        Connection con = getConexion();
        PreparedStatement stat;
        List<Usuario> resultado = new ArrayList<Usuario>();

        try {
            stat = con.prepareStatement(
                    "select id_usuario, clave, nombre, direccion, email, tipo_usuario from categoria");
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                resultado.add(new Usuario(res.getString("id_usuario"), res.getString("clave"), res.getString("nombre"),
                        res.getString("direccion"), res.getString("email"),
                        res.getString("tipo_usuario").equals("Administrador") ? TipoUsuario.Administrador
                                : TipoUsuario.Normal));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

}

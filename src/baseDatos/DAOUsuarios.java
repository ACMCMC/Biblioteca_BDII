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
        PreparedStatement stat = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stat = con.prepareStatement("select id_usuario, clave, nombre, direccion, email, tipo_usuario "
                    + "from usuario " + "where id_usuario = ? and clave = ?");
            stat.setString(1, idUsuario);
            stat.setString(2, clave);
            rsUsuario = stat.executeQuery();
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
                stat.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public void borrarUsuario(String id) {
        Connection con;
        PreparedStatement stat = null;

        con = super.getConexion();

        try {
            stat = con.prepareStatement("delete from usuario where id_usuario = ?");
            stat.setString(1, id);
            stat.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void modificarUsuario(Usuario u) {
        Connection con;
        PreparedStatement stat = null;

        con = super.getConexion();

        try {
            stat = con.prepareStatement("update usuario " + "set clave=?, " + "    nombre=?, " + "    direccion=?, "
                    + "    email=?, " + "    tipo_usuario=? " + "where id_usuario=?");
            stat.setString(1, u.getClave());
            stat.setString(2, u.getNombre());
            stat.setString(3, u.getDireccion());
            stat.setString(4, u.getEmail());
            stat.setString(5, u.getTipoUsuario().toString());
            stat.setString(6, u.getIdUsuario());
            stat.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public void insertarUsuario(Usuario u) {
        Connection con;
        PreparedStatement stat = null;

        con = super.getConexion();

        try {
            stat = con.prepareStatement(
                    "insert into usuario(clave, nombre, direccion, email, tipo_usuario)" + " values (?,?,?,?,?)");
            stat.setString(1, u.getClave());
            stat.setString(2, u.getNombre());
            stat.setString(3, u.getDireccion());
            stat.setString(4, u.getEmail());
            stat.setString(5, u.getTipoUsuario().toString());
            stat.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    public List<Usuario> consultarUsuarios() {
        Connection con = getConexion();
        PreparedStatement stat = null;
        List<Usuario> resultado = new ArrayList<Usuario>();

        try {
            stat = con.prepareStatement(
                    "select id_usuario, clave, nombre, direccion, email, tipo_usuario from usuario order by id_usuario desc");
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
                stat.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

    public List<Usuario> consultarUsuarios(String id, String nombre) {
        Connection con = getConexion();
        PreparedStatement stat = null;
        List<Usuario> resultado = new ArrayList<Usuario>();

        try {
            stat = con.prepareStatement(
                    "select id_usuario, clave, nombre, direccion, email, tipo_usuario from usuario where nombre like ? and id_usuario like ?");
                    stat.setString(1, "%" + nombre + "%");
                    stat.setString(2, "%" + id + "%");
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
                stat.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }

}

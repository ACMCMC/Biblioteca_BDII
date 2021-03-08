/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import aplicacion.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author basesdatos
 */
public class DAOPrestamos extends AbstractDAO {

    public DAOPrestamos(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Prestamo> consultarPrestamos(Usuario u, DAOLibros daoLibros) {
        Connection con = getConexion();
        PreparedStatement stat = null;
        List<Prestamo> resultado = new ArrayList<Prestamo>();

        try {
            stat = con.prepareStatement(
                    "select prestamo.fecha_prestamo, prestamo.fecha_devolucion, public.get_fecha_vencimiento(prestamo.fecha_prestamo), prestamo.ejemplar, prestamo.libro FROM prestamo WHERE prestamo.usuario=?");
                    stat.setString(1, u.getIdUsuario());
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                resultado.add(new Prestamo(res.getDate(1), res.getDate(2), res.getDate(3), daoLibros.consultarEjemplar(res.getInt(4), res.getInt(5)), u));
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

    public void insertarPrestamo(Prestamo p) {
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "insert into prestamo(ejemplar, fecha_prestamo, libro, usuario, fecha_devolucion) values (?,?,?,?,?)");
                    stat.setInt(1, p.getEjemplar().getNumEjemplar());
                    stat.setDate(2, p.getFechaPrestamo());
                    stat.setInt(3, p.getEjemplar().getLibro().getIdLibro());
                    stat.setString(4, p.getUsuario().getIdUsuario());
                    stat.setDate(4, p.getFechaDevolucion());
            stat.executeQuery();
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

}

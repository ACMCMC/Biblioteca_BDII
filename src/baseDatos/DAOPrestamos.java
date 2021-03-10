/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Libro;
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
    
    public List<Prestamo> consultarPrestamosVencidos(Usuario u, DAOLibros daoLibros) {
        Connection con = getConexion();
        PreparedStatement stat = null;
        List<Prestamo> resultado = new ArrayList<Prestamo>();

        try {
            stat = con.prepareStatement(
                    "select prestamo.fecha_prestamo, prestamo.fecha_devolucion, public.get_fecha_vencimiento(prestamo.fecha_prestamo), prestamo.ejemplar, prestamo.libro FROM prestamo WHERE prestamo.usuario=? and prestamo.fecha_devolucion is not null and public.get_fecha_vencimiento(prestamo.fecha_prestamo) < prestamo.fecha_devolucion");
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
    
    public List<Prestamo> consultarPrestamos(Libro l, DAOLibros daoLibros, DAOUsuarios daoUsuarios) {
        Connection con = getConexion();
        PreparedStatement stat = null;
        List<Prestamo> resultado = new ArrayList<Prestamo>();

        try {
            stat = con.prepareStatement(
                    "select prestamo.fecha_prestamo, prestamo.fecha_devolucion, public.get_fecha_vencimiento(prestamo.fecha_prestamo), prestamo.ejemplar, prestamo.libro, prestamo.usuario FROM prestamo WHERE prestamo.libro=?");
                    stat.setInt(1, l.getIdLibro());
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                resultado.add(new Prestamo(res.getDate(1), res.getDate(2), res.getDate(3), daoLibros.consultarEjemplar(res.getInt(4), res.getInt(5)), daoUsuarios.consultarUsuarios(res.getString(6), null).get(0)));
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
                    stat.setDate(5, p.getFechaDevolucion());
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

    public void modificarPrestamo(Prestamo p) {
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "update prestamo set fecha_devolucion=? where ejemplar=? and fecha_prestamo=? and libro=? and usuario=?");
                    stat.setDate(1, p.getFechaDevolucion());
                    stat.setInt(2, p.getEjemplar().getNumEjemplar());
                    stat.setDate(3, p.getFechaPrestamo());
                    stat.setInt(4, p.getEjemplar().getLibro().getIdLibro());
                    stat.setString(5, p.getUsuario().getIdUsuario());
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
    
    public void devolverPrestamo(Prestamo p) {
        p = new Prestamo(p.getFechaPrestamo(), new java.sql.Date(System.currentTimeMillis()), p.getFechaVencimiento(), p.getEjemplar(), p.getUsuario());
        this.modificarPrestamo(p);
    }
    
    public Prestamo getPrestamoActualEjemplar(Ejemplar ej, DAOUsuarios daoUsuarios) {
        Connection con = getConexion();
        PreparedStatement stat = null;
        Prestamo resultado = null;

        try {
            stat = con.prepareStatement(
                    "select prestamo.fecha_prestamo, prestamo.fecha_devolucion, public.get_fecha_vencimiento(prestamo.fecha_prestamo), prestamo.ejemplar, prestamo.libro, prestamo.usuario FROM prestamo WHERE prestamo.libro=? and prestamo.ejemplar=? and prestamo.fecha_devolucion is null");
                    stat.setInt(1, ej.getLibro().getIdLibro());
                    stat.setInt(2, ej.getNumEjemplar());
            ResultSet res = stat.executeQuery();
            if (res.next()) {
                
                
                resultado = new Prestamo(res.getDate(1), res.getDate(2), res.getDate(3), ej, daoUsuarios.consultarUsuarios(res.getString(6), null).get(0));
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
    
    public java.util.List<Prestamo> getPrestamosEjemplar(Ejemplar ej, DAOUsuarios daoUsuarios) {
        Connection con = getConexion();
        PreparedStatement stat = null;
        java.util.List<Prestamo> resultado = new java.util.ArrayList<Prestamo>();

        try {
            stat = con.prepareStatement(
                    "select prestamo.fecha_prestamo, prestamo.fecha_devolucion, public.get_fecha_vencimiento(prestamo.fecha_prestamo), prestamo.ejemplar, prestamo.libro, prestamo.usuario FROM prestamo WHERE prestamo.libro=? and prestamo.ejemplar=?");
                    stat.setInt(1, ej.getLibro().getIdLibro());
                    stat.setInt(2, ej.getNumEjemplar());
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                
                resultado.add(new Prestamo(res.getDate(1), res.getDate(2), res.getDate(3), ej, daoUsuarios.consultarUsuarios(res.getString(6), null).get(0)));
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

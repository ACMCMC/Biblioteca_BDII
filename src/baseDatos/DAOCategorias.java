/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;
import aplicacion.Categoria;
import aplicacion.Libro;
import java.sql.*;
/**
 *
 * @author basesdatos
 */
public class DAOCategorias extends AbstractDAO {
   
    
    public DAOCategorias (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public java.util.List<Categoria> consultarCategorias(){
        java.util.List<Categoria> resultado = new java.util.ArrayList<Categoria>();
        Categoria categoriaActual;
        Connection con;
        PreparedStatement stmCategorias=null;
        ResultSet rsCategorias;

        con=this.getConexion();

        try  {
        stmCategorias=con.prepareStatement("select nombre, descripcion from categoria");
        rsCategorias=stmCategorias.executeQuery();
        while (rsCategorias.next())
        {
            categoriaActual = new Categoria(rsCategorias.getString("nombre"), rsCategorias.getString("descripcion"));
            resultado.add(categoriaActual);
        }

        } catch (SQLException e){
          System.out.println(e.getMessage());
          this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
          try {stmCategorias.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }


    public void insertarCategoria(Categoria c){
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "insert into categoria(nombre, descripcion) values (?,?)");
                    stat.setString(1, c.getNombre());
                    stat.setString(2, c.getDescripcion());
                    
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
    public void borrarCategoria(Categoria c){
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "delete from categoria where nombre=?");
                    stat.setString(1, c.getNombre());
                    
                    
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
    public void modificarCategoria(Categoria c){
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "update categoria set descripcion=? where nombre=?");
                    stat.setString(2, c.getNombre());
                    stat.setString(1, c.getDescripcion());
                    
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
    
    public void anadirCategoriaLibro(int l, String c) {
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "insert into public.cat_tiene_libro(categoria, libro) values (?,?)");
                    stat.setString(1, c);
                    stat.setInt(2, l);
                    
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
    
    public void quitarCategoriaLibro(int l, String c) {
        Connection con = getConexion();
        PreparedStatement stat = null;

        try {
            stat = con.prepareStatement(
                    "delete from public.cat_tiene_libro where categoria=? and libro=?");
                    stat.setString(1, c);
                    stat.setInt(2, l);
                    
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
   

}

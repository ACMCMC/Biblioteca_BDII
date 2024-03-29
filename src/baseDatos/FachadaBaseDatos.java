/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package baseDatos;

import aplicacion.Ejemplar;
import aplicacion.Usuario;
import aplicacion.Categoria;
import aplicacion.Libro;
import aplicacion.Prestamo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author basesdatos
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOLibros daoLibros;
    private DAOCategorias daoCategorias;
    private DAOUsuarios daoUsuarios;
    private DAOPrestamos daoPrestamos;

    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
     

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);

            daoLibros = new DAOLibros(conexion, fa);
            daoCategorias = new DAOCategorias(conexion, fa);
            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoPrestamos = new DAOPrestamos(conexion, fa);
          


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
        }
        
        
        
    }
    
    

    public java.util.List<Libro> consultarCatalogo(Integer id, String titulo, String isbn, String autor){
        return daoLibros.consultarCatalogo(id, titulo, isbn, autor);
    }

    public Libro consultarLibro(Integer idLibro){
        return daoLibros.consultarLibro(idLibro);
    }
    public java.util.List<Ejemplar> consultarEjemplaresLibro(Integer idLibro){
        return daoLibros.consultarEjemplaresLibro(idLibro);
    }
    public java.util.List<String> obtenerRestoCategorias(Integer idLibro){
        return daoLibros.obtenerRestoCategorias(idLibro);
    }
    public Integer insertarLibro(Libro libro){
       return daoLibros.insertarLibro(libro);
    }
    public void borrarLibro(Integer idLibro){
        daoLibros.borrarLibro(idLibro);
    }
    public void modificarLibro(Libro libro){
         daoLibros.modificarLibro(libro);
    }
    public void modificarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
       daoLibros.modificarCategoriasLibro(idLibro, categorias);
    }
    public void insertarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        daoLibros.insertarEjemplarLibro(idLibro, ejemplar);
    }
    public void borrarEjemplaresLibro(Integer idLibro, java.util.List<Integer> numsEjemplar){
        daoLibros.borrarEjemplaresLibro(idLibro, numsEjemplar);
    }
    public void modificarEjemplarLibro(Integer idLibro, Ejemplar ejemplar){
        daoLibros.modificarEjemplarLibro(idLibro, ejemplar);
    }

    public void insertarUsuario(Usuario u){
        daoUsuarios.insertarUsuario(u);
    }
    public void borrarUsuario(String id){
        daoUsuarios.borrarUsuario(id);
    }
    public void modificarUsuario(Usuario u){
        daoUsuarios.modificarUsuario(u);
    }
    public java.util.List<Usuario> consultarUsuarios(){
        return daoUsuarios.consultarUsuarios();
    }

    public Usuario validarUsuario(String idUsuario, String clave){
        return daoUsuarios.validarUsuario(idUsuario, clave);
    }
   
    public java.util.List<Categoria> consultarCategorias(){
        return daoCategorias.consultarCategorias();
    }

    public java.util.List<Usuario> obtenerUsuarios(String id, String nombre){
        return daoUsuarios.consultarUsuarios(id, nombre);
    }
    
    public java.util.List<Prestamo> obtenerPrestamos(Usuario u){
        return daoPrestamos.consultarPrestamos(u, daoLibros);
    }
    
    public java.util.List<Prestamo> obtenerPrestamosVencidos(Usuario u){
        return daoPrestamos.consultarPrestamosVencidos(u, daoLibros);
    }
    
    public java.util.List<Prestamo> obtenerPrestamos(Libro u){
        return daoPrestamos.consultarPrestamos(u, daoLibros, daoUsuarios);
    }

    public void insertarPrestamo(Prestamo p){
        daoPrestamos.insertarPrestamo(p);
    }

    public void modificarPrestamo(Prestamo p){
        daoPrestamos.modificarPrestamo(p);
    }
    
    public void insertarCategoria(Categoria c){
        daoCategorias.insertarCategoria(c);
    }
    public void borrarCategoria(Categoria c){
        daoCategorias.borrarCategoria(c);
    }
    public void modificarCategoria(Categoria c){
        daoCategorias.modificarCategoria(c);
    }
    
    public java.util.List<Libro> consultarLibrosCategoria(Categoria c) {
        return daoLibros.consultarLibrosCategoria(c);
    }
    
    public void anadirCategoriaLibro(int l, String c) {
        daoCategorias.anadirCategoriaLibro(l, c);
    }
    
    public void quitarCategoriaLibro(int l, String c) {
        daoCategorias.quitarCategoriaLibro(l, c);
    }
    
    public void devolverPrestamo(Prestamo e) {
        daoPrestamos.devolverPrestamo(e);
    }
    
    public Prestamo getPrestamoActualEjemplar(Ejemplar ej) {
        return daoPrestamos.getPrestamoActualEjemplar(ej, daoUsuarios);
    }
    
    public java.util.List<Prestamo> getPrestamosEjemplar(Ejemplar ej) {
        return daoPrestamos.getPrestamosEjemplar(ej, daoUsuarios);
    }
    
    public void realizarPrestamo(Prestamo p){
        daoPrestamos.insertarPrestamo(p);
    }
            
}

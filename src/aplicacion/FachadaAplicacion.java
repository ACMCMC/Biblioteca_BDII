/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

/**
 *
 * @author basesdatos
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GesionLibros cl;
    GestionUsuarios cu;
    GestionCategorias cc;
    GestionPrestamos cp;

    public FachadaAplicacion() {
        fgui = new gui.FachadaGui(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
        cl = new GesionLibros(fgui, fbd);
        cu = new GestionUsuarios(fgui, fbd);
        cc = new GestionCategorias(fgui, fbd);
        cp = new GestionPrestamos(fgui, fbd);
    }

    public static void main(String args[]) {
        FachadaAplicacion fa;

        fa = new FachadaAplicacion();
        fa.iniciaInterfazUsuario();
    }

    public void iniciaInterfazUsuario() {
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e) {
        fgui.muestraExcepcion(e);
    }

    public java.util.List<Libro> obtenerLibros(Integer id, String titulo, String isbn, String autor) {
        return cl.obtenerLibros(id, titulo, isbn, autor);
    };

    public void visualizarLibro(Integer idLibro) {
        cl.visualizarLibro(idLibro);
    }

    public void nuevoLibro() {
        cl.nuevoLibro();
    }

    public Integer actualizarLibro(Libro l) {
        return cl.actualizarLibro(l);
    }

    public void borrarLibro(Integer idLibro) {
        cl.borrarLibro(idLibro);
    }

    public void actualizarCategoriasLibro(Integer idLibro, java.util.List<String> categorias) {
        cl.actualizarCategoriasLibro(idLibro, categorias);
    }

    public java.util.List<Ejemplar> actualizarEjemplaresLibro(Integer idLibro, java.util.List<Ejemplar> ejemplares,
            java.util.List<Integer> borrar) {
        return cl.actualizarEjemplaresLibro(idLibro, ejemplares, borrar);
    }

    public Boolean comprobarAutentificacion(String idUsuario, String clave) {
        return cu.comprobarAutentificacion(idUsuario, clave);
    }

    public java.util.List<Usuario> actualizarUsuarios(java.util.List<Usuario> usrs, java.util.List<String> borrar, java.util.List<Usuario> usrsInsertar) {
        return cu.actualizarUsuarios(usrs, borrar, usrsInsertar);
    }

    public void gestionUsuarios() {
        cu.gestionUsuarios();
    }

    public java.util.List<Usuario> obtenerUsuarios(String id, String nombre) {
        return cu.obtenerUsuarios(id, nombre);
    };
    
    public void insertarUsuario(Usuario u) {
        this.fbd.insertarUsuario(u);
    }

    
    public java.util.List<Prestamo> obtenerPrestamos(Usuario u){
        return cp.obtenerPrestamos(u);
    }
    
    public java.util.List<Prestamo> obtenerPrestamos(int libro){
        return cp.obtenerPrestamos(cl.obtenerLibros(libro, null, null, null).get(0));
    }

    public void insertarPrestamo(Prestamo p){
        fbd.insertarPrestamo(p);
    }

    public void modificarPrestamo(Prestamo p){
        fbd.modificarPrestamo(p);
    }
    
    public void gestionCategorias() {
        cc.gestionCategorias();
    }
    
    public java.util.List<Categoria> consultarCategorias() {
        return cc.consultarCategorias();
    }
    
    public java.util.List<Libro> consultarLibrosCategoria(Categoria c) {
        return cl.consultarLibrosCategoria(c);
    }
    
    public java.util.List<Categoria> actualizarCategorias(java.util.List<Categoria> cats, java.util.List<Categoria> borrar, java.util.List<Categoria> insertar) {
        return cc.actualizarCategorias(cats, borrar, insertar);
    }
    
    public void anadirCategoriaLibro(int l, String c) {
        cc.anadirCategoriaLibro(l, c);
    }
    
    public void quitarCategoriaLibro(int l, String c) {
        cc.quitarCategoriaLibro(l, c);
    }
    
    public void devolverPrestamo(Prestamo e) {
        cp.devolverPrestamo(e);
    }
    
    public java.util.Map<Ejemplar, Prestamo> getPrestamosActualesEjemplares(java.util.List<Ejemplar> ejs) {
        return cp.getPrestamosActualesEjemplares(ejs);
    }
    
}

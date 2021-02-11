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
    
    
 public FachadaAplicacion(){
   fgui=new gui.FachadaGui(this);
   fbd= new baseDatos.FachadaBaseDatos(this);
   cl= new GesionLibros(fgui, fbd);
   cu= new GestionUsuarios(fgui, fbd);
 }

 public static void main(String args[]) {
     FachadaAplicacion fa;
     
     fa= new FachadaAplicacion();
     fa.iniciaInterfazUsuario();
 }
 
 public void iniciaInterfazUsuario(){
     fgui.iniciaVista();
 }

 public void muestraExcepcion(String e){
     fgui.muestraExcepcion(e);
 }
 
public java.util.List<Libro> obtenerLibros(Integer id, String titulo, String isbn, String autor){
  return cl.obtenerLibros(id, titulo,  isbn,  autor);
};

public void visualizarLibro(Integer idLibro){
 cl.visualizarLibro(idLibro);
}

public void nuevoLibro(){
 cl.nuevoLibro();
}

public Integer actualizarLibro(Libro l){
  return cl.actualizarLibro(l);
}

public void borrarLibro(Integer idLibro){
   cl.borrarLibro(idLibro);
}

public void actualizarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
 cl.actualizarCategoriasLibro(idLibro, categorias);
}

public java.util.List<Ejemplar> actualizarEjemplaresLibro(Integer idLibro, java.util.List<Ejemplar> ejemplares, java.util.List<Integer> borrar){
  return cl.actualizarEjemplaresLibro(idLibro, ejemplares, borrar);
}


public Boolean comprobarAutentificacion(String idUsuario, String clave){
  return cu.comprobarAutentificacion(idUsuario, clave);
}
 




}

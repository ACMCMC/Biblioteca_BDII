/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;
import gui.FachadaGui;
import baseDatos.FachadaBaseDatos;
/**
 *
 * @author basesdatos
 */
public class GesionLibros{
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GesionLibros(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public java.util.List<Libro> obtenerLibros(Integer id, String titulo, String isbn, String autor){
        return fbd.consultarCatalogo(id, titulo, isbn, autor);
    }

    public void visualizarLibro(Integer idLibro){
        java.util.List<String> restoCategorias;
        java.util.List<Ejemplar> ejemplares;
        Libro l;
        l=fbd.consultarLibro(idLibro);
        restoCategorias=fbd.obtenerRestoCategorias(idLibro);
        fgui.visualizaLibro(l, restoCategorias);
    }

    public void nuevoLibro(){
        java.util.List<String> restoCategorias = new java.util.ArrayList<String> ();

        for(Categoria c:fbd.consultarCategorias()){
            restoCategorias.add(c.getNombre());
        }

        fgui.nuevoLibro(restoCategorias);
    }
    
    public Integer actualizarLibro(Libro l){
        
       Integer idLibro;

       if (l.getIdLibro()==null)
           idLibro=fbd.insertarLibro(l);
       else{
          fbd.modificarLibro(l);
          idLibro=l.getIdLibro();
       }

       return idLibro;
    }

    public void borrarLibro(Integer idLibro){
       fbd.borrarLibro(idLibro);
    }

    public void actualizarCategoriasLibro(Integer idLibro, java.util.List<String> categorias){
       fbd.modificarCategoriasLibro(idLibro, categorias);
    }

    public java.util.List<Ejemplar> actualizarEjemplaresLibro(Integer idLibro, java.util.List<Ejemplar> ejemplares, java.util.List<Integer> borrar){

       for (Ejemplar e:ejemplares){
        if (e.getNumEjemplar()==null) fbd.insertarEjemplarLibro(idLibro, e);
        else fbd.modificarEjemplarLibro(idLibro, e);
       }
       
       fbd.borrarEjemplaresLibro(idLibro, borrar);

       return fbd.consultarEjemplaresLibro(idLibro);
    }
}

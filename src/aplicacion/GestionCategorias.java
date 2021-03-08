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
public class GestionCategorias {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionCategorias(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public void gestionCategorias() {
        fgui.gestionCategorias();
    }
    
    public java.util.List<Categoria> consultarCategorias() {
        return fbd.consultarCategorias();
    }
    
    public java.util.List<Categoria> actualizarCategorias(java.util.List<Categoria> cats, java.util.List<Categoria> borrar, java.util.List<Categoria> insertar) {
        for (Categoria c : cats) {
                fbd.modificarCategoria(c);
        }
        
        for (Categoria c : insertar) {
                fbd.insertarCategoria(c);
        }

        for (Categoria c : borrar) {
            fbd.borrarCategoria(c);
        }
        
        return fbd.consultarCategorias();
    }

}

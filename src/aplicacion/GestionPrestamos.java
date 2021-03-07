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
public class GestionPrestamos {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionPrestamos(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public java.util.List<Prestamo> obtenerPrestamos(Usuario u) {
        return fbd.obtenerPrestamos(u);
    }

}

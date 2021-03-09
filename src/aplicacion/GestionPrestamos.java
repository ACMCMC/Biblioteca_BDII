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
    
    public java.util.List<Prestamo> obtenerPrestamos(Libro l) {
        return fbd.obtenerPrestamos(l);
    }
    
    public void devolverPrestamo(Prestamo e) {
        fbd.devolverPrestamo(e);
    }
    
    public java.util.Map<Ejemplar, Prestamo> getPrestamosActualesEjemplares(java.util.List<Ejemplar> ejs) {
        java.util.Map<Ejemplar, Prestamo> mapa = new java.util.HashMap<Ejemplar, Prestamo>();
        for (Ejemplar ejemp : ejs) {
            mapa.put(ejemp, fbd.getPrestamoActualEjemplar(ejemp));
        }
        return mapa;
    }
    
    public java.util.List<Prestamo> getPrestamosEjemplar(Ejemplar ej) {
        return fbd.getPrestamosEjemplar(ej);
    }

}

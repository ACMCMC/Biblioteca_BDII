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
public class GestionUsuarios {

    FachadaGui fgui;
    FachadaBaseDatos fbd;

    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd) {
        this.fgui = fgui;
        this.fbd = fbd;
    }

    public Boolean comprobarAutentificacion(String idUsuario, String clave) {
        Usuario u;

        u = fbd.validarUsuario(idUsuario, clave);
        if (u != null) {
            return u.getTipoUsuario() == TipoUsuario.Administrador;
        } else
            return false;
    }

    public java.util.List<Usuario> actualizarUsuarios(java.util.List<Usuario> usrs, java.util.List<String> borrar, java.util.List<Usuario> usrsInsertar) {

        for (Usuario u : usrs) {
                fbd.modificarUsuario(u);
        }
        
        for (Usuario u : usrsInsertar) {
                fbd.insertarUsuario(u);
        }

        for (String u : borrar) {
            fbd.borrarUsuario(u);
        }

        return fbd.consultarUsuarios();
    }

    public void gestionUsuarios() {
        fgui.gestionUsuarios();
    }

    public java.util.List<Usuario> obtenerUsuarios(String id, String nombre) {
        return fbd.obtenerUsuarios(id, nombre);
    }

}

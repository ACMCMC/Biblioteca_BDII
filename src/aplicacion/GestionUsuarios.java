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
    
   
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }  
    
    
  public Boolean comprobarAutentificacion(String idUsuario, String clave){
      Usuario u;

      u=fbd.validarUsuario(idUsuario, clave);
      if (u!=null){
          return u.getTipoUsuario()==TipoUsuario.Administrador;
      } else return false;
  }
  
  public java.util.List<Usuario> actualizarUsuarios(java.util.List<Usuario> usrs, java.util.List<String> borrar){

    for (Usuario u:usrs){
     if (u.getIdUsuario()==null) fbd.insertarUsuario(u);
     else fbd.modificarUsuario(u);
    }

    for (String u:borrar){
        fbd.borrarUsuario(u);
    }

    return fbd.consultarUsuarios();
 }
  
  public void gestionUsuarios() {
      fgui.gestionUsuarios();
  }
  
}

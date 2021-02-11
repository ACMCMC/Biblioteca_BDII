/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion;

import aplicacion.Libro;

/**
 *
 * @author basesdatos
 */
public class Ejemplar {

    private Integer numEjemplar;
    private String localizador;
    private String anoCompra;
    private Libro libro;

    public Ejemplar(Libro libro, Integer numEjemplar, String localizador, String anoCompra){
        this.numEjemplar=numEjemplar;
        this.anoCompra=anoCompra;
        this.localizador=localizador;
        this.libro=libro;
    }

    public Integer getNumEjemplar(){
        return this.numEjemplar;
    }

    public String getAnoCompra(){
        return this.anoCompra;
    }

    public String getLocalizador(){
        return this.localizador;
    }

    public Libro getLibro(){
        return this.libro;
    }

    public void setLocalizador(String l){
        localizador =l;
    }
    
    public void setAnoCompra(String a){
        anoCompra = a;
    }
    
}

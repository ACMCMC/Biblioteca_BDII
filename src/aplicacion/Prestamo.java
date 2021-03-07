package aplicacion;

import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author basesdatos
 */
public class Prestamo {
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Date fechaVencimiento;
    private Ejemplar ejemplar;
    private Usuario usuario;

    public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Date fechaVencimiento, Ejemplar ejemplar, Usuario usuario) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaVencimiento = fechaVencimiento;
        this.ejemplar = ejemplar;
        this.usuario = usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import aplicacion.Usuario;
import aplicacion.Prestamo;
import javax.swing.table.*;

/**
 *
 * @author basesdatos
 */
public class ModeloTablaPrestamos extends AbstractTableModel {
    private java.util.List<Usuario> usuarios;
    private java.util.Map<Usuario, java.util.List<Prestamo>> prestamosVencidos;

    public ModeloTablaPrestamos() {
        this.usuarios = new java.util.ArrayList<Usuario>();
    }

    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
        case 0:
            nombre = "Id";
            break;
        case 1:
            nombre = "Nombre";
            break;
        case 2:
            nombre = "Email";
            break;
        case 3:
            nombre = "Prestamos vencidos";
            break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
        case 0:
            clase = java.lang.String.class;
            break;
        case 1:
            clase = java.lang.String.class;
            break;
        case 2:
            clase = java.lang.String.class;
            break;
        case 3:
            clase = java.lang.Integer.class;
            break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col > 0;
    }

    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
        case 0:
            resultado = usuarios.get(row).getIdUsuario();
            break;
        case 1:
            resultado = usuarios.get(row).getNombre();
            break;
        case 2:
            resultado = usuarios.get(row).getEmail();
            break;
        case 3:
            resultado = prestamosVencidos.get(usuarios.get(row)).size();
            break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Usuario> usrs, java.util.Map<Usuario, java.util.List<Prestamo>> prests) {
        this.usuarios = usrs;
        this.prestamosVencidos = prests;
        fireTableDataChanged();
    }

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged(); // To change body of generated methods, choose Tools | Templates.
    }

    public java.util.List<Usuario> getFilas() {
        return this.usuarios;
    }

    public Usuario obtenerUsuario(int i) {
        return this.usuarios.get(i);
    }
}

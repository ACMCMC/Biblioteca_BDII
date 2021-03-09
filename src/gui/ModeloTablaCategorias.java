/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import aplicacion.Categoria;
import javax.swing.table.*;
/**
 *
 * @author basesdatos
 */
public class ModeloTablaCategorias extends AbstractTableModel{
    private java.util.List<Categoria> categorias;

    public ModeloTablaCategorias(){
        this.categorias=new java.util.ArrayList<Categoria>();
    }

    public int getColumnCount (){
        return 1;
    }

    public int getRowCount(){
        return categorias.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Nombre"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= aplicacion.Categoria.class; break;
            
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
       return col>0;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado= categorias.get(row).getNombre(); break;
        }
        return resultado;
    }

    public void setFilas(java.util.List<Categoria> c){
        this.categorias=c;
        fireTableDataChanged();
    }

    public void nuevaCategoria(Categoria c){
        this.categorias.add(c);
        fireTableRowsInserted(this.categorias.size()-1, this.categorias.size()-1);
    }

    public void borrarCategoria(int indice){
        this.categorias.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public java.util.List<Categoria> getFilas(){
        return this.categorias;
    }

    public Categoria obtenerCategoria(int i){
        return this.categorias.get(i);
    }
}

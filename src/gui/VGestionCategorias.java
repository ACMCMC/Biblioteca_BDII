/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VLibro.java
 *
 * Created on 16-feb-2011, 18:17:07
 */

package gui;

import aplicacion.Categoria;
import aplicacion.Libro;
import aplicacion.TipoUsuario;
import aplicacion.Usuario;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import aplicacion.Ejemplar;
import javax.swing.JOptionPane;

/**
 *
 * @author basesdatos
 */
public class VGestionCategorias extends javax.swing.JDialog {

    private java.util.List<Categoria> categoriasBorradas;
    private VPrincipal padre;
    private aplicacion.FachadaAplicacion fa;

    /** Creates new form VLibro */
    public VGestionCategorias(java.awt.Frame parent, boolean modal, aplicacion.FachadaAplicacion fa) {
        super(parent, modal);
        this.fa = fa;
        initComponents();
        padre = (VPrincipal) parent;
        btnAnadir.setEnabled(true);
        this.categoriasBorradas = new java.util.ArrayList<Categoria>();

        ModeloTablaCategorias mTablaC = new ModeloTablaCategorias();
        lstCategorias.setModel(mTablaC);

        mTablaC.setFilas(fa.consultarCategorias());
        lstCategorias.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstCategorias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                if (lstCategorias.getSelectedRow() >= 0) {
                    Categoria c = mTablaC.getFilas().get(lstCategorias.getSelectedRow());
                    actualizarDatos(c);
                    btnBorrar.setEnabled(true);
                }
            }
        });

        btnBorrar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAnadir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstCategorias = new javax.swing.JTable();
        textNombre = new javax.swing.JTextField();
        labelNombre = new javax.swing.JLabel();
        labelDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDesc = new javax.swing.JTextArea();
        labelCats = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de categorías");
        setResizable(false);

        btnAnadir.setText("Añadir");
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        lstCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Categoría"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(lstCategorias);

        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombre.setText("Nombre:");

        labelDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDesc.setText("Descripción:");

        textDesc.setColumns(20);
        textDesc.setLineWrap(true);
        textDesc.setRows(5);
        jScrollPane1.setViewportView(textDesc);

        labelCats.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCats.setText("Categorías:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCats, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalir)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAnadir)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBorrar))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textNombre))
                                .addComponent(labelDesc)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCats)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNombre)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnadir)
                            .addComponent(btnBorrar))
                        .addGap(59, 59, 59)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }// GEN-LAST:event_btnSalirActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        java.util.List<Categoria> cats;
        java.util.List<Categoria> catsInsertar = new java.util.ArrayList<Categoria>();

        ModeloTablaCategorias mu = (ModeloTablaCategorias) lstCategorias.getModel();
        cats = mu.getFilas();

        catsInsertar.add(new Categoria(textNombre.getText(), textDesc.getText()));

        cats = fa.actualizarCategorias(cats, categoriasBorradas, catsInsertar);
        mu.setFilas(cats);
        if (mu.getRowCount() > 0) {
            lstCategorias.setRowSelectionInterval(0, 0);
            btnBorrar.setEnabled(true);
        } else
            btnBorrar.setEnabled(false);
    }// GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        ModeloTablaCategorias mu = (ModeloTablaCategorias) lstCategorias.getModel();
        if (mu.obtenerCategoria(lstCategorias.getSelectedRow()).getNombre() != null) {
            if (fa.consultarLibrosCategoria(mu.obtenerCategoria(lstCategorias.getSelectedRow())).isEmpty()) {
                categoriasBorradas.add(mu.obtenerCategoria(lstCategorias.getSelectedRow()));
            } else {
                fa.muestraExcepcion("No se puede borrar la categoría, tiene libros.");
            }

        }
        java.util.List<Categoria> catsActs = fa.actualizarCategorias(mu.getFilas(), categoriasBorradas,
                new java.util.ArrayList<Categoria>());
        mu.setFilas(catsActs);
        categoriasBorradas.clear();
        if (mu.getRowCount() == 0) {
            btnBorrar.setEnabled(false);
        } else {
            lstCategorias.setRowSelectionInterval(0, 0);
        }
    }// GEN-LAST:event_btnBorrarActionPerformed

    private void actualizarDatos(Categoria c) {

        textNombre.setText(c.getNombre());
        textNombre.setEnabled(true);
        textDesc.setText(c.getDescripcion());
        textDesc.setEnabled(true);

    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelCats;
    private javax.swing.JLabel labelDesc;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JTable lstCategorias;
    private javax.swing.JTextArea textDesc;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables

}

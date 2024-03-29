/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Jugador.java
 *
 * Created on 02-jun-2010, 18:59:14
 */

package com.googlecode.jbridges.lib.interfaz;

import com.googlecode.jbridges.lib.Jugador;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdiazoli
 */
public class NombreJugador extends javax.swing.JDialog {

    Jugador jug;
    /** Creates new form Jugador */
    public NombreJugador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public NombreJugador(java.awt.Dialog parent, boolean modal, int puntuacion) {
        super(parent, modal);
        initComponents();
        jug = new Jugador(null, puntuacion);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jugador");

        jLabel1.setFont(new java.awt.Font("Mufferaw", 1, 14));
        jLabel1.setText("Introduzca su nombre:");

        aceptar.setFont(new java.awt.Font("Croobie", 0, 18));
        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(aceptar)
                .addGap(107, 107, 107))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aceptar)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        // TODO add your handling code here:
        jug.setNombre(nombre.getText());

        Object[][] ranking = MetodosEstaticos.cargarRanking();


        if (jug.getPuntuacion() > Integer.parseInt((ranking[1][9] != null) ? ranking[1][9].toString() : "0")) {
            for (int i = 8; i >= 0; i--) {
                if (jug.getPuntuacion() > Integer.parseInt((ranking[1][i] != null) ? ranking[1][i].toString() : "0")) {
                    ranking[0][i] = ranking[0][i+1];
                    ranking[1][i] = ranking[1][i+1];
                } else {
                    ranking[0][i +1] = jug.getNombre();
                    ranking[1][i +1] = jug.getPuntuacion();
                    continue;
                }
                if (i == 0) {
                    ranking[0][0] = jug.getNombre();
                    ranking[1][0] = jug.getPuntuacion();
                }
            }
        }
                   

        MetodosEstaticos.guardarRanking(ranking);

        Ranking r=new Ranking(this, true);
        r.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_aceptarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NombreJugador dialog = new NombreJugador(new javax.swing.JFrame(), true);
                dialog.setTitle("Nuevo R�cord");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables

}

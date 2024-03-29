/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tamaño.java
 *
 * Created on 01-jun-2010, 18:12:41
 */

package com.googlecode.jbridges.lib.interfaz;




/**
 *
 * @author mdiazoli
 */
public class Dificultad extends javax.swing.JDialog {
    int tam;
    int puntuacion;
    /** Creates new form Tamaño */
    public Dificultad(java.awt.Dialog parent, boolean modal, int tam, int puntuacion) {
        super(parent, modal);
        initComponents();
        buttonGroup1.add(baja);
        buttonGroup1.add(alta);
        buttonGroup1.add(aleatoria);
        this.tam=tam;
        this.puntuacion=puntuacion;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        baja = new javax.swing.JRadioButton();
        alta = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        aleatoria = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dificultad");

        jLabel1.setFont(new java.awt.Font("Mufferaw", 1, 18));
        jLabel1.setText("Elija el nivel de dificultad del juego:");

        baja.setFont(new java.awt.Font("Bradley Hand ITC", 1, 18));
        baja.setText("Baja");
        baja.setName("baja"); // NOI18N
        baja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajaActionPerformed(evt);
            }
        });

        alta.setFont(new java.awt.Font("Bradley Hand ITC", 1, 18));
        alta.setText("Alta");
        alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 12));
        jLabel2.setText("(En caso de elegir baja o alta, el resultado puede tardar unos segundos)");

        aleatoria.setFont(new java.awt.Font("Bradley Hand ITC", 1, 18));
        aleatoria.setText("Aleatoria");
        aleatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aleatoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(baja)
                            .addComponent(alta)
                            .addComponent(aleatoria)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(baja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aleatoria)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        baja.getAccessibleContext().setAccessibleDescription("Baja");

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

    private void bajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajaActionPerformed
        // TODO add your handling code here:
        puntuacion=puntuacion+15;
        if(tam==1){
            TableroPequeño tablero=new TableroPequeño(puntuacion, 1);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }else if(tam==2){
            TableroMediano tablero=new TableroMediano(puntuacion, 1);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }else if(tam==3){
            TableroGrande tablero=new TableroGrande(puntuacion, 1);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }
}//GEN-LAST:event_bajaActionPerformed

    private void aleatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aleatoriaActionPerformed
        // TODO add your handling code here:
        if(tam==1){
            TableroPequeño tablero=new TableroPequeño(puntuacion, 0);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }else if(tam==2){
            TableroMediano tablero=new TableroMediano(puntuacion, 0);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }else if(tam==3){
            TableroGrande tablero=new TableroGrande(puntuacion, 0);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }
        
        


    }//GEN-LAST:event_aleatoriaActionPerformed

    private void altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altaActionPerformed
        // TODO add your handling code here:
        puntuacion=puntuacion+50;
        if(tam==1){
            TableroPequeño tablero=new TableroPequeño(puntuacion, 2);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }else if(tam==2){
            TableroMediano tablero=new TableroMediano(puntuacion, 2);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }else if(tam==3){
            TableroGrande tablero=new TableroGrande(puntuacion, 2);
            tablero.setLocationRelativeTo(null);
            tablero.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_altaActionPerformed

    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Dificultad dialog = new Dificultad(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton aleatoria;
    private javax.swing.JRadioButton alta;
    private javax.swing.JRadioButton baja;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Plantilla.java
 *
 * Created on 01-jun-2010, 21:35:20
 */

package com.googlecode.jbridges.lib.interfaz;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;
import java.awt.Font;
import javax.swing.JTable;

/**
 *
 * @author mdiazoli
 */
public class TableroGrande extends javax.swing.JFrame {

    private int fila;
    private int columna;
 

    FabricaDeProblemas miFabrica=FabricaDeProblemas.getInstancia();
    Tablero problema = miFabrica.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);
    /** Creates new form Plantilla */
    public TableroGrande(int tam) {

        initComponents();
        obtenerTablero(tam);
        RenderTabla miRender = new RenderTabla();
        jTable1.setDefaultRenderer( Object.class, miRender);
        this.fila = -1;
        this.columna = -1;
    
    }

    public TableroGrande(JTable t){
        
        this.add(t);
        t.setVisible(true);
        this.setVisible(true);
        this.fila = -1;
        this.columna = -1;
    }

    public void obtenerTablero(int tam){

        if(tam==1){
            jLabel3.setText("Peque�o");
        }else if(tam==2){
            jLabel3.setText("Mediano");
        }else if(tam==1){
            jLabel3.setText("Grande");
        }
        for(int i=0;i<problema.getAltura();i++){
            for(int j=0;j<problema.getAnchura();j++){
                Coordenadas coord;
                coord=problema.getCoordenadas(i, j);
                Casilla casilla=problema.getCasilla(coord);
                if (casilla instanceof Isla){
                    Isla isla=(Isla)casilla;
                    Integer n=isla.getN();
                    jTable1.setValueAt(n, i, j);
                }
            }
        }
     }
    
    public class Cronometro implements Runnable {

        Thread crono;

        public Cronometro() {


            crono = new Thread(this);
            crono.start();
        }
        int minutos = 0, segundos = 0, horas = 0;

        public void run() {
            try {
                for (;;) {
                    if (segundos == 59) {
                        segundos = 0;
                        minutos++;
                    }
                    if (minutos == 59) {
                        minutos = 0;
                        horas++;
                    }
                    segundos++;
                    Font f2 = new Font("Bradley Hand ITC", Font.BOLD, 24);
                    jLabel7.setFont(f2);
                    jLabel7.setText(horas + ":" + minutos + ":" + segundos);
                    crono.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero Grande");

        jButton1.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton1.setText("Nueva Partida");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton2.setText("Guardar");

        jButton3.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton3.setText("Siguiente Paso");

        jButton4.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton4.setText("Comprobar");

        jButton5.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton5.setText("Solucionar");

        jButton6.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton6.setText("Salir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 36));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HASHIWOKAKERO");

        jButton7.setFont(new java.awt.Font("Croobie", 0, 18));
        jButton7.setText("Clasificaci�n");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        jLabel2.setText("Tama�o:");

        jLabel3.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        jLabel4.setText("Nivel:");

        jLabel5.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        jLabel6.setText("Tiempo:");

        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(118, 118, 118)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton6)
                                        .addComponent(jLabel6)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel2)
                .addGap(63, 63, 63)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(78, 78, 78)
                .addComponent(jLabel5)
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(26, 26, 26)
                        .addComponent(jButton6)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        int f=jTable1.rowAtPoint(evt.getPoint());
        int c=jTable1.columnAtPoint(evt.getPoint());
        Coordenadas coord=problema.getCoordenadas(f, c);
        boolean vacias=false;
        boolean h1=false;
        boolean h2=false;
        boolean v1=false;
        boolean v2=false;

        if((problema.getCasilla(coord)instanceof Isla)){

           if (this.fila == -1 || this.columna == -1) {
                this.fila = jTable1.rowAtPoint(evt.getPoint());
                this.columna = jTable1.columnAtPoint(evt.getPoint());
            } else {
                int fila2 = jTable1.rowAtPoint(evt.getPoint());
                int columna2= jTable1.columnAtPoint(evt.getPoint());

           if(fila==fila2){

                if(columna-columna2<0){
                    //Comprueba que todas las celdas intermedias est�n vacias
                    for(int i=columna+1;i<columna2;i++){
                        if(jTable1.getValueAt(fila, i)==null){
                            vacias=true;
                        }else if(jTable1.getValueAt(fila, i).equals("1PuenteH")){
                            h1=true;
                        }else if(jTable1.getValueAt(fila, i).equals("2PuentesH")){
                            h2=true;
                        }
                    }
                    if(vacias){
                        for(int i=columna+1;i<columna2;i++){
                            jTable1.setValueAt("1PuenteH", fila, i);
                        }
                    }else if(h1){
                        for(int i=columna+1;i<columna2;i++){
                            jTable1.setValueAt("2PuentesH", fila, i);
                        }
                    }else if(h2){
                       for(int i=columna+1;i<columna2;i++){
                            jTable1.setValueAt("borra", fila, i);
                        }
                    }
                }else if(columna-columna2>0){
                    for(int i=columna-1;i<columna2;i--){
                        if(jTable1.getValueAt(fila, i)==null){
                            vacias=true;
                        }else if(jTable1.getValueAt(fila, i).equals("1PuenteH")){
                            h1=true;
                        }else if(jTable1.getValueAt(fila, i).equals("2PuentesH")){
                            h2=true;
                        }
                    }
                    if(vacias){
                        for(int i=columna-1;i<columna2;i--){
                            jTable1.setValueAt("1PuenteH", fila, i);
                        }
                    }else if(h1){
                        for(int i=columna-1;i<columna2;i--){
                            jTable1.setValueAt("2PuentesH", fila, i);
                        }
                    }else if(h2){
                        for(int i=columna-1;i<columna2;i--){
                            jTable1.setValueAt("borra", fila, i);
                        }
                    }
                }
                h2=false;
                h1=false;
                vacias=false;
                this.fila=-1;
                this.columna=-1;
            }else if(columna==columna2){

                if(fila < fila2){
                    for(int i=fila+1;i<fila2;i++){
                        if(jTable1.getValueAt(i, columna)==null){
                            vacias=true;
                        }else if(jTable1.getValueAt(i, columna).equals("1PuenteV")){
                            v1=true;
                        }else if(jTable1.getValueAt(i, columna).equals("2PuentesV")){
                            v2=true;
                        }
                    }
                    if(vacias){
                        for(int i=fila+1;i<fila2;i++){
                            jTable1.setValueAt("1PuenteV", i, columna);
                        }
                    }else if(v1){
                        for(int i=fila+1;i<fila2;i++){
                            jTable1.setValueAt("2PuentesV", i, columna);
                        }
                    }else if(v2){
                        for(int i=fila+1;i<fila2;i++){
                            jTable1.setValueAt("borra", i, columna);
                        }
                    }
                }else if(fila > fila2){
                    for(int i=fila-1;i>fila2;i--){
                        if(jTable1.getValueAt(i, columna)==null){
                            vacias=true;
                        }else if(jTable1.getValueAt(i, columna).equals("1PuenteV")){
                            v1=true;
                        }else if(jTable1.getValueAt(i, columna).equals("2PuentesV")){
                            v2=true;
                        }
                    }
                    if(vacias){
                        for(int i=fila-1;i>fila2;i--){
                            jTable1.setValueAt("1PuenteV", i, columna);
                        }
                    }else if(v1){
                        for(int i=fila-1;i>fila2;i--){
                            jTable1.setValueAt("2PuentesV", i, columna);
                        }
                    }else if(v2){
                        for(int i=fila-1;i>fila2;i--){
                            jTable1.setValueAt("borra", i, columna);
                        }
                    }
                }
                v2=false;
                v1=false;
                vacias=false;
                this.fila=-1;
                this.columna=-1;
            }
        }
    }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Guardar guardarPartida= new Guardar(this, true);
        guardarPartida.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Ranking r=new Ranking(this, true);
        r.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed


    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]) {
//        //.awt.EventQueue.invokeLater(new Runnable() {
//          //  public void run() {
//               TableroGrande p= new TableroGrande();
//
//               p.setVisible(true);
//
//            //}
//        //});
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    

}

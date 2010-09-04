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


import com.googlecode.jbridges.lib.Configuracion;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.FabricaDeSoluciones;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
import com.sleepycat.je.DatabaseException;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;


/////**
//// *
//// * @author mdiazoli
//// */
public class TableroMediano extends javax.swing.JFrame {


    private int fila;
    private int columna;
    int puntuacion;



        FabricaDeProblemas fp;
        Tablero problema;
        FabricaDeSoluciones fs;
        EstrategiaSolucion es;
        List<Solucion> ss;
        public List<ElementoSolucion> solUsuario;
        public List <ElementoSolucion> sol;



    /** Creates new form Plantilla */
    public TableroMediano(int puntuacion){

        initComponents();
        new Cronometro();

        Configuracion.setAltoTablero(10);
        Configuracion.setAnchoTablero(10);

        fp = FabricaDeProblemas.getInstancia();
        problema = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);
//        problema.borrarPuentes();
//        es = new EstrategiaBackTrackingBasica();
//
//        ss = es.solucionar(problema);
//
//        if(!ss.isEmpty()){
//            sol= (List<ElementoSolucion>) ss.get(0);
//        }
//        System.out.println("Tama�o lista soluciones:"+ ss.size());


        MetodosEstaticos.obtenerTablero(problema, jTable1);
        RenderTabla miRender = new RenderTabla();
        jTable1.setDefaultRenderer( Object.class, miRender);
        
        this.fila = -1;
        this.columna = -1;
        this.puntuacion=puntuacion;
        solUsuario=new LinkedList<ElementoSolucion>();

        

       // System.out.println("Tama�o lista soluciones:"+soluciones.size());
    }

    public TableroMediano(JTable t){

        this.add(t);
        t.setVisible(true);
        this.setVisible(true);
        this.fila = -1;
        this.columna = -1;
    }






    public class Cronometro implements Runnable {

        Thread crono;


        /** Creates new form cronometro */
        public Cronometro() {

            crono = new Thread(this);
            crono.start();
        }
        int minutos = 0, segundos = 0, horas = 0;

        public void run() {
            try {
                for (;;) {
                    if (segundos == 59) {
                        segundos = -1;
                        minutos++;
                    }
                    if (minutos == 59) {
                        minutos = -1;
                        horas++;
                    }
                    segundos++;
                    Font f2 = new Font("Tempus Sans ITC", Font.BOLD, 18);
                    cronometro.setFont(f2);
                    cronometro.setText(horas + ":" + minutos + ":" + segundos);
                    crono.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
////
////
////    /** This method is called from within the constructor to
////     * initialize the form.
////     * WARNING: Do NOT modify this code. The content of this method is
////     * always regenerated by the Form Editor.
////     */
////    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nuevaPartida = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        siguientePaso = new javax.swing.JButton();
        comprobar = new javax.swing.JButton();
        solucionar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        clasificacion = new javax.swing.JButton();
        tama�o = new javax.swing.JLabel();
        tipoTama�o = new javax.swing.JLabel();
        nivel = new javax.swing.JLabel();
        tipoNivel = new javax.swing.JLabel();
        tiempo = new javax.swing.JLabel();
        cronometro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero Mediano");

        nuevaPartida.setFont(new java.awt.Font("Croobie", 0, 18));
        nuevaPartida.setText("Nueva Partida");
        nuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaPartidaActionPerformed(evt);
            }
        });

        Guardar.setFont(new java.awt.Font("Croobie", 0, 18)); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        siguientePaso.setFont(new java.awt.Font("Croobie", 0, 18));
        siguientePaso.setText("Siguiente Paso");
        siguientePaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguientePasoActionPerformed(evt);
            }
        });

        comprobar.setFont(new java.awt.Font("Croobie", 0, 18));
        comprobar.setText("Comprobar");
        comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprobarActionPerformed(evt);
            }
        });

        solucionar.setFont(new java.awt.Font("Croobie", 0, 18));
        solucionar.setText("Solucionar");
        solucionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solucionarActionPerformed(evt);
            }
        });

        salir.setFont(new java.awt.Font("Croobie", 0, 18));
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        clasificacion.setFont(new java.awt.Font("Croobie", 0, 18));
        clasificacion.setText("Clasificaci�n");
        clasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clasificacionActionPerformed(evt);
            }
        });

        tama�o.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        tama�o.setText("Tama�o:");

        tipoTama�o.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18));
        tipoTama�o.setText("Mediano");

        nivel.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        nivel.setText("Nivel:");

        tipoNivel.setText("jLabel5");

        tiempo.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        tiempo.setText("Tiempo:");

        cronometro.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jLabel1)
                .addContainerGap(276, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(solucionar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comprobar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(clasificacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(siguientePaso, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(salir)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tiempo)
                                        .addGap(9, 9, 9))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(nuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(cronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nivel)
                            .addComponent(tama�o))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoTama�o)
                            .addComponent(tipoNivel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(811, 811, 811))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(nuevaPartida)
                        .addGap(18, 18, 18)
                        .addComponent(Guardar)
                        .addGap(18, 18, 18)
                        .addComponent(siguientePaso)
                        .addGap(18, 18, 18)
                        .addComponent(comprobar)
                        .addGap(18, 18, 18)
                        .addComponent(solucionar)
                        .addGap(18, 18, 18)
                        .addComponent(clasificacion)
                        .addGap(18, 18, 18)
                        .addComponent(salir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tama�o)
                    .addComponent(tipoTama�o)
                    .addComponent(tiempo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nivel)
                    .addComponent(tipoNivel)
                    .addComponent(cronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaPartidaActionPerformed
        // TODO add your handling code here:
//        Salir s=new Salir(this, true);
//        s.setVisible(true);

}//GEN-LAST:event_nuevaPartidaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//       if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//            MetodosEstaticos.accionRaton(evt, jTable1, problema, fila, columna);
//        }
//        if (!MetodosEstaticos.comparaListas(solUsuario, sol)) {
            int f = jTable1.rowAtPoint(evt.getPoint());
            int c = jTable1.columnAtPoint(evt.getPoint());
            Coordenadas coord = problema.getCoordenadas(f, c);
            boolean vacias = false;
            boolean h1 = false;
            boolean h2 = false;
            boolean v1 = false;
            boolean v2 = false;
//            boolean completado=false;



            if ((problema.getCasilla(coord) instanceof Isla)) {

                if (fila == -1 || columna == -1) {
                    fila = jTable1.rowAtPoint(evt.getPoint());
                    columna = jTable1.columnAtPoint(evt.getPoint());
                } else {
                    int fila2 = jTable1.rowAtPoint(evt.getPoint());
                    int columna2 = jTable1.columnAtPoint(evt.getPoint());

//                    Coordenadas coord1=problema.getCoordenadas(fila, columna);
//                    Coordenadas coord2=problema.getCoordenadas(fila2, columna2);
//                    Isla inicio=(Isla)problema.getCasilla(coord1);
//                    Isla fin=(Isla)problema.getCasilla(coord2);
                    if (fila == fila2) {

                        if (columna - columna2 < 0) {
                            //Comprueba que todas las celdas intermedias est�n vacias
                            for (int i = columna + 1; i < columna2; i++) {
                                if ((jTable1.getValueAt(fila, i) == null) || (jTable1.getValueAt(fila, i).equals("borra"))) {
                                    vacias = true;
                                    //break;
                                } else if ((jTable1.getValueAt(fila, i).equals("1PuenteH"))){// || (jTable1.getValueAt(fila, i).equals("1PuenteH_Error"))) {
                                    h1 = true;
                                } else if ((jTable1.getValueAt(fila, i).equals("2PuentesH"))){// || (jTable1.getValueAt(fila, i).equals("2PuentesH_Error"))) {
                                    h2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = columna + 1; i < columna2; i++) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("1PuenteH", fila, i);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (h1) {
                                for (int i = columna + 1; i < columna2; i++) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("2PuentesH", fila, i);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (h2) {
                                for (int i = columna + 1; i < columna2; i++) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
//                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("borra", fila, i);
//                                        puntuacion=puntuacion-2;
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                    }
                                }
                            }
                        } else if (columna - columna2 > 0) {
                            for (int i = columna - 1; i > columna2; i--) {
                                if ((jTable1.getValueAt(fila, i) == null) || (jTable1.getValueAt(fila, i).equals("borra"))) {
                                    vacias = true;
                                } else if ((jTable1.getValueAt(fila, i).equals("1PuenteH"))){// || (jTable1.getValueAt(fila, i).equals("1PuenteH_Error"))) {
                                    h1 = true;
                                } else if ((jTable1.getValueAt(fila, i).equals("2PuentesH"))){// || (jTable1.getValueAt(fila, i).equals("2PuentesH_Error"))) {
                                    h2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = columna - 1; i > columna2; i--) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("1PuenteH", fila, i);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (h1) {
                                for (int i = columna - 1; i > columna2; i--) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("2PuentesH", fila, i);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (h2) {
                                for (int i = columna - 1; i > columna2; i--) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
//                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("borra", fila, i);
//                                        puntuacion=puntuacion-2;
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                    }
                                }
                            }
                        }
                        h2 = false;
                        h1 = false;
                        vacias = false;
                        fila = -1;
                        columna = -1;
                    } else if (columna == columna2) {

                        if (fila < fila2) {
                            for (int i = fila + 1; i < fila2; i++) {
                                if ((jTable1.getValueAt(i, columna) == null) || (jTable1.getValueAt(i, columna).equals("borra"))) {
                                    vacias = true;
                                    //break;
                                } else if ((jTable1.getValueAt(i, columna).equals("1PuenteV"))){// || (jTable1.getValueAt(i, columna).equals("1PuenteV_Error"))) {
                                    v1 = true;
                                } else if ((jTable1.getValueAt(i, columna).equals("2PuentesV"))){// || (jTable1.getValueAt(i, columna).equals("2PuentesV_Error"))) {
                                    v2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = fila + 1; i < fila2; i++) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("1PuenteV", i, columna);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (v1) {
                                for (int i = fila + 1; i < fila2; i++) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("2PuentesV", i, columna);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (v2) {
                                for (int i = fila + 1; i < fila2; i++) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
//                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("borra", i, columna);
//                                        puntuacion=puntuacion-2;
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                    }
                                }
                            }
                        } else if (fila > fila2) {
                            for (int i = fila - 1; i > fila2; i--) {
                                if ((jTable1.getValueAt(i, columna) == null) || (jTable1.getValueAt(i, columna).equals("borra"))) {
                                    vacias = true;
                                } else if ((jTable1.getValueAt(i, columna).equals("1PuenteV"))){// || (jTable1.getValueAt(i, columna).equals("1PuenteV_Error"))) {
                                    v1 = true;
                                } else if ((jTable1.getValueAt(i, columna).equals("2PuentesV"))){// || (jTable1.getValueAt(i, columna).equals("2PuentesV_Error"))) {
                                    v2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = fila - 1; i > fila2; i--) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("1PuenteV", i, columna);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (v1) {
                                for (int i = fila - 1; i > fila2; i--) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("2PuentesV", i, columna);
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }
//                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
//                                            puntuacion=puntuacion+3;
//                                        }else{
//                                            puntuacion=puntuacion-2;
//                                        }
//                                    }
                                }
                            } else if (v2) {
                                for (int i = fila - 1; i > fila2; i--) {
//                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
//                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("borra", i, columna);
//                                        puntuacion=puntuacion-2;
//                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//                                            completado=true;
//                                        }

//                                    }
                                }
                            }
                        }
                        v2 = false;
                        v1 = false;
                        vacias = false;
                        fila=-1;
                        columna=-1;
                    }
//                    if(completado){
//                        Enhorabuena e=new Enhorabuena(parent, true, puntuacion);
//                        e.setVisible(true);
//                    }
                }
            }else{
                fila=-1;
                columna=-1;
            }
 //       }

    }//GEN-LAST:event_jTable1MouseClicked

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
//        Guardar guardarPartida= new Guardar(this, true, problema);
//        guardarPartida.setVisible(true);
//        this.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

    private void clasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clasificacionActionPerformed
//        try {
//            // TODO add your handling code here:
//            Sample sample = new Sample("C:\\temp", null, jTable1, null, null);
//            try {
//                sample.runJugador("muestra");
//            } catch (Exception ex) {
//                Logger.getLogger(TableroMediano.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            sample.close();
//        } catch (DatabaseException ex) {
//            Logger.getLogger(TableroMediano.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TableroMediano.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Ranking r=new Ranking(this, true);
//        r.setVisible(true);
    }//GEN-LAST:event_clasificacionActionPerformed

    private void solucionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solucionarActionPerformed
        // TODO add your handling code here:
//        if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//            MetodosEstaticos.borrarPuentes(problema, jTable1, solUsuario);
            MetodosEstaticos.obtenerSolucion(sol, solUsuario, jTable1, problema);
//        }
    }//GEN-LAST:event_solucionarActionPerformed

    private void siguientePasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguientePasoActionPerformed
        // TODO add your handling code here:
//        if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//            puntuacion=puntuacion-5;
//            MetodosEstaticos.siguentePaso(solUsuario, sol, jTable1, problema);
//        }
    }//GEN-LAST:event_siguientePasoActionPerformed

    private void comprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprobarActionPerformed
        // TODO add your handling code here:
//        if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//            puntuacion=puntuacion-5;
//            if(MetodosEstaticos.comprobar(solUsuario, sol, jTable1, problema)){
//                  SolParcialCorrecta spc=new SolParcialCorrecta(this, true);
//                  spc.setVisible(true);
//        }
    }//GEN-LAST:event_comprobarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        // TODO add your handling code here:
        Guardar g = new Guardar(this, true, problema);
    }//GEN-LAST:event_GuardarActionPerformed


    /**
    * @param args the command line arguments
    */
//    public static void main(String args[]){
//       // .awt.EventQueue.invokeLater(new Runnable() {
//         //  public void run() {
//               TableroMediano p= new TableroMediano();
//
//               p.setVisible(true);
//
//           // }
//        //});
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton clasificacion;
    private javax.swing.JButton comprobar;
    private javax.swing.JLabel cronometro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel nivel;
    private javax.swing.JButton nuevaPartida;
    private javax.swing.JButton salir;
    private javax.swing.JButton siguientePaso;
    private javax.swing.JButton solucionar;
    private javax.swing.JLabel tama�o;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel tipoNivel;
    private javax.swing.JLabel tipoTama�o;
    // End of variables declaration//GEN-END:variables

    

}

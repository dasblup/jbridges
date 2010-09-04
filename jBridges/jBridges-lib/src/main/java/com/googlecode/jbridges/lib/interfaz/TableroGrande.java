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
import com.googlecode.jbridges.lib.Configuracion;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Direccion;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Puente;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TipoPuente;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.FabricaDeSoluciones;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mdiazoli
 */
public class TableroGrande extends javax.swing.JFrame implements ActionListener{

    private int fila;
    private int columna;
    private int puntuacion;

        FabricaDeProblemas fp;
        Tablero problema;
        Tablero copiaProblema;
        FabricaDeSoluciones fs;
        EstrategiaSolucion es;
        List<Solucion> ss;
        public List<ElementoSolucion> solUsuario;
        public List <ElementoSolucion> sol;

    /** Creates new form Plantilla */
    public TableroGrande(int puntuacion) {

        initComponents();
        new Cronometro();

        Configuracion.setAltoTablero(14);
        Configuracion.setAnchoTablero(14);

        fp = FabricaDeProblemas.getInstancia();
        problema = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);
        copiaProblema=problema.copiaTablero();
        problema.borrarPuentes();
        
        System.out.println("PROBLEMAAAAAAAAAA:"+problema);
        System.out.println("copiaProblema que se supone k solo tiene islas:"+copiaProblema);
        
        es = new EstrategiaBackTrackingBasica();
        problema.borrarPuentes();
        ss = es.solucionar(problema);
        //problema.borrarPuentes();
        System.out.println("problema despues de solucionar: "+problema);
//
//        System.out.println("Tama�o lista soluciones:"+ ss.size());
//
        sol=ss.get(0).solucion;
//
//        System.out.println("solucion wena:"+ sol);
//
//        System.out.println("num bt:"+ ss.get(0).iteracionBactracking);
//        Font f2 = new Font("Tempus Sans ITC", Font.BOLD, 18);
//        if(ss.get(0).iteracionBactracking==0){
//            tipoNivel.setText("F�cil");
//            tipoNivel.setFont(f2);
//        }
//        copiaProblema=problema;
//        copiaProblema.borrarPuentes();
//        System.out.println("copia problema vacio: " + copiaProblema);
        MetodosEstaticos.obtenerTablero(problema, jTable1);
        RenderTabla miRender = new RenderTabla();
        jTable1.setDefaultRenderer( Object.class, miRender);

        this.fila = -1;
        this.columna = -1;
        this.puntuacion=puntuacion;
        solUsuario=new LinkedList<ElementoSolucion>();
        System.out.println("solUsuario: " + solUsuario);
        System.out.println("PROBLEMAAAAAAAAAA:"+problema);
       System.out.println("copiaProblema, se supone k solo tiene islas:"+copiaProblema);

        nuevaPartida.addActionListener(this);
        guardar.addActionListener(this);
        siguientePaso.addActionListener(this);
        comprobar.addActionListener(this);
        solucionar.addActionListener(this);
        clasificacion.addActionListener(this);
        salir.addActionListener(this);

    }

    public TableroGrande(JTable t){
        
        this.add(t);
        t.setVisible(true);
        this.setVisible(true);
        this.fila = -1;
        this.columna = -1;
    }

    public void actionPerformed(ActionEvent e) {

       Object source = e.getSource();

       if(source.getClass().getName().equals("javax.swing.JButton")){
           if(source == nuevaPartida){
               Tama�o t=new Tama�o(this, true);
               this.setVisible(false);
               t.setVisible(true);

           }else if(source == guardar){

           }else if (source == siguientePaso){
                if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                    ss = es.solucionar(problema);
                    System.out.println("compara listas es FALSO y debe entra en siguiente paso");
                    MetodosEstaticos.siguentePaso(solUsuario, sol, jTable1, problema);
                }
           }else if (source == comprobar) {
               System.out.println("solUsuario: " + solUsuario);
               System.out.println("sol: " + sol);
               boolean iguales = MetodosEstaticos.comparaListas(solUsuario, sol);
               System.out.println("iguales: " + iguales);
           }else if(source == solucionar){
               System.out.println("tablero con puentes puestos por el raton: " + problema);
               if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                   MetodosEstaticos.borrarPuentes(problema, jTable1, solUsuario);
                   System.out.println("tablero despues de borrar puentes: " + problema);
                   MetodosEstaticos.obtenerSolucion(sol, solUsuario, jTable1, copiaProblema);
               }
           }else if(source == clasificacion){

           }else if(source == salir){
                Guardar guardarPartida= new Guardar(this, true, problema);//CUIDADO CON LAS ISLAS ACTUALIZADAS DE PROBLEMA
                guardarPartida.setVisible(true);
                this.setVisible(false);
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
   

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nuevaPartida = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
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
        setTitle("Tablero Grande");

        nuevaPartida.setFont(new java.awt.Font("Croobie", 0, 18));
        nuevaPartida.setText("Nueva Partida");
        nuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaPartidaActionPerformed(evt);
            }
        });

        guardar.setFont(new java.awt.Font("Croobie", 0, 18));
        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
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
        tipoTama�o.setText("Grande");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(guardar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(siguientePaso, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(nuevaPartida))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(solucionar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comprobar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(clasificacion, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(cronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(118, 118, 118)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(salir)
                                        .addComponent(tiempo)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(tama�o)
                .addGap(63, 63, 63)
                .addComponent(tipoTama�o)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(nivel)
                .addGap(78, 78, 78)
                .addComponent(tipoNivel)
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
                        .addComponent(nuevaPartida)
                        .addGap(18, 18, 18)
                        .addComponent(guardar)
                        .addGap(18, 18, 18)
                        .addComponent(siguientePaso)
                        .addGap(18, 18, 18)
                        .addComponent(comprobar)
                        .addGap(18, 18, 18)
                        .addComponent(solucionar)
                        .addGap(18, 18, 18)
                        .addComponent(clasificacion)
                        .addGap(26, 26, 26)
                        .addComponent(salir)
                        .addGap(57, 57, 57)
                        .addComponent(tiempo)
                        .addGap(18, 18, 18)
                        .addComponent(cronometro))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoTama�o)
                    .addComponent(tipoNivel)
                    .addComponent(nivel)
                    .addComponent(tama�o))
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

            Isla islaI=null;
            Isla islaF=null;

            if ((problema.getCasilla(coord) instanceof Isla)) {

                if (fila == -1 || columna == -1) {
                    fila = jTable1.rowAtPoint(evt.getPoint());
                    columna = jTable1.columnAtPoint(evt.getPoint());

                } else {
                    int fila2 = jTable1.rowAtPoint(evt.getPoint());
                    int columna2 = jTable1.columnAtPoint(evt.getPoint());

                    Coordenadas coordI=problema.getCoordenadas(fila, columna);
                    islaI=(Isla)problema.getCasilla(coordI);
                    Coordenadas coordF=problema.getCoordenadas(fila2, columna2);
                    islaF=(Isla)problema.getCasilla(coordF);

                    if (fila == fila2) {

                        if (columna - columna2 < 0) {//La isla inicio esta a la izqda
                            int puenteD = islaI.getPuentes(Sentido.ESTE);
                            if (puenteD == 2) {
                                for (int i = columna + 1; i < columna2; i++) {
                                    jTable1.setValueAt("borra", fila, i);
                                    try {
                                        islaI.borrarPuente(islaF, true);
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        System.out.println("solUsuario: " + solUsuario);
                                    } catch (PuenteProhibidoException ex) {}
                                }
                            } else {
                                try {
                                    islaI.setPuente(islaF, true);
                                    System.out.println(problema);
                                    System.out.println("copiaProblema que se supone k solo tiene islas:"+copiaProblema);
                                    int puentes = islaI.getPuentes(Sentido.ESTE);
                                    if (puentes == 1) {
                                        for (int i = columna + 1; i < columna2; i++) {
                                            jTable1.setValueAt("1PuenteH", fila, i);
                                        }

                                    } else if (puentes == 2) {
                                        for (int i = columna + 1; i < columna2; i++) {
                                            jTable1.setValueAt("2PuentesH", fila, i);
                                        }
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                    System.err.print("Puente Prohibido");
                                }
                            }

                        } else if (columna - columna2 > 0) {//La isla inicio esta a la dcha
                            int puenteD = islaI.getPuentes(Sentido.OESTE);
                            if (puenteD == 2) {
                                for (int i = columna - 1; i > columna2; i--) {
                                    jTable1.setValueAt("borra", fila, i);
                                    try {
                                        islaF.borrarPuente(islaI, true);
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        System.out.println("solUsuario: " + solUsuario);
                                    } catch (PuenteProhibidoException ex) {}
                                }
                            } else {
                                try {
                                    islaF.setPuente(islaI, true);
                                    System.out.println(problema);
                                    System.out.println("copiaProblema que se supone k solo tiene islas:"+copiaProblema);
                                    int puentes = islaI.getPuentes(Sentido.OESTE);
                                    if (puentes == 1) {
                                        for (int i = columna - 1; i > columna2; i--) {
                                            jTable1.setValueAt("1PuenteH", fila, i);
                                        }
                                    } else if (puentes == 2) {
                                        for (int i = columna - 1; i > columna2; i--) {
                                            jTable1.setValueAt("2PuentesH", fila, i);
                                        }
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                    System.err.print("Puente Prohibido");
                                }
                            }
                        }

                        fila = -1;
                        columna = -1;
                    } else if (columna == columna2) {

                        if (fila < fila2) { // La isla inicio esta arriba
                            int puenteD = islaI.getPuentes(Sentido.SUR);
                            if (puenteD == 2) {
                                for (int i = fila + 1; i < fila2; i++) {
                                    jTable1.setValueAt("borra", i, columna);
                                    try {
                                        islaI.borrarPuente(islaF, true);
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        System.out.println("solUsuario: " + solUsuario);
                                    } catch (PuenteProhibidoException ex) {}
                                }
                            } else {
                                try {
                                    islaI.setPuente(islaF, true);
                                    System.out.println(problema);
                                    System.out.println("copiaProblema que se supone k solo tiene islas:"+copiaProblema);
                                    int puentes = islaI.getPuentes(Sentido.SUR);
                                    if (puentes == 1) {
                                        for (int i = fila + 1; i < fila2; i++) {
                                            jTable1.setValueAt("1PuenteV", i, columna);
                                        }
                                    } else if (puentes == 2) {
                                        for (int i = fila + 1; i < fila2; i++) {
                                            jTable1.setValueAt("2PuentesV", i, columna);
                                        }
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                }
                            }

                        } else if (fila > fila2) {//La isla inicio esta abajo
                            int puenteD = islaI.getPuentes(Sentido.NORTE);
                            if (puenteD == 2) {
                                for (int i = fila - 1; i > fila2; i--) {
                                    jTable1.setValueAt("borra", i, columna);
                                    try {
                                        islaF.borrarPuente(islaI, true);
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        solUsuario.remove(new ElementoSolucion(islaI, islaF));
                                        System.out.println("solUsuario: " + solUsuario);
                                    } catch (PuenteProhibidoException ex) {}
                                }
                            } else {
                                try {
                                    islaF.setPuente(islaI, true);
                                    System.out.println(problema);
                                    System.out.println("copiaProblema que se supone k solo tiene islas:"+copiaProblema);
                                    int puentes = islaI.getPuentes(Sentido.NORTE);
                                    if (puentes == 1) {
                                        for (int i = fila - 1; i > fila2; i--) {
                                            jTable1.setValueAt("1PuenteV", i, columna);
                                        }
                                    } else if (puentes == 2) {
                                        for (int i = fila - 1; i > fila2; i--) {
                                            jTable1.setValueAt("2PuentesV", i, columna);
                                        }
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                }
                            }
                        }
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
//        Guardar guardarPartida= new Guardar(this, true);
//        guardarPartida.setVisible(true);
//        this.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

    private void clasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clasificacionActionPerformed
        // TODO add your handling code here:
 //       Ranking r=new Ranking(this, true);
 //       r.setVisible(true);
    }//GEN-LAST:event_clasificacionActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarActionPerformed

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



    private void solucionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solucionarActionPerformed
        // TODO add your handling code here:
//        if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
//            MetodosEstaticos.borrarPuentes(copiaProblema, jTable1, solUsuario);
//            MetodosEstaticos.obtenerSolucion(sol, solUsuario, jTable1, problema);
//        }
//        Casilla c;
//        Coordenadas coord;
//        Puente p;
//
//        for (int i = 0; i < problema.getAnchura(); i++) {
//            for (int j = 0; j < problema.getAltura(); j++) {
//                coord = problema.getCoordenadas(i, j);
//                c = problema.getCasilla(coord);
//                if (c instanceof Puente) {
//                    p=(Puente)c;
//                    if (p.getTipo() == TipoPuente.SIMPLE) {
//                        if (p.getDireccion() == Direccion.HORIZONTAL) {
//                            jTable1.setValueAt("1PuenteH", i, j);
//                        } else {
//                            jTable1.setValueAt("1PuenteV", i, j);
//                        }
//                    } else {
//                        if (p.getDireccion() == Direccion.HORIZONTAL) {
//                            jTable1.setValueAt("2PuentesH", i, j);
//                        } else {
//                            jTable1.setValueAt("2PuentesV", i, j);
//                        }
//                    }
//                }
//            }
//        }
//
//        solUsuario.clear();
//        solUsuario.addAll(sol);
//        }
    }//GEN-LAST:event_solucionarActionPerformed


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
    private javax.swing.JButton clasificacion;
    private javax.swing.JButton comprobar;
    private javax.swing.JLabel cronometro;
    private javax.swing.JButton guardar;
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
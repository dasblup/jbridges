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
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
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
public class TableroMediano extends javax.swing.JFrame implements ActionListener{

    private int fila;
    private int columna;
    private int puntuacion;
    Thread crono;
    int veces;

        FabricaDeProblemas fp;
        Tablero problema;
        Tablero copiaProblema;
        FabricaDeSoluciones fs;
        EstrategiaSolucion es;
        List<Solucion> ss;
        public List<ElementoSolucion> solUsuario;
        public List <ElementoSolucion> sol;

    /** Creates new form Plantilla */
    public TableroMediano(int puntuacion, int dificultad){


        initComponents();
        new Cronometro();

        Configuracion.setAltoTablero(10);
        Configuracion.setAnchoTablero(10);

        fp = FabricaDeProblemas.getInstancia();
        problema = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);
        copiaProblema=problema.copiaTablero();
        problema.borrarPuentes();

        //System.out.println("PROBLEMAAAAAAAAAA:"+problema);
        //System.out.println("copiaProblema que se supone k solo tiene islas:"+copiaProblema);

        es = new EstrategiaBackTrackingBasica();
        problema.borrarPuentes();
        ss = es.solucionar(problema);
        problema.borrarPuentes();
        //System.out.println("problema despues de solucionar: "+problema);
//
//        System.out.println("Tamaño lista soluciones:"+ ss.size());
//
        sol=ss.get(0).solucion;
//
//        System.out.println("solucion wena:"+ sol);
//
        System.out.println("num bt:"+ ss.get(0).iteracionBactracking);

//        copiaProblema=problema;
//        copiaProblema.borrarPuentes();
//        System.out.println("copia problema vacio: " + copiaProblema);
        Font f2 = new Font("Tempus Sans ITC", Font.BOLD, 18);
        tipoNivel.setFont(f2);

        if(dificultad==1){//Baja
            if(ss.get(0).iteracionBactracking==0){//el problema generado tiene dificultad baja
                MetodosEstaticos.obtenerTablero(problema, jTable1);
                RenderTabla miRender = new RenderTabla();
                jTable1.setDefaultRenderer( Object.class, miRender);
                tipoNivel.setText("Bajo");
            }else{
                TableroMediano t=new TableroMediano(puntuacion, dificultad);
                //t.setVisible(true);
            }
        }else if(dificultad==2){//Alta
            if(ss.get(0).iteracionBactracking!=0){//el problema generado tiene dificultad alta
                MetodosEstaticos.obtenerTablero(problema, jTable1);
                RenderTabla miRender = new RenderTabla();
                jTable1.setDefaultRenderer( Object.class, miRender);
                tipoNivel.setText("Alto");
            }else{
                TableroMediano t=new TableroMediano(puntuacion, dificultad);
                //t.setVisible(true);
            }
        } else if (dificultad == 0) {//Aleatoria
            MetodosEstaticos.obtenerTablero(problema, jTable1);
            RenderTabla miRender = new RenderTabla();
            jTable1.setDefaultRenderer(Object.class, miRender);

            if (ss.get(0).iteracionBactracking == 0) {
                tipoNivel.setText("Bajo");
                puntuacion=puntuacion+15;
            } else {
                tipoNivel.setText("Alto");
                puntuacion=puntuacion+50;
            }
        }

        num_puntos.setFont(f2);
        num_puntos.setText("  "+puntuacion);
        this.fila = -1;
        this.columna = -1;
        veces=0;
        this.puntuacion=puntuacion;
        solUsuario=new LinkedList<ElementoSolucion>();
        System.out.println("solUsuario: " + solUsuario);
        System.out.println("PROBLEMAAAAAAAAAA:"+problema);
       System.out.println("copiaProblema, se supone k solo tiene islas:"+copiaProblema);
       //copiaSol.addAll(sol);

        nuevaPartida.addActionListener(this);
        guardar.addActionListener(this);
        siguientePaso.addActionListener(this);
        comprobar.addActionListener(this);
        solucionar.addActionListener(this);
        clasificacion.addActionListener(this);
        salir.addActionListener(this);
        pista.addActionListener(this);
    }

    public TableroMediano(JTable t){

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
               Tamaño t=new Tamaño(this, true);
               this.setVisible(false);
               t.setLocationRelativeTo(null);
               t.setVisible(true);

           }else if(source == guardar){

           }else if (source == siguientePaso){

                if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                    puntuacion=puntuacion-5;
                    num_puntos.setText("  "+puntuacion);
                    //System.out.println("compara listas es FALSO y debe entra en siguiente paso");
                    MetodosEstaticos.siguentePaso(solUsuario, sol, jTable1, problema, this, puntuacion);
                }
           }else if (source == comprobar) {

              if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                  puntuacion=puntuacion-3;
                  num_puntos.setText("  "+puntuacion);
                  if(MetodosEstaticos.comprobar_nuevo(solUsuario, sol, jTable1, problema)){
                    SolParcialCorrecta spc=new SolParcialCorrecta(this, true);
                    spc.setLocationRelativeTo(null);
                    spc.setVisible(true);
                  }else{
                    SolParcialIncorrecta spi=new SolParcialIncorrecta(this, true);
                    spi.setLocationRelativeTo(null);
                    spi.setVisible(true);
                  }
              }
           }else if(source == solucionar){

               System.out.println("tablero con puentes puestos por el raton: " + problema);
               if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                   puntuacion=0;
                   num_puntos.setText("  "+puntuacion);
                   MetodosEstaticos.borrarPuentes(problema, jTable1, solUsuario);
                   System.out.println("tablero despues de borrar puentes: " + problema);
                   MetodosEstaticos.obtenerSolucion(sol, solUsuario, jTable1, copiaProblema);
               }
           }else if(source == clasificacion){

           }else if(source == salir){
                Guardar guardarPartida= new Guardar(this, true, problema);//CUIDADO CON LAS ISLAS ACTUALIZADAS DE PROBLEMA
                guardarPartida.setLocationRelativeTo(null);
                guardarPartida.setVisible(true);
                this.setVisible(false);

           }else if(source == pista){
               if(veces==0){
                veces=MetodosEstaticos.puenteObligatorio(jTable1, solUsuario, sol, problema, this, puntuacion, num_puntos);
               }
           }
       }
    }

    public class Cronometro implements Runnable {

        //Thread crono;


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
        guardar = new javax.swing.JButton();
        siguientePaso = new javax.swing.JButton();
        comprobar = new javax.swing.JButton();
        solucionar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        clasificacion = new javax.swing.JButton();
        tamaño = new javax.swing.JLabel();
        tipoTamaño = new javax.swing.JLabel();
        nivel = new javax.swing.JLabel();
        tipoNivel = new javax.swing.JLabel();
        tiempo = new javax.swing.JLabel();
        cronometro = new javax.swing.JLabel();
        pista = new javax.swing.JButton();
        puntos = new javax.swing.JLabel();
        num_puntos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero Mediano");

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
        clasificacion.setText("Clasificación");
        clasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clasificacionActionPerformed(evt);
            }
        });

        tamaño.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        tamaño.setText("Tamaño:");

        tipoTamaño.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18));
        tipoTamaño.setText("Mediano");

        nivel.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        nivel.setText("Nivel:");

        tipoNivel.setText("jLabel5");

        tiempo.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        tiempo.setText("Tiempo:");

        cronometro.setText("jLabel7");

        pista.setFont(new java.awt.Font("Croobie", 0, 18)); // NOI18N
        pista.setText("Pista");
        pista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pistaActionPerformed(evt);
            }
        });

        puntos.setFont(new java.awt.Font("Bradley Hand ITC", 1, 24));
        puntos.setText("Puntuación:");

        num_puntos.setText("jLabel8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jLabel1)
                .addContainerGap(329, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(102, 102, 102)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(clasificacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(solucionar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comprobar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(132, 132, 132)
                            .addComponent(salir)
                            .addGap(50, 50, 50))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nuevaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siguientePaso, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pista)
                            .addGap(48, 48, 48)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tiempo)
                            .addComponent(cronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(num_puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(puntos))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nivel)
                            .addComponent(tamaño))
                        .addGap(121, 121, 121)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tipoNivel)
                            .addComponent(tipoTamaño)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(811, 811, 811))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nuevaPartida)
                        .addGap(18, 18, 18)
                        .addComponent(guardar)
                        .addGap(18, 18, 18)
                        .addComponent(siguientePaso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pista)
                        .addGap(18, 18, 18)
                        .addComponent(comprobar)
                        .addGap(18, 18, 18)
                        .addComponent(solucionar)
                        .addGap(18, 18, 18)
                        .addComponent(clasificacion)
                        .addGap(18, 18, 18)
                        .addComponent(salir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoTamaño)
                    .addComponent(tiempo)
                    .addComponent(puntos)
                    .addComponent(tamaño))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nivel)
                    .addComponent(tipoNivel)
                    .addComponent(cronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(num_puntos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
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
              int num=0;
        boolean completado=false;
        if (!MetodosEstaticos.comparaListas(solUsuario, sol)) {
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
                                        num=1;
                                    } else if (puentes == 2) {
                                        for (int i = columna + 1; i < columna2; i++) {
                                            jTable1.setValueAt("2PuentesH", fila, i);
                                        }
                                        num=2;
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                    System.err.print("Puente Prohibido");
                                }
                            }
                            if(MetodosEstaticos.compruebaPuente(new ElementoSolucion(islaI, islaF), sol, num)){
                                    puntuacion=puntuacion+3;
                            }else{
                                    puntuacion=puntuacion-2;
                            }
                            //num_puntos.setText("  "+puntuacion);
                            if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
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
                                        num=1;
                                    } else if (puentes == 2) {
                                        for (int i = columna - 1; i > columna2; i--) {
                                            jTable1.setValueAt("2PuentesH", fila, i);
                                        }
                                        num=2;
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                    System.err.print("Puente Prohibido");
                                }
                            }
                            if(MetodosEstaticos.compruebaPuente(new ElementoSolucion(islaI, islaF), sol, num)){
                                    puntuacion=puntuacion+3;
                            }else{
                                    puntuacion=puntuacion-2;
                            }
                            //num_puntos.setText("  "+puntuacion);
                            if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
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
                                        num=1;
                                    } else if (puentes == 2) {
                                        for (int i = fila + 1; i < fila2; i++) {
                                            jTable1.setValueAt("2PuentesV", i, columna);
                                        }
                                        num=2;
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                }
                            }
                            if(MetodosEstaticos.compruebaPuente(new ElementoSolucion(islaI, islaF), sol, num)){
                                    puntuacion=puntuacion+3;
                            }else{
                                    puntuacion=puntuacion-2;
                            }
                            //num_puntos.setText("  "+puntuacion);
                            if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                    completado=true;
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
                                        num=1;
                                    } else if (puentes == 2) {
                                        for (int i = fila - 1; i > fila2; i--) {
                                            jTable1.setValueAt("2PuentesV", i, columna);
                                        }
                                        num=2;
                                    }
                                    solUsuario.add(new ElementoSolucion(islaI, islaF));
                                    System.out.println("solUsuario: " + solUsuario);
                                } catch (PuenteProhibidoException ex) {
                                }
                            }
                            if(MetodosEstaticos.compruebaPuente(new ElementoSolucion(islaI, islaF), sol, num)){
                                    puntuacion=puntuacion+3;
                            }else{
                                    puntuacion=puntuacion-2;
                            }
                            //num_puntos.setText("  "+puntuacion);
                            if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                     completado=true;
                            }
                        }
                        fila=-1;
                        columna=-1;
                        num=0;
                    }
                    if(completado){
                        crono.stop();
                        Enhorabuena e=new Enhorabuena(this, true, puntuacion);
                        e.setLocationRelativeTo(null);
                        e.setVisible(true);
                    }
                }
            }else{
                fila=-1;
                columna=-1;
            }

        }

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

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        MetodosEstaticos.guardarPartida(sol, solUsuario, problema.getAltura(), problema.getAnchura(), puntuacion);
    }//GEN-LAST:event_guardarActionPerformed

    private void pistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pistaActionPerformed

}//GEN-LAST:event_pistaActionPerformed


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
    private javax.swing.JLabel num_puntos;
    private javax.swing.JButton pista;
    private javax.swing.JLabel puntos;
    private javax.swing.JButton salir;
    private javax.swing.JButton siguientePaso;
    private javax.swing.JButton solucionar;
    private javax.swing.JLabel tamaño;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel tipoNivel;
    private javax.swing.JLabel tipoTamaño;
    // End of variables declaration//GEN-END:variables

    

}

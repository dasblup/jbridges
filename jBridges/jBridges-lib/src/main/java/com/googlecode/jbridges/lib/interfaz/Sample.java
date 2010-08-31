/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Coordenadas2D;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Jugador;
import com.googlecode.jbridges.lib.Tablero;
import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.collections.TransactionWorker;
import com.sleepycat.je.DatabaseException;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JTable;

/**
 *
 * @author mdiazoli
 */
public class Sample {

    private SampleDataBase db;
    private SampleViews views;
    private static Jugador jug;
    private static JTable tableJug;
    private static Tablero problema;
    private static JTable tableProbl;

//    public static void main(String args[]){
//        System.out.println("\nRunning sample: " + Sample.class);
//        String homeDir = "C:\\temp";// Este direcorio debe existir antes de ejecutar el ejemplo
//
//        for (int i=0; i<args.length;i++){
//            String arg = args[i];
//            if(args[i].equals("-h") && i < args.length-1){
//                i++;
//                homeDir =args[i];
//            }else{
//                System.err.println("Use:\n java "+ Sample.class.getName() + "\n [-h <home-directory>]");
//                System.exit(2);
//            }
//        }
//
//        Sample sample = null;
//        try{
//            sample = new Sample(homeDir, jug, table, coordKey, islaData);
//            sample.run();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            if(sample != null){
//                try{
//                    sample.close();
//                }catch (Exception e){
//                    System.err.println("Excepcion durante el cierre de la base de datos");
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public Sample(String homeDir, Jugador info, JTable tableJug, Tablero problema, JTable tableProbl)
            throws DatabaseException, FileNotFoundException{

        db = new SampleDataBase(homeDir);
        views = new SampleViews(db);
        this.jug=info;
        this.tableJug=tableJug;
        this.problema=problema;
        this.tableProbl=tableProbl;
//        this.coordKey=coord;
//        this.islaData=isla;
    }

    public void close() throws DatabaseException{
        db.close();
    }

    public void runJugador(String s) throws Exception{

        TransactionRunner runner = new TransactionRunner(db.getEnvironment());
        if(s.equals("inserta"))
            runner.run(new PopulateDatabaseJugador());
        else if(s.equals("muestra"))
            runner.run(new PrintDatabaseJugador());
    }

    public void runPartida(String s) throws Exception{
         TransactionRunner runner = new TransactionRunner(db.getEnvironment());
         if(s.equals("inserta"))
            runner.run(new PopulateDatabasePartida());
        else if(s.equals("muestra"))
            runner.run(new PrintDatabasePartida());
    }

    private class PopulateDatabaseJugador implements TransactionWorker{

        public void doWork() throws Exception{
            addJugador(jug);
        }
    }

    private class PrintDatabaseJugador implements TransactionWorker{
        public void doWork() throws Exception{
            printEntriesJugador(views.getJugadorEntrySet().iterator(), tableJug);
        }
    }

    private class PopulateDatabasePartida implements TransactionWorker{
        public void doWork() throws Exception{
            addPartida(problema);
        }
    }

        private class PrintDatabasePartida implements TransactionWorker{
        public void doWork() throws Exception{
            printEntriesJugador(views.getPartidaEntrySet().iterator(), tableProbl);
        }
    }

    public void printEntriesJugador(Iterator iterator, JTable table){

        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry)iterator.next();
            Jugador j = (Jugador) entry.getValue();

            for(int i=0; i<table.getRowCount(); i++){
                table.setValueAt(j.getNombre(), i, 0);
                table.setValueAt(j.getPuntuacion(), i, 1);
            }
        }
    }

    public void printEntriesPartida(Iterator iterator, JTable table){

        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry)iterator.next();
            Isla isla = (Isla) entry.getValue();
            Coordenadas2D coord= (Coordenadas2D) entry.getKey();
            Integer n=isla.getN();
            table.setValueAt(n,coord.getX() , coord.getY());
        }

    }

    private void addJugador(Jugador jug){
        Map jugador = views.getJugadorMap();

//        if(jugador.isEmpty()){
            System.out.println("Añadiendo jugador");

            jugador.put(new Jugador(jug.getNombre(), jug.getPuntuacion()), new Jugador(jug.getNombre(), jug.getPuntuacion()));
            
//        }

    }

    private void addPartida(Tablero problema){
        //¿Antes de añadir una nueva partida no habria que borrar la anterior?
        Map partida =views.getJugadorMap();

        for(int i=0; i<problema.getAnchura(); i++){
            for (int j=0; j<problema.getAltura(); j++){
                Coordenadas coord;
                coord=problema.getCoordenadas(i, j);
                Casilla casilla=problema.getCasilla(coord);
                if(casilla instanceof Isla){
                    Isla isla = (Isla) casilla;
                    partida.put(casilla.getCoordenadas(), isla);
                }

            }
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

//import javax.swing.ImageIcon;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Coordenadas2D;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.excepciones.DireccionInvalidaException;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.FabricaDeSoluciones;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
//import com.sun.org.apache.xpath.internal.operations.Equals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mdiazoli
 */
public class Main {

    public static void main(String args[]) {
        //COmprobar si funciona compararListas

//        FabricaDeProblemas fp;
//        Tablero tablero;
//
//        fp = FabricaDeProblemas.getInstancia();
//        tablero = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_DE_PRUEBA);
//
//        boolean iguales=false;
//        boolean puenteEncontrado=false;
//        List<ElementoSolucion> solUsuario=new LinkedList<ElementoSolucion>();
//        List<ElementoSolucion> sol=new LinkedList<ElementoSolucion>();
//
//        Isla i=null;
//        Isla f=null;
//
//        Coordenadas coordI;
//        Coordenadas coordF;
//
//        coordI = tablero.getCoordenadas(4, 2);
//        coordF = tablero.getCoordenadas(2, 2);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//
//        coordI = tablero.getCoordenadas(2, 2);
//        coordF = tablero.getCoordenadas(2, 4);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//        solUsuario.add(new ElementoSolucion(i, f));
//
//       // sol.add(new ElementoSolucion(f, i));
//       // sol.add(new ElementoSolucion(f, i));
//
//        coordI = tablero.getCoordenadas(2, 4);
//        coordF = tablero.getCoordenadas(4, 4);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//
//        sol.add(new ElementoSolucion(f, i));
//
//        coordI = tablero.getCoordenadas(4, 4);
//        coordF = tablero.getCoordenadas(4, 2);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//        solUsuario.add(new ElementoSolucion(i, f));
//
//        sol.add(new ElementoSolucion(f, i));
//        sol.add(new ElementoSolucion(f, i));
//
//        coordI = tablero.getCoordenadas(4, 2);
//        coordF = tablero.getCoordenadas(2, 2);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        sol.add(new ElementoSolucion(i, f));
//
//        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//            iguales=true;
//        }
//        System.out.println("Listas iguales?");
//        System.out.println(iguales);
//
//        //COMPROBAR SI FUNCIONA COMPRUEBAPUENTESIMPLE
//
//        coordI = tablero.getCoordenadas(2, 2);
//        coordF = tablero.getCoordenadas(2, 4);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        if(MetodosEstaticos.comprobarPuente(sol, f, i, "doble")){
//            puenteEncontrado=true;
//        }
//        System.out.println("Puente correcto?");
//        System.out.println(puenteEncontrado);
//-----------------------------------------------------------------
//FabricaDeProblemas fp;
//        Tablero problema;
//        FabricaDeSoluciones fs;
//        EstrategiaSolucion es;
//        List<Solucion> ss;
//        List<ElementoSolucion> solUsuario;
//        List <ElementoSolucion> sol;
//
//
//        fp = FabricaDeProblemas.getInstancia();
//        problema = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_DE_PRUEBA);
//        problema.borrarPuentes();
//        System.out.println(problema);
//
//        for(int i = 0; i<problema.getAnchura(); i++){
//            for(int j= 0; j<problema.getAltura(); j++){
//                Coordenadas coord=problema.getCoordenadas(i, j);
//                System.out.println("coordenadas: " + ((Coordenadas2D)coord).getX()+ ","+((Coordenadas2D)coord).getY());
//                Casilla c= problema.getCasilla(coord);
//                if(c instanceof Isla){
//                    Isla isla=(Isla)c;
//                    Coordenadas coo=isla.getCoord();
//                    System.out.println("coordenadas con getCoord: " + coo);
//                }
//            }
//        }
//        es = new EstrategiaBackTrackingBasica();
//
//        ss = es.solucionar(problema);
//
//        if(!ss.isEmpty()){
           
//        }
//        System.out.println("Tamaño lista soluciones:"+ ss.size());
        // sol= (List<ElementoSolucion>) ss.get(0);
      //  System.out.println("sol:"+ sol);

//--------------------------------------------------------------------------



//            int fila=2;
//            int columna=4;
//            int fila2=2;
//            int columna2=2;
//
//            Coordenadas coordI=problema.getCoordenadas(fila, columna);
//            Isla islaI=(Isla)problema.getCasilla(coordI);
//            Coordenadas coordF=problema.getCoordenadas(fila2, columna2);
//            Isla islaF=(Isla)problema.getCasilla(coordF);

//            if (fila == fila2) {
//
//                        if (columna - columna2 < 0) {//La isla inicio esta a la izqda
//                            try {
//                                islaI.setPuente(islaF);
//
//                                System.out.println(problema);
//                                int puentes = islaI.getPuentes(Sentido.ESTE);
//                            } catch (PuenteProhibidoException ex) {
//                                System.err.print("Puente Prohibido");
//                            }
//
//
//                        } else if (columna - columna2 > 0) {//La isla inicio esta a la dcha
//                            try {
//                                islaF.setPuente(islaI);
//                                System.out.println(problema);
//                                int puentes = islaI.getPuentes(Sentido.OESTE);
//                                System.out.println("puentes: " + puentes);
//                            } catch (PuenteProhibidoException ex) {
//                                System.err.print("Puente Prohibido");
//                            }
//                        }
//
//                    } else if (columna == columna2) {
//
//                        if (fila < fila2) { // La isla inicio esta arriba
//                            try {
//                                islaI.setPuente(islaF);
//                                System.out.println(problema);
//                                int puentes = islaI.getPuentes(Sentido.SUR);
//                            } catch (PuenteProhibidoException ex) {
//                                System.err.print("Puente Prohibido");
//                            }
//
//                        } else if (fila > fila2) {
//                            try {
//                                islaF.setPuente(islaI);
//                                System.out.println(problema);
//                                int puentes = islaI.getPuentes(Sentido.NORTE);
//                            } catch (PuenteProhibidoException ex){
//                                System.err.print("Puente Prohibido");
//                            }
//                        }
//                    }
 //               MetodosEstaticos.siguentePaso(solUsuario, sol, null, problema);
        TableroArray t1 = new TableroArray();
        t1.setDimension(10, 10);
        TableroArray t2 = new TableroArray();
        t2.setDimension(10, 10);
        try {
            t1.setIsla(t1.getCoordenadas(1, 1));
            t1.setIsla(t1.getCoordenadas(2, 2));
            t2.setIsla(t2.getCoordenadas(1, 1));
            t2.setIsla(t2.getCoordenadas(2, 2));


            Isla i1t1 = t1.getIsla(t1.getCoordenadas(1, 1));
            Isla i2t1 = t1.getIsla(t1.getCoordenadas(2, 2));
            Isla i1t2 = t2.getIsla(t2.getCoordenadas(1, 1));
            Isla i2t2 = t2.getIsla(t2.getCoordenadas(2, 2));

            ElementoSolucion es1 = new ElementoSolucion(i1t1, i2t1);
            ElementoSolucion es2 = new ElementoSolucion(i1t2, i2t2);

            boolean iguales=es1.equals(es2);
            System.out.println("iguales:"+ iguales);
         } catch (CasillaOcupadaException ex) {
            //Logger.getLogger(Equals.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IslaNoEncontradaException ine) {}
    }
                
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import com.googlecode.jbridges.lib.Agua;
import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Coordenadas2D;
import com.googlecode.jbridges.lib.Direccion;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Puente;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.TipoPuente;
import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
import java.awt.Event;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author mdiazoli
 */
public class MetodosEstaticos {
//

    public static void obtenerTablero(Tablero problema, JTable jTable1){

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

    public static void obtenerSolucion(List<ElementoSolucion> sol, List<ElementoSolucion> solUsuario, JTable jTable1, Tablero problema) {
        
//        Iterator it=sol.iterator();
//        ElementoSolucion el=null;
//        Isla inicio=null;
//        Isla fin=null;

        Casilla c;
        Coordenadas coord;
        Puente p;
        for (int i = 0; i < problema.getAnchura(); i++) {
            for (int j = 0; j < problema.getAltura(); j++) {
                coord = problema.getCoordenadas(i, j);
                c = problema.getCasilla(coord);
                if (c instanceof Puente) {
                    p=(Puente)c;
                    if (p.getTipo() == TipoPuente.SIMPLE) {
                        if (p.getDireccion() == Direccion.HORIZONTAL) {
                            jTable1.setValueAt("1PuenteH", i, j);
                        } else {
                            jTable1.setValueAt("1PuenteV", i, j);
                        }
                    } else {
                        if (p.getDireccion() == Direccion.HORIZONTAL) {
                            jTable1.setValueAt("2PuentesH", i, j);
                        } else {
                            jTable1.setValueAt("2PuentesV", i, j);
                        }
                    }
                }
            }
        }
        solUsuario.clear();
        solUsuario.addAll(sol);
    }


//    public static void accionRaton(MouseEvent evt, JTable jTable1, Tablero problema, int fila, int columna) {//(MouseEvent evt, JTable jTable1, Tablero problema, int fila, int columna, List<ElementoSolucion> solUsuario,
////            List<ElementoSolucion> sol, Frame parent, int puntuacion)
//
//
////        if (!MetodosEstaticos.comparaListas(solUsuario, sol)) {
//            int f = jTable1.rowAtPoint(evt.getPoint());
//            int c = jTable1.columnAtPoint(evt.getPoint());
//            Coordenadas coord = problema.getCoordenadas(f, c);
//            boolean vacias = false;
//            boolean h1 = false;
//            boolean h2 = false;
//            boolean v1 = false;
//            boolean v2 = false;
////            boolean completado=false;
//
//
//
//            if ((problema.getCasilla(coord) instanceof Isla)) {
//
//                if (fila == -1 || columna == -1) {
//                    fila = jTable1.rowAtPoint(evt.getPoint());
//                    columna = jTable1.columnAtPoint(evt.getPoint());
//                } else {
//                    int fila2 = jTable1.rowAtPoint(evt.getPoint());
//                    int columna2 = jTable1.columnAtPoint(evt.getPoint());
//
////                    Coordenadas coord1=problema.getCoordenadas(fila, columna);
////                    Coordenadas coord2=problema.getCoordenadas(fila2, columna2);
////                    Isla inicio=(Isla)problema.getCasilla(coord1);
////                    Isla fin=(Isla)problema.getCasilla(coord2);
//                    if (fila == fila2) {
//
//                        if (columna - columna2 < 0) {
//                            //Comprueba que todas las celdas intermedias están vacias
//                            for (int i = columna + 1; i < columna2; i++) {
//                                if ((jTable1.getValueAt(fila, i) == null) || (jTable1.getValueAt(fila, i).equals("borra"))) {
//                                    vacias = true;
//                                    //break;
//                                } else if ((jTable1.getValueAt(fila, i).equals("1PuenteH"))){// || (jTable1.getValueAt(fila, i).equals("1PuenteH_Error"))) {
//                                    h1 = true;
//                                } else if ((jTable1.getValueAt(fila, i).equals("2PuentesH"))){// || (jTable1.getValueAt(fila, i).equals("2PuentesH_Error"))) {
//                                    h2 = true;
//                                }
//                            }
//                            if (vacias) {
//                                for (int i = columna + 1; i < columna2; i++) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(inicio, fin));
//                                        jTable1.setValueAt("1PuenteH", fila, i);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (h1) {
//                                for (int i = columna + 1; i < columna2; i++) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(inicio, fin));
//                                        jTable1.setValueAt("2PuentesH", fila, i);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (h2) {
//                                for (int i = columna + 1; i < columna2; i++) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
////                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
//                                        jTable1.setValueAt("borra", fila, i);
////                                        puntuacion=puntuacion-2;
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                    }
//                                }
//                            }
//                        } else if (columna - columna2 > 0) {
//                            for (int i = columna - 1; i < columna2; i--) {
//                                if ((jTable1.getValueAt(fila, i) == null) || (jTable1.getValueAt(fila, i).equals("borra"))) {
//                                    vacias = true;
//                                } else if ((jTable1.getValueAt(fila, i).equals("1PuenteH"))){// || (jTable1.getValueAt(fila, i).equals("1PuenteH_Error"))) {
//                                    h1 = true;
//                                } else if ((jTable1.getValueAt(fila, i).equals("2PuentesH"))){// || (jTable1.getValueAt(fila, i).equals("2PuentesH_Error"))) {
//                                    h2 = true;
//                                }
//                            }
//                            if (vacias) {
//                                for (int i = columna - 1; i < columna2; i--) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(fin, inicio));
//                                        jTable1.setValueAt("1PuenteH", fila, i);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (h1) {
//                                for (int i = columna - 1; i < columna2; i--) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(fin, inicio));
//                                        jTable1.setValueAt("2PuentesH", fila, i);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (h2) {
//                                for (int i = columna - 1; i < columna2; i--) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
////                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
//                                        jTable1.setValueAt("borra", fila, i);
////                                        puntuacion=puntuacion-2;
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                    }
//                                }
//                            }
//                        }
//                        h2 = false;
//                        h1 = false;
//                        vacias = false;
//                        fila = -1;
//                        columna = -1;
//                    } else if (columna == columna2) {
//
//                        if (fila < fila2) {
//                            for (int i = fila + 1; i < fila2; i++) {
//                                if ((jTable1.getValueAt(i, columna) == null) || (jTable1.getValueAt(i, columna).equals("borra"))) {
//                                    vacias = true;
//                                    //break;
//                                } else if ((jTable1.getValueAt(i, columna).equals("1PuenteV"))){// || (jTable1.getValueAt(i, columna).equals("1PuenteV_Error"))) {
//                                    v1 = true;
//                                } else if ((jTable1.getValueAt(i, columna).equals("2PuentesV"))){// || (jTable1.getValueAt(i, columna).equals("2PuentesV_Error"))) {
//                                    v2 = true;
//                                }
//                            }
//                            if (vacias) {
//                                for (int i = fila + 1; i < fila2; i++) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(inicio, fin));
//                                        jTable1.setValueAt("1PuenteV", i, columna);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (v1) {
//                                for (int i = fila + 1; i < fila2; i++) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(inicio, fin));
//                                        jTable1.setValueAt("2PuentesV", i, columna);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (v2) {
//                                for (int i = fila + 1; i < fila2; i++) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
////                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
//                                        jTable1.setValueAt("borra", i, columna);
////                                        puntuacion=puntuacion-2;
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                    }
//                                }
//                            }
//                        } else if (fila > fila2) {
//                            for (int i = fila - 1; i > fila2; i--) {
//                                if ((jTable1.getValueAt(i, columna) == null) || (jTable1.getValueAt(i, columna).equals("borra"))) {
//                                    vacias = true;
//                                } else if ((jTable1.getValueAt(i, columna).equals("1PuenteV"))){// || (jTable1.getValueAt(i, columna).equals("1PuenteV_Error"))) {
//                                    v1 = true;
//                                } else if ((jTable1.getValueAt(i, columna).equals("2PuentesV"))){// || (jTable1.getValueAt(i, columna).equals("2PuentesV_Error"))) {
//                                    v2 = true;
//                                }
//                            }
//                            if (vacias) {
//                                for (int i = fila - 1; i > fila2; i--) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(fin, inicio));
//                                        jTable1.setValueAt("1PuenteV", i, columna);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (v1) {
//                                for (int i = fila - 1; i > fila2; i--) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.add(new ElementoSolucion(fin, inicio));
//                                        jTable1.setValueAt("2PuentesV", i, columna);
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
////                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
////                                            puntuacion=puntuacion+3;
////                                        }else{
////                                            puntuacion=puntuacion-2;
////                                        }
////                                    }
//                                }
//                            } else if (v2) {
//                                for (int i = fila - 1; i > fila2; i--) {
////                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
////                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
//                                        jTable1.setValueAt("borra", i, columna);
////                                        puntuacion=puntuacion-2;
////                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
////                                            completado=true;
////                                        }
//
////                                    }
//                                }
//                            }
//                        }
//                        v2 = false;
//                        v1 = false;
//                        vacias = false;
//                        fila=-1;
//                        columna=-1;
//                    }
////                    if(completado){
////                        Enhorabuena e=new Enhorabuena(parent, true, puntuacion);
////                        e.setVisible(true);
////                    }
//                }
//            }
// //       }
//
//    }

//    public static void comprobar(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, JTable jTable1, Tablero problema){
//        //Cada elemento de solUsuario, lo comparo con los elementos de sol, si no encuentro
//        //ninguno igual, significa que ese puente está mal.Lo meto en una lista para dibujarlos luego
//        boolean encontrado=false;
//        List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();
//        copiaSol.addAll(sol);
//        Iterator itUsuario=solUsuario.iterator();
//        Iterator it=copiaSol.iterator();
//        //En esta lista se guardarán todos los puentes incorrectos
//        List<ElementoSolucion> l=new LinkedList<ElementoSolucion>();
//
//        while(itUsuario.hasNext()){
//            ElementoSolucion elUsuario=(ElementoSolucion)itUsuario.next();
//            while(it.hasNext() && !encontrado){
//                ElementoSolucion el=(ElementoSolucion)it.next();
//                if(!elUsuario.equals(el)){
//                    l.add(elUsuario);
//                }else{
//                    copiaSol.remove(el);
//                    encontrado=true;
//                }
//            }
//        }
//        //Pinto los errores
////        if(!l.isEmpty()){
////            Iterator itL=l.iterator();
////            while(itL.hasNext()){
////                ElementoSolucion error=(ElementoSolucion)itL.next();
////                Coordenadas coordInicio=error.inicio.getCoord();
////                Coordenadas coordFin=error.fin.getCoord();
////                MetodosEstaticos.dibujaPuente(jTable1, coordInicio, coordFin, 2, solUsuario, problema);
////            }
////        }
//    }


    public static boolean comparaListas_mal(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol) {

        //Este método se utiliza para deshabilitar algunos botones cuando el usuario ya haya encontrado la solucion

        boolean iguales = true;
        boolean enc = false;

        if (solUsuario.size() != sol.size()) {
            iguales = false;
        } else {
            if (!solUsuario.isEmpty() && !sol.isEmpty()) {
                Iterator itUsuario = solUsuario.iterator();
                List<ElementoSolucion> copiaSol = new LinkedList<ElementoSolucion>();
                copiaSol.addAll(sol);
                Iterator itSol = copiaSol.iterator();

                while (itUsuario.hasNext() && iguales) {
                    ElementoSolucion elUsuario = (ElementoSolucion) itUsuario.next();
                    while (itSol.hasNext() && !enc) {
                        ElementoSolucion elSol = (ElementoSolucion) itSol.next();
                        if (elUsuario.equals(elSol)) {
                            copiaSol.remove(elSol);
                            enc = true;
                        }
                    }
                    if (!enc) {
                        iguales = false;
                    }
                }
            }
        }
        return iguales;
    }
        public static boolean comparaListas(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol) {

        //Este método se utiliza para deshabilitar algunos botones cuando el usuario ya haya encontrado la solucion

        boolean iguales = false;
        boolean enc = true;

        System.out.println("Lista sol:"+sol);
        System.out.println("Lista solUsuario:"+solUsuario);
        if (solUsuario.size() != sol.size()) {
            iguales = false;
        } else {
            if (!solUsuario.isEmpty() && !sol.isEmpty()) {
                List<ElementoSolucion> copiaSolUsuario=new LinkedList<ElementoSolucion>();
                copiaSolUsuario.addAll(solUsuario);
                
                Iterator itSol=sol.iterator();
                System.out.println("ENTRA");
                while(itSol.hasNext() && enc){
                    enc=false;
                    ElementoSolucion elSol=(ElementoSolucion)itSol.next();
                    System.out.println("elSol"+elSol);
                    Iterator itSolUsuario=solUsuario.iterator();
                    while(itSolUsuario.hasNext() && !enc){
                        ElementoSolucion elSolUsuario=(ElementoSolucion)itSolUsuario.next();
                        System.out.println("elSolUsuario"+elSolUsuario);
                        if(elSol.equals(elSolUsuario)){
                            System.out.println("son iguales");
                            enc=true;
                            copiaSolUsuario.remove(elSol);
                        }
                    }
                }
                System.out.println("Lista solUsuario despues de comparar listas:"+copiaSolUsuario);
                if (enc) {
                    if (copiaSolUsuario.isEmpty()) {
                        iguales = true;
                    }
                }
            }
        }
        return iguales;
    }

    public static void borrarPuentes(Tablero problema, JTable jTable1, List<ElementoSolucion> solUsuario){
        //Recorrer la tabla y borrar todos lo puentes que habia,
        //ademas borrar la lista de solución que estaba creando el usuario
        System.out.println("problema con el que se entra a borrar puentes: " + problema);
        for(int i=0;i<problema.getAltura();i++){
            for(int j=0;j<problema.getAnchura();j++){
                Coordenadas coord;
                coord=problema.getCoordenadas(i, j);
                Casilla casilla=problema.getCasilla(coord);
                if (casilla instanceof Puente){
                    problema.borraCasilla(i, j);
                    jTable1.setValueAt("borra", i, j);
                }
            }
        }
        problema.borrarPuentes();
        solUsuario.clear();
    }

//    public static boolean comprobarPuente(List<ElementoSolucion> sol, Isla inicio, Isla fin, String es){
//        boolean existe=false;
//        boolean uno=false;
//
//        ElementoSolucion el = new ElementoSolucion(inicio, fin);
//        List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();
//        copiaSol.addAll(sol);
//        System.out.println("Tamaño de copiaSol:" + copiaSol.size());
//        Iterator it = copiaSol.iterator();
//
//        if (es == "simple") {
//
//            while (it.hasNext() && !existe) {
//                ElementoSolucion el2 = (ElementoSolucion) it.next();
//                if (el.equals(el2)) {
//                    existe = true;
//                }
//            }
//
//        }else if(es =="doble"){
//
//            while(it.hasNext() && !uno){
//
//                ElementoSolucion el2 = (ElementoSolucion) it.next();
//
//                if (el.equals(el2)) {
//                    uno = true;
//                    copiaSol.remove(el);
//                    System.out.println("Tamaño de copiaSol:" + copiaSol.size());
//                }
//            }
//            if(uno){
//                Iterator it2=copiaSol.iterator();
//                while(it2.hasNext() && !existe){
//                    ElementoSolucion el3 = (ElementoSolucion) it2.next();
//                    if (el.equals(el3)) {
//                        existe = true;
//                    }
//                }
//            }
//
//        }
//
//        return existe;
//    }


    public static boolean newJugador(){
        boolean nuevo=false;

        return nuevo;
    }
    
    public static void siguentePaso(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, JTable jTable1, Tablero problema,
            JFrame parent, int puntuacion) {

        //Si la solucion que está formando el usuario es correcta, la pista será un nuevo puente, en caso contrario,
        //la pista será indicar uno de los puentes incorrectos
        //System.out.println("entra en siguiente paso");
        //System.out.println("problema con el k entro a siguiente paso"+ problema);
        LinkedList<ElementoSolucion> copiaSol = new LinkedList<ElementoSolucion>();
        LinkedList<ElementoSolucion> copiaUsuario = new LinkedList<ElementoSolucion>();
        copiaSol.addAll(sol);
        copiaUsuario.addAll(solUsuario);
//        System.out.println("sol: " + sol);
//        System.out.println("solUsuario: " + solUsuario);
//        System.out.println("copiaSol: " + copiaSol);
//        System.out.println("copiaUsuario: " + copiaUsuario);

        Iterator itUsuario = solUsuario.iterator();
        Iterator itCopia = copiaSol.iterator();
        boolean encontrado = true;
        ElementoSolucion el=null;
        ElementoSolucion elUsuario;

        //System.out.println("itUsuario tiene next?" + itUsuario.hasNext());
        if(!solUsuario.isEmpty()){
            while(itCopia.hasNext() && encontrado){
                
                //System.out.println("solCopia tiene siguiente");
                el = (ElementoSolucion) itCopia.next();
                //System.out.println("elemento actual de copiaSol"+el);
                encontrado=false;
                //System.out.println("isla solCopia:"+el);
                while(itUsuario.hasNext() && !encontrado){
                    //System.out.println("solUsuario tiene siguiente");
                    elUsuario = (ElementoSolucion) itUsuario.next();
//                    System.out.println("isla solCopia:"+el);
//                    System.out.println("isla solUsuario"+elUsuario);
                    if(el.equals(elUsuario)){

                        encontrado=true;
                        //copiaSol.remove(el);
                        
                    }
                    //System.out.println(el.equals(elUsuario));
                    
                }
                
                if (!encontrado) {
                //System.out.println("El elemento de sol que estamos tratando no está en solUsuario");
                Coordenadas coordInicio = el.inicio.getCoordenadas();
                Coordenadas coordFin = el.fin.getCoordenadas();
                //copiaSol.remove(el);
//                System.out.println("cojo coordenadas");
//                System.out.println("coordenadaXInicio: " + ((Coordenadas2D)coordInicio).getX());
//                System.out.println("coordenadaYInicio: " + ((Coordenadas2D)coordInicio).getY());
                MetodosEstaticos.dibujaPuente_nuevo(jTable1, coordInicio, coordFin, solUsuario, problema, sol, parent, puntuacion);
            }
            }
       
        }else if(solUsuario.isEmpty() ){
             //System.out.println("Ahora entra por primera vez");
             Isla inicio = sol.get(0).inicio;
             Isla fin = sol.get(0).fin;
             Coordenadas coordInicio=inicio.getCoordenadas();
             Coordenadas coordFin=fin.getCoordenadas();
//             System.out.println("cojo coordenadas");
//             System.out.println("coordenadaXInicio: " + ((Coordenadas2D)coordInicio).getX());
//             System.out.println("coordenadaYInicio: " + ((Coordenadas2D)coordInicio).getY());
             MetodosEstaticos.dibujaPuente_nuevo(jTable1, coordInicio, coordFin, solUsuario, problema, sol, parent, puntuacion);
             //copiaSol.remove(el);
        }
        

        //La solucion del usuario es correcta-> buscar un puente de sol que no esté en solUsuario
        
    }

        public static void dibujaPuente_nuevo(JTable jTable1, Coordenadas coordInicio, Coordenadas coordFin, List<ElementoSolucion> solUsuario,
            Tablero problema, List<ElementoSolucion> sol, JFrame parent, int puntuacion){
        //Falta construir solUsuario
            //System.out.println("problema con el k entro a dibuja puentes: " + problema);
            boolean completado=false;
        //Puente Pista
            //System.out.println("Entro en dibujapuente como pista") ;
            Isla inicio=(Isla)problema.getCasilla(coordInicio);
            Isla fin=(Isla)problema.getCasilla(coordFin);
            int inicioX=((Coordenadas2D) coordInicio).getX();
            int inicioY=((Coordenadas2D) coordInicio).getY();
            int finX=((Coordenadas2D) coordFin).getX();
            int finY=((Coordenadas2D) coordFin).getY();
//            System.out.println("coordInicio.x:" + inicioX) ;
//            System.out.println("coordInicio.y:" +inicioY) ;
//            System.out.println("coordFin.x:" +finX) ;
//            System.out.println("coordFin.y:" +finY) ;

            if ( inicioX == finX) {//((Coordenadas2D) coordInicio).getX() == ((Coordenadas2D) coordFin).getX()
                //System.out.println("estan en la misma fila" );
                if (inicioY < finY) {//Inicio esta a la izqda
                    //System.out.println("entro en la isla incio esta a la izqda");
                    int puentes = inicio.getPuentes(Sentido.ESTE);

                    if(puentes == 1){
                        try{
                            inicio.setPuente(fin, true);
                            //System.out.println(problema);
                           // solUsuario.add(new ElementoSolucion(inicio, fin));
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            for (int j = inicioY+1; j < finY; j++) {
                                jTable1.setValueAt("2PuentesH_Pista", inicioX, j);
                            }
                        }catch (PuenteProhibidoException e){
                        }
                    }else if(puentes == 0){
                        try{
                            inicio.setPuente(fin, true);
                            //System.out.println(problema);
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            for (int j = inicioY+1; j < finY; j++) {
                                jTable1.setValueAt("1PuenteH_Pista", inicioX, j);
                            }
                        }catch (PuenteProhibidoException e){
                        }

                    }
                    if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                            completado=true;
                    }
                } else if (finY < inicioY) {
                    //System.out.println("entro en la isla incio esta a la dcha");
                    int puentes = inicio.getPuentes(Sentido.OESTE);

                    if(puentes == 1){
                        try{
                            fin.setPuente(inicio, true);
                            //System.out.println(problema);
                           // solUsuario.add(new ElementoSolucion(fin, inicio));
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            for (int j = finY+1; j < inicioY; j++) {
                                jTable1.setValueAt("2PuentesH_Pista", inicioX, j);
                            }
                        }catch (PuenteProhibidoException e){
                        }
                    }else if (puentes == 0){
                        try{
                            fin.setPuente(inicio, true);
                            //System.out.println(problema);
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            for (int j = finY+1; j < inicioY; j++) {
                                jTable1.setValueAt("1PuenteH_Pista", inicioX, j);
                            }
                        }catch(PuenteProhibidoException e){
                        }
                    }
                    if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                            completado=true;
                    }
                }
            } else if (inicioY == finY) {
                //System.out.println("estan en la misma columna: " );
                if (inicioX < finX) {//Inicio esta arriba
                    //System.out.println("entro en la isla incio esta arriba");
                    int puentes = inicio.getPuentes(Sentido.SUR);
                    if(puentes == 1){
                        try{
                            inicio.setPuente(fin, true);
                            //System.out.println(problema);
                           // solUsuario.add(new ElementoSolucion(inicio, fin));
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            for (int i = inicioX+1; i < finX; i++) {
                                jTable1.setValueAt("2PuentesV_Pista", i, finY);
                            }
                        }catch (PuenteProhibidoException e){
                        }
                    }else if (puentes == 0){
                        try{
                            inicio.setPuente(fin, true);
                            //System.out.println(problema);
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            for (int i = inicioX+1; i < finX; i++) {
                                jTable1.setValueAt("1PuenteV_Pista", i, inicioY);//ponia X
                            }
                        }catch(PuenteProhibidoException e){
                        }
                    }
                    if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                            completado=true;
                    }
                } else if (finX < inicioX) {
                    //System.out.println("entro en la isla incio esta aabajo");
                    int puentes = inicio.getPuentes(Sentido.NORTE);
                    if(puentes == 1){
                        try{
                            fin.setPuente(inicio, true);
                            //System.out.println(problema);
                          //  solUsuario.add(new ElementoSolucion(fin, inicio));
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            for (int i = finX+1; i < inicioX; i++) {
                                jTable1.setValueAt("2PuentesV_Pista", i, inicioY);
                            }
                        }catch (PuenteProhibidoException e){
                        }
                    }else if(puentes == 0){
                        try{
                            fin.setPuente(inicio, true);
                            //System.out.println(problema);
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            for (int i = finX+1; i < inicioX; i++) {
                                jTable1.setValueAt("1PuenteV_Pista", i, inicioY);//ponia X
                            }
                        }catch (PuenteProhibidoException e){
                        }
                    }
                    if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                            completado=true;
                    }
                }
            }
            if(completado){
                 Enhorabuena e=new Enhorabuena(parent, true, puntuacion);
                 e.setLocationRelativeTo(null);
                 e.setVisible(true);
            }
    }
    public static boolean comprobar_nuevo(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, JTable jTable1, Tablero problema){

        int i=0;
        int j=0;
        boolean encontrado=true;
        List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();
        copiaSol.addAll(sol);

        Iterator itSol=copiaSol.iterator();
        Iterator itUsuario=solUsuario.iterator();

        while(itUsuario.hasNext() && encontrado){
            System.out.println("Entra");
            encontrado=false;
            ElementoSolucion elUsuario=(ElementoSolucion)itUsuario.next();
            System.out.println("elUsuario: "+ elUsuario);
            while(itSol.hasNext() &&!encontrado){
                ElementoSolucion el=(ElementoSolucion)itSol.next();
                if(el.equals(elUsuario)){
                    encontrado=true;
                    //copiaSol.remove(el);
                }
            }
        }

        return encontrado;
    }

    public static int puenteObligatorio(JTable jTable1, List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, Tablero problema,
            JFrame parent, int puntuacion, JLabel num_puntos, List<Solucion> ss){

        int veces=0;
        Iterator it=sol.iterator();
        if (ss.get(0).iteracionBactracking != 0) {
            System.out.println("numIT" + ss.get(0).iteracionBactracking);
            MetodosEstaticos.borrarPuentes(problema, jTable1, solUsuario);


            while (it.hasNext()) {
                ElementoSolucion el = (ElementoSolucion) it.next();
                if (el.obligatorio) {//El puente es obligatorio
                    Coordenadas coordInicio = el.inicio.getCoordenadas();
                    Coordenadas coordFin = el.fin.getCoordenadas();

                    puntuacion = puntuacion - 10;
                    num_puntos.setText("  " + puntuacion);
                    dibujaPuente_nuevo(jTable1, coordInicio, coordFin, solUsuario, problema, sol, parent, puntuacion);
                    veces++;
                }
            }
        }
        return veces;
    }



    public static boolean compruebaPuente(ElementoSolucion el, List<ElementoSolucion> sol, int n){
        boolean encontrado=false;
        boolean encontrado1=false;
        Iterator it=sol.iterator();
        List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();

        if(n==1){
            while(it.hasNext() && !encontrado){
                ElementoSolucion elSol=(ElementoSolucion)it.next();
                if(elSol.equals(el)){
                    encontrado=true;
                }
            }
        }else{
            copiaSol.addAll(sol);
            Iterator it2=sol.iterator();
            while(it2.hasNext() && !encontrado1){
                ElementoSolucion elSol=(ElementoSolucion)it2.next();
                if(elSol.equals(el)){
                    encontrado1=true;
                    copiaSol.remove(el);
                }
            }
            if(encontrado1){
                Iterator it3=copiaSol.iterator();
                while(it3.hasNext() && !encontrado){
                ElementoSolucion elSol=(ElementoSolucion)it3.next();
                if(elSol.equals(el)){
                    encontrado=true;
                }
            }
            }
        }

        return encontrado;
    }


    public static void guardarPartida (List<ElementoSolucion> solucion, List<ElementoSolucion> solucionUsuario, int anchoTablero, int largotablero, int puntuacion) {

        FileWriter fichero = null;
        PrintWriter pw = null;
        BufferedWriter bw = null;
        try
        {
            fichero = new FileWriter("C:\\partida.txt", false);
            pw = new PrintWriter(fichero);
            bw = new BufferedWriter(pw);

            bw.append(anchoTablero + ":" + largotablero);
            bw.newLine();

            bw.append(puntuacion + "");
            bw.newLine();

            for (ElementoSolucion es : solucion) {
                bw.append(es.toString() + ":");
            }

            bw.newLine();

            for (ElementoSolucion es : solucionUsuario) {
                bw.append(es.toString() + ":");
            }

            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

    public static void guardarRanking (Object[][] ranking) {

        FileWriter fichero = null;
        PrintWriter pw = null;
        BufferedWriter bw = null;
        try
        {
            fichero = new FileWriter("C:\\ranking.txt", false);
            pw = new PrintWriter(fichero);
            bw = new BufferedWriter(pw);

            for (int i = 0; i < ranking[1].length; i++) {
                if (ranking[0][i] != null) {
                    bw.append(ranking[0][i] + "," + ranking[1][i]);
                    bw.newLine();
                }
            }

            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

    
    public static Object[][] cargarRanking(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      Object[][] ranking = new Object[2][10];

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:\\ranking.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         int i = 0;
         while (((linea=br.readLine())!=null) && (i < ranking.length)) {
             String[] aux;
             aux = linea.split(",");
             ranking[0][i] = aux[0];
             ranking[1][i] = aux[1];
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta
         // una excepcion.
         try{
            if( null != fr ){
               fr.close();
            }
         }catch (Exception e2){
            e2.printStackTrace();
         }
      }
      return ranking;
    }
}

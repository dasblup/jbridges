/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import com.googlecode.jbridges.lib.Agua;
import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Coordenadas2D;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
        EstrategiaBackTrackingBasica ebb = new EstrategiaBackTrackingBasica();
        //sol = ebb.solucionar(problema);
        sol = ebb.solucionar(problema).get(0).solucion;
        ElementoSolucion el;
        Iterator it = sol.iterator();
        //recorrer la lista de solucion y por cada ElementoSolucion, poner el puente entre esas islas o el doble puente en el
        //caso de que ya haya uno
        while (it.hasNext()) {
            el = (ElementoSolucion) it.next();
            Coordenadas coordInicio = el.inicio.getCoord();
            Coordenadas coordFin = el.fin.getCoord();
            if (((Coordenadas2D)coordInicio).getX() == ((Coordenadas2D)coordFin).getX()) {
                if (((Coordenadas2D)coordInicio).getY() < ((Coordenadas2D)coordFin).getY()) {
                    for (int j = ((Coordenadas2D)coordInicio).getY() + 1; j < ((Coordenadas2D)coordFin).getY(); j++) {
                        if (jTable1.getValueAt(((Coordenadas2D)coordFin).getX(), j).equals("1PuenteH")) {
                            jTable1.setValueAt("2PuentesH", ((Coordenadas2D)coordInicio).getX(), j);
                        } else {
                            jTable1.setValueAt("1PuenteH", ((Coordenadas2D)coordInicio).getX(), j);
                        }
                    }
                } else if (((Coordenadas2D)coordInicio).getY() > ((Coordenadas2D)coordFin).getY()) {
                    for (int j = ((Coordenadas2D)coordFin).getY() + 1; j < ((Coordenadas2D)coordInicio).getY(); j++) {
                        if (jTable1.getValueAt(((Coordenadas2D)coordInicio).getX(), j).equals("1PuenteH")) {
                            jTable1.setValueAt("2PuentesH", ((Coordenadas2D)coordInicio).getX(), j);
                        } else {
                            jTable1.setValueAt("1PuenteH", ((Coordenadas2D)coordInicio).getX(), j);
                        }
                    }
                }
            } else if (((Coordenadas2D)coordInicio).getY() == ((Coordenadas2D)coordFin).getY()) {
                if (((Coordenadas2D)coordInicio).getX() < ((Coordenadas2D)coordFin).getX()) {
                    for (int i = ((Coordenadas2D)coordInicio).getX() + 1; i < ((Coordenadas2D)coordFin).getX(); i++) {
                        if (jTable1.getValueAt(i, ((Coordenadas2D)coordInicio).getY()).equals("1PuenteV")) {
                            jTable1.setValueAt("2PuentesV", i, ((Coordenadas2D)coordInicio).getY());
                        } else {
                            jTable1.setValueAt("1PuenteV", i, ((Coordenadas2D)coordInicio).getY());
                        }
                    }
                } else if (((Coordenadas2D)coordInicio).getX() > ((Coordenadas2D)coordFin).getX()) {
                    for (int i = ((Coordenadas2D)coordFin).getX() + 1; i < ((Coordenadas2D)coordInicio).getX(); i++) {
                        if (jTable1.getValueAt(i, ((Coordenadas2D)coordInicio).getY()).equals("1PuenteV")) {
                            jTable1.setValueAt("2PuentesV", i, ((Coordenadas2D)coordInicio).getY());
                        } else {
                            jTable1.setValueAt("1PuenteV", i, ((Coordenadas2D)coordInicio).getY());
                        }
                    }
                }
            }
            solUsuario.clear();
            solUsuario.addAll(sol);
        }

    }

////    public static void siguentePaso_mal(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, JTable jTable1) {
////
////        //Si la solucion que está formando el usuario es correcta, la pista será un nuevo puente, en caso contrario,
////        //la pista será indicar uno de los puentes incorrectos
////
////        Iterator itUsuario = solUsuario.iterator();
////        Iterator it = sol.iterator();
////        boolean encontrado=false;
////
////        if(sol.containsAll(solUsuario)){
////            //Dar una pista.  Buscar un elemento de sol que no esté en solUsuario
////            while(it.hasNext()){
////                ElementoSolucion el=(ElementoSolucion) it.next();
////                while(itUsuario.hasNext() && !encontrado){
////                    ElementoSolucion elUsuario=(ElementoSolucion)itUsuario.next();
////                    if(el.equals(elUsuario)){
////                        encontrado=true;
////                    }
////                }
////                if(!encontrado){
////                    Coordenadas2D coordInicio=el.inicio.getCoordenadas();
////                    Coordenadas2D coordFin=el.fin.getCoordenadas();
////                    MetodosEstaticos.dibujaPuenteErroneo(jTable1, coordInicio, coordFin);
////                }
////
////            }
////        }else{
////            //Buscar el primero que esté mal y dibujarlo
////            List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();
////            copiaSol.addAll(sol);
////            copiaSol.removeAll(solUsuario);
////            ElementoSolucion pista=copiaSol.get(0);
////            Coordenadas2D coordInicio=pista.inicio.getCoordenadas();
////            Coordenadas2D coordFin=pista.fin.getCoordenadas();
////            MetodosEstaticos.dibujaPuenteErroneo(jTable1, coordInicio, coordFin);
////        }
////
////    }
//
    public static void accionRaton(MouseEvent evt, JTable jTable1, Tablero problema, int fila, int columna, List<ElementoSolucion> solUsuario,
            List<ElementoSolucion> sol, Frame parent, int puntuacion) {

        if (!MetodosEstaticos.comparaListas(solUsuario, sol)) {
            int f = jTable1.rowAtPoint(evt.getPoint());
            int c = jTable1.columnAtPoint(evt.getPoint());
            Coordenadas coord = problema.getCoordenadas(f, c);
            boolean vacias = false;
            boolean h1 = false;
            boolean h2 = false;
            boolean v1 = false;
            boolean v2 = false;
            boolean completado=false;



            if ((problema.getCasilla(coord) instanceof Isla)) {

                if (fila == -1 || columna == -1) {
                    fila = jTable1.rowAtPoint(evt.getPoint());
                    columna = jTable1.columnAtPoint(evt.getPoint());
                } else {
                    int fila2 = jTable1.rowAtPoint(evt.getPoint());
                    int columna2 = jTable1.columnAtPoint(evt.getPoint());

                    Coordenadas coord1=problema.getCoordenadas(fila, columna);
                    Coordenadas coord2=problema.getCoordenadas(fila2, columna2);
                    Isla inicio=(Isla)problema.getCasilla(coord1);
                    Isla fin=(Isla)problema.getCasilla(coord2);
                    if (fila == fila2) {

                        if (columna - columna2 < 0) {
                            //Comprueba que todas las celdas intermedias están vacias
                            for (int i = columna + 1; i < columna2; i++) {
                                if (jTable1.getValueAt(fila, i) == null || jTable1.getValueAt(fila, i).equals("borra")) {
                                    vacias = true;
                                    break;
                                } else if ((jTable1.getValueAt(fila, i).equals("1PuenteH")) || (jTable1.getValueAt(fila, i).equals("1PuenteH_Error"))) {
                                    h1 = true;
                                } else if ((jTable1.getValueAt(fila, i).equals("2PuentesH")) || (jTable1.getValueAt(fila, i).equals("2PuentesH_Error"))) {
                                    h2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = columna + 1; i < columna2; i++) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("1PuenteH", fila, i);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (h1) {
                                for (int i = columna + 1; i < columna2; i++) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("2PuentesH", fila, i);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (h2) {
                                for (int i = columna + 1; i < columna2; i++) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("borra", fila, i);
                                        puntuacion=puntuacion-2;
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                    }
                                }
                            }
                        } else if (columna - columna2 > 0) {
                            for (int i = columna - 1; i < columna2; i--) {
                                if (jTable1.getValueAt(fila, i) == null || jTable1.getValueAt(fila, i).equals("borra")) {
                                    vacias = true;
                                } else if ((jTable1.getValueAt(fila, i).equals("1PuenteH")) || (jTable1.getValueAt(fila, i).equals("1PuenteH_Error"))) {
                                    h1 = true;
                                } else if ((jTable1.getValueAt(fila, i).equals("2PuentesH")) || (jTable1.getValueAt(fila, i).equals("2PuentesH_Error"))) {
                                    h2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = columna - 1; i < columna2; i--) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("1PuenteH", fila, i);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (h1) {
                                for (int i = columna - 1; i < columna2; i--) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("2PuentesH", fila, i);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (h2) {
                                for (int i = columna - 1; i < columna2; i--) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("borra", fila, i);
                                        puntuacion=puntuacion-2;
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                    }
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
                                if (jTable1.getValueAt(i, columna) == null || jTable1.getValueAt(i, columna).equals("borra")) {
                                    vacias = true;
                                    break;
                                } else if ((jTable1.getValueAt(i, columna).equals("1PuenteV")) || (jTable1.getValueAt(i, columna).equals("1PuenteV_Error"))) {
                                    v1 = true;
                                } else if ((jTable1.getValueAt(i, columna).equals("2PuentesV")) || (jTable1.getValueAt(i, columna).equals("2PuentesV_Error"))) {
                                    v2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = fila + 1; i < fila2; i++) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("1PuenteV", i, columna);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (v1) {
                                for (int i = fila + 1; i < fila2; i++) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("2PuentesV", i, columna);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (v2) {
                                for (int i = fila + 1; i < fila2; i++) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
                                        solUsuario.remove(new ElementoSolucion(inicio, fin));
                                        jTable1.setValueAt("borra", i, columna);
                                        puntuacion=puntuacion-2;
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                    }
                                }
                            }
                        } else if (fila > fila2) {
                            for (int i = fila - 1; i > fila2; i--) {
                                if (jTable1.getValueAt(i, columna) == null || jTable1.getValueAt(i, columna).equals("borra")) {
                                    vacias = true;
                                } else if ((jTable1.getValueAt(i, columna).equals("1PuenteV")) || (jTable1.getValueAt(i, columna).equals("1PuenteV_Error"))) {
                                    v1 = true;
                                } else if ((jTable1.getValueAt(i, columna).equals("2PuentesV")) || (jTable1.getValueAt(i, columna).equals("2PuentesV_Error"))) {
                                    v2 = true;
                                }
                            }
                            if (vacias) {
                                for (int i = fila - 1; i > fila2; i--) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("1PuenteV", i, columna);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "simple")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (v1) {
                                for (int i = fila - 1; i > fila2; i--) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.add(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("2PuentesV", i, columna);
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }
                                        if(MetodosEstaticos.comprobarPuente(sol, inicio, fin, "doble")){
                                            puntuacion=puntuacion+3;
                                        }else{
                                            puntuacion=puntuacion-2;
                                        }
                                    }
                                }
                            } else if (v2) {
                                for (int i = fila - 1; i > fila2; i--) {
                                    if(!MetodosEstaticos.comparaListas(solUsuario, sol)){
                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
                                        solUsuario.remove(new ElementoSolucion(fin, inicio));
                                        jTable1.setValueAt("borra", i, columna);
                                        puntuacion=puntuacion-2;
                                        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
                                            completado=true;
                                        }

                                    }
                                }
                            }
                        }
                        v2 = false;
                        v1 = false;
                        vacias = false;
                    }
                    if(completado){
                        Enhorabuena e=new Enhorabuena(parent, true);
                        e.setVisible(true);
                    }
                }
            }
        }
    }

    public static void comprobar(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, JTable jTable1, Tablero problema){
        //Cada elemento de solUsuario, lo comparo con los elementos de sol, si no encuentro
        //ninguno igual, significa que ese puente está mal.Lo meto en una lista para dibujarlos luego
        boolean encontrado=false;
        List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();
        copiaSol.addAll(sol);
        Iterator itUsuario=solUsuario.iterator();
        Iterator it=copiaSol.iterator();
        //En esta lista se guardarán todos los puentes incorrectos
        List<ElementoSolucion> l=new LinkedList<ElementoSolucion>();

        while(itUsuario.hasNext()){
            ElementoSolucion elUsuario=(ElementoSolucion)itUsuario.next();
            while(it.hasNext() && !encontrado){
                ElementoSolucion el=(ElementoSolucion)it.next();
                if(!elUsuario.equals(el)){
                    l.add(elUsuario);
                }else{
                    copiaSol.remove(el);
                    encontrado=true;
                }
            }
        }
        //Pinto los errores
        if(!l.isEmpty()){
            Iterator itL=l.iterator();
            while(itL.hasNext()){
                ElementoSolucion error=(ElementoSolucion)itL.next();
                Coordenadas coordInicio=error.inicio.getCoord();
                Coordenadas coordFin=error.fin.getCoord();
                MetodosEstaticos.dibujaPuente(jTable1, coordInicio, coordFin, 2, solUsuario, problema);
            }
        }
    }

    public static void dibujaPuente(JTable jTable1, Coordenadas coordInicio, Coordenadas coordFin, int n, List<ElementoSolucion> solUsuario,
            Tablero problema) {
        //Falta construir solUsuario
        if (n == 1) {//Puente Pista
            Isla inicio=(Isla)problema.getCasilla(coordInicio);
            Isla fin=(Isla)problema.getCasilla(coordFin);
            if (((Coordenadas2D) coordInicio).getX() == ((Coordenadas2D) coordFin).getX()) {
                if (((Coordenadas2D) coordInicio).getY() < ((Coordenadas2D) coordFin).getY()) {
                    for (int j = ((Coordenadas2D) coordInicio).getY(); j < ((Coordenadas2D) coordFin).getY(); j++) {
                        //si ya tiene un puente, lo pintamos doble
                        if (jTable1.getValueAt(((Coordenadas2D) coordInicio).getX(), j).equals("1PuenteH_Pista")) {
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            jTable1.setValueAt("2PuentesH_Pista", ((Coordenadas2D) coordInicio).getX(), j);
                        } else {
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            jTable1.setValueAt("1PuentesH_Pista", ((Coordenadas2D) coordInicio).getX(), j);
                        }
                    }
                } else if (((Coordenadas2D) coordFin).getY() < ((Coordenadas2D) coordInicio).getY()) {
                    for (int j = ((Coordenadas2D) coordFin).getY(); j < ((Coordenadas2D) coordInicio).getY(); j++) {
                        if (jTable1.getValueAt(((Coordenadas2D) coordInicio).getX(), j).equals("1PuenteH_Pista")) {
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            jTable1.setValueAt("2PuentesH_Pista", ((Coordenadas2D) coordInicio).getX(), j);
                        } else {
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            jTable1.setValueAt("1PuentesH_Pista", ((Coordenadas2D) coordInicio).getX(), j);
                        }
                    }
                }
            } else if (((Coordenadas2D) coordInicio).getY() == ((Coordenadas2D) coordFin).getY()) {
                if (((Coordenadas2D) coordInicio).getX() < ((Coordenadas2D) coordFin).getX()) {
                    for (int i = ((Coordenadas2D) coordInicio).getX(); i < ((Coordenadas2D) coordFin).getX(); i++) {
                        if (jTable1.getValueAt(i, ((Coordenadas2D) coordInicio).getY()).equals("1PuenteV_Pista")) {
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            jTable1.setValueAt("2PuentesV_Pista", i, ((Coordenadas2D) coordInicio).getY());
                        } else {
                            solUsuario.add(new ElementoSolucion(inicio, fin));
                            jTable1.setValueAt("1PuentesV_Pista", i, ((Coordenadas2D) coordInicio).getX());
                        }
                    }
                } else if (((Coordenadas2D) coordFin).getX() < ((Coordenadas2D) coordInicio).getX()) {
                    for (int i = ((Coordenadas2D) coordFin).getX(); i < ((Coordenadas2D) coordInicio).getX(); i++) {
                        if (jTable1.getValueAt(i, ((Coordenadas2D) coordInicio).getY()).equals("1PuenteV_Pista")) {
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            jTable1.setValueAt("2PuentesV_Pista", i, ((Coordenadas2D) coordInicio).getY());
                        } else {
                            solUsuario.add(new ElementoSolucion(fin, inicio));
                            jTable1.setValueAt("1PuentesV_Pista", i, ((Coordenadas2D) coordInicio).getX());
                        }
                    }
                }
            }
        }else{//Puente Error
                        if (((Coordenadas2D) coordInicio).getX() == ((Coordenadas2D) coordFin).getX()) {
                if (((Coordenadas2D) coordInicio).getY() < ((Coordenadas2D) coordFin).getY()) {
                    for (int j = ((Coordenadas2D) coordInicio).getY(); j < ((Coordenadas2D) coordFin).getY(); j++) {
                        //si ya tiene un puente, lo pintamos doble
                        if (jTable1.getValueAt(((Coordenadas2D) coordInicio).getX(), j).equals("1PuenteH_Error")) {
                            jTable1.setValueAt("2PuentesH_Error", ((Coordenadas2D) coordInicio).getX(), j);
                        }else if (jTable1.getValueAt(((Coordenadas2D) coordInicio).getX(), j).equals("2PuentesH")){
                            jTable1.setValueAt("2PuentesH_1Error", ((Coordenadas2D) coordInicio).getX(), j);
                        }else {
                            jTable1.setValueAt("1PuentesH_Error", ((Coordenadas2D) coordInicio).getX(), j);
                        }
                    }
                } else if (((Coordenadas2D) coordFin).getY() < ((Coordenadas2D) coordInicio).getY()) {
                    for (int j = ((Coordenadas2D) coordFin).getY(); j < ((Coordenadas2D) coordInicio).getY(); j++) {
                        if (jTable1.getValueAt(((Coordenadas2D) coordInicio).getX(), j).equals("1PuenteH_Error")) {
                            jTable1.setValueAt("2PuentesH_Error", ((Coordenadas2D) coordInicio).getX(), j);
                        }else if (jTable1.getValueAt(((Coordenadas2D) coordInicio).getX(), j).equals("2PuentesH")){
                            jTable1.setValueAt("2PuentesH_1Error", ((Coordenadas2D) coordInicio).getX(), j);
                        }else {
                            jTable1.setValueAt("1PuentesH_Error", ((Coordenadas2D) coordInicio).getX(), j);
                        }
                    }
                }
            } else if (((Coordenadas2D) coordInicio).getY() == ((Coordenadas2D) coordFin).getY()) {
                if (((Coordenadas2D) coordInicio).getX() < ((Coordenadas2D) coordFin).getX()) {
                    for (int i = ((Coordenadas2D) coordInicio).getX(); i < ((Coordenadas2D) coordFin).getX(); i++) {
                        if (jTable1.getValueAt(i, ((Coordenadas2D) coordInicio).getY()).equals("1PuenteV_Error")) {
                            jTable1.setValueAt("2PuentesV_Error", i, ((Coordenadas2D) coordInicio).getY());
                        }else if (jTable1.getValueAt(i, ((Coordenadas2D) coordInicio).getY()).equals("2PuentesV")){
                            jTable1.setValueAt("2PuentesV_1Error", i, ((Coordenadas2D) coordInicio).getY());
                        }else {
                            jTable1.setValueAt("1PuentesV_Error", i, ((Coordenadas2D) coordInicio).getX());
                        }
                    }
                } else if (((Coordenadas2D) coordFin).getX() < ((Coordenadas2D) coordInicio).getX()) {
                    for (int i = ((Coordenadas2D) coordFin).getX(); i < ((Coordenadas2D) coordInicio).getX(); i++) {
                        if (jTable1.getValueAt(i, ((Coordenadas2D) coordInicio).getY()).equals("1PuenteV_Error")) {
                            jTable1.setValueAt("2PuentesV_Error", i, ((Coordenadas2D) coordInicio).getY());
                        }else if (jTable1.getValueAt(i, ((Coordenadas2D) coordInicio).getY()).equals("2PuentesV")){
                            jTable1.setValueAt("2PuentesV_1Error", i, ((Coordenadas2D) coordInicio).getY());
                        }else {
                            jTable1.setValueAt("1PuentesV_Error", i, ((Coordenadas2D) coordInicio).getX());
                        }
                    }
                }
            }
        }
    }

    public static void siguentePaso(List<ElementoSolucion> solUsuario, List<ElementoSolucion> sol, JTable jTable1, Tablero problema) {

        //Si la solucion que está formando el usuario es correcta, la pista será un nuevo puente, en caso contrario,
        //la pista será indicar uno de los puentes incorrectos

        LinkedList<ElementoSolucion> copiaSol = new LinkedList<ElementoSolucion>();
        copiaSol.addAll(sol);

        Iterator itUsuario = solUsuario.iterator();
        Iterator itCopia = copiaSol.iterator();
        boolean encontrado = false;

        while (itUsuario.hasNext() && encontrado) {
            ElementoSolucion elUsuario = (ElementoSolucion) itUsuario.next();
            while (itCopia.hasNext() && !encontrado) {
                ElementoSolucion elCopia = (ElementoSolucion) itCopia.next();
                if (elUsuario.equals(elCopia)) {
                    encontrado = true;
                    copiaSol.remove(elCopia);
                }
            }
            //El elemento de solUsuario que estamos tratando no está en copiaSol-> mostrarlo como pista
            if (!encontrado) {
                Coordenadas coordInicio = elUsuario.inicio.getCoord();
                Coordenadas coordFin = elUsuario.fin.getCoord();
                MetodosEstaticos.dibujaPuente(jTable1, coordInicio, coordFin, 1, solUsuario, problema);
            }
        }

        //La solucion del usuario es correcta-> buscar un puente de sol que no esté en solUsuario
        if (encontrado) {
            List<ElementoSolucion> copiaSolUsuario = new LinkedList<ElementoSolucion>();
            copiaSolUsuario.addAll(solUsuario);
            Iterator itSol = sol.iterator();
            Iterator itCopiaSolUsuario = copiaSolUsuario.iterator();
            boolean enc = false;

            while (itSol.equals(sol)) {
                ElementoSolucion el = (ElementoSolucion) itSol.next();
                while (itCopiaSolUsuario.hasNext() && !enc) {
                    ElementoSolucion elCopiaUsu = (ElementoSolucion) itCopiaSolUsuario.next();
                    if (el.equals(elCopiaUsu)) {
                        enc = true;
                        copiaSolUsuario.remove(elCopiaUsu);
                    }
                }
                if (!enc) {
                    Coordenadas coordI = el.inicio.getCoord();
                    Coordenadas coordF = el.fin.getCoord();
                    MetodosEstaticos.dibujaPuente(jTable1, coordI, coordF, 2, solUsuario, problema);
                }
            }
        }
    }

    public static boolean comparaListas(List<ElementoSolucion> l1, List<ElementoSolucion> l2){

        //Este método se utiliza para deshabilitar algunos botones cuando el usuario ya haya encontrado la solucion

        boolean iguales=true;
        boolean enc=false;

        if(l1.size()!=l2.size()){
            iguales=false;
        }else{
            Iterator it1=l1.iterator();
            List<ElementoSolucion> copiaL2=new LinkedList<ElementoSolucion>();
            copiaL2.addAll(l2);
            Iterator it2=copiaL2.iterator();

            while(it1.hasNext() && iguales){
                ElementoSolucion el1=(ElementoSolucion)it1.next();
                while(it2.hasNext() && !enc){
                    ElementoSolucion el2=(ElementoSolucion)it2.next();
                    if(el1.equals(el2)){
                        copiaL2.remove(el2);
                        enc=true;
                    }
                }
                if(!enc){
                    iguales=false;
                }
            }
      }
          return iguales;
    }

    public static void borrarPuentes(Tablero problema, JTable jTable1, List<ElementoSolucion> solUsuario){
        //Recorrer la tabla y borrar todos lo puentes que habia,
        //ademas borrar la lista de solución que estaba creando el usuario
        for(int i=0;i<problema.getAltura();i++){
            for(int j=0;j<problema.getAnchura();j++){
                Coordenadas coord;
                coord=problema.getCoordenadas(i, j);
                Casilla casilla=problema.getCasilla(coord);
                if (casilla instanceof Agua){
                    jTable1.setValueAt("borra", i, j);
                }
            }
        }
        solUsuario.clear();
    }

    public static boolean comprobarPuente(List<ElementoSolucion> sol, Isla inicio, Isla fin, String es){
        boolean existe=false;
        boolean uno=false;

        ElementoSolucion el = new ElementoSolucion(inicio, fin);
        List<ElementoSolucion> copiaSol=new LinkedList<ElementoSolucion>();
        copiaSol.addAll(sol);
        System.out.println("Tamaño de copiaSol:" + copiaSol.size());
        Iterator it = copiaSol.iterator();

        if (es == "simple") {

            while (it.hasNext() && !existe) {
                ElementoSolucion el2 = (ElementoSolucion) it.next();
                if (el.equals(el2)) {
                    existe = true;
                }
            }

        }else if(es =="doble"){

            while(it.hasNext() && !uno){
                
                ElementoSolucion el2 = (ElementoSolucion) it.next();

                if (el.equals(el2)) {
                    uno = true;
                    copiaSol.remove(el);
                    System.out.println("Tamaño de copiaSol:" + copiaSol.size());
                }
            }
            if(uno){
                Iterator it2=copiaSol.iterator();
                while(it2.hasNext() && !existe){
                    ElementoSolucion el3 = (ElementoSolucion) it2.next();
                    if (el.equals(el3)) {
                        existe = true;
                    }
                }
            }

        }

        return existe;
    }

    public void conectaBD(){
        String bd="jkh";
        String login="asd";
        String contraseña="contraseña";
        String url="jdbc:mysql://localhost/"+bd;

        Connection conn=null;
        try{
            Class.forName("");
            conn=DriverManager.getConnection(url, login, contraseña);
            if(conn!=null){
                System.out.println("Conexion a base de datos"+url+"...OK");
                conn.close();
            }
        }catch(SQLException ex){
            System.out.println("Hubo un problema al conectarse a la base de datos");
        }catch(ClassNotFoundException ex){
            System.out.println(ex);
        }


    }
}

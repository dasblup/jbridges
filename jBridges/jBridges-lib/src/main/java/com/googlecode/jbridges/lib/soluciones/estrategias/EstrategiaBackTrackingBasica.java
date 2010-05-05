/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.soluciones.estrategias;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author pabloramix
 */
public class EstrategiaBackTrackingBasica implements EstrategiaSolucion {


    private List<Isla> candidatas0;
    private List<Isla> candidatas1;
    private List<Isla> listaIslas;
    private List<List<Isla>> componentesConexas;
    private List<Solucion> soluciones;

    public EstrategiaBackTrackingBasica () {
        candidatas0 = new LinkedList<Isla>();
        candidatas1 = new LinkedList<Isla>();
        soluciones = new LinkedList<Solucion>();
    }

    public Solucion solucionar (Tablero t) {

        Etapa x;

        x = new Etapa();

        x.tablero = t;
        x.iteracion = 0;
        x.solucion = new Solucion();

        obtenerIslas(t);

        btTodas(x);

        return new Solucion();
    }

    private void btTodas (Etapa x) {

        Etapa xsig;
        Queue<Solucion> candidatos;
        Solucion candidatoSeleccionado;

        if (esSolucion(x)) {
            comunicarSolucion(x);
        }

        xsig = new Etapa(x);
        candidatos = calcularCandidatos(x);

        while (quedanCandidatos(candidatos)) {
                candidatoSeleccionado = seleccionarCandidatos(candidatos, xsig);
                if (esPrometedor(candidatoSeleccionado, xsig)) {
                    anotarEnSolucion(candidatoSeleccionado, xsig);
                    btTodas(xsig);
                    cancelarAnotacion(candidatoSeleccionado, xsig);
                }
        }
    }

    private Queue<Solucion> calcularCandidatos(Etapa x) {

        cargarListas();

        Queue<Solucion> s;
        s = new LinkedList<Solucion>();

        Solucion sol;
        sol = new Solucion();

        while (hayCandidatas()) {

            ponerPuentes(x.tablero, sol);
            cargarListas();
        }

        Isla i;

        i = listaIslas.get(1);

        for(Sentido sent : Sentido.values()) {
            Solucion aux;

            try {
                Isla vecina = i.getVecina(sent);
                try {
                    aux = (Solucion) sol.clone();
                    sol.solucion.add(new ElementoSolucion(i, vecina));
                    s.add(sol);
                } catch (CloneNotSupportedException ex) {}
            } catch (IslaNoEncontradaException inee) {}
        }

        return s;
    }

    private void obtenerIslas (Tablero t) {

        listaIslas = new LinkedList<Isla>();
        componentesConexas = new LinkedList<List<Isla>>();

        TableroArray tablero = (TableroArray) t;

         for (int i = 0; i < tablero.getAltura(); i++) {
            for (int j = 0; j < tablero.getAnchura(); j++) {

                Casilla c = tablero.getCasilla(tablero.getCoordenadas(i, j));
                if (c instanceof Isla) {
                    listaIslas.add((Isla)c);
                    List l = new LinkedList<Isla>();
                    l.add((Isla)c);
                    componentesConexas.add(l);
                }
            }
        }
    }

    private void cargarListas() {

        Isla islaVecina;

        int puentesPosibles;
        int numeroVecinas;

        Isla i;
        i = null;

        for (Iterator<Isla> it = listaIslas.iterator();
             it.hasNext();
             i = it.next()) {

            if (i.getPuentes() == i.getN()) {
                it.remove();

            } else {

                puentesPosibles = 0;
                numeroVecinas = 0;

                for(Sentido s : Sentido.values()) {
                    try {
                        islaVecina = i.getVecina(s);
                        numeroVecinas++;
                        puentesPosibles += islaVecina.getN() > 1 ? 2 : 1;
                    } catch(IslaNoEncontradaException inee) {}

                }

                if (puentesPosibles - i.getN() == 0) {
                    candidatas0.add(i);
                    it.remove();
                } else if (puentesPosibles - i.getN() == 1) {
                    if (i.getN() / numeroVecinas - 1 > 2) {
                        candidatas1.add(i);
                    }
                }
            }
        }
    }

    private boolean hayCandidatas() {
        return !(candidatas0.isEmpty() && candidatas1.isEmpty());
    }

    private void ponerPuentes(Tablero t, Solucion sol) {
        
        for (Isla i : candidatas0) {

            List<Isla> contieneI;

            contieneI = null;

            for(List<Isla> comp: componentesConexas) {
                if (comp.contains(i)) {
                    contieneI = comp;
                }
            }

            for (Sentido s : Sentido.values()) {
                Isla vecina;
                try {
                    vecina = i.getVecina(s);
                    i.setPuente(vecina);
                    sol.solucion.add(new ElementoSolucion(i, vecina));
                    i.setPuente(vecina);
                    sol.solucion.add(new ElementoSolucion(i, vecina));

                    if (!contieneI.contains(vecina)) {

                        List<Isla> comp;

                        comp = null;

                        for(Iterator it = componentesConexas.iterator();
                            it.hasNext();
                            comp = (List<Isla>) it.next()) {
                            if (comp.contains(vecina)) {
                                contieneI.addAll(comp);
                                it.remove();
                            }
                        }
                    }

                } catch (IslaNoEncontradaException inee) {
                } catch (PuenteProhibidoException ppe) {
                }
            }
        }

        for (Isla i : candidatas1) {

            List<Isla> contieneI;

            contieneI = null;

            for(List<Isla> comp: componentesConexas) {
                if (comp.contains(i)) {
                    contieneI = comp;
                }
            }

            for (Sentido s : Sentido.values()) {
                if (i.getPuentes(s) == 0) {
                    Isla vecina;
                    try {
                        vecina = i.getVecina(s);
                        i.setPuente(vecina);
                        sol.solucion.add(new ElementoSolucion(i, vecina));

                        if (!contieneI.contains(vecina)) {

                            List<Isla> comp;

                            comp = null;

                            for(Iterator it = componentesConexas.iterator();
                                it.hasNext();
                                comp = (List<Isla>) it.next()) {
                                if (comp.contains(vecina)) {
                                    contieneI.addAll(comp);
                                    it.remove();
                                }
                            }
                        }

                    } catch (IslaNoEncontradaException inee) {
                    } catch (PuenteProhibidoException ppe) {
                    }
                }
            }
        }
    }

    private boolean esSolucion(Etapa x) {
        return listaIslas.isEmpty() && componentesConexas.size() == 1;
    }

    private void comunicarSolucion(Etapa x) {
        this.soluciones.add(x.solucion);
    }

    private boolean quedanCandidatos(Queue<Solucion> candidatos) {
        return !candidatos.isEmpty();
    }

    private Solucion seleccionarCandidatos(Queue<Solucion> candidatos, Etapa xsig) {
        return candidatos.poll();
    }

    private boolean esPrometedor(Solucion candidato, Etapa xsig) {
        return true;
    }

    private void anotarEnSolucion(Solucion candidato, Etapa xsig) {
        xsig.solucion.solucion.addAll(candidato.solucion);
        ElementoSolucion es = (ElementoSolucion) ((Queue)candidato.solucion).peek();
        try {
            es.inicio.setPuente(es.fin);
        } catch (PuenteProhibidoException ex) {}
    }

    private void cancelarAnotacion(Solucion candidato, Etapa xsig) {
        List nuevaSolucion;
        nuevaSolucion = xsig.solucion.solucion
                .subList(0, xsig.solucion.solucion.size() - candidato.solucion.size() - 1);

        for (ElementoSolucion es : candidato.solucion) {
            try {
                es.inicio.borrarPuente(es.fin);
            } catch (PuenteProhibidoException ex) {}
        }
    }

    private class Etapa {

        int iteracion;
        Tablero tablero;
        Solucion solucion;

        private Etapa () {
            super();
        }

        private Etapa (Etapa x) {
            super();

            this.iteracion = x.iteracion + 1;
            this.tablero = x.tablero;
            this.solucion = x.solucion;
        }
    }
}

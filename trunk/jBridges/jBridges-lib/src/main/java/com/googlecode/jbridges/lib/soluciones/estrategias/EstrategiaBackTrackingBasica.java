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
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pabloramix
 */
public class EstrategiaBackTrackingBasica implements EstrategiaSolucion {


    private List<Isla> candidatas0;
    private List<Isla> candidatas1;
    private List<Isla> listaIslas;

    public EstrategiaBackTrackingBasica () {
        candidatas0 = new LinkedList<Isla>();
        candidatas1 = new LinkedList<Isla>();
    }

    public Solucion solucionar (Tablero t) {

        Etapa x;

        x = new Etapa();

        x.tablero = t;
        x.iteracion = 0;
        x.solucion = new LinkedList<ElementoSolucion>();

        btTodas(x);

        return new Solucion();
    }

    private void btTodas (Etapa x) {

        Etapa xsig;
        List<Tablero> candidatos;

        if (esSolucion(x)) {
            comunicarSolucion(x);
        }

        xsig = new Etapa(x);
        candidatos = calcularCandidatos(x);

        while (quedanCandidatos(candidatos)) {
                seleccionarCandidatos(candidatos, xsig);
                if (esPrometedor(candidatos, xsig)) {
                    anotarEnSolucion(candidatos, xsig);
                    btTodas(xsig);
                    cancelarAnotacion(candidatos, xsig);
                }
        }
    }

    public List<Tablero> calcularCandidatos(Etapa x) {

        listaIslas = obtenerIslas(x.tablero);

        cargarListas();

        while (hayCandidatas()) {

            ponerPuentes(x.tablero);
            cargarListas();
        }

        return null;
    }

    private List<Isla> obtenerIslas (Tablero t) {

        List<Isla> l;

        l = new LinkedList<Isla>();

        TableroArray tablero = (TableroArray) t;

         for (int i = 0; i < tablero.getAltura(); i++) {
            for (int j = 0; j < tablero.getAnchura(); j++) {

                Casilla c = tablero.getCasilla(tablero.getCoordenadas(i, j));
                if (c instanceof Isla) {
                    l.add((Isla)c);
                }
            }
        }

        return l;

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

    private void ponerPuentes(Tablero t) {
        
        for (Isla i : candidatas0) {
            for (Sentido s : Sentido.values()) {
                Isla vecina;
                try {
                    vecina = i.getVecina(s);
                    i.setPuente(vecina);
                    i.setPuente(vecina);
                } catch (IslaNoEncontradaException inee) {
                } catch (PuenteProhibidoException ppe) {
                }
            }
        }

        for (Isla i : candidatas1) {
            for (Sentido s : Sentido.values()) {
                if (i.getPuentes(s) == 0) {
                    Isla vecina;
                    try {
                        vecina = i.getVecina(s);
                        i.setPuente(vecina);
                    } catch (IslaNoEncontradaException inee) {
                    } catch (PuenteProhibidoException ppe) {
                    }
                }
            }
        }
    }

    private class Etapa {

        int iteracion;
        Tablero tablero;
        List<ElementoSolucion> solucion;

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

    private class ElementoSolucion {

        Isla inicio;
        Isla fin;
    }
}

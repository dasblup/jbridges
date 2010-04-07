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
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
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

    public Solucion solucionar(Tablero t) {

        listaIslas = obtenerIslas(t);

        calcularCandidatas();

        while (hayCandidatas()) {

            ponerPuentes(t);
            calcularCandidatas();
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

    private void calcularCandidatas() {

        Isla islaVecina;

        int puentesPosibles;
        int numeroVecinas;

        for (Isla i : listaIslas) {

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
            } else if (puentesPosibles - i.getN() == 1) {
                if (i.getN() / numeroVecinas - 1 > 2) {
                    candidatas1.add(i);
                }
            }
        }
    }

    private boolean hayCandidatas() {
        return !(candidatas0.isEmpty() && candidatas1.isEmpty());
    }

    private void ponerPuentes(Tablero t) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

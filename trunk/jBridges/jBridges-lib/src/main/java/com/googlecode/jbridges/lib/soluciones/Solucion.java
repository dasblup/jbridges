/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.soluciones;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pabloramix
 */
public class Solucion implements Cloneable {

    public List<ElementoSolucion> solucion;

    public int iteracionBactracking;

    public Solucion () {

        this.solucion = new LinkedList<ElementoSolucion>();
    }

    public Object clone () throws CloneNotSupportedException {

        Solucion clon;
        clon = (Solucion) super.clone();

        clon.solucion = new LinkedList<ElementoSolucion>();

        for (ElementoSolucion es : this.solucion) {
            clon.solucion.add((ElementoSolucion) es.clone());
        }

        clon.iteracionBactracking = this.iteracionBactracking;

        return clon;
    }

    public String toString () {

        StringBuilder sb = new StringBuilder();

        for (ElementoSolucion es : solucion) {
            sb.append(es);
        }

        return sb.toString();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.soluciones;

import com.googlecode.jbridges.lib.Isla;

/**
 *
 * @author pabloramix
 */
public class ElementoSolucion implements Cloneable {

        public Isla inicio;
        public Isla fin;

        public ElementoSolucion (Isla i, Isla f) {
            inicio = i;
            fin = f;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {

            ElementoSolucion clon = (ElementoSolucion) super.clone();

            clon.inicio = this.inicio;
            clon.fin = this.fin;

            return clon;
        }
}

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
        public boolean obligatorio;

        public ElementoSolucion (Isla i, Isla f) {
            inicio = i;
            fin = f;
            obligatorio = false;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {

            ElementoSolucion clon = (ElementoSolucion) super.clone();

            clon.inicio = this.inicio;
            clon.fin = this.fin;
            clon.obligatorio = this.obligatorio;

            return clon;
        }

        public boolean equals(Object obj){
            boolean iguales= false;
            ElementoSolucion el=(ElementoSolucion) obj;
            if((this.inicio.equals(el.inicio)&&this.fin.equals(el.fin))||(this.fin.equals(el.inicio)&&this.inicio.equals(el.fin))){
                iguales=true;

            }
            return iguales;
        }

        public String toString() {
            return "(" + inicio + " -> " + fin + ")";
        }
}

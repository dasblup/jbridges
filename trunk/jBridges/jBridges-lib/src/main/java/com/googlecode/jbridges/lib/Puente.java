/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

/**
 *
 * @author pabloramix
 */
public interface Puente extends ElementoTablero {

    /**
     *
     * @return tipo de puente SIMPLE/DOBLE
     */
    public TipoPuente getTipo();

    /**
     * Convierte un puente en doble. Si ya es doble lo deja igual.
     */
    public void setDoble();

    /**
     *
     * @return direccion del puente HORIZONTAL/VERTICAL
     */
    public Direccion getDireccion();
}

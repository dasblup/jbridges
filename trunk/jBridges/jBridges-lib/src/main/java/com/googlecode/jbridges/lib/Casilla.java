/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

/**
 *
 * @author pabloramix
 */
public abstract class Casilla {

    private Coordenadas c;

    public Casilla (Coordenadas c) {
        super();
        this.c = c;
    }
    
    public Coordenadas getCoordenadas() {
        return c.clone();
    }
}

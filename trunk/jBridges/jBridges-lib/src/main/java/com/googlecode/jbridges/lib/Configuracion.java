/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

/**
 *
 * @author pabloramix
 */
public class Configuracion {

    private static int ancho;
    private static int alto;

    public static int getAnchoTablero() {
        return Configuracion.ancho != 0 ? Configuracion.ancho : 10;
    }

    public static void setAnchoTablero(int ancho) {
        Configuracion.ancho = ancho;
    }

    public static int getAltoTablero() {
        return Configuracion.alto != 0 ? Configuracion.alto : 10;
    }

    public static void setAltoTablero(int alto) {
        Configuracion.alto = alto;
    }

}

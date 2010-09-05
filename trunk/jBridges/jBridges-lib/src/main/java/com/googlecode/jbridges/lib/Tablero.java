/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;

/**
 *
 * @author pabloramix
 */
public interface Tablero extends Cloneable {

    /**
     * Inicializa el tamaño de un tablero
     * @param i ancho del tablero
     * @param j alto del tablero
     * @throws TableroInicializadoException si se llama a éste método
     * más de una vez
     */
    public void setDimension(int i, int j);

    /**
     * Obtiene la altura del tablero
     * @return altura
     * @throws TableroNoInicializadoException si no se ha inicializado el
     * tablero antes de llamar a éste método
     */
    public int getAltura();

    /**
      Obtiene la anchura del tablero
     * @return anchura
     * @throws TableroNoInicializadoException si no se ha inicializado el
     * tablero antes de llamar a éste método
     */
    public int getAnchura();

    /**
     * Añade una nueva isla al tablero en las coordenadas i, j.
     * @param i
     * @param j
     * @throws TableroNoInicializadoException si se llama a éste método antes
     * de iniciar el tablero
     */
    public void setIsla(Coordenadas c) throws CasillaOcupadaException;

    /**
     * Borra la isla i
     * @param i una isla
     */
    public void borrarIsla(Isla i);
    /**
     * Obtiene el elemento del tablero de la posición i, j
     * @param i
     * @param j
     * @return Casilla
     * @throws TableroNoInicializadoException
     */
    public Casilla getCasilla(Coordenadas c);

    public Coordenadas getCoordenadas( int i, int j);

    public void borrarPuentes();

    public void borraCasilla(int f, int c);

    public Tablero copiaTablero();

    public void setPuente(Coordenadas c, Puente p);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

/**
 *
 * @author pabloramix
 */
public interface Tablero {

    /**
     * Inicializa el tama�o de un tablero
     * @param i ancho del tablero
     * @param j alto del tablero
     * @throws TableroInicializadoException si se llama a �ste m�todo
     * m�s de una vez
     */
    public void setDimension(int i, int j);

    /**
     * Obtiene la altura del tablero
     * @return altura
     * @throws TableroNoInicializadoException si no se ha inicializado el
     * tablero antes de llamar a �ste m�todo
     */
    public int getAltura();

    /**
      Obtiene la anchura del tablero
     * @return anchura
     * @throws TableroNoInicializadoException si no se ha inicializado el
     * tablero antes de llamar a �ste m�todo
     */
    public int getAnchura();

    /**
     * A�ade una nueva isla al tablero en las coordenadas i, j.
     * @param i
     * @param j
     * @throws TableroNoInicializadoException si se llama a �ste m�todo antes
     * de iniciar el tablero
     */
    public void setIsla(int i, int j);

    /**
     * Obtiene el elemento del tablero de la posici�n i, j
     * @param i
     * @param j
     * @return ElementoTablero
     * @throws TableroNoInicializadoException
     */
    public ElementoTablero getElementoTablero(int i, int j);
}

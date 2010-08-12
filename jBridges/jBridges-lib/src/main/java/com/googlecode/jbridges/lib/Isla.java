/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
/**
 *
 * @author pabloramix
 */
public interface Isla {


    /**
     *
     * @return el numero de dentro de la Isla
     */
    public int getN();

    /**
     *
     * Cambia numPuentes por el número pasado como parámetro
     */
    public void setN(int n);
    /**
     * 
     * @return las coordenadas de la isla en el tablero
     */

   public Coordenadas getCoord();
    /**
     *
     * @return el nœmero de puentes que tiene la isla actualmente
     */
    public int getPuentes();

    /**
     *
     * @param d Sentido para la que se quiere consultar el nœmero de puentes
     * @return nœmero de puentes en el sentido s
     */
    public int getPuentes(Sentido s);

    /**
     * Coloca un puente entre la Žsta isla e i
     * @param i Isla i
     */
    public void setPuente(Isla i) throws PuenteProhibidoException;
    public void setPuente(Isla i, boolean actualizarN) throws PuenteProhibidoException;

    /**
     * Borra un puente que existe entre Žsta isla e i
     * @param i
     * @throws PuenteProhibidoException
     */
    public void borrarPuente(Isla i) throws PuenteProhibidoException;
    public void borrarPuente(Isla i, boolean actualizarN) throws PuenteProhibidoException;

    /**
     *
     * @param s sentido
     * @return vecina a la isla actual en el sentido s
     * @throws IslaNoEncontradaException si no se encuentra ninguna isla vecina
     * en el sentido indicado
     */
    public Isla getVecina(Sentido s) throws IslaNoEncontradaException;
    //anterior
    public void setPuente(Sentido s) throws SentidoInvalidoException;
    public void setPuente(Sentido s, boolean actualizarN) throws SentidoInvalidoException;
}

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
     * @return el n�mero de puentes que tiene la isla actualmente
     */
    public int getPuentes();

    /**
     *
     * @param d Sentido para la que se quiere consultar el n�mero de puentes
     * @return n�mero de puentes en el sentido s
     */
    public int getPuentes(Sentido s);

    /**
     * Coloca un puente entre la �sta isla e i
     * @param i Isla i
     */
    public void setPuente(Isla i) throws PuenteProhibidoException;

    /**
     * Borra un puente que existe entre �sta isla e i
     * @param i
     * @throws PuenteProhibidoException
     */
    public void borrarPuente(Isla i) throws PuenteProhibidoException;

    /**
     *
     * @param s sentido
     * @return vecina a la isla actual en el sentido s
     * @throws IslaNoEncontradaException si no se encuentra ninguna isla vecina
     * en el sentido indicado
     */
    public Isla getVecina(Sentido s) throws IslaNoEncontradaException;
}

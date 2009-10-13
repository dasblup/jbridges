/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
/**
 *
 * @author pabloramix
 */
public interface Isla extends ElementoTablero {


    /**
     *
     * @return el numero de dentro de la Isla
     */
    public int getN();

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
    public void setPuente(Isla i);

    /**
     * Coloca un puente adicional en un sentido en el que ya existe un puente
     * @param s sentido
     * @throws SentidoInvalidoException si no existe un puente en el sentido
     * indicado
     */
    public void setPuente(Sentido s) throws SentidoInvalidoException;

    /**
     *
     * @param s sentido
     * @return vecina a la isla actual en el sentido s
     * @throws IslaNoEncontradaException si no se encuentra ninguna isla vecina
     * en el sentido indicado
     */
    public Isla getVecina(Sentido s) throws IslaNoEncontradaException;
}

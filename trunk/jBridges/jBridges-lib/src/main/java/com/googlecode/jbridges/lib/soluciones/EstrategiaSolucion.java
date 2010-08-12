/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.soluciones;

import com.googlecode.jbridges.lib.Tablero;
import java.util.List;

/**
 *
 * @author pabloramix
 */
public interface EstrategiaSolucion {

    List<Solucion> solucionar (Tablero t);

}

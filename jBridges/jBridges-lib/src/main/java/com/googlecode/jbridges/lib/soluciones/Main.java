/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.soluciones;

import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;

/**
 *
 * @author mdiazoli
 */
public class Main {
    public static void main(String args[]) {
        FabricaDeProblemas f=FabricaDeProblemas.getInstancia();
        Tablero p = f.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);
        System.out.println(p.toString());
    }
}

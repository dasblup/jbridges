/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.FabricaDeSoluciones;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
import java.util.List;

/**
 *
 * @author ptomeno
 */
public class Main {
    public static void main (String[] args) {
        FabricaDeProblemas fp;
        Tablero tablero;
        FabricaDeSoluciones fs;
        EstrategiaSolucion es;

        fp = FabricaDeProblemas.getInstancia();

        tablero = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);

        tablero.borrarPuentes();

        System.out.print(tablero);

        es = new EstrategiaBackTrackingBasica();

        List<Solucion> ss = es.solucionar(tablero);

        int i = 0;

        for (Solucion s : ss) {
            
            System.out.print("Solucion " + ++i + ": " + s);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.problemas;

import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.problemas.estrategias.EstrategiaAleatoriaBasica;
import com.googlecode.jbridges.lib.problemas.estrategias.EstartegiaDePrueba;

/**
 *
 * @author pabloramix
 */
public class FabricaDeProblemas {

    private static FabricaDeProblemas instancia;

    private static EstrategiaProblema estrategia;

    private FabricaDeProblemas() {
        super();
    }

    public static void setEstrategia (EstrategiaProblema s) {
        FabricaDeProblemas.estrategia = s;
    }

    public static FabricaDeProblemas getInstancia() {

        if (instancia == null) {
            instancia = new FabricaDeProblemas();
        }

        return instancia;
    }

    public Tablero obtenerProblema (Estrategias2D e) {
        
        EstrategiaProblema s;

        switch(e) {
            case ESTRATEGIA_ALEATORIA_BASICA:
                s = new EstrategiaAleatoriaBasica();
                break;
            case ESTRATEGIA_DE_PRUEBA:
                s = new EstartegiaDePrueba();
                break;
            default:
                throw new RuntimeException("Wawawawaaaaaaa!");
        }

        return s.crearTablero();
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.problemas.estrategias;

import com.googlecode.jbridges.lib.Configuracion;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.problemas.EstrategiaProblema;

/**
 *
 * @author ptomeno
 */
public class EstartegiaDePrueba implements EstrategiaProblema {

    public Tablero crearTablero() {

        Tablero t;
        Coordenadas coord1;
        Coordenadas coord2;
        Coordenadas coord3;
        Coordenadas coord4;
        Isla isla1;
        Isla isla2;
        Isla isla3;
        Isla isla4;

        t = new TableroArray();

        t.setDimension(Configuracion.getAltoTablero(),
                        Configuracion.getAnchoTablero());

        coord1 = t.getCoordenadas(2, 2);
        coord2 = t.getCoordenadas(2, 3);
        coord3 = t.getCoordenadas(4, 4);
        coord4 = t.getCoordenadas(4, 2);

        try {

            t.setIsla(coord1);
            isla1 = (Isla) t.getCasilla(coord1);
            t.setIsla(coord2);
            isla2 = (Isla) t.getCasilla(coord2);
            t.setIsla(coord3);
            isla3 = (Isla) t.getCasilla(coord3);
            t.setIsla(coord4);
            isla4 = (Isla) t.getCasilla(coord4);

            isla1.setPuente(isla2);
            isla1.setPuente(Sentido.ESTE);
            //isla2.setPuente(isla3);
            isla3.setPuente(isla4);
            isla3.setPuente(Sentido.OESTE);

        } catch (CasillaOcupadaException coe) {
        } catch (PuenteProhibidoException ppe) {
        } catch (SentidoInvalidoException sie) {}


        return t;
    }

}

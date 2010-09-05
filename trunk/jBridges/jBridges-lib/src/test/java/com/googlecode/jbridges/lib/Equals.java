/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ptomeno
 */
public class Equals {
    public static void main (String[] args) {

        TableroArray t1 = new TableroArray();
        t1.setDimension(10, 10);
        TableroArray t2 = new TableroArray();
        t2.setDimension(10, 10);
        try {
            t1.setIsla(t1.getCoordenadas(1, 1));
            t1.setIsla(t1.getCoordenadas(2, 2));
            t2.setIsla(t2.getCoordenadas(2, 2));
            t2.setIsla(t2.getCoordenadas(1, 1));


            Isla i1t1 = t1.getIsla(t1.getCoordenadas(1, 1));
            Isla i2t1 = t1.getIsla(t1.getCoordenadas(2, 2));
            Isla i1t2 = t2.getIsla(t2.getCoordenadas(2, 2));
            Isla i2t2 = t2.getIsla(t2.getCoordenadas(1, 1));

            ElementoSolucion es1 = new ElementoSolucion(i1t1, i2t1);
            ElementoSolucion es2 = new ElementoSolucion(i1t2, i2t2);

            boolean iguales=es1.equals(es2);
            System.out.println("iguales:"+ iguales);
         } catch (CasillaOcupadaException ex) {
            Logger.getLogger(Equals.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IslaNoEncontradaException ine) {}
    }
}

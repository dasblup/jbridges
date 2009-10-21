/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.problemas.estrategias;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Configuracion;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.problemas.EstrategiaProblema;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author pabloramix
 */
public class EstrategiaAleatoriaBasica implements EstrategiaProblema {

    private Random r;
    private Tablero t;

    public EstrategiaAleatoriaBasica() {
        r = new Random (System.currentTimeMillis());
    }

    public EstrategiaAleatoriaBasica(long semilla) {
        r = new Random (System.currentTimeMillis() ^ semilla);
    }

    public Tablero crearTablero() {

        Coordenadas coord;
        Casilla casilla;
        Isla i;
        Isla i2;

        Queue<Isla> colaIslas;

        colaIslas = new LinkedList<Isla>();

        t = new TableroArray();

        t.setDimension(Configuracion.altoTablero(),
                Configuracion.anchoTablero());

        coord = coordenadasAleatorias();

        t.setIsla(coord);

        colaIslas.add((Isla)t.getCasilla(coord));

        while(!colaIslas.isEmpty()) {

            i = colaIslas.poll();

            for (Sentido s : Sentido.values()) {

                // Ponemos la isla?
                if (r.nextBoolean()) {
                    coord = expandir(s, i);
                    t.setIsla(coord);
                    i2 = (Isla) t.getCasilla(coord);
                    //TO-DO
                    i.setPuente(i2);
                    //Comprobar que no peta
                    //Si peta, quitar isla
                    //Si no peta
                    //Puente doble?
                    if(r.nextBoolean()) {
                        try {
                            i.setPuente(s);
                        } catch (Exception ex){}
                    }
                    colaIslas.add(i2);
                }
            }
        }

        return t;
    }

    private Coordenadas coordenadasAleatorias() {

        int i;
        int j;

        i = r.nextInt(t.getAltura());
        j = r.nextInt(t.getAnchura());

        return t.getCoordenadas(i, j);
    }

    private Coordenadas expandir (Sentido s, Isla i) {
        throw new NotImplementedException();
    }

}

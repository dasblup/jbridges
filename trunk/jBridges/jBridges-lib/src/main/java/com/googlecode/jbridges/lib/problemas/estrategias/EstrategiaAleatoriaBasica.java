/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.problemas.estrategias;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Configuracion;
import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Coordenadas2D;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.problemas.EstrategiaProblema;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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
        Isla i2 = null;

        Queue<Isla> colaIslas;

        colaIslas = new LinkedList<Isla>();

        t = new TableroArray();

        t.setDimension(Configuracion.altoTablero(),
                Configuracion.anchoTablero());

        coord = coordenadasAleatorias();

        try {
            t.setIsla(coord);
        } catch (CasillaOcupadaException coe){
            System.err.print("IMPOSIBLE!!!");
            coe.printStackTrace(System.err);
        }

        colaIslas.add((Isla)t.getCasilla(coord));

        while(!colaIslas.isEmpty()) {

            i = colaIslas.poll();

            for (Sentido s : Sentido.values()) {

                // Ponemos la isla?
               // if (r.nextBoolean()) {
                    coord = expandir(s, i, t);
                    try {
                        t.setIsla(coord);
                        i2 = (Isla) t.getCasilla(coord);
                        i.setPuente(i2);
                        //Puente doble?
                        if(r.nextBoolean()) {
                            i.setPuente(s);
                        }
                        colaIslas.add(i2);
                    } catch (CasillaOcupadaException coe) {
                        System.err.print("ERROR Casilla Ocupada");
                    } catch (PuenteProhibidoException ppe) {
                        t.borrarIsla(i2);
                    } catch (SentidoInvalidoException sie) {
                        System.err.print("IMPOSIBLE!!!");
                    }
                //}
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

    private Coordenadas expandir (Sentido s, Isla i, Tablero t) {
        
        Coordenadas c;
        int numero;
        TableroArray ta;

        ta = (TableroArray) t;

        numero = 0;

        c = ((Casilla)i).getCoordenadas();

        /**
         * No queremos que las islas queden en casillas contiguas,
         * así que haremos que el número generado al azar esté entre 1 y
         * el límite de casillas hasta el final del tablero.
         *
         * Por eso le sumamos uno al empezar el bucle for, y le quitamos 1 al
         * limite superior de casillas del tablero.
         */
        switch (s) {
            case NORTE:
                numero = ((Coordenadas2D)c).getX() - 1;
                break;
            case ESTE:
                numero = ta.getAnchura() - 2 - ((Coordenadas2D)c).getY();
                break;
            case SUR:
                numero = ta.getAltura() - 2 - ((Coordenadas2D)c).getX();
                break;
            case OESTE:
                numero = ((Coordenadas2D)c).getY() - 1;
                break;
        }

        if (numero > 0) {
            numero = r.nextInt(numero);

            for (numero = numero + 2; numero > 0;numero--) {
                ta.avanzar(c, s);
            }
        }

        return c;

    }

}

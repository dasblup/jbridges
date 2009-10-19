/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.excepciones.TableroInicializadoException;

/**
 *
 * @author pabloramix
 */
public class TableroArray implements Tablero{

    Casilla[][] tablero;

    public void setDimension(int i, int j) {

        if (this.tablero == null) {

            this.tablero = new Casilla[i][j];

        } else {

            throw new TableroInicializadoException();
        }
    }

    public int getAltura() {
        return this.tablero.length;
    }

    public int getAnchura() {
        return this.tablero[0].length;
    }

    public void setIsla(Coordenadas c) {
        this.tablero[((CoordenadasArray)c).getX()]
                    [((CoordenadasArray)c).getY()] = new IslaArray(c);
        
    }

    public Isla getIsla(Coordenadas c) throws IslaNoEncontradaException {

        int i;
        int j;

        i = ((CoordenadasArray)c).getX();
        j = ((CoordenadasArray)c).getY();
        
        if (!(this.tablero[i][j] instanceof Isla)) {
            throw new IslaNoEncontradaException();
        }

        return (Isla) this.tablero[i][j];
    }

    private void avanzar(Coordenadas c, Sentido d) {

        CoordenadasArray coord;

        coord = (CoordenadasArray) c;

        switch (d) {
            case NORTE:
                coord.x--;
                break;
            case SUR:
                coord.x++;
                break;
            case OESTE:
                coord.y--;
                break;
            case ESTE:
                coord.y++;
                break;
        }
    }

    public Casilla getCasilla(Coordenadas c) {

        Casilla el;
        int i;
        int j;

        i = ((CoordenadasArray)c).getX();
        j = ((CoordenadasArray)c).getY();

        el = tablero[i][j];

        if (el == null) {
            el = new Agua(new CoordenadasArray(i,j));
        }

        return el;
    }

    class IslaArray extends Casilla implements Isla {

        private int numeroPuentes;

        private IslaArray(CoordenadasArray c) {
            super(c);
            this.setN(0);
        }

        public int getN() {
            return this.numeroPuentes;
        }

        private void setN(int n) {
            this.numeroPuentes = n;
        }

        public int getPuentes() {
            
            int puentes;

            puentes = 0;

            puentes += getPuentes(Sentido.NORTE);
            puentes += getPuentes(Sentido.SUR);
            puentes += getPuentes(Sentido.ESTE);
            puentes += getPuentes(Sentido.OESTE);

            return puentes;
        }

        public int getPuentes(Sentido s) {

            int puentes;
            CoordenadasArray coord;
            Casilla c;

            coord = (CoordenadasArray)this.getCoordenadas();
            puentes = 0;

            avanzar(coord, s);

            c = TableroArray.this.getCasilla(coord);

            if (c instanceof Puente) {
                puentes = ((Puente)c).getTipo() == TipoPuente.SIMPLE ? 1 : 2;
            }

            return puentes;
        }

        public void setPuente(Isla i) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void setPuente(Sentido s) throws SentidoInvalidoException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Isla getVecina(Sentido s) throws IslaNoEncontradaException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public boolean esVecina (Isla i) {

            boolean vecina;
            IslaArray isla;
            CoordenadasArray cThis;
            CoordenadasArray cIsla;
            Sentido s;
            IslaArray inicio;
            IslaArray fin;

            vecina = false;
            isla = (IslaArray)i;

            cThis = (CoordenadasArray) this.getCoordenadas();
            cIsla = (CoordenadasArray) isla.getCoordenadas();

            if ((cThis.getX() - cIsla.getX() == 0) ||
                (cThis.getY() - cIsla.getY() == 0)) {

                if (cThis.getX() - cIsla.getX() == 0) {

                    if (cThis.getY() - cIsla.getY() > 0) {

                    } else {

                    }
                }

                if (cThis.getY() - cIsla.getY() == 0) {

                    if (cThis.getX() - cIsla.getX() > 0) {

                    } else {

                    }
                }
            }

            return vecina;
        }



    }

    class PuenteArray extends Casilla implements Puente {

        private TipoPuente tipo;
        private Direccion d;

        private PuenteArray(Coordenadas c) {
           super(c);
           this.setTipo(TipoPuente.SIMPLE);
        }
        

        private void setTipo(TipoPuente t) {
            this.tipo = t;
        }

        public TipoPuente getTipo() {
            return this.tipo;
        }

        public void setDoble() {
            this.tipo = TipoPuente.DOBLE;
        }

        public Direccion getDireccion() {
            return this.d;
        }

        private void setDireccion (Direccion d) {
            this.d = d;
        }

    }

    class CoordenadasArray extends Coordenadas2D {

        private CoordenadasArray(Integer x, Integer y) {
            super(x,y);
        }
    }

}

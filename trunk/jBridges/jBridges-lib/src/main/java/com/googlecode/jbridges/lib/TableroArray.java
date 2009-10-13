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

    ElementoTablero[][] tablero;

    public void setDimension(int i, int j) {

        if (this.tablero == null) {

            this.tablero = new ElementoTablero[i][j];

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

    public void setIsla(int i, int j) {
        this.tablero[i][j] = new IslaArray();
        
    }

    public Isla getIsla(int i, int j) throws IslaNoEncontradaException {

        if (!(this.tablero[i][j] instanceof Isla)) {
            throw new IslaNoEncontradaException();
        }

        return (Isla) this.tablero[i][j];
    }

    private void avanzar(Integer x, Integer y, Sentido d) {

        switch (d) {
            case NORTE:
                x--;
                break;
            case SUR:
                x++;
                break;
            case OESTE:
                y--;
                break;
            case ESTE:
                y++;
                break;
        }
    }

    public ElementoTablero getElementoTablero(int i, int j) {

        ElementoTablero el;
        el = tablero[i][j];

        if (el == null) {
            el = new Agua(i,j);
        }

        return el;
    }

    class IslaArray implements Isla {

        private int numeroPuentes;
        private int x;
        private int y;

        private IslaArray() {
            super();
            this.numeroPuentes = 0;
        }

        private IslaArray(int x, int y) {
            this();
            this.setX(x);
            this.setY(y);
        }

        private void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return this.x;
        }

        private void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return this.y;
        }

        public int getN() {
            return this.numeroPuentes;
        }

        private void setN(int n) {
            this.numeroPuentes = n;
        }

        public int getPuentes() {
            ElementoTablero el;
            int numPuentes;

            numPuentes = 0;

            el = TableroArray.this.getElementoTablero(this.x, this.y - 1);

            if (el instanceof PuenteArray) {
                numPuentes = ((PuenteArray) el).getTipo() == TipoPuente.SIMPLE
                        ? numPuentes + 1 : numPuentes + 2;
            }

            el = TableroArray.this.getElementoTablero(this.x - 1, this.y);

            if (el instanceof PuenteArray) {
                numPuentes = ((PuenteArray) el).getTipo() == TipoPuente.SIMPLE
                        ? numPuentes + 1 : numPuentes + 2;
            }

            el = TableroArray.this.getElementoTablero(this.x, this.y + 1);

            if (el instanceof PuenteArray) {
                numPuentes = ((PuenteArray) el).getTipo() == TipoPuente.SIMPLE
                        ? numPuentes + 1 : numPuentes + 2;
            }

            el = TableroArray.this.getElementoTablero(this.x + 1, this.y);

            if (el instanceof PuenteArray) {
                numPuentes = ((PuenteArray) el).getTipo() == TipoPuente.SIMPLE
                        ? numPuentes + 1 : numPuentes + 2;
            }

            return numPuentes;
        }

        public int getPuentes(Sentido s) {
            throw new UnsupportedOperationException("Not supported yet.");
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



    }

    class PuenteArray implements Puente {

        private TipoPuente tipo;

        private int x;
        private int y;

        private PuenteArray() {
            super();
            this.setTipo(TipoPuente.SIMPLE);
        }

        private PuenteArray(int x, int y) {
            this();
            this.setX(x);
            this.setY(y);
        }

        private void setX(int x) {
            this.x = x;
        }

        public int getX() {
            return this.x;
        }

        private void setY(int y) {
            this.y = y;
        }

        public int getY() {
            return this.y;
        }
        

        private void setTipo(TipoPuente t) {
            this.tipo = t;
        }

        public TipoPuente getTipo() {
            return this.tipo;
        }

    }

}

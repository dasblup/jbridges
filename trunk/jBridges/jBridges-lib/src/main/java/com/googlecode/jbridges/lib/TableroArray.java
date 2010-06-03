/*
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.excepciones.CasillaOcupadaException;
import com.googlecode.jbridges.lib.excepciones.FueraDeRangoException;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.excepciones.TableroInicializadoException;

/**
 *
 * @author pabloramix
 */
public class TableroArray implements Tablero {

    Casilla[][] tablero;

    public void setDimension(int i, int j){
        if (this.tablero == null){
            this.tablero=new Casilla[i][j];
        }else{
            throw new TableroInicializadoException();
        }
    }

    public int getAltura() {
        return this.tablero.length;
    }

    public int getAnchura() {
        return this.tablero[0].length;
    }

    public void setIsla(Coordenadas c) throws CasillaOcupadaException {

        Casilla casilla;
        casilla=getCasilla(c);

        if(casilla instanceof Agua){
            this.tablero[((CoordenadasArray)c).getX()]
                    [((CoordenadasArray)c).getY()]=new IslaArray((CoordenadasArray)c);
        }else{
            throw new CasillaOcupadaException();
        }
    }

    public Object clone () throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void borrarIsla(Isla i) {

        CoordenadasArray ca;
        ca=(CoordenadasArray)((IslaArray)i).getCoordenadas();

        this.tablero[ca.getX()][ca.getY()]=null;
        
    }

    public Isla getIsla(Coordenadas c) throws IslaNoEncontradaException {

        int i;
        int j;

        i=((CoordenadasArray)c).getX();
        j=((CoordenadasArray)c).getY();

        if(!(this.tablero[i][j] instanceof Isla)){
            throw new IslaNoEncontradaException();
        }else{
            return (Isla)this.tablero[i][j];
        }
    }

    public Coordenadas getCoordenadas (int i, int j) {

       Casilla inutil;
       inutil=tablero[i][j];
       
       return new CoordenadasArray(i,j);

    }

    public void avanzar(Coordenadas c, Sentido d) {

        CoordenadasArray coord;
        coord=(CoordenadasArray)c;
        boolean fuera;
        fuera=false;

        switch (d){
            case NORTE:
                if(coord.x==0){
                    fuera=true;
                }else{
                    coord.x--;
                }
                break;
            case SUR:
                if(coord.x==this.getAltura()-1){
                    fuera=true;
                }else{
                    coord.x++;
                }
                break;
            case OESTE:
                if(coord.y==0){
                    fuera=true;
                }else{
                    coord.y--;
                }
                break;
            case ESTE:
                if(coord.y==this.getAnchura()-1){
                    fuera=true;
                }else{
                    coord.y++;
                }
                break;   
        }

        if (fuera){
            throw new FueraDeRangoException();
        }
        
    }

    public String toString() {

        String res;
        Casilla c;
        res="";

        for(int i=0;i<this.tablero.length;i++){
            for(int j=0;j<this.tablero[0].length;j++){

                c=this.tablero[i][j];

                if(c==null){
                    res+="";
                }else if(c instanceof IslaArray){
                    IslaArray isla;
                    isla=(IslaArray)c;

                    res+=isla.getN();
                }else if(c instanceof PuenteArray){
                    PuenteArray puente;
                    puente=(PuenteArray)c;

                    if(puente.getTipo()==TipoPuente.SIMPLE){
                        if(puente.getDireccion()==Direccion.HORIZONTAL){
                            res+="-";
                        }else{
                            res+="|";
                        }
                    }else{
                        res+="#";
                    }
                }
            }
            res+="\n";
        }
        return res;
    }

    public Casilla getCasilla(Coordenadas c) {

        Casilla el;
        int i; 
        int j;
        
        i=((CoordenadasArray)c).getX();
        j=((CoordenadasArray)c).getY();

        el=this.tablero[i][j];       

        if(el==null){
            el=new Agua(new CoordenadasArray(i,j));
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

        public void setPuente(Isla i) throws PuenteProhibidoException {
            
            IslaArray isla;
            CoordenadasArray coordenadasLaOtraIsla;
            CoordenadasArray coordenadasEstaIsla;
            Direccion d;
            Sentido s;
            PuenteArray puente;
            
            isla=(IslaArray)i;
            
            coordenadasLaOtraIsla=(CoordenadasArray)isla.getCoordenadas();
            coordenadasEstaIsla=(CoordenadasArray)this.getCoordenadas();
            
            if(coordenadasEstaIsla.getX().equals(coordenadasLaOtraIsla.getX())){
                d=Direccion.HORIZONTAL;
                if(coordenadasEstaIsla.getY()-coordenadasLaOtraIsla.getY()<0){
                    s=Sentido.ESTE;
                }else if(coordenadasEstaIsla.getY()-coordenadasLaOtraIsla.getY()>0){
                    s=Sentido.OESTE;
                }else{
                    throw new RuntimeException("La misma casilla!! Arregla esto");
                }
            }else if(coordenadasLaOtraIsla.getY().equals(coordenadasEstaIsla.getY())){
                d=Direccion.VERTICAL;
                if(coordenadasEstaIsla.getX()-coordenadasLaOtraIsla.getX()<0){
                    s=Sentido.NORTE; //No esta al reves?
                }else if(coordenadasEstaIsla.getX()-coordenadasLaOtraIsla.getX()>0){
                    s=Sentido.SUR; //No esta al reves?
                }else{
                    throw new RuntimeException("La misma casilla!! Arregla esto");
                }
            }else{
                throw new RuntimeException("Arregla esto!!!");
            }

            if (this.getPuentes(s) >= 2 ||
                this.getPuentes() >= this.getN() ||
                i.getPuentes(invertirSentido(s)) >= 2 ||
                i.getPuentes() >= i.getN()) {

                throw new PuenteProhibidoException();
            }

            try {

                hacerPuenteDoble(s);

            } catch (SentidoInvalidoException see) {

                TableroArray.this.avanzar(coordenadasEstaIsla, s);
                while(!coordenadasEstaIsla.equals(coordenadasLaOtraIsla)){
                    if(!(getCasilla(coordenadasEstaIsla) instanceof Agua)){
                        throw new PuenteProhibidoException();
                    }
                    TableroArray.this.avanzar(coordenadasEstaIsla, s);
                }

                coordenadasEstaIsla=(CoordenadasArray)this.getCoordenadas();

                TableroArray.this.avanzar(coordenadasEstaIsla, s);
                while(!coordenadasEstaIsla.equals(coordenadasLaOtraIsla)){
                    puente=new PuenteArray(coordenadasEstaIsla);
                    puente.setDireccion(d);
                    TableroArray.this.tablero[coordenadasEstaIsla.getX()]
                            [coordenadasEstaIsla.getX()]=puente;
                    TableroArray.this.avanzar(coordenadasEstaIsla, s);
                }
            }

            this.numeroPuentes++;
            isla.numeroPuentes++;
        }

        /*
         * Si existe un puente en el sentido s, lo hace doble.
         **/
        private void hacerPuenteDoble(Sentido s) throws SentidoInvalidoException {

            CoordenadasArray coordenadasEstaIsla;
            Casilla c;
            PuenteArray puente;

            coordenadasEstaIsla = (CoordenadasArray) this.getCoordenadas();
           
            TableroArray.this.avanzar(coordenadasEstaIsla, s);
            c = TableroArray.this.getCasilla(coordenadasEstaIsla);
                    
            while (c instanceof PuenteArray) {
                puente = (PuenteArray)c;

                if (puente.getDireccion() == Direccion.HORIZONTAL) {
                    if (s == Sentido.NORTE || s == Sentido.SUR) {
                        throw new SentidoInvalidoException();
                    }
                } else {
                    if (s == Sentido.ESTE || s == Sentido.OESTE) {
                        throw new SentidoInvalidoException();
                    }
                }

                puente.setDoble();
                TableroArray.this.avanzar(coordenadasEstaIsla, s);
                c = TableroArray.this.getCasilla(coordenadasEstaIsla);
            }

            /**
             * Se hace esta comprobación por si el primer vecino no es un puente
             */
            if (c instanceof IslaArray) {
                this.numeroPuentes++;
                ((IslaArray)c).numeroPuentes++;
            } else {
                throw new SentidoInvalidoException();
            }
        }

        public Isla getVecina(Sentido s) throws IslaNoEncontradaException {
            CoordenadasArray coordenadasEstaIsla;
            Casilla c;
                        
            coordenadasEstaIsla=(CoordenadasArray)this.getCoordenadas();
            
            TableroArray.this.avanzar(coordenadasEstaIsla, s);
            c=TableroArray.this.getCasilla(coordenadasEstaIsla);
            
            while(c instanceof Agua){
                
                TableroArray.this.avanzar(coordenadasEstaIsla, s);
                c=TableroArray.this.getCasilla(coordenadasEstaIsla);
            }
            
            if (!(c instanceof IslaArray)){
                throw new IslaNoEncontradaException();
            }
                
            return (Isla)c;
 
            
        }

        public boolean esVecina (Isla i) {

            boolean vecina;
            IslaArray isla;
            CoordenadasArray cThis;
            CoordenadasArray cIsla;
            Sentido s=Sentido.ESTE;
            IslaArray islaVecina;
            islaVecina=null;

            vecina = false;
            isla = (IslaArray)i;

            cThis = (CoordenadasArray) this.getCoordenadas();
            cIsla = (CoordenadasArray) isla.getCoordenadas();
            
            if((cThis.getX() - cIsla.getX() == 0) ||
                    (cThis.getY() - cIsla.getY() == 0)){
                //estan en la misma fila
                if (cThis.getX() - cIsla.getX() == 0){
                    if (cThis.getY() - cIsla.getY() > 0) {
                        s=Sentido.OESTE;
                    } else {
                        s=Sentido.ESTE;
                    }
                }
                //estan en la misma columna
                else if (cThis.getY() - cIsla.getY() == 0) {
                    if (cThis.getX() - cIsla.getX() > 0) {
                        s=Sentido.NORTE;
                    } else {
                        s=Sentido.SUR;
                    }
                }

                try{
                    islaVecina=(IslaArray)this.getVecina(s);
                
                    if(islaVecina.equals(isla)){
                         vecina=true;
                    }
                }catch (IslaNoEncontradaException inee){
                    vecina=false;
                }
            }


            return vecina;
        }

        private Sentido invertirSentido(Sentido s) {

            Sentido s1;

            switch (s) {
                case NORTE:
                    s1 = Sentido.SUR;
                    break;
                case SUR:
                    s1 = Sentido.NORTE;
                    break;
                case ESTE:
                    s1 = Sentido.OESTE;
                    break;
                case OESTE:
                    s1 = Sentido.ESTE;
                    break;
                default:
                    throw new RuntimeException();
            }

            return s1;
        }

        public void borrarPuente(Isla i) throws PuenteProhibidoException {
            throw new UnsupportedOperationException("Not supported yet.");
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

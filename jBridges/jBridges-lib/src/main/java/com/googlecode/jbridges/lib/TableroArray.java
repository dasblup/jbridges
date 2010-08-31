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
import java.io.Serializable;

/**
 *
 * @author pabloramix
 */
public class TableroArray implements Tablero, Serializable {

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
        boolean adyacente;
        
        adyacente = false;
        Sentido s;
       for (int i = 0; i < Sentido.values().length && !adyacente; i++){
            s = Sentido.values()[i];
            try{
                TableroArray.this.avanzar(c, s);
            } catch (FueraDeRangoException fre) {}
            
            adyacente = getCasilla(c) instanceof Isla;
        }

        if(casilla instanceof Agua && !adyacente){
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

                if(c instanceof IslaArray){
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
                        if(puente.getDireccion()==Direccion.HORIZONTAL){
                            res+="=";
                        }else{
                            res+="#";
                        }
                    }
                } else {
                    res+=" ";
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

    public void borrarPuentes() {

        for(int i=0;i<this.tablero.length;i++){
            for(int j=0;j<this.tablero[0].length;j++){

                if(this.tablero[i][j] instanceof PuenteArray){

                    this.tablero[i][j] = null;
                            
                }
            }
        }
    }

    class IslaArray extends Casilla implements Isla, Serializable {

        private int numeroPuentes;
        private int x;
        private int y;

        private IslaArray(CoordenadasArray c) {
            super(c);
            this.setN(0);
            this.x=c.getX();
            this.y=c.getY();
        }

        public int getN() {
            return this.numeroPuentes;
        }

        public void setN(int n) {
            this.numeroPuentes = n;
        }

        public Coordenadas getCoord(){
            Coordenadas coord=null;
            IslaArray isla=(IslaArray)this;
            ((Coordenadas2D)coord).x=isla.x;
            ((Coordenadas2D)coord).y=isla.y;

            return coord;
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

            try
            {
                avanzar(coord, s);
                c = TableroArray.this.getCasilla(coord);

                if (c instanceof Puente) {
                    puentes = ((Puente)c).getTipo() == TipoPuente.SIMPLE ? 1 : 2;
                }
            } catch (FueraDeRangoException fdr) {}

            return puentes;
        }

        public void setPuente(Isla i, boolean actualizarN) throws PuenteProhibidoException {
             IslaArray isla; 
             CoordenadasArray coordenadasLaOtraIsla; 
             CoordenadasArray coordenadasEstaIsla; 
             Sentido s; 
             Direccion d; 
             PuenteArray puente; 

             if (!actualizarN) {
                 if ((i.getPuentes() >= i.getN()) ||
                      this.getPuentes() >= this.getN()) {
                     throw new PuenteProhibidoException();
                 }
             }

             isla = (IslaArray) i; 
             coordenadasLaOtraIsla = (CoordenadasArray) isla.getCoordenadas(); 
             coordenadasEstaIsla = (CoordenadasArray) this.getCoordenadas(); 

             if (coordenadasEstaIsla.getX().equals(coordenadasLaOtraIsla.getX())) { 
                 d = Direccion.HORIZONTAL; 
                 if ((coordenadasEstaIsla.getY() - coordenadasLaOtraIsla.getY()) < 0) { 
                     s = Sentido.ESTE; 
                 } else if ((coordenadasEstaIsla.getY() - coordenadasLaOtraIsla.getY()) > 0) { 
                     s = Sentido.OESTE; 
                 } else { 
                     throw new RuntimeException("La misma Casilla!! - ARREGLA ESTO!!!"); 
                 } 
             } else if (coordenadasEstaIsla.getY().equals(coordenadasLaOtraIsla.getY())) { 
                 d = Direccion.VERTICAL; 
                 if ((coordenadasEstaIsla.getX() - coordenadasLaOtraIsla.getX()) < 0) { 
                     s = Sentido.SUR; 
                 } else if ((coordenadasEstaIsla.getX() - coordenadasLaOtraIsla.getX()) > 0) { 
                     s = Sentido.NORTE; 
                 } else { 
                     throw new RuntimeException("La misma Casilla!! - ARREGLA ESTO!!!"); 
                 } 
             } else { 
                 throw new RuntimeException("ARREGLA ESTO!!!"); 
             } 
  
             TableroArray.this.avanzar(coordenadasEstaIsla, s);

             if (getCasilla(coordenadasEstaIsla) instanceof Agua) {
                 while (!coordenadasEstaIsla.equals(coordenadasLaOtraIsla)) {
                     if (!(getCasilla(coordenadasEstaIsla) instanceof Agua)) {
                         throw new PuenteProhibidoException();
                     }
                     TableroArray.this.avanzar(coordenadasEstaIsla, s);
                 }

                 coordenadasEstaIsla = (CoordenadasArray) this.getCoordenadas();

                 TableroArray.this.avanzar(coordenadasEstaIsla, s);
                 while (!coordenadasEstaIsla.equals(coordenadasLaOtraIsla)) {
                     puente = new PuenteArray(coordenadasEstaIsla);
                     puente.setDireccion(d);
                     TableroArray.this.tablero[coordenadasEstaIsla.getX()]
                                              [coordenadasEstaIsla.getY()] = puente;
                     TableroArray.this.avanzar(coordenadasEstaIsla, s);
                 }
             } else if (getCasilla(coordenadasEstaIsla) instanceof Puente) {
                 while (!coordenadasEstaIsla.equals(coordenadasLaOtraIsla)) {
                     if (!(getCasilla(coordenadasEstaIsla) instanceof Puente)) {
                         throw new PuenteProhibidoException();
                     } else if (((Puente)getCasilla(coordenadasEstaIsla)).getDireccion() != d) {
                         throw new PuenteProhibidoException();
                     }
                     TableroArray.this.avanzar(coordenadasEstaIsla, s);
                 }
                 try {
                    setPuente(s, false);
                 } catch (SentidoInvalidoException sie) {}

             }

             if (actualizarN) {
                this.numeroPuentes++;
                ((IslaArray)i).numeroPuentes++;
             }
         }  

         public void setPuente(Isla i) throws PuenteProhibidoException {

             setPuente(i, true);
         }

         public void setPuente(Sentido s, boolean actualizarN) throws SentidoInvalidoException {

             CoordenadasArray coordenadasEstaIsla;
             Casilla c;
             PuenteArray puente;

             if (!actualizarN) {
                 try {
                     if ((getVecina(s).getPuentes() >= getVecina(s).getN()) ||
                          this.getPuentes() >= this.getN()) {
                         throw new SentidoInvalidoException();
                     }
                 } catch (IslaNoEncontradaException inee) {
                    throw new SentidoInvalidoException();
                 }
             }

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
                 if (actualizarN) {
                    this.numeroPuentes++;
                    ((IslaArray)c).numeroPuentes++;
                 }
             } else {
                 throw new SentidoInvalidoException();
             }
         }

        public void setPuente(Sentido s) throws SentidoInvalidoException {

            setPuente(s, true);
        }

        /*
         * Si existe un puente en el sentido s, lo hace doble.
         **/
        private void hacerPuenteDoble(Sentido s, boolean actualizarN) throws SentidoInvalidoException {

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
                if (actualizarN) {
                    this.numeroPuentes++;
                    ((IslaArray)c).numeroPuentes++;
                }
            } 
            else {
                throw new SentidoInvalidoException();
            }
        }

        private void hacerPuenteDoble(Sentido s) throws SentidoInvalidoException {
            hacerPuenteDoble(s, true);
        }

        public Isla getVecina(Sentido s) throws IslaNoEncontradaException {
            CoordenadasArray coordenadasEstaIsla;
            Casilla c;
            
            Direccion d;
            
            switch (s){
                case NORTE :
                case SUR :
                    d = Direccion.VERTICAL;
                    break;
                default:
                    d = Direccion.HORIZONTAL;
            }

            coordenadasEstaIsla=(CoordenadasArray)this.getCoordenadas();

            try {
                TableroArray.this.avanzar(coordenadasEstaIsla, s);
                c=TableroArray.this.getCasilla(coordenadasEstaIsla);
                
                if (c instanceof Agua) {
                    while(c instanceof Agua){
                        TableroArray.this.avanzar(coordenadasEstaIsla, s);
                        c=TableroArray.this.getCasilla(coordenadasEstaIsla);
                    }
                } else if (c instanceof Puente) {
                    if (((Puente)c).getDireccion() == d) {
                        while(c instanceof Puente){
                            TableroArray.this.avanzar(coordenadasEstaIsla, s);
                            c=TableroArray.this.getCasilla(coordenadasEstaIsla);
                        }
                    }
                }

                if (!(c instanceof IslaArray)){
                    throw new IslaNoEncontradaException();
                }

            } catch (FueraDeRangoException fdre) {
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

        public void borrarPuente(Isla i, boolean actualizarN) throws PuenteProhibidoException {
             IslaArray isla;
             CoordenadasArray coordenadasLaOtraIsla;
             CoordenadasArray coordenadasEstaIsla;
             Sentido s;
             Direccion d;
             PuenteArray puente;

             isla = (IslaArray) i;
             coordenadasLaOtraIsla = (CoordenadasArray) isla.getCoordenadas();
             coordenadasEstaIsla = (CoordenadasArray) this.getCoordenadas();

             if (coordenadasEstaIsla.getX().equals(coordenadasLaOtraIsla.getX())) {
                 d = Direccion.HORIZONTAL;
                 if ((coordenadasEstaIsla.getY() - coordenadasLaOtraIsla.getY()) < 0) {
                     s = Sentido.ESTE;
                 } else if ((coordenadasEstaIsla.getY() - coordenadasLaOtraIsla.getY()) > 0) {
                     s = Sentido.OESTE;
                 } else {
                     throw new RuntimeException("La misma Casilla!! - ARREGLA ESTO!!!");
                 }
             } else if (coordenadasEstaIsla.getY().equals(coordenadasLaOtraIsla.getY())) {
                 d = Direccion.VERTICAL;
                 if ((coordenadasEstaIsla.getX() - coordenadasLaOtraIsla.getX()) < 0) {
                     s = Sentido.SUR;
                 } else if ((coordenadasEstaIsla.getX() - coordenadasLaOtraIsla.getX()) > 0) {
                     s = Sentido.NORTE;
                 } else {
                     throw new RuntimeException("La misma Casilla!! - ARREGLA ESTO!!!");
                 }
             } else {
                 throw new RuntimeException("ARREGLA ESTO!!!");
             }

             coordenadasEstaIsla = (CoordenadasArray) this.getCoordenadas();

             TableroArray.this.avanzar(coordenadasEstaIsla, s);
             while (!coordenadasEstaIsla.equals(coordenadasLaOtraIsla)) {
                if (!(getCasilla(coordenadasEstaIsla) instanceof PuenteArray)) {
                     throw new PuenteProhibidoException();
                 } else {
                    puente = (PuenteArray) getCasilla(coordenadasEstaIsla);
                    if (puente.tipo == TipoPuente.DOBLE) {
                        puente.setTipo(TipoPuente.SIMPLE);
                    } else {
                        TableroArray.this.tablero[coordenadasEstaIsla.getX()]
                                          [coordenadasEstaIsla.getY()] = null;
                    }
                 }
                TableroArray.this.avanzar(coordenadasEstaIsla, s);
             }

             if (actualizarN) {
                this.numeroPuentes--;
                isla.numeroPuentes--;
             }
        }

        public void borrarPuente(Isla i) throws PuenteProhibidoException {
            borrarPuente(i, true);
        }

        public boolean equals(Object obj){
            boolean iguales=false;
            IslaArray i=(IslaArray)obj;
            CoordenadasArray cThis=(CoordenadasArray)this.getCoordenadas();
            CoordenadasArray cOtra=(CoordenadasArray)i.getCoordenadas();
            if(cThis.getX().intValue()==cOtra.getX().intValue() && cThis.getY().intValue()==cOtra.getY().intValue()){
                iguales=true;
            }
            return iguales;
        }

        public String toString () {
           return ((Coordenadas2D) this.getCoordenadas()).getX() + "," + ((Coordenadas2D) this.getCoordenadas()).getY();
        }

        public int compareTo(Object arg0) {
            return equals(arg0) ? 0 : 1;
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

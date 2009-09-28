package com.googlecode.jbridges.lib.problemas;
import java.util.*;
public class Islas{
	
	private Posicion posicion;//posicion  en el tablero
	
	private int tipo;//1 si es isla, 0 si es agua
	
	private ValorIsla valor;
	
	private Set conexo;

        public Islas() {
            super();
        }

	public Islas(int tipo,int x,int y){
		super();
		this.tipo=tipo;
		this.posicion.setPosi(x);
		this.posicion.setPosj(y);
	}
	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion pos) {
		this.posicion =pos ;
	}

	public ValorIsla getValor() {
		return valor;
	}

	public void setValor(ValorIsla valor) {
		this.valor = valor;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public Set getConexo() {
		return conexo;
	}
	public void setConexo(Set conexo) {
		this.conexo = conexo;
	}
    
	public boolean equals(Object obj) {
	        boolean salida = false;
	        
	         if (obj != null && (obj.getClass().equals(this.getClass()))) {
	            Islas isla = (Islas)obj;
	            salida  = (this.getPosicion().getPosi() == isla.getPosicion().getPosi()) && 
	            			(this.getPosicion().getPosj() == isla.getPosicion().getPosj());
	        }

	        return salida;
	}
	
	   public Object clone() throws CloneNotSupportedException {
	    	Islas clon = (Islas) super.clone();
	    	
	    	clon.getPosicion().setPosi(this.getPosicion().getPosi());
	    	clon.getPosicion().setPosj(this.getPosicion().getPosj());
	    	
	    	clon.setTipo(this.getTipo());
	    	
	    	clon.getValor().setN(this.getValor().getN());
			clon.getValor().getNorte().setPosibles(this.getValor().getNorte().getPosibles());
			clon.getValor().getNorte().setExistentes(this.getValor().getNorte().getExistentes());
			clon.getValor().getSur().setPosibles(this.getValor().getSur().getPosibles());
			clon.getValor().getSur().setExistentes(this.getValor().getSur().getExistentes());
			clon.getValor().getOeste().setPosibles(this.getValor().getOeste().getPosibles());
			clon.getValor().getOeste().setExistentes(this.getValor().getOeste().getExistentes());
			clon.getValor().getEste().setPosibles(this.getValor().getEste().getPosibles());
			clon.getValor().getEste().setExistentes(this.getValor().getEste().getExistentes());
	    	
	    	return clon;
	    }
	

	
	
}

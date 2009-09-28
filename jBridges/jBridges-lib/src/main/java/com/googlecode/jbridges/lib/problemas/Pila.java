package com.googlecode.jbridges.lib.problemas;

import java.util.*;

public class Pila {
//{{x,y}, {n,arriba, abajo, izquierda ,derecha}, M, desconocidas}  
	private Islas isla;
	
	List m;
	
	List deconocidas;
	
	public Pila(){
		super();
	}
	
	public Pila(Islas i, List m, List d){
		this.isla=i;
		this.m=m;
		this.deconocidas=d;
	}

	public List getDeconocidas() {
		return deconocidas;
	}

	public void setDeconocidas(List deconocidas) {
		this.deconocidas = deconocidas;
	}

	public Islas getIsla() {
		return isla;
	}

	public void setIsla(Islas isla) {
		this.isla = isla;
	}

	public List getM() {
		return m;
	}

	public void setM(List m) {
		this.m = m;
	}
	
	
	
	
}

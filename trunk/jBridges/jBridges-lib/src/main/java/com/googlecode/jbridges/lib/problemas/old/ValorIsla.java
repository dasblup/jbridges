package com.googlecode.jbridges.lib.problemas.old;

public class ValorIsla {

	int n; //numero de valor de la isla
	
	Capacidad norte;
	
	Capacidad sur;
	
	Capacidad este;
	
	Capacidad oeste;
	
	public ValorIsla(){
		super();
	}

	public Capacidad getEste() {
		return este;
	}

	public void setEste(Capacidad este) {
		this.este = este;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Capacidad getNorte() {
		return norte;
	}

	public void setNorte(Capacidad norte) {
		this.norte = norte;
	}

	public Capacidad getOeste() {
		return oeste;
	}

	public void setOeste(Capacidad oeste) {
		this.oeste = oeste;
	}

	public Capacidad getSur() {
		return sur;
	}

	public void setSur(Capacidad sur) {
		this.sur = sur;
	}
	
	
}

package com.googlecode.jbridges.lib.problemas.old;

public class Jugador {

	String jugador;
	int tiempo;
	
	public Jugador(){
		super();
	}
	
	public Jugador(String jugador, int tiempo){
		super();
		this.jugador=jugador;
		this.tiempo=tiempo;
		
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	
	
}

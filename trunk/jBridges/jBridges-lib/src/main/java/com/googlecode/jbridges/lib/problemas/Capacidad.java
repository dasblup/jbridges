package com.googlecode.jbridges.lib.problemas;

public class Capacidad {
	
	private int posibles;//indica el n�mero de puentes que pueden salir en esa direcci�n
	private int existentes;//indica el n�mero de puentes que de hecho ya salen de la isla situada en esa posici�n en la direcci�n que indica el nombre de la variable
	
	
	public int getExistentes() {
		return existentes;
	}
	public void setExistentes(int existentes) {
		this.existentes = existentes;
	}
	public int getPosibles() {
		return posibles;
	}
	public void setPosibles(int posibles) {
		this.posibles = posibles;
	}
	
	//Al final la suma de �existentes� debe coincidir con el n�mero inicial de cada isla

	
	
}

package com.googlecode.jbridges.lib.problemas.old;

import com.googlecode.jbridges.lib.problemas.old.Posicion;

public class OrdenPuentes {

	private Posicion iOrigen;
	
	private Posicion iDestino;
	
	private String dirOrigen;
	
	private String dirDestino;
	
	public OrdenPuentes(){
		super();
	}

	public String getDirDestino() {
		return dirDestino;
	}

	public void setDirDestino(String dirDestino) {
		this.dirDestino = dirDestino;
	}

	public String getDirOrigen() {
		return dirOrigen;
	}

	public void setDirOrigen(String dirOrigen) {
		this.dirOrigen = dirOrigen;
	}

	public Posicion getIDestino() {
		return iDestino;
	}

	public void setIDestino(Posicion destino) {
		iDestino = destino;
	}

	public Posicion getIOrigen() {
		return iOrigen;
	}

	public void setIOrigen(Posicion origen) {
		iOrigen = origen;
	}

	public boolean equals(Object obj) {
        boolean salida = false;
        
         if (obj != null && (obj.getClass().equals(this.getClass()))) {
            OrdenPuentes orden = (OrdenPuentes)obj;
            salida  = (this.getIOrigen() == orden.getIOrigen()) && 
            			(this.getIDestino() == orden.getIDestino()) && (this.getDirOrigen() == orden.getDirOrigen())
            			 && (this.getDirDestino() == orden.getDirDestino());
        }

        return salida;
}
	
	
}

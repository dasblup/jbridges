package com.googlecode.jbridges.lib.problemas.old;

import com.googlecode.jbridges.lib.problemas.*;

public class Arista {

	Islas iOrigen, iFin;
	
	public Arista (Islas iOrigen, Islas IFin){
		
		this.iOrigen=iOrigen;
		this.iFin=IFin;
	}

	public Islas getIFin() {
		return iFin;
	}

	public void setIFin(Islas fin) {
		iFin = fin;
	}

	public Islas getIOrigen() {
		return iOrigen;
	}

	public void setIOrigen(Islas origen) {
		iOrigen = origen;
	}
	
	
}

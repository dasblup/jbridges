package com.googlecode.jbridges.lib.problemas;



public class Hashiwokakero {

	public static Tablero Generador(int tama�o){
	
		
		Tablero t=new Tablero(); 
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				Islas isla=new Islas();
				//t.l.add(isla.getPosicion());
				t.matriz[i][j]=isla;
				//t.todasIslas.add(isla);
			}
		}
		return t;
	}
}

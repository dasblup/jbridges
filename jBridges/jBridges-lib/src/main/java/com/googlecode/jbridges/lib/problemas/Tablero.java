package com.googlecode.jbridges.lib.problemas;


public class Tablero {

	public Islas[][] matriz;
	
	
	public Tablero(){
		matriz=new Islas[20][20];
		
	}
	
	public Tablero(int numFilas, int numColumnas){
		if (numFilas==0 || numColumnas==0)
			throw new ArrayIndexOutOfBoundsException("Una matriz no puede tener dimenssion 0");
		matriz = new Islas[numFilas][numColumnas];
		//falta generar islas. la siguiente instruccion habria que cambiarla, para poner las casillas donde hayan islas con un atributo que se llame contieneIsla a true y tb voy a poner las demas con un atributo con pintadas a falso
	}
	
		
	public int getNumFilas(){
		return matriz.length;
	}
	
	public int getNumColumnas(){
		return matriz[0].length;
	}
	

	/*
	public Islas[] getColumna(int j) {
		if(j<0 || j>=getNumColumnas())
			throw new ArrayIndexOutOfBoundsException("N�mero de columna fuera de rango");
		Islas[] columna = new Islas[getNumFilas()];
		for(int i=0;i<getNumFilas();i++)
			columna[i] = matriz[i][j];
		return columna;
	}*/

	/*
	public Islas[] getFila(int i) {
		if(i<0 || i>=getNumColumnas())
			throw new ArrayIndexOutOfBoundsException("N�mero de columna fuera de rango");
		return matriz[i];
	}*/
	
	
	public Islas getElemento(int f, int c) {
		if(f<0 || c<0 || f>=getNumFilas() || c>=getNumColumnas())
			throw new ArrayIndexOutOfBoundsException("Posici�n fuera de rango");
		return matriz[f][c];
	}
	
	
	public void setElementoIsla(int f, int c){
		if(f<0 || c<0 || f>=getNumFilas() || c>=getNumColumnas())
			throw new ArrayIndexOutOfBoundsException("Posici�n fuera de rango");
		
		matriz[f][c]=new Islas(1,f,c);
	}
	
	public void setElementoAgua(int f, int c){
		if(f<0 || c<0 || f>=getNumFilas() || c>=getNumColumnas())
			throw new ArrayIndexOutOfBoundsException("Posici�n fuera de rango");
		
		matriz[f][c]=new Islas(0,f,c);
	}

	public Islas[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Islas[][] matriz) {
		this.matriz = matriz;
	}
	
	
	
	

}

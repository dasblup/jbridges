package com.googlecode.jbridges.lib.problemas;

import java.util.*;

public class Cargar_juego {
	
	static public void inicializaCeldas(Tablero matriz){
		for(int i=0;i<matriz.getNumFilas();i++){
			for(int j=0;j<matriz.getNumColumnas();j++){
				matriz.setElementoAgua(i, j);
			}
		}
	}
	
	static public void cargar(Tablero matriz){
		Random rnd=new Random();
		List l=new LinkedList();
		Islas isla=null;
		Islas islaAdy=null;
		int cont=0;
		int x;
		int y;
		int pos_lista;
		int orientacion;
		/*int numIslas*/
		//Numeros aleatorios para la fila y la columna de la matriz donde vamos a a�adir una isla
		inicializaCeldas(matriz);
		x=rnd.nextInt(20);
		y=rnd.nextInt(20);
		matriz.setElementoIsla(x, y);
		//A�ado la isla a una lista
		l.add(matriz.getElemento(x, y));
		cont++;
		while(cont<=15){//o l.isEmpty??
			//Numero aleatorio para elegir una isla de una posion determinada de la lista
			pos_lista=rnd.nextInt(l.size());
			isla=(Islas)l.get(pos_lista);
			//borra la isla de la lista para a�adirla modificada
			l.remove(pos_lista);
			//numero aleatorio para a�adir un puente en la isla elegida (N=0, S=1, E=2, O=3)
			orientacion=rnd.nextInt(4);
			
			if(orientacion==0){
				//A�ade puente al norte (cambia la x, decrementa)
				islaAdy=ponIslaNorte(matriz, isla, x, y);
				if(islaAdy!=null){
						//incrementa n de las islas
						isla.getValor().setN(isla.getValor().getN()+1);
						islaAdy.getValor().setN(islaAdy.getValor().getN()+1);
							//incrementar el valor de existentes(norte) de la isla actual y decrementar posibles
							isla.getValor().getNorte().setExistentes(isla.getValor().getNorte().getExistentes()+1);
							//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
							isla.getValor().getNorte().setPosibles(isla.getValor().getNorte().getPosibles()-1);
							//incrementar el valor de existentes(sur) de la isla ady y decrementar posibles
							islaAdy.getValor().getSur().setExistentes(islaAdy.getValor().getSur().getExistentes()+1);
							//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
							islaAdy.getValor().getSur().setPosibles(islaAdy.getValor().getSur().getPosibles()-1);
							//marca las celdas intermedias
							MetodosEstaticos.marcaVerticales(matriz, isla, islaAdy);
							//a�ade islaAdy a la lista
							l.add(islaAdy);
				}
				//a�ade la isla modificada
				l.add(isla);
			}else if(orientacion==1){
				//A�ade puente al sur (cambia la x, incrementa)
				islaAdy=ponIslaSur(matriz, isla, x, y);
				if(islaAdy!=null){	
						//incrementa n de las islas
						isla.getValor().setN(isla.getValor().getN()+1);
						islaAdy.getValor().setN(islaAdy.getValor().getN()+1);
						//incrementa y decrementa existentes y posibles de isla (sur), respectivamente
						isla.getValor().getSur().setExistentes(isla.getValor().getSur().getExistentes()+1);
						//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
						isla.getValor().getSur().setPosibles(isla.getValor().getSur().getPosibles()-1);
						//incrementar el valor de existentes(norte) de la isla ady y decrementar posibles
						isla.getValor().getNorte().setExistentes(isla.getValor().getNorte().getExistentes()+1);
						//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
						isla.getValor().getNorte().setPosibles(isla.getValor().getNorte().getPosibles()-1);
						//marca las celdas intermedias
						MetodosEstaticos.marcaVerticales(matriz, isla, islaAdy);
						//a�ade islaAdy a la lista
						l.add(islaAdy);
				}
				//a�ade la isla modificada
				l.add(isla);
			}else if(orientacion==2){
				//A�ade puente al este (cambia la y, decrementa)
				islaAdy=ponIslaEste(matriz, isla, x, y);
					if(MetodosEstaticos.miraCeldasIntermedias(matriz, isla, islaAdy)){
						//incrementa n de las islas
						isla.getValor().setN(isla.getValor().getN()+1);
						islaAdy.getValor().setN(islaAdy.getValor().getN()+1);
						//incrementa y decrementa existentes y posibles de isla (este), respectivamente
						isla.getValor().getEste().setExistentes(isla.getValor().getEste().getExistentes()+1);
						//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
						isla.getValor().getEste().setPosibles(isla.getValor().getEste().getPosibles()-1);
						//incrementar el valor de existentes(oeste) de la isla ady y decrementar posibles
						isla.getValor().getOeste().setExistentes(isla.getValor().getOeste().getExistentes()+1);
						//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
						isla.getValor().getOeste().setPosibles(isla.getValor().getOeste().getPosibles()-1);
						//marca las celdas intermedias
						MetodosEstaticos.marcaHorizontales(matriz, isla, islaAdy);
						//a�ade islaAdy a la lista
						l.add(islaAdy);
					}	
					//a�ade la isla modificada
					l.add(isla);
			}else{
				//A�ade puente al oeste (cambia la y, incrementa)
				islaAdy=ponIslaOeste(matriz, isla, x, y);
				if(islaAdy!=null){	
					//incrementa n de las islas
						isla.getValor().setN(isla.getValor().getN()+1);
						islaAdy.getValor().setN(islaAdy.getValor().getN()+1);
						//incrementa y decrementa existentes y posibles de isla (oeste), respectivamente
						isla.getValor().getOeste().setExistentes(isla.getValor().getOeste().getExistentes()+1);
						//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
						isla.getValor().getOeste().setPosibles(isla.getValor().getOeste().getPosibles()-1);
						//incrementar el valor de existentes(este) de la isla ady y decrementar posibles
						isla.getValor().getEste().setExistentes(isla.getValor().getEste().getExistentes()+1);
						//DUDA:�como vamos a decrementar posibles si inicialmente est� a 0?
						isla.getValor().getEste().setPosibles(isla.getValor().getEste().getPosibles()-1);
						//marca las celdas intermedias
						MetodosEstaticos.marcaHorizontales(matriz, isla, islaAdy);
						//a�ade islaAdy a la lista
						l.add(islaAdy);
				}
				//a�ade la isla modificada
				l.add(isla);
			}
			
		}
		
		
	}
	
	
	static public Islas ponIslaNorte(Tablero matriz, Islas isla, int x, int y){
		
		Islas islaAdy=null;
		Random rnd=new Random();
		int longitud;
		int a=0;
		if(x==0){
			throw new FueraDeRango("Posi�n de la nueva isla fuera de rango");
		}else if(x==1){
			//longitud1
				a=x-1;
		}else if(x==2){
			longitud=rnd.nextInt(35);
			if(longitud>=0 && longitud<=5){
				//longitud 1
				a=x-1;
			}else if(longitud>=6 && longitud<=34){
				//longitud 2
				a=x-2;
			}		
		}else if(x==3){
			longitud=rnd.nextInt(65);
			if(longitud>=0 && longitud<=5){
				//longitud 1
					a=x-1;
			}else if(longitud>=6 && longitud<=34){
				//longitud 2
					a=x-2;
			}else if(longitud>=35 && longitud<=64){
				//longitud 3
					a=x-3;
			}		
		}else if(x==4){
			longitud=rnd.nextInt(95);
			if(longitud>=0 && longitud<=5){
				//longitud 1
					a=x-1;
			}else if(longitud>=6 && longitud<=34){
				//longitud 2
					a=x-2;
			}else if(longitud>=35 && longitud<=64){
				//longitud 3
					a=x-3;
			}else if(longitud>=65 && longitud<=94){
				//longitud 4
					a=x-4;
			}
		}else if(x==5){
			longitud=rnd.nextInt(98);
			if(longitud>=0 && longitud<=5){
				//longitud 1
					a=x-1;
			}else if(longitud>=6 && longitud<=34){
				//longitud 2
					a=x-2;
			}else if(longitud>=35 && longitud<=64){
				//longitud 3
					a=x-3;
			}else if(longitud>=65 && longitud<=94){
				//longitud 4
					a=x-4;
			}else if(longitud>=95 && longitud<=97){
				//longitud 5
					a=x-5;
			}
		}else if(x>=6){//x-0>6
			longitud=rnd.nextInt(100);
			if(longitud>=0 && longitud<=5){
				//longitud 1
					a=x-1;
			}else if(longitud>=6 && longitud<=34){
				//longitud 2
					a=x-2;
			}else if(longitud>=35 && longitud<=64){
				//longitud 3
					a=x-3;
			}else if(longitud>=65 && longitud<=94){
				//longitud 4
					a=x-4;
			}else if(longitud>=95 && longitud<=97){
				//longitud 5
					a=x-5;
			}else{ //(longitud>=98 && longitud<=99)
				//longitud 6
					a=x-6;
			}
			
		}
		matriz.setElementoIsla(a, y);
		islaAdy=matriz.getElemento(a, y);
		if (!MetodosEstaticos.miraCeldasIntermedias(matriz, isla, islaAdy)){
			//borro la isla que acabo de a�adir a la matriz, y dejo esa celda como agua
			matriz.setElementoAgua(a, y);
			islaAdy=null;
		}
		
		return islaAdy;
	}

static public Islas ponIslaSur(Tablero matriz, Islas isla, int x, int y){
	
	Islas islaAdy=null;
	Random rnd=new Random();
	int longitud;
	int a=0;
	if(matriz.getNumFilas()-1-x==0){
		throw new FueraDeRango("Posi�n de la nueva isla fuera de rango");
	}else if(matriz.getNumFilas()-1-x==1){
		//longitud1
		a=x+1;
	}else if(matriz.getNumFilas()-1-x==2){
		longitud=rnd.nextInt(35);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				a=x+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				a=x+2;			
		}		
	}else if(matriz.getNumFilas()-1-x==3){
		longitud=rnd.nextInt(65);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				a=x+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				a=x+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				a=x+3;
		}		
	}else if(matriz.getNumFilas()-1-x==4){
		longitud=rnd.nextInt(95);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				a=x+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				a=x+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				a=x+3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				a=x+4;
		}
	}else if(matriz.getNumFilas()-1-x==5){
		longitud=rnd.nextInt(98);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				a=x+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				a=x+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				a=x+3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				a=x+4;
		}else if(longitud>=95 && longitud<=97){
			//longitud 5
				a=x+5;
		}
	}else if(matriz.getNumFilas()-1-x>=6){//x-0>6
		longitud=rnd.nextInt(100);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				a=x+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				a=x+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				a=x+3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				a=x+4;
		}else if(longitud>=95 && longitud<=97){
			//longitud 5
				a=x+5;
		}else{ //(longitud>=98 && longitud<=99)
			//longitud 6
				a=x+6;
		}
		
	}
	matriz.setElementoIsla(a, y);
	islaAdy=matriz.getElemento(a, y);
	if (!MetodosEstaticos.miraCeldasIntermedias(matriz, isla, islaAdy)){
		matriz.setElementoAgua(a, y);
		islaAdy=null;
	}
	
	return islaAdy;
}

static public Islas ponIslaEste(Tablero matriz, Islas isla, int x, int y){
	
	Islas islaAdy=null;
	Random rnd=new Random();
	int longitud;
	int b=0;
	if(matriz.getNumColumnas()-1-y==0){
		throw new FueraDeRango("Posi�n de la nueva isla fuera de rango");
	}else if(matriz.getNumColumnas()-1-y==1){
		//longitud1
		b=y+1;
	}else if(matriz.getNumColumnas()-1-y==2){
		longitud=rnd.nextInt(35);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y+2;			
		}		
	}else if(matriz.getNumColumnas()-1-y==3){
		longitud=rnd.nextInt(65);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y+3;
		}		
	}else if(matriz.getNumColumnas()-1-y==4){
		longitud=rnd.nextInt(95);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y+3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				b=y+4;
		}
	}else if(matriz.getNumColumnas()-1-y==5){
		longitud=rnd.nextInt(98);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y+3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				b=y+4;
		}else if(longitud>=95 && longitud<=97){
			//longitud 5
				b=y+5;
		}
	}else if(matriz.getNumColumnas()-1-y>=6){//x-0>6
		longitud=rnd.nextInt(100);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y+1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y+2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y+3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				b=y+4;
		}else if(longitud>=95 && longitud<=97){
			//longitud 5
				b=y+5;
		}else{ //(longitud>=98 && longitud<=99)
			//longitud 6
				b=y+6;
		}
		
	}
	matriz.setElementoIsla(x, b);
	islaAdy=matriz.getElemento(x, b);
	if (!MetodosEstaticos.miraCeldasIntermedias(matriz, isla, islaAdy)){
		matriz.setElementoAgua(x, b);
		islaAdy=null;
	}
	
	return islaAdy;
}

static public Islas ponIslaOeste(Tablero matriz, Islas isla, int x, int y){
	
	
	Islas islaAdy=null;
	Random rnd=new Random();
	int longitud;
	int b=0;
	if(y==0){
		throw new FueraDeRango("Posi�n de la nueva isla fuera de rango");
	}else if(y==1){
		//longitud1
		b=y-1;
	}else if(y==2){
		longitud=rnd.nextInt(35);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y-1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y-2;		
		}		
	}else if(y==3){
		longitud=rnd.nextInt(65);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y-1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y-2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y-3;
		}		
	}else if(y==4){
		longitud=rnd.nextInt(95);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y-1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y-2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y-3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				b=y-4;
		}
	}else if(y==5){
		longitud=rnd.nextInt(98);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y-1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y-2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y-3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				b=y-4;
		}else if(longitud>=95 && longitud<=97){
			//longitud 5
				b=y-5;
		}
	}else if(y>=6){//x-0>6
		longitud=rnd.nextInt(100);
		if(longitud>=0 && longitud<=5){
			//longitud 1
				b=y-1;
		}else if(longitud>=6 && longitud<=34){
			//longitud 2
				b=y-2;
		}else if(longitud>=35 && longitud<=64){
			//longitud 3
				b=y-3;
		}else if(longitud>=65 && longitud<=94){
			//longitud 4
				b=y-4;
		}else if(longitud>=95 && longitud<=97){
			//longitud 5
				b=y-5;
		}else{ //(longitud>=98 && longitud<=99)
			//longitud 6
				b=y-6;
		}
		
	}
	matriz.setElementoIsla(x, b);
	islaAdy=matriz.getElemento(x, b);
	if (!MetodosEstaticos.miraCeldasIntermedias(matriz, isla, islaAdy)){
		matriz.setElementoAgua(x, b);
		islaAdy=null;
	}
	
	return islaAdy;
}



}

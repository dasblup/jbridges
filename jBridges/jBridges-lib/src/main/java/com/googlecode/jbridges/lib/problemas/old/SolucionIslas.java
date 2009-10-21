package com.googlecode.jbridges.lib.problemas.old;
import java.util.*;


public class SolucionIslas {

	/*public Tablero solucionProblema() {
		Tablero matriz = new Tablero();
		Posicion posIslaActual = new Posicion();
		MetodosEstaticos.buscaAdyacentes(matriz);
		posIslaActual = MetodosEstaticos.primeraIsla(matriz);
		MetodosEstaticos.ponPuentes(matriz, posIslaActual);
		while (MetodosEstaticos.quedanIslas(matriz)) {
			posIslaActual = MetodosEstaticos.siguienteIsla(matriz, posIslaActual);
			MetodosEstaticos.ponPuentes(matriz, posIslaActual);
		}
		return matriz;
	}*/
	Tablero matriz=new Tablero();
	
	static List desconocidas;
	static List M;
	static List pila;
	static List solucion_paso_a_paso;//se ir�n guardando los puentes que se van encontrado en el orden l�gico en el que se han ido averiguando.
	static List solucion;
	
	static List soluciones;
	

	
	
	public static void SolucionPasatiempo(Tablero matriz, List desconocidas, List M){
		
		soluciones=new LinkedList();//hacer que sea una lista de listas.
		
		MetodosEstaticos.inicializaDyM(matriz, desconocidas);
		MetodosEstaticos.inicializaDyM(matriz, M);
		//No se si esto hay que hacerlo aqui
		//problemas.MetodosEstaticos.inicializaListaAdyacentes(matriz, M);
		MetodosEstaticos.inicializaListaConexo(matriz, M, desconocidas);
		/*problemas.MetodosEstaticos.inicializaExistentes(matriz);
		problemas.MetodosEstaticos.inicializaPosibles(matriz, desconocidas);*/
		solucion=new LinkedList();
		solucion_paso_a_paso=new LinkedList();
		//falta inicializar pila
		pila=new LinkedList();
		SolucionBasica(matriz, desconocidas, M, solucion, solucion_paso_a_paso);
		
	}	
	public static boolean SolucionBasica(Tablero matriz, List desconocidas, List M, List solucion, List solucion_paso_a_paso){
			
		boolean puedesSeguir=true;
		boolean contradiccion=false;
		
		while(puedesSeguir){
			puedesSeguir=false;
			if(MetodosEstaticos.ponPuentes(matriz, desconocidas, M, solucion, solucion_paso_a_paso)){
				puedesSeguir=true;
			}
		}
		
		/*Si desconocidas est� vac�a, comprobar si la soluci�n parcial encontrada define una estructura conexa 
		 * (haciendo una b�squeda en anchura con la informaci�n de la matriz M). 
		 * Si es conexo, a�adir a soluciones la soluci�n encontrada, FIN 
		 * (se acaba el proceso, porque se ha resuelto el pasatiempo con las t�cnicas elementales, 
		 * sin necesidad de entrar en backtracking); en otro caso el pasatiempo no tiene soluci�n, fin si.*/
		
		if(desconocidas.isEmpty()){
			if(MetodosEstaticos.esConexo(matriz, M, solucion)){
				soluciones=solucion;
			}else{
				//Que cuando se comprueba si un estado de posible soluci�n es conexo, se llegue a que no lo es.
				contradiccion=true;//no se si esto est� bien.
			}
		}else{
			//el problema no tiene solucion
		}
		return contradiccion;
	}
		
}

	


		
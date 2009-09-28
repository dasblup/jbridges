package com.googlecode.jbridges.lib.problemas;


import java.util.*;

public class MetodosEstaticos {

	
	public static void inicializaPosibles(Tablero matriz, List desconocidas){
		
		Islas islaAdy=null;
		int n;
		
		Iterator it=desconocidas.iterator();
		
		while(it.hasNext()){
				Islas isla=(Islas)it.next();
				desconocidas.remove(isla);
				n=isla.getValor().getN();
				//Norte
				islaAdy=adyacentesNorte(matriz, isla, desconocidas);
				if(islaAdy!=null){
					
					//si n=1 y el n de la islaAdy tb es 1, no se pone posible, si es mayor que 1,si. 
					if((n==1) && (n<islaAdy.getValor().getN())){
						isla.getValor().getNorte().setPosibles(1);
					}
					if((n>1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getNorte().setPosibles(1);
					}
					if ((n==1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getNorte().setPosibles(0);
					}
					if((n==2) && (islaAdy.getValor().getN()==2)){
						isla.getValor().getNorte().setPosibles(1);
					}
					if((n>=2)&& (islaAdy.getValor().getN()>n) || ((n>2) && (islaAdy.getValor().getN()>=2) && (islaAdy.getValor().getN()<n))){
						isla.getValor().getNorte().setPosibles(2);
					}
				}else{
					isla.getValor().getNorte().setPosibles(0);
				}
				
				//Sur
				islaAdy=adyacentesSur(matriz, isla, desconocidas);
				if(islaAdy!=null){
					
					//si n=1 y el n de la islaAdy tb es 1, no se pone posible, si es mayor que 1,si. 
					if((n==1) && (n<islaAdy.getValor().getN())){
						isla.getValor().getSur().setPosibles(1);
					}
					if((n>1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getSur().setPosibles(1);
					}
					if ((n==1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getSur().setPosibles(0);
					}
					if((n==2) && (islaAdy.getValor().getN()==2)){
						isla.getValor().getSur().setPosibles(1);
					}
					if((n>=2)&& (islaAdy.getValor().getN()>n) || ((n>2) && (islaAdy.getValor().getN()>=2) && (islaAdy.getValor().getN()<n))){
						isla.getValor().getSur().setPosibles(2);
					}
				}else{
					isla.getValor().getSur().setPosibles(0);
				}
				
				//Este
				islaAdy=adyacentesEste(matriz, isla, desconocidas);
				if(islaAdy!=null){
					//si n=1 y el n de la islaAdy tb es 1, no se pone posible, si es mayor que 1,si. 
					if((n==1) && (n<islaAdy.getValor().getN())){
						isla.getValor().getEste().setPosibles(1);
					}
					if((n>1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getEste().setPosibles(1);
					}
					if ((n==1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getEste().setPosibles(0);
					}
					if((n==2) && (islaAdy.getValor().getN()==2)){
						isla.getValor().getEste().setPosibles(1);
					}
					if((n>=2)&& (islaAdy.getValor().getN()>n) || ((n>2) && (islaAdy.getValor().getN()>=2) && (islaAdy.getValor().getN()<n))){
						isla.getValor().getEste().setPosibles(2);
					}
				}else{
					isla.getValor().getEste().setPosibles(0);
				}
				
				//Oeste
				islaAdy=adyacentesOeste(matriz, isla, desconocidas);
				if(islaAdy!=null){
					//si n=1 y el n de la islaAdy tb es 1, no se pone posible, si es mayor que 1,si. 
					if((n==1) && (n<islaAdy.getValor().getN())){
						isla.getValor().getOeste().setPosibles(1);
					}
					if((n>1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getOeste().setPosibles(1);
					}
					if ((n==1) && (islaAdy.getValor().getN()==1)){
						isla.getValor().getOeste().setPosibles(0);
					}
					if((n==2) && (islaAdy.getValor().getN()==2)){
						isla.getValor().getOeste().setPosibles(1);
					}
					if((n>=2)&& (islaAdy.getValor().getN()>n) || ((n>2) && (islaAdy.getValor().getN()>=2) && (islaAdy.getValor().getN()<n))){
						isla.getValor().getOeste().setPosibles(2);
					}
				}else{
					isla.getValor().getOeste().setPosibles(0);
				}
				desconocidas.add(isla);
				
		}
		
		
	}
	
	public static void inicializaExistentes(Tablero matriz){
		//Cuando haya pistas...habria que cambiar este metodo.Ya que en un principio, todos los campos existentes de
		//todas las casillas valen 0 (tb de las que son agua), pero si hay k dar pistas, habria que modificarlo
		Islas isla=null;
		for(int i=0;i<matriz.getNumFilas();i++){
			for(int j=0;j<matriz.getNumColumnas();j++){
				isla=matriz.getElemento(i, j);
				isla.getValor().getNorte().setExistentes(0);
				isla.getValor().getSur().setExistentes(0);
				isla.getValor().getEste().setExistentes(0);
				isla.getValor().getOeste().setExistentes(0);
			}
		}
	}

	public static int sumatorioExistentes(Islas isla){
		int existentes;
		
		existentes=isla.getValor().getNorte().getExistentes()+isla.getValor().getSur().getExistentes()+
		isla.getValor().getEste().getExistentes()+isla.getValor().getOeste().getExistentes();
		
		return existentes;
	}
	
	public static void posiblesA0(Islas isla){
		isla.getValor().getNorte().setPosibles(0);
		isla.getValor().getSur().setPosibles(0);
		isla.getValor().getEste().setPosibles(0);
		isla.getValor().getOeste().setPosibles(0);
	}

	public static boolean ponPuentes(Tablero matriz, List desconocidas, List M, List solucion, List solucion_paso_a_paso) {
			
			//Con esta lista comprobaremos si al final del m�todo hemos conseguido reunir alguna informaci�n mas
			List sol_paso_a_paso_ini=new LinkedList();
			sol_paso_a_paso_ini.addAll(solucion_paso_a_paso);
			boolean masInfo=true;
			
			Iterator it=desconocidas.iterator();
			while(it.hasNext()){
				Islas islaActual=(Islas)it.next();
				desconocidas.remove(islaActual);
				if(islaActual.getValor().getNorte().getPosibles()>0){
					//Devuelvo la islaActual, por si dentro de alguno de los m�todos cambiamos algo de ella
					islaActual=ponPuentesNorte(matriz, desconocidas, islaActual, M, solucion_paso_a_paso);
				}
				
				if(islaActual.getValor().getSur().getPosibles()>0){
					islaActual=ponPuentesSur(matriz, desconocidas, islaActual, M, solucion_paso_a_paso);
				}
				if(islaActual.getValor().getOeste().getPosibles()>0){
					islaActual=ponPuentesOeste(matriz, desconocidas, islaActual, M, solucion_paso_a_paso);
				}
				if(islaActual.getValor().getEste().getPosibles()>0){
					islaActual=ponPuentesEste(matriz, desconocidas, islaActual, M, solucion_paso_a_paso);
				}
			}
			
			//Comparando estas dos listas vemos si hemos conseguido mas informacion
			if(solucion_paso_a_paso.size()==sol_paso_a_paso_ini.size()){
				masInfo=false;
			}else
				masInfo=true;
		
			return masInfo;
	}
	
	public static Islas ponPuentesNorte(Tablero matriz, List desconocidas, Islas isla, List M, List solucion_paso_a_paso){
		//N - sumatorio de los puentes contruibles en el resto de las direcciones
		
		int sumaPuentesConstruibles=0;
		Islas islaAdy=null;
		Islas islasRestantes=null;
		OrdenPuentes orden=null; //Guardamos los pares de islas en los que ponemos puentes (en solucion_paso_a_paso)
		islaAdy=adyacentesNorte(matriz, isla, M);
		orden.setIOrigen(isla.getPosicion());
		orden.setDirOrigen("norte");
		orden.setIDestino(islaAdy.getPosicion());
		orden.setDirDestino("sur");
			int N=isla.getValor().getN();
			
			
			if(islaAdy!=null){
				if(islaAdy.getValor().getSur().getPosibles()>0){
					if(miraCeldasIntermedias(matriz, isla, islaAdy)){
						//Sumo los puentes contruibles de las islas del resto de direcciones
						//La de la derecha
						islasRestantes=adyacentesEste(matriz, isla, M);
						if(islasRestantes!=null){
							//sumo los posibles de su derecha
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getEste().getPosibles();
						}
						//La de abajo
						islasRestantes=adyacentesSur(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los posibles de su sur
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getSur().getPosibles();
						}
						//La de la izquierda
						islasRestantes=adyacentesOeste(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los de su izquierda
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getOeste().getPosibles();
						}
						
					
				
						int puentes=N-sumaPuentesConstruibles;
						if(puentes>0 && puentes<3){
							//desconocidas.remove(isla); LA HE BORRADO EN PONPUENTES
							desconocidas.remove(islaAdy);
							//Incrementar existentes de las islas, decrementar posibles y n y marcar casillas intermedias
							if(puentes==1){
								isla.getValor().getNorte().setExistentes(isla.getValor().getNorte().getExistentes()+1);
								islaAdy.getValor().getSur().setExistentes(islaAdy.getValor().getSur().getExistentes()+1);
								
								isla.getValor().getNorte().setPosibles(isla.getValor().getNorte().getPosibles()-1);
								islaAdy.getValor().getSur().setPosibles(isla.getValor().getSur().getPosibles()-1);
																
								solucion_paso_a_paso.add(orden);
														
							}else if (puentes==2){
//								Si al poner un puente ya el sumatorioExistentes==N, solo pongo 1 puente, sino, pongo 2
								isla.getValor().getNorte().setExistentes(isla.getValor().getNorte().getExistentes()+1);
								islaAdy.getValor().getSur().setExistentes(islaAdy.getValor().getSur().getExistentes()+1);
								
								if(sumatorioExistentes(isla)==isla.getValor().getN()){
									//para no tener problemas luego, ponemos los posibles a 0, ya que ahi no podr� existir un puente
									isla.getValor().getNorte().setPosibles(0);
									islaAdy.getValor().getSur().setPosibles(0);
									
									solucion_paso_a_paso.add(orden);
									
								}else{
									isla.getValor().getNorte().setExistentes(isla.getValor().getNorte().getExistentes()+1);
									islaAdy.getValor().getSur().setExistentes(islaAdy.getValor().getSur().getExistentes()+1);
									
									isla.getValor().getNorte().setPosibles(isla.getValor().getNorte().getPosibles()-2);
									islaAdy.getValor().getSur().setPosibles(isla.getValor().getSur().getPosibles()-2);
																	
									solucion_paso_a_paso.add(orden);
									solucion_paso_a_paso.add(orden);
								}	
								
							}
							if(isla.getPosicion().getPosi()==islaAdy.getPosicion().getPosi()){
								marcaHorizontales(matriz, isla, islaAdy);
							}else{
								marcaVerticales(matriz, isla, islaAdy);
							}
							
							//Actualiza conexo de isla
							Iterator it=islaAdy.getConexo().iterator();
							while (it.hasNext()){
								Islas iConexa=(Islas)it.next();
								if(!isla.getConexo().contains(iConexa))
									isla.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de islaAdy
							Iterator it1=isla.getConexo().iterator();
							while (it1.hasNext()){
								Islas iConexa=(Islas)it1.next();
								if(!islaAdy.getConexo().contains(iConexa))
									islaAdy.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de las demas islas de la lista
							Iterator it2=isla.getConexo().iterator();
							while(it2.hasNext()){
								Islas iC=(Islas)it2.next();
								if(!iC.equals(isla) && ! iC.equals(islaAdy)){
									Iterator it3=islaAdy.getConexo().iterator();
									while(it3.hasNext()){
										Islas iC2=(Islas)it.next();
										if(!iC.getConexo().contains(iC2))
											iC.getConexo().add(iC2);
									}
								}
							}
							
							desconocidas.add(isla);
							desconocidas.add(islaAdy);
							
							//Mirar si alguna de las islas (isla e islaAdy) est� completa: quitarla de desconocidas y a�adirla a solucion
//							si N=existentes, quito esa isla de la lista l (que contiene las islas que quedan por cubrir)
							
							//isla
							if(sumatorioExistentes(isla)==isla.getValor().getN()){
								posiblesA0(isla);
								desconocidas.remove(isla);
								problemas.SolucionIslas.solucion.add(isla);
							}
							
							//islaAdy
							if(sumatorioExistentes(islaAdy)==islaAdy.getValor().getN()){
								posiblesA0(islaAdy);
								desconocidas.remove(islaAdy);
								problemas.SolucionIslas.solucion.add(islaAdy);
							}

						}
						
					}
				}
			}else{
				isla.getValor().getNorte().setPosibles(0);
			}
				
			return isla;
	}
			
		

	public static Islas ponPuentesSur(Tablero matriz,List desconocidas, Islas isla, List M, List solucion_paso_a_paso) {
//		N-sumatorio de los puentes contruibles en el resto de las direcciones
		int sumaPuentesConstruibles=0;
		Islas islaAdy=null;
		Islas islasRestantes=null;
		OrdenPuentes orden=null;
		
			
			islaAdy=adyacentesSur(matriz, isla, M);
			orden.setIOrigen(isla.getPosicion());
			orden.setDirOrigen("sur");
			orden.setIDestino(islaAdy.getPosicion());
			orden.setDirDestino("norte");
			int N=isla.getValor().getN();
			
			
			
			if(islaAdy!=null){
				if(islaAdy.getValor().getNorte().getPosibles()>0){
					if(miraCeldasIntermedias(matriz, isla, islaAdy)){
						//Sumo los puentes contruibles de las islas del resto de direcciones
						//La de la derecha
						islasRestantes=adyacentesEste(matriz, isla, M);
						if(islasRestantes!=null){
							//sumo los posibles de su derecha
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getEste().getPosibles();
						}
						//La de arriba
						islasRestantes=adyacentesNorte(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los posibles de su norte
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getNorte().getPosibles();
						}
						//La de la izquierda
						islasRestantes=adyacentesOeste(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los de su izq
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getOeste().getPosibles();
						}
						
							
						int puentes=N-sumaPuentesConstruibles;
						if(puentes>0 && puentes<3){
							//desconocidas.remove(isla); LA HE BORRADO EN PONPUENTES
							desconocidas.remove(islaAdy);
							//Incrementar existentes de las islas, decrementar posibles y marcar casillas intermedias
							if(puentes==1){
								isla.getValor().getSur().setExistentes(isla.getValor().getSur().getExistentes()+1);
								islaAdy.getValor().getNorte().setExistentes(islaAdy.getValor().getNorte().getExistentes()+1);
								
								isla.getValor().getSur().setPosibles(isla.getValor().getSur().getPosibles()-1);
								islaAdy.getValor().getNorte().setPosibles(isla.getValor().getNorte().getPosibles()-1);
																
								solucion_paso_a_paso.add(orden);
								
							}else if (puentes==2){
								//Si al poner un puente ya el sumatorioExistentes==N, solo pongo 1 puente, sino, pongo 2
								isla.getValor().getSur().setExistentes(isla.getValor().getSur().getExistentes()+1);
								islaAdy.getValor().getNorte().setExistentes(islaAdy.getValor().getNorte().getExistentes()+1);
								
								if(sumatorioExistentes(isla)==isla.getValor().getN()){
									isla.getValor().getSur().setPosibles(0);
									islaAdy.getValor().getNorte().setPosibles(0);
									
									solucion_paso_a_paso.add(orden);
									
								}else{
									isla.getValor().getSur().setExistentes(isla.getValor().getSur().getExistentes()+1);
									islaAdy.getValor().getNorte().setExistentes(islaAdy.getValor().getNorte().getExistentes()+1);
									
									isla.getValor().getSur().setPosibles(isla.getValor().getSur().getPosibles()-2);
									islaAdy.getValor().getNorte().setPosibles(isla.getValor().getNorte().getPosibles()-2);
																	
									solucion_paso_a_paso.add(orden);
									solucion_paso_a_paso.add(orden);
								}	
							}
							
							if(isla.getPosicion().getPosi()==islaAdy.getPosicion().getPosi()){
								marcaHorizontales(matriz, isla, islaAdy);
							}else{
								marcaVerticales(matriz, isla, islaAdy);
							}
							
							//Actualiza conexo de isla
							Iterator it=islaAdy.getConexo().iterator();
							while (it.hasNext()){
								Islas iConexa=(Islas)it.next();
								if(!isla.getConexo().contains(iConexa))
									isla.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de islaAdy
							Iterator it1=isla.getConexo().iterator();
							while (it1.hasNext()){
								Islas iConexa=(Islas)it1.next();
								if(!islaAdy.getConexo().contains(iConexa))
									islaAdy.getConexo().add(iConexa);
							}

							//Actualizo conexo de las demas islas de la lista
							Iterator it2=isla.getConexo().iterator();
							while(it2.hasNext()){
								Islas iC=(Islas)it2.next();
								if(!iC.equals(isla) && ! iC.equals(islaAdy)){
									Iterator it3=islaAdy.getConexo().iterator();
									while(it3.hasNext()){
										Islas iC2=(Islas)it.next();
										if(!iC.getConexo().contains(iC2))
											iC.getConexo().add(iC2);
									}
								}
							}
							desconocidas.add(isla);
							desconocidas.add(islaAdy);
							
							//Mirar si alguna de las islas (isla e islaAdy) est� completa: quitarla de desconocidas y a�adirla a solucion
							//si N=existentes, quito esa isla de la lista l (que contiene las islas que quedan por cubrir)
							
							//isla
							if(sumatorioExistentes(isla)==isla.getValor().getN()){
								posiblesA0(isla);
								desconocidas.remove(isla);
								problemas.SolucionIslas.solucion.add(isla);
							}
							
							//islaAdy
							if(sumatorioExistentes(islaAdy)==islaAdy.getValor().getN()){
								posiblesA0(islaAdy);
								desconocidas.remove(islaAdy);
								problemas.SolucionIslas.solucion.add(islaAdy);
							}
						}
										
					}
				}	
			}else{
				isla.getValor().getSur().setPosibles(0);
			}
				return isla;
	}
	
	public static Islas ponPuentesOeste(Tablero matriz,List desconocidas, Islas isla, List M, List solucion_paso_a_paso) {
//		N-sumatorio de los puentes contruibles en el resto de las direcciones
		int sumaPuentesConstruibles=0;
		Islas islaAdy=null;
		Islas islasRestantes=null;
		OrdenPuentes orden=null;
		
			
			islaAdy=adyacentesOeste(matriz, isla, M);
			orden.setIOrigen(isla.getPosicion());
			orden.setDirOrigen("oeste");
			orden.setIDestino(islaAdy.getPosicion());
			orden.setDirDestino("este");
			int N=isla.getValor().getN();
			
			
			
			if(islaAdy!=null){
				if(islaAdy.getValor().getEste().getPosibles()>0){	
					if(miraCeldasIntermedias(matriz, isla, islaAdy)){
						//Sumo los puentes contruibles de las islas del resto de direcciones
						//La de la derecha
						islasRestantes=adyacentesEste(matriz, isla, M);
						if(islasRestantes!=null){
							//sumo los posibles de su derecha
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getEste().getPosibles();
						}
						//La de arriba
						islasRestantes=adyacentesNorte(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los posibles de su norte
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getNorte().getPosibles();
						}
						//La de la izquierda
						islasRestantes=adyacentesSur(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los de abajo
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getSur().getPosibles();
						}
								
						int puentes=N-sumaPuentesConstruibles;
						if(puentes>0 && puentes<3){
							//desconocidas.remove(isla); LA HE BORRADO EN PONPUENTES
							desconocidas.remove(islaAdy);
							//Incrementar existentes de las islas, decrementar posibles y marcar casillas intermedias
							if(puentes==1){
								isla.getValor().getOeste().setExistentes(isla.getValor().getOeste().getExistentes()+1);
								islaAdy.getValor().getEste().setExistentes(islaAdy.getValor().getEste().getExistentes()+1);
								
								isla.getValor().getOeste().setPosibles(isla.getValor().getOeste().getPosibles()-1);
								islaAdy.getValor().getEste().setPosibles(isla.getValor().getEste().getPosibles()-1);
								
								solucion_paso_a_paso.add(orden);
								
							}else if (puentes==2){
								
								//Si al poner un puente ya el sumatorioExistentes==N, solo pongo 1 puente, sino, pongo 2
								isla.getValor().getOeste().setExistentes(isla.getValor().getOeste().getExistentes()+1);
								islaAdy.getValor().getEste().setExistentes(islaAdy.getValor().getEste().getExistentes()+1);
								
								if(sumatorioExistentes(isla)==isla.getValor().getN()){
									isla.getValor().getOeste().setPosibles(0);
									islaAdy.getValor().getEste().setPosibles(0);
									
									solucion_paso_a_paso.add(orden);
									
								}else{
									isla.getValor().getOeste().setExistentes(isla.getValor().getOeste().getExistentes()+1);
									islaAdy.getValor().getEste().setExistentes(islaAdy.getValor().getEste().getExistentes()+1);
								
									isla.getValor().getOeste().setPosibles(isla.getValor().getOeste().getPosibles()-2);
									islaAdy.getValor().getEste().setPosibles(isla.getValor().getEste().getPosibles()-2);
																
									solucion_paso_a_paso.add(orden);
									solucion_paso_a_paso.add(orden);
								}
							}
							
							if(isla.getPosicion().getPosi()==islaAdy.getPosicion().getPosi()){
								marcaHorizontales(matriz, isla, islaAdy);
							}else{
								marcaVerticales(matriz, isla, islaAdy);
							}
							
							//Actualiza conexo de isla
							Iterator it=islaAdy.getConexo().iterator();
							while (it.hasNext()){
								Islas iConexa=(Islas)it.next();
								if(!isla.getConexo().contains(iConexa))
									isla.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de islaAdy
							Iterator it1=isla.getConexo().iterator();
							while (it1.hasNext()){
								Islas iConexa=(Islas)it1.next();
								if(!islaAdy.getConexo().contains(iConexa))
									islaAdy.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de las demas islas de la lista
							Iterator it2=isla.getConexo().iterator();
							while(it2.hasNext()){
								Islas iC=(Islas)it2.next();
								if(!iC.equals(isla) && ! iC.equals(islaAdy)){
									Iterator it3=islaAdy.getConexo().iterator();
									while(it3.hasNext()){
										Islas iC2=(Islas)it.next();
										if(!iC.getConexo().contains(iC2))
											iC.getConexo().add(iC2);
									}
								}
							}
							desconocidas.add(isla);
							desconocidas.add(islaAdy);
							
							//Mirar si alguna de las islas (isla e islaAdy) est� completa: quitarla de desconocidas y a�adirla a solucion
							//si N=existentes, quito esa isla de la lista l (que contiene las islas que quedan por cubrir)
							
							//isla
							if(sumatorioExistentes(isla)==isla.getValor().getN()){
								posiblesA0(isla);
								desconocidas.remove(isla);
								problemas.SolucionIslas.solucion.add(isla);
							}
							
							//islaAdy
							if(sumatorioExistentes(islaAdy)==islaAdy.getValor().getN()){
								posiblesA0(islaAdy);
								desconocidas.remove(islaAdy);
								problemas.SolucionIslas.solucion.add(islaAdy);
							}
							
						}
						
					}
			}
		}else{
			isla.getValor().getOeste().setPosibles(0);
		}
				return isla;
	}
	
	public static Islas ponPuentesEste(Tablero matriz, List desconocidas, Islas isla, List M, List solucion_paso_a_paso) {
		//N-sumatorio de los puentes contruibles en el resto de las direcciones
		int sumaPuentesConstruibles=0;
		Islas islaAdy=null;
		Islas islasRestantes=null;
		OrdenPuentes orden=null;
		
			
			islaAdy=adyacentesEste(matriz, isla, M);
			orden.setIOrigen(isla.getPosicion());
			orden.setDirOrigen("este");
			orden.setIDestino(islaAdy.getPosicion());
			orden.setDirDestino("oeste");
			int N=isla.getValor().getN();
			
			
			
			if(islaAdy!=null){
				if(islaAdy.getValor().getOeste().getPosibles()>0){
					
					if(miraCeldasIntermedias(matriz, isla, islaAdy)){
						//Sumo los puentes contruibles de las islas del resto de direcciones
						//La de la derecha
						islasRestantes=adyacentesSur(matriz, isla, M);
						if(islasRestantes!=null){
							//sumo los posibles de abajo
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getSur().getPosibles();
						}
						//La de arriba
						islasRestantes=adyacentesNorte(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los posibles de arriba
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getNorte().getPosibles();
						}
						//La de la izquierda
						islasRestantes=adyacentesOeste(matriz, isla, M);
						if(islasRestantes!=null){
							//Sumo los de su izq
							sumaPuentesConstruibles=sumaPuentesConstruibles+isla.getValor().getOeste().getPosibles();
						}
								
						int puentes=N-sumaPuentesConstruibles;
						if(puentes>0 && puentes<3){
							//desconocidas.remove(isla);LA HE BORRADO EN PONPUENTES
							desconocidas.remove(islaAdy);
							//Incrementar existentes de las islas, decrementar posibles y marcar casillas intermedias
							if(puentes==1){
								isla.getValor().getEste().setExistentes(isla.getValor().getEste().getExistentes()+1);
								islaAdy.getValor().getOeste().setExistentes(islaAdy.getValor().getOeste().getExistentes()+1);
								
								isla.getValor().getEste().setPosibles(isla.getValor().getEste().getPosibles()-1);
								islaAdy.getValor().getOeste().setPosibles(isla.getValor().getOeste().getPosibles()-1);
																
								solucion_paso_a_paso.add(orden);
								
							}else if (puentes==2){
								//Si al poner un puente ya el sumatorioExistentes==N, solo pongo 1 puente, sino, pongo 2
								isla.getValor().getEste().setExistentes(isla.getValor().getEste().getExistentes()+1);
								islaAdy.getValor().getOeste().setExistentes(islaAdy.getValor().getOeste().getExistentes()+1);
								
								if(sumatorioExistentes(isla)==isla.getValor().getN()){
									isla.getValor().getEste().setPosibles(0);
									islaAdy.getValor().getOeste().setPosibles(0);
									
									solucion_paso_a_paso.add(orden);
									
								}else{
								
									isla.getValor().getEste().setExistentes(isla.getValor().getEste().getExistentes()+1);
									islaAdy.getValor().getOeste().setExistentes(islaAdy.getValor().getOeste().getExistentes()+1);
									
									isla.getValor().getEste().setPosibles(isla.getValor().getEste().getPosibles()-2);
									islaAdy.getValor().getOeste().setPosibles(isla.getValor().getOeste().getPosibles()-2);
									
									solucion_paso_a_paso.add(orden);
									solucion_paso_a_paso.add(orden);
								}
							}
							
							if(isla.getPosicion().getPosi()==islaAdy.getPosicion().getPosi()){
								marcaHorizontales(matriz, isla, islaAdy);
							}else{
								marcaVerticales(matriz, isla, islaAdy);
							}
							
							//Actualiza conexo de isla
							Iterator it=islaAdy.getConexo().iterator();
							while (it.hasNext()){
								Islas iConexa=(Islas)it.next();
								if(!isla.getConexo().contains(iConexa))
									isla.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de islaAdy
							Iterator it1=isla.getConexo().iterator();
							while (it1.hasNext()){
								Islas iConexa=(Islas)it1.next();
								if(!islaAdy.getConexo().contains(iConexa))
									islaAdy.getConexo().add(iConexa);
							}
							
							//Actualizo conexo de las demas islas de la lista
							Iterator it2=isla.getConexo().iterator();
							while(it2.hasNext()){
								Islas iC=(Islas)it2.next();
								if(!iC.equals(isla) && ! iC.equals(islaAdy)){
									Iterator it3=islaAdy.getConexo().iterator();
									while(it3.hasNext()){
										Islas iC2=(Islas)it.next();
										if(!iC.getConexo().contains(iC2))
											iC.getConexo().add(iC2);
									}
								}
							}
							desconocidas.add(isla);
							desconocidas.add(islaAdy);
							
							//Mirar si alguna de las islas (isla e islaAdy) est� completa: quitarla de desconocidas y a�adirla a solucion
							//si N=existentes, quito esa isla de la lista l (que contiene las islas que quedan por cubrir)
							
							//isla
							if(sumatorioExistentes(isla)==isla.getValor().getN()){
								posiblesA0(isla);
								desconocidas.remove(isla);
								problemas.SolucionIslas.solucion.add(isla);
							}
							
							//islaAdy
							if(sumatorioExistentes(islaAdy)==islaAdy.getValor().getN()){
								posiblesA0(islaAdy);
								desconocidas.remove(islaAdy);
								problemas.SolucionIslas.solucion.add(islaAdy);
							}
						}
						
					}
				}	
			}else{
				isla.getValor().getEste().setPosibles(0);
			}
			return isla;
	}
	
	public static boolean miraCeldasIntermedias(Tablero matriz,Islas islaActual, Islas islaAdy){
		boolean libre=true;
			while(libre){
				//La islaActual est� arriba
				if(islaActual.getPosicion().getPosi()<islaAdy.getPosicion().getPosj()){
					for(int i=islaActual.getPosicion().getPosi()+1;i<islaAdy.getPosicion().getPosi();i++){
						Islas celda=matriz.getElemento(i, islaActual.getPosicion().getPosj());
						if(celda.getTipo()==0 && celda.getValor().getN()==2)
							libre=false;
					}	
				//La islaActual est� abajo
				}else if(islaActual.getPosicion().getPosi()>islaAdy.getPosicion().getPosi()){
					for(int i=islaAdy.getPosicion().getPosi()-1;i<islaActual.getPosicion().getPosi();i++){
						Islas celda=matriz.getElemento(i, islaActual.getPosicion().getPosj());
						if(celda.getTipo()==0 && celda.getValor().getN()==2)
							libre=false;	
					}
				//La islaActual est� a la izquierda
				}else if(islaActual.getPosicion().getPosj()<islaAdy.getPosicion().getPosj()){	
					for(int j=islaActual.getPosicion().getPosj()+1;j<islaAdy.getPosicion().getPosj();j++){
						Islas celda=matriz.getElemento(islaActual.getPosicion().getPosi(), j);
						if(celda.getTipo()==0 && celda.getValor().getN()==1)
							libre=false;
					}
					
				//La islaActual est� a la derecha
				}else if(islaActual.getPosicion().getPosj()>islaAdy.getPosicion().getPosj()){
						for (int j=islaActual.getPosicion().getPosj()-1;j<islaAdy.getPosicion().getPosj();j--){
							Islas celda=matriz.getElemento(islaActual.getPosicion().getPosi(), j);
							if(celda.getTipo()==0 && celda.getValor().getN()==1) 
								libre=false;
						}	
				}
			}	
			return libre;
	}
	
	
	public static void marcaHorizontales(Tablero matriz, Islas islaActual, Islas islaAdy){
		Islas celda=null;
		//La isla Actual est� a la izquierda
		if(islaActual.getPosicion().getPosj()<islaAdy.getPosicion().getPosj()){
			for(int j=islaActual.getPosicion().getPosj()+1;j<islaAdy.getPosicion().getPosj();j++){
				celda=matriz.getElemento(islaActual.getPosicion().getPosi(), j);
				celda.getValor().setN(2);
			}
		//La islaActual est� a la derecha
		}else if(islaActual.getPosicion().getPosj()>islaAdy.getPosicion().getPosj()){
			for (int j=islaActual.getPosicion().getPosj()-1;j<islaAdy.getPosicion().getPosj();j--){
				celda=matriz.getElemento(islaActual.getPosicion().getPosi(), j);
				celda.getValor().setN(2);
			}
		}	
	}
	
	public static void marcaVerticales(Tablero matriz, Islas islaActual, Islas islaAdy){
		Islas celda=null;
		//La islaActual est� arriba
		if(islaActual.getPosicion().getPosi()<islaAdy.getPosicion().getPosi()){
			for(int i=islaActual.getPosicion().getPosi()+1;i<islaAdy.getPosicion().getPosi();i++){
				celda=matriz.getElemento(i, islaActual.getPosicion().getPosj());
				celda.getValor().setN(1);
			}
		//La islaActual est� abajo
		}else if(islaActual.getPosicion().getPosi()>islaAdy.getPosicion().getPosi()){
			for(int i=islaActual.getPosicion().getPosi()-1;i<islaAdy.getPosicion().getPosi();i--){
				celda=matriz.getElemento(i, islaActual.getPosicion().getPosj());
				celda.getValor().setN(1);
			}
		}	
	}
	
	
	/*public static void inicializaListaAdyacentes(Tablero matriz, List M) {

		Islas islaAdy=null;		
		Iterator it1=M.iterator();
		while(it1.hasNext()){
			Islas isla=(Islas)it1.next();
			
			M.remove(isla);
					
			islaAdy=adyacentesArriba(matriz, isla);
			if(islaAdy!=null){
				isla.getAdyacentes().add(islaAdy);
			}
			islaAdy=adyacentesAbajo(matriz, isla);
			if(islaAdy!=null){
				isla.getAdyacentes().add(islaAdy);
			}
			islaAdy=adyacentesDerecha(matriz, isla);
			if(islaAdy!=null){
				isla.getAdyacentes().add(islaAdy);
			}
			islaAdy=adyacentesIzquierda(matriz, isla);
			if(islaAdy!=null){
				isla.getAdyacentes().add(islaAdy);
			}
			M.add(isla);
		}
	}*/
	
	public static Islas adyacentesNorte(Tablero matriz, Islas isla, List M){
		int fila=isla.getPosicion().getPosi();
		int columna=isla.getPosicion().getPosj();
		int menor=Integer.MIN_VALUE;
		Islas iAdy=null;
		Islas islaAdy=null;
		Iterator it=M.iterator();
		while(it.hasNext()){
			iAdy=(Islas)it.next();
			if((iAdy.getPosicion().getPosj()==columna) && (iAdy.getPosicion().getPosi()<fila) && (iAdy.getPosicion().getPosi()>menor)){
				menor=iAdy.getPosicion().getPosi();
				islaAdy=iAdy;
			}
			
		}
		if(iAdy.getValor().getSur().getPosibles()==0){
			islaAdy=null;
		}
		return islaAdy;
	}
	
	public static Islas adyacentesSur(Tablero matriz, Islas isla, List M){
		int fila=isla.getPosicion().getPosi();
		int columna=isla.getPosicion().getPosj();
		int menor=Integer.MAX_VALUE;
		Islas iAdy=null;
		Islas islaAdy=null;
		Iterator it=M.iterator();
		while(it.hasNext()){
			iAdy=(Islas)it.next();
			if((iAdy.getPosicion().getPosj()==columna) && (iAdy.getPosicion().getPosi()>fila) && (iAdy.getPosicion().getPosi()<menor)){
				menor=iAdy.getPosicion().getPosi();
				islaAdy=iAdy;
			}
		}
		if(iAdy.getValor().getNorte().getPosibles()==0){
			islaAdy=null;
		}
		return islaAdy;
	}
	
	public static Islas adyacentesEste(Tablero matriz, Islas isla, List M){
		int fila=isla.getPosicion().getPosi();
		int columna=isla.getPosicion().getPosj();
		int menor=Integer.MAX_VALUE;
		Islas iAdy=null;
		Islas islaAdy=null;
		Iterator it=M.iterator();
		while(it.hasNext()){
			iAdy=(Islas)it.next();
			if((iAdy.getValor().getOeste().getPosibles()>0) && (iAdy.getPosicion().getPosi()==fila) && (iAdy.getPosicion().getPosi()>columna) && (iAdy.getPosicion().getPosi()<menor)){
				menor=iAdy.getPosicion().getPosi();
				islaAdy=iAdy;
			}
		}
		if(iAdy.getValor().getOeste().getPosibles()==0){
			islaAdy=null;
		}
		return islaAdy;
	}
	
	public static Islas adyacentesOeste(Tablero matriz, Islas isla, List M){
		int fila=isla.getPosicion().getPosi();
		int columna=isla.getPosicion().getPosj();
		int menor=Integer.MIN_VALUE;
		Islas iAdy=null;
		Islas islaAdy=null;
		Iterator it=M.iterator();
		while(it.hasNext()){
			iAdy=(Islas)it.next();
			if((iAdy.getValor().getEste().getPosibles()>0) && (iAdy.getPosicion().getPosj()==fila) && (iAdy.getPosicion().getPosi()<columna) && (iAdy.getPosicion().getPosi()>menor)){
				menor=iAdy.getPosicion().getPosi();
				islaAdy=iAdy;
			}
		}
		if(iAdy.getValor().getEste().getPosibles()==0){
			islaAdy=null;
		}
		return islaAdy;
	}
	
	public static void inicializaListaConexo(Tablero matriz, List M, List desconocidas ){
		//A cada lista de conexo de cada isla hay que a�adirle a ella misma
		
		Iterator it=M.iterator();
		while(it.hasNext()){
			Islas isla=(Islas)it.next();
			M.remove(isla);
			desconocidas.remove(isla);
			isla.getConexo().add(isla);
			M.add(isla);
			desconocidas.add(isla);
		}
		
	}

	public static void inicializaDyM(Tablero matriz, List desconocidas){
		
		Islas isla=null;
				
		for (int i=0;i<matriz.getNumFilas();i++){
			for(int j=0;j<matriz.getNumColumnas();j++){
				if ((matriz.getElemento(i, j).getTipo()==1)){
					isla=matriz.getElemento(i, j);
					desconocidas.add(isla);
				}
			}
		}
	}
	
	
	
	public static boolean esConexo(Tablero matriz, List M, List solucion){
		//El tama�o de la lista que guarda todas las islas, debe ser igual al tama�o de la lista de conexo de cada isla
		boolean b=true;
		
		Iterator it=solucion.iterator();
		while(it.hasNext() && b){
			Islas isla= (Islas)it.next();
			if (isla.getConexo().size() != M.size()){
				b=false;
			}
		}
		return b;
	
	}

	public static boolean n0Posibles0(Islas isla){
		boolean b=true;
		int n=isla.getValor().getN();
		int pNorte=isla.getValor().getNorte().getPosibles();
		int pSur=isla.getValor().getSur().getPosibles();
		int pOeste=isla.getValor().getOeste().getPosibles();
		int pEste=isla.getValor().getEste().getPosibles();
		
		if((n!=0) || (pNorte!=0) || (pSur!=0) || (pOeste!=0) || (pEste!=0)){
			b=false;
		}
		
		return b;
	}
	
	public static void copiaLista(List sol, List copia_sol){
		
		Iterator it=copia_sol.iterator();
		while(it.hasNext()){
			Islas i=(Islas)it.next();
			if(!sol.contains(i))
				sol.add(i);
		}
		
	}
	
	public static void backtracking(Tablero matriz) throws CloneNotSupportedException{	
		
		//Hago una copia de las variables principales del problema.
		List copia_l=problemas.SolucionIslas.desconocidas;
		List copia_solucion=problemas.SolucionIslas.solucion;
		List copia_solucion_paso_a_paso=problemas.SolucionIslas.solucion_paso_a_paso;
		
		//A�adir a pila la primera celda de la lista desconocidas, con los valores de las variables 
		//{n,arriba, abajo, izquierda ,derecha} correspondientes a la suposici�n de a�adir un puente 
		//hacia arriba (por seguir un orden): 
		//{n,arriba, abajo, izquierda ,derecha}  {n-1,(arriba[1]-1,arriba[2]+1), abajo, izquierda, derecha}
		
		//MIRAR DUDAS!! ESTO VA EN UN WHILE???
		Islas iPrimera=(Islas)copia_l.get(0);
		//Borro de copia_l la isla que acabo de sacar de l xq vamos a modificarla
		copia_l.remove(iPrimera);
		cambiarEstado(matriz, iPrimera, problemas.SolucionIslas.M, copia_l, copia_solucion, copia_solucion_paso_a_paso);
		
		while(!problemas.SolucionIslas.pila.isEmpty()){
			boolean boolean2=true;
			//Cargar los valores de las vbles que corresponden al �ltimo elemento de la lista pila
			int tam=problemas.SolucionIslas.pila.size()-1;
			Islas iPila=(Islas)problemas.SolucionIslas.pila.get(tam);
			problemas.SolucionIslas.pila.remove(iPila);
			
			//si encuentro contradiccion, boolean2=false
			if(!problemas.SolucionIslas.SolucionBasica(matriz, copia_l, problemas.SolucionIslas.M, copia_solucion, copia_solucion_paso_a_paso)){
				boolean2=false;
			}
			//si no hemos llegado a contradiccion
			if(boolean2){
				if(copia_l.isEmpty() && esConexo(matriz, problemas.SolucionIslas.M, copia_solucion)){
						copiaLista(problemas.SolucionIslas.solucion, copia_solucion);
						copiaLista(problemas.SolucionIslas.solucion_paso_a_paso, copia_solucion_paso_a_paso);
						while(!problemas.SolucionIslas.pila.isEmpty() && n0Posibles0(iPila)){
							problemas.SolucionIslas.pila.remove(iPila);
						}
						if(!problemas.SolucionIslas.pila.isEmpty()){
							cambiarEstado(matriz, iPila, problemas.SolucionIslas.M, copia_l, copia_solucion, copia_solucion_paso_a_paso);
						}
				}else{
					Islas iPila2=(Islas)copia_l.get(0);
					copia_l.remove(iPila2);
					cambiarEstado(matriz, iPila2, problemas.SolucionIslas.M, copia_l, copia_solucion, copia_solucion_paso_a_paso);
					
				}
			}
		}
	}



	
	public static void cambiarEstado(Tablero matriz, Islas iPrimera, List M, List copia_l, List copia_solucion, List copia_solucion_paso_a_paso) throws CloneNotSupportedException{
		Islas islaAdy=null;
		Islas islaPila=null;
		OrdenPuentes orden=null;
		int existentes;
		
		//Si norte.posibles y tiene adyacente arriba, empezamos por arriba
			
			if((iPrimera.getValor().getNorte().getPosibles()>0) && (islaAdy=adyacentesNorte(matriz, iPrimera, M))!=null  && (islaAdy.getValor().getSur().getPosibles()>0)){
				//Si puedo poner un puente arriba (en isla) 
					//pongo puente y actualizo isla
					islaPila=(Islas)iPrimera.clone();
					islaPila.getValor().getNorte().setPosibles(islaPila.getValor().getNorte().getPosibles()-1);
					islaPila.getValor().getNorte().setExistentes(islaPila.getValor().getNorte().getExistentes()+1);
					
					//Como puedo a�adir un puente en la islaAdy por abajo,la borro de copia_l y la actualizo
					copia_l.remove(islaAdy);
					
					islaAdy.getValor().getSur().setExistentes(islaAdy.getValor().getSur().getExistentes()+1);
					islaAdy.getValor().getSur().setPosibles(islaAdy.getValor().getSur().getPosibles()-1);
					//a�ado iPrimera e islaAdy a copia_solucion_paso_a_paso
					orden.setIOrigen(islaPila.getPosicion());
					orden.setDirOrigen("norte");
					orden.setIDestino(islaAdy.getPosicion());
					orden.setDirDestino("sur");
				
			//si no, por abajo
			}else if((iPrimera.getValor().getSur().getPosibles()>0) && (islaAdy=adyacentesSur(matriz, iPrimera, M))!=null && (islaAdy.getValor().getNorte().getPosibles()>0)){

					islaPila=(Islas)iPrimera.clone();
					islaPila.getValor().getSur().setPosibles(islaPila.getValor().getSur().getPosibles()-1);
					islaPila.getValor().getSur().setExistentes(islaPila.getValor().getSur().getExistentes()+1);
					
					//Como puedo a�adir un puente en la islaAdy por arriba,la borro de copia_l y la actualizo
					copia_l.remove(islaAdy);
					
					islaAdy.getValor().getNorte().setExistentes(islaAdy.getValor().getNorte().getExistentes()+1);
					islaAdy.getValor().getNorte().setPosibles(islaAdy.getValor().getNorte().getPosibles()-1);
					
					//a�adir iPrimera e islaAdy a copiasolpasoapaso
					orden.setIOrigen(islaPila.getPosicion());
					orden.setDirOrigen("sur");
					orden.setIDestino(islaAdy.getPosicion());
					orden.setDirDestino("norte");
							
			//si no, por el este (derecha)
			}else if ((iPrimera.getValor().getEste().getPosibles()>0) && (islaAdy=adyacentesEste(matriz, iPrimera, M))!=null && (islaAdy.getValor().getOeste().getPosibles()>0)){
				
					islaPila=(Islas)iPrimera.clone();
					islaPila.getValor().getEste().setPosibles(islaPila.getValor().getEste().getPosibles()-1);
					islaPila.getValor().getEste().setExistentes(islaPila.getValor().getEste().getExistentes()+1);
					
					//Como puedo a�ardir un puente en la islaAdy por el oeste,la borro de copia_l y la actualizo
					copia_l.remove(islaAdy);
						
					islaAdy.getValor().getOeste().setExistentes(islaAdy.getValor().getOeste().getExistentes()+1);
					islaAdy.getValor().getOeste().setPosibles(islaAdy.getValor().getOeste().getPosibles()-1);
					
					//A�adir iPrimera e islaAdy a copiasolpasoapaso
					orden.setIOrigen(islaPila.getPosicion());
					orden.setDirOrigen("este");
					orden.setIDestino(islaAdy.getPosicion());
					orden.setDirDestino("oeste");
				
			//si no, por el oeste (izqda)
			}else if ((iPrimera.getValor().getOeste().getPosibles()>0) && (islaAdy=adyacentesOeste(matriz, iPrimera, M))!=null && (islaAdy.getValor().getEste().getPosibles()>0)){
				
					islaPila=(Islas)iPrimera.clone();
					islaPila.getValor().getOeste().setPosibles(islaPila.getValor().getOeste().getPosibles()-1);
					islaPila.getValor().getOeste().setExistentes(islaPila.getValor().getOeste().getExistentes()+1);
					
					//Como puedo a�adir un puente en la islaAdy por el este,la borro islaAdy de copia_l
					copia_l.remove(islaAdy);	
					
					islaAdy.getValor().getEste().setExistentes(islaAdy.getValor().getEste().getExistentes()+1);
					islaAdy.getValor().getEste().setPosibles(islaAdy.getValor().getEste().getPosibles()-1);
					
					//A�adir iPrimera e islaAdy a copiasolpasoapaso
					orden.setIOrigen(islaPila.getPosicion());
					orden.setDirOrigen("oeste");
					orden.setIDestino(islaAdy.getPosicion());
					orden.setDirDestino("este");
				
			}
				
				//A�adir a copia_solucion_paso_a_paso
				copia_solucion_paso_a_paso.add(orden);
				problemas.SolucionIslas.pila.add(islaPila);
			
				//Actualiza conexo de islaPila
				Iterator it=islaAdy.getConexo().iterator();
				while (it.hasNext()){
					Islas iConexa=(Islas)it.next();
					if(!islaPila.getConexo().contains(iConexa))
						islaPila.getConexo().add(iConexa);
				}
				
				//Actualizo conexo de islaAdy
				Iterator it1=islaPila.getConexo().iterator();
				while (it1.hasNext()){
					Islas iConexa=(Islas)it1.next();
					if(!islaAdy.getConexo().contains(iConexa))
						islaAdy.getConexo().add(iConexa);
				}
				
				//Actualizo conexo de las demas islas de la lista
				Iterator it2=islaPila.getConexo().iterator();
				while(it2.hasNext()){
					Islas iC=(Islas)it2.next();
					if(!iC.equals(islaPila) && ! iC.equals(islaAdy)){
						Iterator it3=islaAdy.getConexo().iterator();
						while(it3.hasNext()){
							Islas iC2=(Islas)it.next();
							if(!iC.getConexo().contains(iC2))
								iC.getConexo().add(iC2);
						}
					}
				}
				
				//SI las islas estan terminadas, a�adirlas a copia_solucion y borrarlas de copia_l
				existentes=islaPila.getValor().getNorte().getExistentes()+islaPila.getValor().getSur().getExistentes()+
				islaPila.getValor().getEste().getExistentes()+islaPila.getValor().getOeste().getExistentes();
				if((islaPila.getValor().getN())==existentes){
					posiblesA0(islaPila);
					copia_solucion.add(islaPila);
				}else
					copia_l.add(islaPila);
				
				existentes=islaAdy.getValor().getNorte().getExistentes()+islaAdy.getValor().getSur().getExistentes()+
				islaAdy.getValor().getEste().getExistentes()+islaAdy.getValor().getOeste().getExistentes();
				if((islaAdy.getValor().getN())==existentes){
					posiblesA0(islaAdy);
					copia_solucion.add(islaAdy);
				}else
					copia_l.add(islaAdy);

	}
	

	
}

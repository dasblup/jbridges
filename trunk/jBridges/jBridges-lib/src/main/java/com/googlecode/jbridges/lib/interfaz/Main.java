/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

//import javax.swing.ImageIcon;

import com.googlecode.jbridges.lib.Coordenadas;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.problemas.Estrategias2D;
import com.googlecode.jbridges.lib.problemas.FabricaDeProblemas;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import com.googlecode.jbridges.lib.soluciones.estrategias.EstrategiaBackTrackingBasica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author mdiazoli
 */
public class Main {

    public static void main(String args[]) {
        //COmprobar si funciona compararListas

//        FabricaDeProblemas fp;
//        Tablero tablero;
//
//        fp = FabricaDeProblemas.getInstancia();
//        tablero = fp.obtenerProblema(Estrategias2D.ESTRATEGIA_DE_PRUEBA);
//
//        boolean iguales=false;
//        boolean puenteEncontrado=false;
//        List<ElementoSolucion> solUsuario=new LinkedList<ElementoSolucion>();
//        List<ElementoSolucion> sol=new LinkedList<ElementoSolucion>();
//
//        Isla i=null;
//        Isla f=null;
//
//        Coordenadas coordI;
//        Coordenadas coordF;
//
//        coordI = tablero.getCoordenadas(4, 2);
//        coordF = tablero.getCoordenadas(2, 2);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//
//        coordI = tablero.getCoordenadas(2, 2);
//        coordF = tablero.getCoordenadas(2, 4);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//        solUsuario.add(new ElementoSolucion(i, f));
//
//       // sol.add(new ElementoSolucion(f, i));
//       // sol.add(new ElementoSolucion(f, i));
//
//        coordI = tablero.getCoordenadas(2, 4);
//        coordF = tablero.getCoordenadas(4, 4);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//
//        sol.add(new ElementoSolucion(f, i));
//
//        coordI = tablero.getCoordenadas(4, 4);
//        coordF = tablero.getCoordenadas(4, 2);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        solUsuario.add(new ElementoSolucion(i, f));
//        solUsuario.add(new ElementoSolucion(i, f));
//
//        sol.add(new ElementoSolucion(f, i));
//        sol.add(new ElementoSolucion(f, i));
//
//        coordI = tablero.getCoordenadas(4, 2);
//        coordF = tablero.getCoordenadas(2, 2);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        sol.add(new ElementoSolucion(i, f));
//
//        if(MetodosEstaticos.comparaListas(solUsuario, sol)){
//            iguales=true;
//        }
//        System.out.println("Listas iguales?");
//        System.out.println(iguales);
//
//        //COMPROBAR SI FUNCIONA COMPRUEBAPUENTESIMPLE
//
//        coordI = tablero.getCoordenadas(2, 2);
//        coordF = tablero.getCoordenadas(2, 4);
//
//        i=(Isla)tablero.getCasilla(coordI);
//        f=(Isla)tablero.getCasilla(coordF);
//
//        if(MetodosEstaticos.comprobarPuente(sol, f, i, "doble")){
//            puenteEncontrado=true;
//        }
//        System.out.println("Puente correcto?");
//        System.out.println(puenteEncontrado);

    FabricaDeProblemas miFabrica=FabricaDeProblemas.getInstancia();
    Tablero problema = miFabrica.obtenerProblema(Estrategias2D.ESTRATEGIA_ALEATORIA_BASICA);


    EstrategiaBackTrackingBasica ebb=new EstrategiaBackTrackingBasica ();

    List <Solucion> soluciones=ebb.solucionar(problema);

    System.out.println("Tamaño lista soluciones"+ soluciones.size());
    }
}

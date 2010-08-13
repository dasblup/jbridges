/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.soluciones.estrategias;

import com.googlecode.jbridges.lib.Casilla;
import com.googlecode.jbridges.lib.Isla;
import com.googlecode.jbridges.lib.Sentido;
import com.googlecode.jbridges.lib.Tablero;
import com.googlecode.jbridges.lib.TableroArray;
import com.googlecode.jbridges.lib.excepciones.IslaNoEncontradaException;
import com.googlecode.jbridges.lib.excepciones.PuenteProhibidoException;
import com.googlecode.jbridges.lib.excepciones.SentidoInvalidoException;
import com.googlecode.jbridges.lib.soluciones.ElementoSolucion;
import com.googlecode.jbridges.lib.soluciones.EstrategiaSolucion;
import com.googlecode.jbridges.lib.soluciones.Solucion;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author pabloramix
 */
public class EstrategiaBackTrackingBasica implements EstrategiaSolucion {


    private List<Isla> candidatas0;
    private List<Isla> candidatas1;
    private List<Solucion> soluciones;

    public EstrategiaBackTrackingBasica () {
        candidatas0 = new LinkedList<Isla>();
        candidatas1 = new LinkedList<Isla>();
        soluciones = new LinkedList<Solucion>();
    }

    public List<Solucion> solucionar (Tablero t) {

        Etapa x;

        x = new Etapa();

        System.out.println("Etapa " + x.iteracion + " --> Entrando en solucionar");

        x.tablero = t;
        x.iteracion = 0;
        x.solucion = new Solucion();

        obtenerIslas(t, x);

        btTodas(x);

        System.out.println("Etapa " + x.iteracion + " --> Saliendo de solucionar");

        return soluciones;
    }

    private void btTodas (Etapa x) {

        Etapa xsig;
        Queue<Solucion> candidatos;
        Solucion candidatoSeleccionado;

        System.out.println("Etapa " + x.iteracion + " --> Entrando en btTodas");
        System.out.println("Etapa " + x.iteracion + " --> Solucion: " + x.solucion);
        System.out.println("Etapa " + x.iteracion + " --> Tablero: ");
        System.out.println(x.tablero);
        System.out.println("Etapa " + x.iteracion + " --> Componentes conexas: ");

        int n = 0;
        for (List<Isla> l : x.componentesConexas) {
            System.out.print("Componete conexa " + ++n + ": ");
            for (Isla i : l) {
                System.out.print(i);
            }
            System.out.println();
        }


        if (esSolucion(x)) {
            comunicarSolucion(x);
        }

        xsig = new Etapa(x);
        candidatos = calcularCandidatos(x);

        for (Solucion s : candidatos) {
            System.out.println("Etapa " + x.iteracion + " --> candidato: " + s);
        }

        while (quedanCandidatos(candidatos)) {
                candidatoSeleccionado = seleccionarCandidatos(candidatos, xsig);

                System.out.println("Etapa " + x.iteracion + " --> candidato seleccionado: " + candidatoSeleccionado);

                if (esPrometedor(candidatoSeleccionado, xsig)) {
                    anotarEnSolucion(candidatoSeleccionado, xsig);
                    btTodas(xsig);
                    cancelarAnotacion(candidatoSeleccionado, xsig);
                }
        }

        System.out.println("Etapa " + x.iteracion + " --> Saliendo de btTodas");
    }

    private Queue<Solucion> calcularCandidatos(Etapa x) {

        System.out.println("Etapa " + x.iteracion + " --> Entrando calcularCandidatos");
        System.out.println("Etapa " + x.iteracion + " --> Entrando en cargarListas");

        cargarListas(x);

        System.out.println("Etapa " + x.iteracion + " --> Saliendo de cargarListas");

        Queue<Solucion> s;
        s = new LinkedList<Solucion>();

        Solucion sol;
        sol = new Solucion();

        System.out.println("Etapa " + x.iteracion + " --> hayCandidatas?");

        while (hayCandidatas()) {

            System.out.println("Etapa " + x.iteracion + " --> hayCandidatas");
            System.out.println("Etapa " + x.iteracion + " --> Entrando en ponerPuentes");

            ponerPuentes(x.tablero, sol, x);

            System.out.println("Etapa " + x.iteracion + " --> Saliendo de ponerPuentes");
            System.out.println("Etapa " + x.iteracion + " --> Entrando en cargarListas");
            
            cargarListas(x);

            System.out.println("Etapa " + x.iteracion + " --> Saliendo de cargarListas");
        }

        System.out.println("Etapa " + x.iteracion + " --> listaIslas.isEmpty?(" + x.listaIslas.isEmpty() + ")");

        if (!x.listaIslas.isEmpty()) {

            Isla i;
            i = x.listaIslas.get(0);

            System.out.println("Etapa " + x.iteracion + " --> Isla: " + i);

            for(Sentido sent : Sentido.values()) {
                Solucion aux;

                try {
                    Isla vecina = i.getVecina(sent);
                    System.out.println("Etapa " + x.iteracion + " --> Vecina " + sent + ": " + vecina);

                    try {
                        aux = (Solucion) sol.clone();
                        aux.solucion.add(new ElementoSolucion(i, vecina));

                        try {
                            i.setPuente(vecina, false);

                        } catch (PuenteProhibidoException ppe) {}


                        System.out.println("Etapa " + x.iteracion + " --> Solucion candidata " + aux);

                        s.add(aux);
                    } catch (CloneNotSupportedException ex) {
                        //ex.printStackTrace();
                    }
                } catch (IslaNoEncontradaException inee) {
                    System.out.println("Isla no encontrada sentido " + sent);
                }
            }
        }

        return s;
    }

    private void obtenerIslas (Tablero t, Etapa x) {

        x.listaIslas = new LinkedList<Isla>();
        x.componentesConexas = new LinkedList<List<Isla>>();

        TableroArray tablero = (TableroArray) t;

         for (int i = 0; i < tablero.getAltura(); i++) {
            for (int j = 0; j < tablero.getAnchura(); j++) {

                Casilla c = tablero.getCasilla(tablero.getCoordenadas(i, j));
                if (c instanceof Isla) {
                    x.listaIslas.add((Isla)c);
                    List l = new LinkedList<Isla>();
                    l.add((Isla)c);
                    x.componentesConexas.add(l);
                }
            }
        }
    }

    private void cargarListas(Etapa x) {

        Isla islaVecina;

        int puentesPosibles;
        int numeroVecinas;

        Isla i;
        i = null;

        for (Iterator<Isla> it = x.listaIslas.iterator();
             it.hasNext();) {

            i = it.next();

            if (i.getPuentes() == i.getN()) {
                it.remove();

            } else {

                puentesPosibles = 0;
                numeroVecinas = 0;

                for(Sentido s : Sentido.values()) {
                    try {
                        islaVecina = i.getVecina(s);
                        numeroVecinas++;
                        puentesPosibles += islaVecina.getN() > 1 ? 2 : 1;
                    } catch(IslaNoEncontradaException inee) {}

                }

                if (puentesPosibles - i.getN() == 0) {
                    candidatas0.add(i);
                    it.remove();
                } else if (puentesPosibles - i.getN() == 1) {
                    if (i.getN() / numeroVecinas - 1 > 2) {
                        candidatas1.add(i);
                    }
                }
            }
        }

        System.out.print("cargarListas --> candidatas0: ");

        for (Isla is : candidatas0) {
            System.out.print("(" + is + ")");
        }
        System.out.println();

        System.out.print("cargarListas --> candidatas1: ");
        for (Isla is : candidatas1) {
            System.out.print("(" + is + ")");
        }
        System.out.println();

        System.out.print("cargarListas --> listaIslas: ");
        for (Isla is : x.listaIslas) {
            System.out.print("(" + is + ")");
        }
        System.out.println();
    }

    private boolean hayCandidatas() {
        return !(candidatas0.isEmpty() && candidatas1.isEmpty());
    }

    private void ponerPuentes(Tablero t, Solucion sol, Etapa x) {

        System.out.println("ponerPuebtes --> candidatas0: " + candidatas0.size());

         System.out.println("Etapa " + x.iteracion + " --> Componentes conexas: ");

        int n = 0;
        for (List<Isla> l : x.componentesConexas) {
            System.out.print("Componete conexa " + ++n + ": ");
            for (Isla i : l) {
                System.out.print(i);
            }
            System.out.println();
        }

        for (Iterator itCandidatas0 = candidatas0.iterator();
             itCandidatas0.hasNext();) {

            Isla i = (Isla) itCandidatas0.next();
            List<Isla> contieneI;

            contieneI = null;

             System.out.println("Etapa " + x.iteracion + " --> Buscando en que componente esta " + i);
             System.out.println("Etapa " + x.iteracion + " --> Componentes conexas: ");

             int j = 0;
             for (List<Isla> l : x.componentesConexas) {
             System.out.print("Componete conexa " + ++j + ": ");
             for (Isla is : l) {
                System.out.print(is);
             }
             System.out.println();
        }


            

            for(Iterator itCompConexas = x.componentesConexas.iterator();
                itCompConexas.hasNext();) {
                List<Isla> comp = (List<Isla>) itCompConexas.next();
                if (comp.contains(i)) {
                    contieneI = comp;
                }
            }

            for (Sentido s : Sentido.values()) {
                Isla vecina;
                try {
                    vecina = i.getVecina(s);
                    i.setPuente(vecina, false);
                    sol.solucion.add(new ElementoSolucion(i, vecina));
                    i.setPuente(s, false);
                    sol.solucion.add(new ElementoSolucion(i, vecina));

                    try {
                        contieneI.contains(vecina);
                    } catch (Exception e) {
                         System.out.println("Capturada excepcion" + e + " Vecina: " + vecina + " ContieneI: " + contieneI);
                    }
                    if (!contieneI.contains(vecina)) {

                        List<Isla> comp;

                        comp = null;

                        for(Iterator it = x.componentesConexas.iterator();
                            it.hasNext();) {

                            comp = (List<Isla>) it.next();
                            if (comp.contains(vecina)) {
                                contieneI.addAll(comp);
                                it.remove();
                            }
                        }
                    }

                } catch (IslaNoEncontradaException inee) {
                } catch (PuenteProhibidoException ppe) {
                } catch (SentidoInvalidoException sie) {}
            }

            itCandidatas0.remove();

        }

        for (Iterator itCandidatas1 = candidatas1.iterator();
             itCandidatas1.hasNext();) {

            Isla i = (Isla) itCandidatas1.next();
            List<Isla> contieneI;

            contieneI = null;

            for(List<Isla> comp: x.componentesConexas) {
                if (comp.contains(i)) {
                    contieneI = comp;
                }
            }

            for (Sentido s : Sentido.values()) {
                if (i.getPuentes(s) == 0) {
                    Isla vecina;
                    try {
                        vecina = i.getVecina(s);
                        i.setPuente(vecina, false);
                        sol.solucion.add(new ElementoSolucion(i, vecina));

                        if (!contieneI.contains(vecina)) {

                            List<Isla> comp;

                            comp = null;

                            for(Iterator it = x.componentesConexas.iterator();
                                it.hasNext();
                                comp = (List<Isla>) it.next()) {
                                if (comp.contains(vecina)) {
                                    contieneI.addAll(comp);
                                    it.remove();
                                }
                            }
                        }

                    } catch (IslaNoEncontradaException inee) {
                    } catch (PuenteProhibidoException ppe) {
                    }
                }
            }

            itCandidatas1.remove();
        }
    }

    private boolean esSolucion(Etapa x) {
        return x.listaIslas.isEmpty() && x.componentesConexas.size() == 1;
    }

    private void comunicarSolucion(Etapa x) {
        this.soluciones.add(x.solucion);
    }

    private boolean quedanCandidatos(Queue<Solucion> candidatos) {
        return !candidatos.isEmpty();
    }

    private Solucion seleccionarCandidatos(Queue<Solucion> candidatos, Etapa xsig) {
        return candidatos.poll();
    }

    private boolean esPrometedor(Solucion candidato, Etapa xsig) {
        return true;
    }

    private void anotarEnSolucion(Solucion candidato, Etapa xsig) {
        xsig.solucion.solucion.addAll(candidato.solucion);
        
        System.out.println("Etapa " + xsig.iteracion + " --> anotarEnSolucion --> Solucion: " + xsig.solucion + " --> Tamaño solucion: " + xsig.solucion.solucion.size());
        System.out.println("Etapa " + xsig.iteracion + " --> anotarEnSolucion --> Tamaño candidatos: " + candidato + " --> Tamaño candidatos: " + candidato.solucion.size());

        ElementoSolucion es = (ElementoSolucion) ((Queue)candidato.solucion).peek();

        try {
            es.inicio.setPuente(es.fin, false);
        } catch (PuenteProhibidoException ex) {}
    }

    private void cancelarAnotacion(Solucion candidato, Etapa xsig) {

        System.out.println("Etapa " + xsig.iteracion + " --> cancelarAnotacion --> Solucion: " + xsig.solucion + " --> Tamaño solucion: " + xsig.solucion.solucion.size());
        System.out.println("Etapa " + xsig.iteracion + " --> cancelarAnotacion --> Tamaño candidatos: " + candidato + " --> Tamaño candidatos: " + candidato.solucion.size());
        
        xsig.solucion.solucion = xsig.solucion.solucion
                .subList(0, xsig.solucion.solucion.size() - candidato.solucion.size());

        for (ElementoSolucion es : candidato.solucion) {
            try {
                es.inicio.borrarPuente(es.fin, false);
            } catch (PuenteProhibidoException ex) {}
        }
    }

    private class Etapa {

        int iteracion;
        Tablero tablero;
        Solucion solucion;
        List<Isla> listaIslas;
        List<List<Isla>> componentesConexas;

        private Etapa () {
            super();
        }

        private Etapa (Etapa x) {
            super();

            this.iteracion = x.iteracion + 1;
            this.tablero = x.tablero;
            this.solucion = x.solucion;
            this.listaIslas = new LinkedList<Isla>();

            for (Isla i : x.listaIslas) {
                this.listaIslas.add(i);
            }

            this.componentesConexas = new LinkedList<List<Isla>>();

            for (List<Isla> l : x.componentesConexas) {
                LinkedList<Isla> l1 = new LinkedList<Isla>();
                for (Isla i : l) {
                    l1.add(i);
                }
                this.componentesConexas.add(l1);
            }
        }
    }
}

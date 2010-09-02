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
import java.util.Stack;
import org.apache.log4j.Logger;

/**
 *
 * @author pabloramix
 */
public class EstrategiaBackTrackingBasica implements EstrategiaSolucion {


    private static Logger  logger = Logger.getLogger(EstrategiaBackTrackingBasica.class);
    private static final String NEWLINE = System.getProperty("line.separator");

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
        x.inicializar(t);

        btTodas(x);

        return soluciones;
    }

    private void btTodas (Etapa x) {

        Etapa xsig;
        Queue<ElementoSolucion> candidatos;
        ElementoSolucion candidatoSeleccionado;

        logger.debug("\n********************* ENTRANDO EN BTTODAS **********************\n");
        logger.debug(x);

        simplificar(x);

        if (esSolucion(x)) {

            comunicarSolucion(x);
        
        } else {

            candidatos = calcularCandidatos(x);

            if (logger.isDebugEnabled()) {
                for (ElementoSolucion es : candidatos) {
                    logger.debug("Etapa " + x.iteracion + " --> candidato: " + es);
                }
            }

            while (quedanCandidatos(candidatos)) {
                    candidatoSeleccionado = seleccionarCandidatos(candidatos, x);

                    logger.debug("Etapa " + x.iteracion + " --> candidato seleccionado: " + candidatoSeleccionado);

                    if (esPrometedor(candidatoSeleccionado, x)) {
                        anotarEnSolucion(candidatoSeleccionado, x);
                        xsig = new Etapa(x);
                        btTodas(xsig);
                        cancelarAnotacion(candidatoSeleccionado, x);
                    }
            }
        }
        logger.debug("\n********************* SALIENDO DE BTTODAS **********************\n");
        logger.debug(x);
    }

    private void simplificar(Etapa x) {

        logger.debug("\n******\nEtapa " + x.iteracion + "\n******\nSimplificar el tablero por el algoritmo de resolucion basico");

        cargarListas(x);

        while (hayCandidatas()) {

            logger.debug("\n******\nMientras hay islas de grado 0 o 1 ponemos los puesntes que correspondan\n******\n");

            //Un puente por sentido para las de grado 1 y todos para las de grado 0 (siempre que sea posible)

            ponerPuentes(x);

            logger.debug("\n******\nSolucion simplifaicada: " + x.solucion + "\n******\n");

            /*
             * Volvemos a hacer el calculo de candidatos hasta que no queden candidatos
             * bien porque resolvamos el problema o bien porque no consigamos ninguna isla
             * de grado 0 o 1
             */
            cargarListas(x);
        }

        logger.debug("\nsimplificar: Asi queda el tablero despues de simplificar:\n" + x.tablero);
    }

    private Queue<ElementoSolucion> calcularCandidatos(Etapa x) {

        Queue<ElementoSolucion> s;
        s = new LinkedList<ElementoSolucion>();

        if (!x.listaIslas.isEmpty()) {

            logger.debug("\n******\nQuedan islas pendientes tras aplicar el algoritmo básico"+
                    "\nHabrá que pasar a la siguiente etapa de bactracking\n******\n");

            Isla i;
            i = x.listaIslas.get(0);

            for(Sentido sent : Sentido.values()) {

                try {
                    Isla vecina = i.getVecina(sent);
                    i.setPuente(vecina, false);
                    logger.debug("\nCandidato: " + new ElementoSolucion(i, vecina) + "\nTablero antes de borrar el puente:\n" + x.tablero);
                    i.borrarPuente(vecina, false);
                    logger.debug("\nTablero despues de borrar el puente:\n" + x.tablero);
                    ElementoSolucion ele = new ElementoSolucion(i, vecina);
                    ele.obligatorio = true;
                    s.add(ele);
                } catch (PuenteProhibidoException ppe) {
//                    logger.debug("puente prohibido " + i + " --> " + vecina);
                } catch (IslaNoEncontradaException inee) {
//                    logger.debug("Isla no encontrada sentido " + sent);
                }
            }
        }

        logger.debug("\n******\nSe selecciona una isla y se genera una solucion parcial " +
                "\npor cada sentido en el que se pueda añadir un puente\n******\n");

        if (logger.isDebugEnabled()) {
            int i = 0;
            StringBuilder sb = new StringBuilder();
            for (ElementoSolucion elSol : s) {
                sb.append("\nCANDIDATO " + ++i + ": " + elSol);
            }
            logger.debug(sb);
        }

        logger.debug("\ncalcularCandidatos: Asi queda el tablero:\n" + x.tablero);

        return s;
    }

    private void cargarListas(Etapa x) {

        Isla islaVecina;

        int puentesPosibles;
        int numeroVecinas;

        Isla i;
        i = null;

        logger.debug("\n******\nCalculamos el grado de cada isla\n******\n");

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
                        puentesPosibles += islaVecina.getN() - islaVecina.getPuentes() < 2 ? islaVecina.getN() - islaVecina.getPuentes() : 2;
                    } catch(IslaNoEncontradaException inee) {}

                }

                logger.debug("\nIsla " + i + ": " + (puentesPosibles - (i.getN() - i.getPuentes())) +
                        "(" + (puentesPosibles / numeroVecinas == 2 ? "OK" : "KO") + ")" +
                        "(Puentes posibles (" + puentesPosibles + ") - N(" + i.getN() + ")");

                if (puentesPosibles - (i.getN() - i.getPuentes()) == 0) {
                    candidatas0.add(i);

                    it.remove();
                } else if (puentesPosibles - (i.getN() - i.getPuentes()) == 1) {
                    if (puentesPosibles / numeroVecinas == 2) {
                        candidatas1.add(i);
                    }
                }
            }
        }
    }

    private boolean hayCandidatas() {
        return !(candidatas0.isEmpty() && candidatas1.isEmpty());
    }

    private void ponerPuentes(Etapa x) {

        for (Iterator itCandidatas0 = candidatas0.iterator();
             itCandidatas0.hasNext();) {

            Isla i = (Isla) itCandidatas0.next();

            for (Sentido s : Sentido.values()) {
                Isla vecina;
                try {
                    vecina = i.getVecina(s);

                    if (i.getPuentes() < i.getN() && vecina.getPuentes() < vecina.getN()) {
                        i.setPuente(vecina, false);
                        x.solucion.solucion.add(new ElementoSolucion(i, vecina));
                        unirComponentesConexas(i, vecina, x);
                        logger.debug("\nponerPuentes: " + i + " --> " + vecina + " (candidatas0)");
                    }

                    if (i.getPuentes() < i.getN() && vecina.getPuentes() < vecina.getN()) {
                        i.setPuente(s, false);
                        x.solucion.solucion.add(new ElementoSolucion(i, vecina));
                        unirComponentesConexas(i, vecina, x);
                        logger.debug("\nponerPuentes: " + i + " --> " + vecina + " (candidatas0)");
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

            for (Sentido s : Sentido.values()) {
                if (i.getPuentes(s) == 0) {
                    Isla vecina;
                    try {
                        vecina = i.getVecina(s);
                        if (i.getPuentes() < i.getN() && vecina.getPuentes() < vecina.getN()) {

                            i.setPuente(vecina, false);
                            x.solucion.solucion.add(new ElementoSolucion(i, vecina));
                            unirComponentesConexas(i, vecina, x);
                            logger.debug("\nponerPuentes: " + i + " --> " + vecina + " (candidatas1)");
                        }

                    } catch (IslaNoEncontradaException inee) {
                    } catch (PuenteProhibidoException ppe) {
                    }
                }
            }

            itCandidatas1.remove();

            logger.debug("\nponerPuentes: Asi queda el tablero:\n" + x.tablero);
        }
    }

    private boolean esSolucion(Etapa x) {

        logger.debug("Etapa " + x.iteracion + " --> esSolucion --> Solucion: " + x.solucion);
        logger.debug("Etapa " + x.iteracion + " --> esSolucion --> listaIslas: " + x.listaIslas.size());
        logger.debug("Etapa " + x.iteracion + " --> esSolucion --> componentesConexas: " + x.componentesConexas.size());

        logger.debug("Etapa " + x.iteracion + " --> esSolucion --> esSolucion?: " + (x.listaIslas.isEmpty() && x.componentesConexas.size() == 1));

        return x.listaIslas.isEmpty() && x.componentesConexas.size() == 1;
    }

    private void comunicarSolucion(Etapa x) {
        x.solucion.iteracionBactracking = x.iteracion;
        this.soluciones.add(x.solucion);
    }

    private boolean quedanCandidatos(Queue<ElementoSolucion> candidatos) {
        return !candidatos.isEmpty();
    }

    private ElementoSolucion seleccionarCandidatos(Queue<ElementoSolucion> candidatos, Etapa xsig) {
        return candidatos.poll();
    }

    private boolean esPrometedor(ElementoSolucion candidato, Etapa xsig) {
        return candidato != null;
    }

    private void anotarEnSolucion(ElementoSolucion es, Etapa xsig) {

        xsig.listaIslasModificadas = xsig.copiarListaIslas();
        xsig.componentesConexasModificadas = xsig.copiarComponentesConexas();
        try {
            es.inicio.setPuente(es.fin, false);
            xsig.solucion.solucion.add(es);
            unirComponentesConexas(es.inicio, es.fin, xsig);
            if (es.inicio.getPuentes() == es.inicio.getN()) {
                xsig.listaIslas.remove(es.inicio);
            }
            if (es.fin.getPuentes() == es.fin.getN()) {
                xsig.listaIslas.remove(es.fin);
            }
        } catch (PuenteProhibidoException ex) {
            logger.debug(ex);
        }

//        logger.debug("Etapa " + xsig.iteracion + " --> anotarEnSolucion --> Tablero:\n" + xsig.tablero);
    }

    private void cancelarAnotacion(ElementoSolucion candidato, Etapa x) {

       logger.debug("\nTablero antes de cancelarAnotacion: \n" + x.tablero);

       logger.debug("\nSolucion antes de cancelarAnotacion: " + x.solucion);
       logger.debug("\nCandidato antes de cancelarAnotacion " + candidato);

       x.solucion.solucion.remove(candidato);

       logger.debug("\nSolucion despues de cancelarAnotacion: " + x.solucion);

        try {
            candidato.inicio.borrarPuente(candidato.fin, false);
        } catch (PuenteProhibidoException ex) {}

        x.listaIslas = x.copiarListaIslas(x.listaIslasModificadas);
        x.componentesConexas = x.copiarComponentesConexas(x.componentesConexasModificadas);

        logger.debug("\nTablero despues de cancelarAnotacion: \n" + x.tablero);
    }

    private void unirComponentesConexas(Isla i, Isla vecina, Etapa x) {

        List<Isla> contieneI;
        contieneI = null;

        for (Iterator itCompConexas = x.componentesConexas.iterator();
                itCompConexas.hasNext() && contieneI == null;) {
            List<Isla> comp = (List<Isla>) itCompConexas.next();
            if (comp.contains(i)) {
                contieneI = comp;
            }
        }

        if (!contieneI.contains(vecina)) {

            List<Isla> comp;
            comp = null;

            for (Iterator it = x.componentesConexas.iterator();
                    it.hasNext();) {

                comp = (List<Isla>) it.next();
                if (comp.contains(vecina) && comp != contieneI) {
                    contieneI.addAll(comp);
                    it.remove();
                }
            }
        }
    }

    private class Etapa {

        int iteracion;
        Tablero tablero;
        Solucion solucion;
        List<Isla> listaIslas;
        List<Isla> listaIslasModificadas;
        List<List<Isla>> componentesConexas;
        List<List<Isla>> componentesConexasModificadas;
        Etapa etapaAnterior;

        private Etapa () {
            super();
        }

        private Etapa (Etapa x) {
            super();

            this.iteracion = x.iteracion + 1;
            this.tablero = x.tablero;
            try {
                this.solucion = (Solucion) x.solucion.clone();
            } catch (CloneNotSupportedException cnse) {
                logger.error(cnse);
                throw new RuntimeException ("Error al clonar Solucion");
            }
            this.etapaAnterior = x;
            
            this.listaIslas = copiarListaIslas(x);

            this.componentesConexas = copiarComponentesConexas(x);
        }

        private void inicializar (Tablero t) {

            this.tablero = t;
            this.iteracion = 0;
            this.solucion = new Solucion();

            this.listaIslas = new LinkedList<Isla>();
            this.componentesConexas = new LinkedList<List<Isla>>();

            TableroArray ta = (TableroArray) t;

             for (int i = 0; i < ta.getAltura(); i++) {
                for (int j = 0; j < ta.getAnchura(); j++) {

                    Casilla c = ta.getCasilla(ta.getCoordenadas(i, j));
                    if (c instanceof Isla) {
                        this.listaIslas.add((Isla)c);
                        List l = new LinkedList<Isla>();
                        l.add((Isla)c);
                        this.componentesConexas.add(l);
                    }
                }
            }

            this.etapaAnterior = new Etapa();
            this.etapaAnterior.tablero = this.tablero;
            this.etapaAnterior.iteracion = -1;
            this.etapaAnterior.solucion = new Solucion();

            this.etapaAnterior.listaIslas = copiarListaIslas();
            this.etapaAnterior.componentesConexas = copiarComponentesConexas();
        }

        private List<Isla> copiarListaIslas(List<Isla> l) {

            List<Isla> li = new LinkedList<Isla>();
            for (Isla i : l) {
                li.add(i);
            }

            return li;
        }

        private List<Isla> copiarListaIslas(Etapa x) {
            return copiarListaIslas(x.listaIslas);
        }

        private List<Isla> copiarListaIslas () {
            return copiarListaIslas (this);
        }

        private List<List<Isla>> copiarComponentesConexas(List<List<Isla>> c) {

            List<List<Isla>> cc = new LinkedList<List<Isla>>();

            for (List<Isla> l : c) {
                LinkedList<Isla> l1 = new LinkedList<Isla>();
                for (Isla i : l) {
                    l1.add(i);
                }
                cc.add(l1);
            }

            return cc;
        }

        private List<List<Isla>> copiarComponentesConexas(Etapa x) {
            return copiarComponentesConexas(x.componentesConexas);
        }

        private List<List<Isla>> copiarComponentesConexas() {
            return copiarComponentesConexas(this);
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();

            sb.append(NEWLINE);
            sb.append("*********************************************************************************************************");
            sb.append(NEWLINE);
            sb.append("ETAPA: " + this.iteracion);
            sb.append(NEWLINE);
            sb.append("*********************************************************************************************************");
            sb.append(NEWLINE);
            sb.append("TABLERO : ");
            sb.append(NEWLINE);
            sb.append(this.tablero.toString());
            sb.append(NEWLINE);
            sb.append("*********************************************************************************************************");
            sb.append(NEWLINE);
            sb.append("SOLUCION : ");
            for (ElementoSolucion es : this.solucion.solucion) {
                sb.append(es + " ");
            }
            sb.append(NEWLINE);
            sb.append("*********************************************************************************************************");
            sb.append(NEWLINE);
            sb.append("LISTA ISLAS : ");
            for (Isla is : this.listaIslas) {
                sb.append(is + " ");
            }
            sb.append(NEWLINE);
            sb.append("*********************************************************************************************************");
            sb.append(NEWLINE);
            int j = 0;
            for (List<Isla> l : this.componentesConexas) {
                sb.append("COMPONENTE CONEXA " + ++j + ": ");
                for (Isla is : l) {
                    sb.append(is + " ");
                }
                sb.append(NEWLINE);
            }
            sb.append("*********************************************************************************************************");
            sb.append(NEWLINE);

            return sb.toString();
        }
    }
}

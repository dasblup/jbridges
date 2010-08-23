/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import java.io.Serializable;

/**
 *
 * @author mdiazoli
 */
public class Jugador{

    String nombre;
    int puntuacion;

    public Jugador(String nombre, int puntuacion){
        this.nombre=nombre;
        this.puntuacion=puntuacion;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setPuntuacion(int puntuacion){
        this.puntuacion=puntuacion;
    }

    public int getPuntuacion(){
        return this.puntuacion;
    }
}

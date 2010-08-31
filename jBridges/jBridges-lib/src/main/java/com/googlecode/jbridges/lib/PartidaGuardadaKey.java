/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import com.googlecode.jbridges.lib.Coordenadas2D;

/**
 *
 * @author mdiazoli
 */
public class PartidaGuardadaKey {

    Coordenadas2D coord;

    public PartidaGuardadaKey(Coordenadas2D coord){
        this.coord=coord;
    }

    public void setPartidaGuardadaKey(Coordenadas2D coord){
        this.coord=coord;
    }

    public Coordenadas2D getPartidaGuardada(){
        return coord;
    }

}

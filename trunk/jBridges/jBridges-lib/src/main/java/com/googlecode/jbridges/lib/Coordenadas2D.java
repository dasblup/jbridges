/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib;

import java.io.Serializable;

/**
 *
 * @author pabloramix
 */
public abstract class Coordenadas2D implements Coordenadas, Serializable {

    Integer x;
    Integer y;

    public Coordenadas2D (Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public final Integer getX() {
        return this.x;
    }

    public final Integer getY() {
        return this.y;
    }

    public final Coordenadas clone() {
        Coordenadas2D clon;

        clon = null;

        try {
            clon = (Coordenadas2D)super.clone();
        } catch (CloneNotSupportedException cne) {
            cne.printStackTrace(); //Imposible que pase
        }

        clon.x = new Integer(this.x);
        clon.y = new Integer(this.y);

        return clon;
    }

    public boolean equals (Object o) {
        return this.x.equals(((Coordenadas2D)o).x) &&
               this.y.equals(((Coordenadas2D)o).y);
    }
}

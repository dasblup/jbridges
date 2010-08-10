/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author mdiazoli
 */
public class Cronometro implements Runnable{

    Thread crono;


        /** Creates new form cronometro */
        public Cronometro() {

            crono = new Thread(this);
            crono.start();
        }
        int minutos = 0, segundos = 0, horas = 0;

        public void run(JLabel cronometro) {
            try {
                for (;;) {
                    if (segundos == 59) {
                        segundos = -1;
                        minutos++;
                    }
                    if (minutos == 59) {
                        minutos = -1;
                        horas++;
                    }
                    segundos++;
                    Font f2 = new Font("Tempus Sans ITC", Font.BOLD, 18);
                    cronometro.setFont(f2);
                    cronometro.setText(horas + ":" + minutos + ":" + segundos);
                    crono.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

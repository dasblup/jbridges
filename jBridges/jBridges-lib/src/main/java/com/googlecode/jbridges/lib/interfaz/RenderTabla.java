
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jbridges.lib.interfaz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
/**
 *
 * @author mdiazoli
 */

 class RenderTabla extends DefaultTableCellRenderer {


     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                                
                               
           JLabel cell =(JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           
           cell.setHorizontalAlignment(SwingConstants.CENTER);

           table.setBorder(BorderFactory.createEmptyBorder());
           //System.out.println("Entra en render");
            if(value!=null){
                if(value.equals(new Integer(1))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1.png"));
                }else if(value.equals(new Integer(2))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2.png"));
                }else if(value.equals(new Integer(3))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/3.png"));
                }else if(value.equals(new Integer(4))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/4.png"));
                }else if(value.equals(new Integer(5))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/5.png"));
                }else if(value.equals(new Integer(6))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/6.png"));
                }else if(value.equals(new Integer(7))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/7.png"));
                }else if(value.equals(new Integer(8))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/8.png"));
                }else if(value.equals("1PuenteH")){
                   // System.out.println("Entra en 1puenteH");
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1PuenteH.png"));
                   // System.out.println("Dibuja 1puenteH");
                }else if(value.equals("1PuenteV")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1PuenteV.png"));
                }else if(value.equals("2PuentesH")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesH.png"));
                }else if(value.equals("2PuentesV")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesV.png"));
                }else if(value.equals("1PuenteH_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1PuenteH_Error.png"));
                }else if(value.equals("1PuenteV_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1PuenteV_Error.png"));
                }else if(value.equals("2PuentesH_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesH_Error.png"));
                }else if(value.equals("2PuentesV_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesV_Error.png"));
                }else if(value.equals("1PuenteH_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1PuenteH_Pista.png"));
                }else if(value.equals("1PuenteV_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/1PuenteV_Pista.png"));
                }else if(value.equals("2PuentesH_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesH_Pista.png"));
                }else if(value.equals("2PuentesV_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesV_Pista.png"));
                }else if(value.equals("2PuentesV_1Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesV_1Error.png"));
                }else if(value.equals("2PuentesH_1Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("Imagenes/2PuentesH_1Error.png"));
                }else if(value.equals("borra")){
                    cell.setText("");
                    cell.setBackground(Color.white);
                    cell.setIcon(null);
                }else {
                    cell.setIcon(null);
                    cell.setText("");
                }
            }else{
               cell.setIcon(null);
               cell.setBackground(Color.white);
               cell.setText("");
            }
            return cell;
     }
 }

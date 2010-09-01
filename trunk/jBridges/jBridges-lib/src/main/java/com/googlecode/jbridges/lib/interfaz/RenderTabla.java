
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
            if(value!=null){
                if(value.equals(new Integer(1))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1.png"));
                }else if(value.equals(new Integer(2))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2.png"));
                }else if(value.equals(new Integer(3))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\3.png"));
                }else if(value.equals(new Integer(4))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\4.png"));
                }else if(value.equals(new Integer(5))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\5.png"));
                }else if(value.equals(new Integer(6))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\6.png"));
                }else if(value.equals(new Integer(7))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\7.png"));
                }else if(value.equals(new Integer(8))){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\8.png"));
                }else if(value.equals("1PuenteH")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1PuenteH.png"));
                }else if(value.equals("1PuenteV")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1PuenteV.png"));
                }else if(value.equals("2PuentesH")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesH.png"));
                }else if(value.equals("2PuentesV")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesV.png"));
                }else if(value.equals("1PuenteH_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1PuenteH_Error.png"));
                }else if(value.equals("1PuenteV_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1PuenteV_Error.png"));
                }else if(value.equals("2PuentesH_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesH_Error.png"));
                }else if(value.equals("2PuentesV_Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesV_Error.png"));
                }else if(value.equals("1PuenteH_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1PuenteH_Pista.png"));
                }else if(value.equals("1PuenteV_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\1PuenteV_Pista.png"));
                }else if(value.equals("2PuentesH_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesH_Pista.png"));
                }else if(value.equals("2PuentesV_Pista")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesV_Pista.png"));
                }else if(value.equals("2PuentesV_1Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesV_1Error.png"));
                }else if(value.equals("2PuentesH_1Error")){
                    cell.setText("");
                    cell.setIcon(new ImageIcon("C:\\Documents and Settings\\mdiazoli\\Escritorio\\Proyecto Fin de Carrera\\Imágenes\\2PuentesH_1Error.png"));
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

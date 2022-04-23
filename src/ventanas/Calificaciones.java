/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import java.sql.*;
import clases.Conexion;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Flia. Durán Cuello
 */
public class Calificaciones extends javax.swing.JFrame {

    public static int trimestre = 0;
    public static int calif[];
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form Calificaciones
     */
    public Calificaciones() {
        initComponents();
        setSize(500, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Calificaciones de " + Login.nombre);
        jLabel_Titulo.setText(Login.nombre + " - " + Menu.carrera);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        try {
            try (Connection cn = Conexion.conectar()) {
                try (PreparedStatement pst = cn.prepareStatement(
                        "select trimestre, materias, creditos, calificacion, calif from pensum where id_alumno = '" + Login.ID + "' order by trimestre, codigo")) {
                    ResultSet rs = pst.executeQuery();
                    
                    jTable_Calificacion = new JTable(model) {
                        private static final long serialVersionUID = 1L;
                        
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        };
                    };
                    jTable_Calificacion.getTableHeader().setReorderingAllowed(false);
                    
                    jScrollPane1.setViewportView(jTable_Calificacion);
                    
                    model.addColumn("T");
                    model.addColumn("Materia");
                    model.addColumn("CR");
                    model.addColumn("Calif.");
                    model.addColumn("Puntos");
                    
                    while (rs.next()) {
                        Object[] fila = new Object[5];
                        for (int i = 0; i < 5; i++) {
                            fila[i] = rs.getObject(i + 1);
                        }
                        model.addRow(fila);
                    }
                    pst.close();
                }
                cn.close();
            }
            
        } catch (SQLException e) {
            System.err.println("Error en el llenado de la tabla. " + e);
            JOptionPane.showMessageDialog(null, "Error en el llenado de la tabla. Contacte al administrador.");
        }
        
        TableColumnModel columnModel = jTable_Calificacion.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(0).setMinWidth(30);
        columnModel.getColumn(0).setMaxWidth(30);
        
        columnModel.getColumn(1).setPreferredWidth(270);
        columnModel.getColumn(1).setMinWidth(270);
        columnModel.getColumn(1).setMaxWidth(270);
        
        columnModel.getColumn(2).setPreferredWidth(30);
        columnModel.getColumn(2).setMinWidth(30);
        columnModel.getColumn(2).setMaxWidth(30);
        
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(3).setMinWidth(40);
        columnModel.getColumn(3).setMaxWidth(40);
        
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(4).setMinWidth(60);
        columnModel.getColumn(4).setMaxWidth(60);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable_Calificacion.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable_Calificacion.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable_Calificacion.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable_Calificacion.getColumnModel().getColumn(4).setCellRenderer(tcr);
        
        jTable_Calificacion.setRowHeight(30);
        
        if (jComboBox1.getSelectedItem().equals("Trimestre 1")) {
            jLabel_Promedio.setText(Float.toString(Trimestre1()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 2")){
            jLabel_Promedio.setText(Float.toString(Trimestre2()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 3")){
            jLabel_Promedio.setText(Float.toString(Trimestre3()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 4")){
            jLabel_Promedio.setText(Float.toString(Trimestre4()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 5")){
            jLabel_Promedio.setText(Float.toString(Trimestre5()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 6")){
            jLabel_Promedio.setText(Float.toString(Trimestre6()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 7")){
            jLabel_Promedio.setText(Float.toString(Trimestre7()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 8")){
            jLabel_Promedio.setText(Float.toString(Trimestre8()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 9")){
            jLabel_Promedio.setText(Float.toString(Trimestre9()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 10")){
            jLabel_Promedio.setText(Float.toString(Trimestre10()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 11")){
            jLabel_Promedio.setText(Float.toString(Trimestre11()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 12")){
            jLabel_Promedio.setText(Float.toString(Trimestre12()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 13")){
            jLabel_Promedio.setText(Float.toString(Trimestre13()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 14")){
            jLabel_Promedio.setText(Float.toString(Trimestre14()));
        } else if (jComboBox1.getSelectedItem().equals("Todos")){
            jLabel_Promedio.setText(Float.toString(General()));
        }
    }
    
    public float Trimestre1(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_1 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_1 = numerador / denominador;
        return promedio_1;
    }
    
    public float Trimestre2(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_2 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_2 = numerador / denominador;
        return promedio_2;
    }
    
    public float Trimestre3(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_3 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_3 = numerador / denominador;
        return promedio_3;
    }
    
    public float Trimestre4(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_4 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_4 = numerador / denominador;
        return promedio_4;
    }
    
    public float Trimestre5(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_5 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_5 = numerador / denominador;
        return promedio_5;
    }
    
    public float Trimestre6(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_6 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_6 = numerador / denominador;
        return promedio_6;
    }
    
    public float Trimestre7(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_7 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_7 = numerador / denominador;
        return promedio_7;
    }
    
    public float Trimestre8(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_8 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_8 = numerador / denominador;
        return promedio_8;
    }
    
    public float Trimestre9(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_9 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_9 = numerador / denominador;
        return promedio_9;
    }
    
    public float Trimestre10(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_10 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_10 = numerador / denominador;
        return promedio_10;
    }
    
    public float Trimestre11(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_11 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_11 = numerador / denominador;
        return promedio_11;
    }
    
    public float Trimestre12(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_12 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_12 = numerador / denominador;
        return promedio_12;
    }
    
    public float Trimestre13(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_13 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_13 = numerador / denominador;
        return promedio_13;
    }
    
    public float Trimestre14(){
        int contar = jTable_Calificacion.getRowCount();
        float numerador = 0;
        float denominador = 0;
        float promedio_14 = 0;
        
        for (int i = 0; i < contar; i++) {
            if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                
                numerador = numerador + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                denominador = denominador + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
            }
        }
        promedio_14 = numerador / denominador;
        return promedio_14;
    }
    
    public float General(){
        int contar = jTable_Calificacion.getRowCount();
        float trimestre_1 = 0;
        float numerador_1 = 0;
        float denominador_1 = 0;
        float trimestre_2 = 0;
        float numerador_2 = 0;
        float denominador_2 = 0;
        float trimestre_3 = 0;
        float numerador_3 = 0;
        float denominador_3 = 0;
        float trimestre_4 = 0;
        float numerador_4 = 0;
        float denominador_4 = 0;
        float trimestre_5 = 0;
        float numerador_5 = 0;
        float denominador_5 = 0;
        float trimestre_6 = 0;
        float numerador_6 = 0;
        float denominador_6 = 0;
        float trimestre_7 = 0;
        float numerador_7 = 0;
        float denominador_7 = 0;
        float trimestre_8 = 0;
        float numerador_8 = 0;
        float denominador_8 = 0;
        float trimestre_9 = 0;
        float numerador_9 = 0;
        float denominador_9 = 0;
        float trimestre_10 = 0;
        float numerador_10 = 0;
        float denominador_10 = 0;
        float trimestre_11 = 0;
        float numerador_11 = 0;
        float denominador_11 = 0;
        float trimestre_12 = 0;
        float numerador_12 = 0;
        float denominador_12 = 0;
        float trimestre_13 = 0;
        float numerador_13 = 0;
        float denominador_13 = 0;
        float trimestre_14 = 0;
        float numerador_14 = 0;
        float denominador_14 = 0;
        float promedio = 0;
        
        for (int i = 0; i < contar; i++) {
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 1) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_1 = numerador_1 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_1 = denominador_1 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_1 = numerador_1 / denominador_1;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 2) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_2 = numerador_2 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_2 = denominador_2 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_2 = numerador_2 / denominador_2;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 3) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_3 = numerador_3 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_3 = denominador_3 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_3 = numerador_3 / denominador_3;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 4) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_4 = numerador_4 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_4 = denominador_4 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_4 = numerador_4 / denominador_4;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 5) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_5 = numerador_5 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_5 = denominador_5 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_5 = numerador_5 / denominador_5;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 6) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_6 = numerador_6 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_6 = denominador_6 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_6 = numerador_6 / denominador_6;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 7) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_7 = numerador_7 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_7 = denominador_7 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_7 = numerador_7 / denominador_7;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 8) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_8 = numerador_8 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_8 = denominador_8 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_8 = numerador_8 / denominador_8;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 9) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_9 = numerador_9 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_9 = denominador_9 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_9 = numerador_9 / denominador_9;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 10) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_10 = numerador_10 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_10 = denominador_10 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_10 = numerador_10 / denominador_10;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 11) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_11 = numerador_11 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_11 = denominador_11 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_11 = numerador_11 / denominador_11;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 12) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_12 = numerador_12 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_12 = denominador_12 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_12 = numerador_12 / denominador_12;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 13) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_13 = numerador_13 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_13 = denominador_13 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_13 = numerador_13 / denominador_13;
            }
            if (Float.parseFloat(jTable_Calificacion.getValueAt(i, 0).toString()) == 14) {
                if ((Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()) > 0) && (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) > 0)) {
                    
                    numerador_14 = numerador_14 + (Float.parseFloat(jTable_Calificacion.getValueAt(i, 4).toString()) * Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString()));
                    denominador_14 = denominador_14 + Float.parseFloat(jTable_Calificacion.getValueAt(i, 2).toString());
                }
                trimestre_14 = numerador_14 / denominador_14;
            }
        }
        int casos = 0;
        if (trimestre_14 > 0) {
            casos = 14;
        } else if (trimestre_13 > 0) {
            casos = 13;
        } else if (trimestre_12 > 0) {
            casos = 12;
        } else if (trimestre_11 > 0) {
            casos = 11;
        } else if (trimestre_10 > 0) {
            casos = 10;
        } else if (trimestre_9 > 0) {
            casos = 9;
        } else if (trimestre_8 > 0) {
            casos = 8;
        } else if (trimestre_7 > 0) {
            casos = 7;
        } else if (trimestre_6 > 0) {
            casos = 6;
        } else if (trimestre_5 > 0) {
            casos = 5;
        } else if (trimestre_4 > 0) {
            casos = 4;
        } else if (trimestre_3 > 0) {
            casos = 3;
        } else if (trimestre_2 > 0) {
            casos = 2;
        } else if (trimestre_1 > 0) {
            casos = 1;
        }
        
        switch(casos){
            case 1:
                promedio = trimestre_1;
                break;
            case 2:
                promedio = (trimestre_1 + trimestre_2) / 2;
                break;
            case 3:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3) / 3;
                break;
            case 4:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4) / 4;
                break;
            case 5:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5) / 5;
                break;
            case 6:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6) / 6;
                break;
            case 7:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7) / 7;
                break;
            case 8:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8) / 8;
                break;
            case 9:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8 + trimestre_9) / 9;
                break;
            case 10:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8 + trimestre_9 + trimestre_10) / 10;
                break;
            case 11:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8 + trimestre_9 + trimestre_10 + trimestre_11) / 11;
                break;
            case 12:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8 + trimestre_9 + trimestre_10 + trimestre_11 + trimestre_12) / 12;
                break;
            case 13:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8 + trimestre_9 + trimestre_10 + trimestre_11 + trimestre_12 + trimestre_13) / 13;
                break;
            case 14:
                promedio = (trimestre_1 + trimestre_2 + trimestre_3 + trimestre_4 + trimestre_5 + trimestre_6 + trimestre_7 + trimestre_8 + trimestre_9 + trimestre_10 + trimestre_11 + trimestre_12 + trimestre_13 + trimestre_14) / 14;
                break;
        }
        return promedio;
    }
    
    /*
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/codinglove.png"));
        return retValue;
    }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Calificacion = new javax.swing.JTable();
        jLabel_Titulo_Promedio = new javax.swing.JLabel();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Promedio = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Titulo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Titulo.setText("jLabel1");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jTable_Calificacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_Calificacion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 450, 250));

        jLabel_Titulo_Promedio.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Titulo_Promedio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Titulo_Promedio.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Titulo_Promedio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Titulo_Promedio.setText("Promedio");
        getContentPane().add(jLabel_Titulo_Promedio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, -1, -1));

        jLabel_footer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_footer.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_footer.setText("Creado por Oscar Durán ®");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 540, -1, -1));

        jLabel_Promedio.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_Promedio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Promedio.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Promedio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_Promedio.setText("jLabel1");
        getContentPane().add(jLabel_Promedio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Trimestre 1", "Trimestre 2", "Trimestre 3", "Trimestre 4", "Trimestre 5", "Trimestre 6", "Trimestre 7", "Trimestre 8", "Trimestre 9", "Trimestre 10", "Trimestre 11", "Trimestre 12", "Trimestre 13", "Trimestre 14" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 110, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        
        int trimestre = jComboBox1.getSelectedIndex();
        String query = "";
        
        model.setRowCount(0);
        model.setColumnCount(0);
        
        try {
            try (Connection cn = Conexion.conectar()) {
                if (trimestre == 0) {
                    query = "select trimestre, materias, creditos, calificacion, calif from pensum where id_alumno = '" + Login.ID + "' order by trimestre, codigo";
                } else {
                    query = "select trimestre, materias, creditos, calificacion, calif from pensum where trimestre = '" + trimestre + "' and id_alumno = '" + Login.ID + "' order by trimestre, codigo";
                }
                
                try (PreparedStatement pst = cn.prepareStatement(query)) {
                    ResultSet rs = pst.executeQuery();
                    
                    jTable_Calificacion = new JTable(model) {
                        private static final long serialVersionUID = 1L;
                        
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        };
                    };
                    jTable_Calificacion.getTableHeader().setReorderingAllowed(false);
                    
                    jScrollPane1.setViewportView(jTable_Calificacion);
                    
                    model.addColumn("T");
                    model.addColumn("Materia");
                    model.addColumn("CR");
                    model.addColumn("Calif.");
                    model.addColumn("Puntos");
                    
                    while (rs.next()) {
                        Object[] fila = new Object[5];
                        for (int i = 0; i < 5; i++) {
                            fila[i] = rs.getObject(i + 1);
                        }
                        model.addRow(fila);
                    }
                    pst.close();
                }
                cn.close();
            }
            
        } catch (SQLException e) {
            System.err.println("Error al recuperar los registros de trimestres. " + e);
            JOptionPane.showMessageDialog(null, "Error al recuperar los registros de trimestres. Contacte al administrador.");
        }
        
        TableColumnModel columnModel = jTable_Calificacion.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(0).setMinWidth(30);
        columnModel.getColumn(0).setMaxWidth(30);
        
        columnModel.getColumn(1).setPreferredWidth(270);
        columnModel.getColumn(1).setMinWidth(270);
        columnModel.getColumn(1).setMaxWidth(270);
        
        columnModel.getColumn(2).setPreferredWidth(30);
        columnModel.getColumn(2).setMinWidth(30);
        columnModel.getColumn(2).setMaxWidth(30);
        
        columnModel.getColumn(3).setPreferredWidth(40);
        columnModel.getColumn(3).setMinWidth(40);
        columnModel.getColumn(3).setMaxWidth(40);
        
        columnModel.getColumn(4).setPreferredWidth(60);
        columnModel.getColumn(4).setMinWidth(60);
        columnModel.getColumn(4).setMaxWidth(60);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable_Calificacion.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable_Calificacion.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable_Calificacion.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable_Calificacion.getColumnModel().getColumn(4).setCellRenderer(tcr);
        
        jTable_Calificacion.setRowHeight(30);
        
        if (jComboBox1.getSelectedItem().equals("Trimestre 1")) {
            jLabel_Promedio.setText(Float.toString(Trimestre1()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 2")){
            jLabel_Promedio.setText(Float.toString(Trimestre2()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 3")){
            jLabel_Promedio.setText(Float.toString(Trimestre3()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 4")){
            jLabel_Promedio.setText(Float.toString(Trimestre4()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 5")){
            jLabel_Promedio.setText(Float.toString(Trimestre5()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 6")){
            jLabel_Promedio.setText(Float.toString(Trimestre6()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 7")){
            jLabel_Promedio.setText(Float.toString(Trimestre7()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 8")){
            jLabel_Promedio.setText(Float.toString(Trimestre8()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 9")){
            jLabel_Promedio.setText(Float.toString(Trimestre9()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 10")){
            jLabel_Promedio.setText(Float.toString(Trimestre10()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 11")){
            jLabel_Promedio.setText(Float.toString(Trimestre11()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 12")){
            jLabel_Promedio.setText(Float.toString(Trimestre12()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 13")){
            jLabel_Promedio.setText(Float.toString(Trimestre13()));
        } else if (jComboBox1.getSelectedItem().equals("Trimestre 14")){
            jLabel_Promedio.setText(Float.toString(Trimestre14()));
        } else if (jComboBox1.getSelectedItem().equals("Todos")) {
            jLabel_Promedio.setText(Float.toString(General()));
        }
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calificaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calificaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel_Promedio;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Titulo_Promedio;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Calificacion;
    // End of variables declaration//GEN-END:variables

}

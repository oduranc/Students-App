/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.sql.*;
import clases.Conexion;

/**
 *
 * @author Flia. Durán Cuello
 */
public class Materia extends javax.swing.JFrame {
    
    String id_alumno = Login.ID;
    String nombre_alumno = Login.nombre;
    String codigo_materia = Pensum.codigo;
    String estado = Pensum.estado;
    int creditos = Pensum.creditos;
    int trimestre = Pensum.trimestre;
    String profesor = Pensum.profesor;
    String materia = Pensum.materia;
    String calificacion = Pensum.calificacion;

    /**
     * Creates new form Materia
     */
    public Materia() {
        initComponents();
        setSize(500, 460);
        setResizable(false);
        setTitle("Editar Materia");
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();
        
        jTextField_id_alumno.setText(id_alumno);
        jTextField_nombre_alumno.setText(nombre_alumno);
        jTextField_codigo.setText(codigo_materia);
        jTextField_profesor.setText(profesor);
        jTextField_materia.setText(materia);
        cmb_estado.setSelectedItem(estado);
        cmb_trimestre.setSelectedIndex(trimestre -1);
        cmb_calificacion.setSelectedItem(calificacion);
        jSpinner_creditos.setValue(creditos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_id_alumno = new javax.swing.JLabel();
        jLabel_nombre_alumno = new javax.swing.JLabel();
        jLabel_materia = new javax.swing.JLabel();
        jLabel_codigo = new javax.swing.JLabel();
        jLabel_profesor = new javax.swing.JLabel();
        jLabel_creditos = new javax.swing.JLabel();
        jTextField_id_alumno = new javax.swing.JTextField();
        jTextField_nombre_alumno = new javax.swing.JTextField();
        jTextField_profesor = new javax.swing.JTextField();
        jTextField_materia = new javax.swing.JTextField();
        jTextField_codigo = new javax.swing.JTextField();
        jSpinner_creditos = new javax.swing.JSpinner();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_estado = new javax.swing.JLabel();
        cmb_estado = new javax.swing.JComboBox<>();
        jLabel_trimestre = new javax.swing.JLabel();
        cmb_trimestre = new javax.swing.JComboBox<>();
        jLabel_calificacion = new javax.swing.JLabel();
        cmb_calificacion = new javax.swing.JComboBox<>();
        jButton_Actualizar = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Titulo.setText("Editar Materia");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_id_alumno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_id_alumno.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_id_alumno.setText("ID del alumno:");
        getContentPane().add(jLabel_id_alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel_nombre_alumno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_nombre_alumno.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_nombre_alumno.setText("Nombre del alumno:");
        getContentPane().add(jLabel_nombre_alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel_materia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_materia.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_materia.setText("Nombre de la materia:");
        getContentPane().add(jLabel_materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel_codigo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_codigo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_codigo.setText("Código de la materia:");
        getContentPane().add(jLabel_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel_profesor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_profesor.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_profesor.setText("Nombre del profesor:");
        getContentPane().add(jLabel_profesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel_creditos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_creditos.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_creditos.setText("Créditos:");
        getContentPane().add(jLabel_creditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jTextField_id_alumno.setEditable(false);
        jTextField_id_alumno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField_id_alumno.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_id_alumno.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(jTextField_id_alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, -1));

        jTextField_nombre_alumno.setEditable(false);
        jTextField_nombre_alumno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField_nombre_alumno.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_nombre_alumno.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(jTextField_nombre_alumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 230, -1));

        jTextField_profesor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField_profesor.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_profesor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(jTextField_profesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 230, -1));

        jTextField_materia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField_materia.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_materia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(jTextField_materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 230, -1));

        jTextField_codigo.setEditable(false);
        jTextField_codigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField_codigo.setForeground(new java.awt.Color(0, 0, 0));
        jTextField_codigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        getContentPane().add(jTextField_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 230, -1));

        jSpinner_creditos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        getContentPane().add(jSpinner_creditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 120, -1));

        jLabel_footer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_footer.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_footer.setText("Creado por Oscar Durán ®");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 400, -1, -1));

        jLabel_estado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_estado.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_estado.setText("Estado de la materia:");
        getContentPane().add(jLabel_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));

        cmb_estado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmb_estado.setForeground(new java.awt.Color(255, 255, 255));
        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No cursada", "Cursada", "Cursando" }));
        getContentPane().add(cmb_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 120, -1));

        jLabel_trimestre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_trimestre.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_trimestre.setText("Trimestre:");
        getContentPane().add(jLabel_trimestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        cmb_trimestre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmb_trimestre.setForeground(new java.awt.Color(255, 255, 255));
        cmb_trimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" }));
        getContentPane().add(cmb_trimestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 120, -1));

        jLabel_calificacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_calificacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_calificacion.setText("Calificación:");
        getContentPane().add(jLabel_calificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        cmb_calificacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmb_calificacion.setForeground(new java.awt.Color(255, 255, 255));
        cmb_calificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "EI", "A", "B+", "B", "C+", "C" }));
        getContentPane().add(cmb_calificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 120, -1));

        jButton_Actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 120, 40));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed

        int validacion = 0;
        String materia, profesor, estado, calificacion;
        int creditos, trimestre;
        float calif = 0;
        
        materia = jTextField_materia.getText().trim();
        profesor = jTextField_profesor.getText().trim();
        estado = cmb_estado.getSelectedItem().toString();
        creditos = (int) jSpinner_creditos.getValue();
        trimestre = cmb_trimestre.getSelectedIndex();
        calificacion = cmb_calificacion.getSelectedItem().toString();
        if (calificacion.equals("EI") || calificacion.equals("A")) {
            calif = (float) 4;
        } else if (calificacion.equals("B+")){
            calif = (float) 3.5;
        } else if (calificacion.equals("B")){
            calif = (float) 3;
        } else if (calificacion.equals("C+")){
            calif = (float) 2.5;
        } else if (calificacion.equals("C")){
            calif = (float) 2;
        } else {
            calif = (float) 0;
        }
        
        if (materia.equals("")) {
            jTextField_materia.setBackground(Color.red);
            validacion++;
        }
        
        if (validacion == 0) {
            
            try {
                
                try (Connection cn = Conexion.conectar()) {
                    try (PreparedStatement pst = cn.prepareStatement(
                            "UPDATE pensum SET materias=?, profesores=?, estado=?, trimestre=?, creditos=?, calificacion=?, calif=? "
                                    + "WHERE codigo = '" + Pensum.codigo + "'")) {
                        pst.setString(1, materia);
                        pst.setString(2, profesor);
                        pst.setString(3, estado);
                        pst.setInt(4, (trimestre + 1));
                        pst.setInt(5, creditos);
                        pst.setString(6, calificacion);
                        pst.setFloat(7, calif);
                        
                        pst.executeUpdate();
                        pst.close();
                    }
                    cn.close();
                }
               
               jTextField_materia.setBackground(Color.green);
               jTextField_codigo.setBackground(Color.green);
               jTextField_profesor.setBackground(Color.green);
               
               jTextField_materia.setText("");
               jTextField_codigo.setText("");
               jTextField_profesor.setText("");
               
               JOptionPane.showMessageDialog(null, "Actualización correcta.");
               this.dispose();
               
               
            } catch (SQLException e) {
                System.err.println("Error en actualizar la materia." + e);
                JOptionPane.showMessageDialog(null, "Error en actualizar la materia. Contacte al administrador.");
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Es obligatorio colocar el nombre de la materia.");
        }
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(Materia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Materia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Materia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Materia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Materia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_calificacion;
    private javax.swing.JComboBox<String> cmb_estado;
    private javax.swing.JComboBox<String> cmb_trimestre;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_calificacion;
    private javax.swing.JLabel jLabel_codigo;
    private javax.swing.JLabel jLabel_creditos;
    private javax.swing.JLabel jLabel_estado;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JLabel jLabel_id_alumno;
    private javax.swing.JLabel jLabel_materia;
    private javax.swing.JLabel jLabel_nombre_alumno;
    private javax.swing.JLabel jLabel_profesor;
    private javax.swing.JLabel jLabel_trimestre;
    private javax.swing.JSpinner jSpinner_creditos;
    private javax.swing.JTextField jTextField_codigo;
    private javax.swing.JTextField jTextField_id_alumno;
    private javax.swing.JTextField jTextField_materia;
    private javax.swing.JTextField jTextField_nombre_alumno;
    private javax.swing.JTextField jTextField_profesor;
    // End of variables declaration//GEN-END:variables
}

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
import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Flia. Durán Cuello
 */
public class Pensum extends javax.swing.JFrame {

    String nombre = Login.nombre;
    public static String carrera = "";
    DefaultTableModel model = new DefaultTableModel();
    public static String codigo = "";
    public static String materia = "";
    public static String profesor = "";
    public static String estado = "";
    public static int trimestre = 0;
    public static int creditos = 0;
    public static String calificacion = "";

    /**
     * Creates new form Pensum
     */
    
    public Pensum() {
        initComponents();
        setSize(720, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        try {
            try (Connection cn = Conexion.conectar()) {
                try (PreparedStatement pst = cn.prepareStatement(
                        "select carrera_alumno from usuarios where id_alumno = '" + Login.ID + "'")) {
                    ResultSet rs = pst.executeQuery();
                    
                    if (rs.next()) {
                        carrera = rs.getString("carrera_alumno");
                        jLabel_Carrera.setText(carrera);
                        jLabel_Titulo.setText("Pensum de " + nombre);
                        setTitle("Pensum - " + nombre + " - " + carrera);
                    }
                    pst.close();
                }
                cn.close();
            }
            
        } catch (SQLException e) {
            System.err.println("Error al seleccionar la carrera del alumno." + e);
            JOptionPane.showMessageDialog(null, "Error al encontrar la carrera del alumno. Contacte al administrador.");
        }

        try {
            try (Connection cn = Conexion.conectar()) {
                try (PreparedStatement pst = cn.prepareStatement(
                        "select codigo, materias, profesores, estado, trimestre, creditos, calificacion from pensum where id_alumno = '" + Login.ID + "' order by trimestre, codigo")) {
                    ResultSet rs = pst.executeQuery();
                    
                    jTable_Pensum = new JTable(model) {
                        private static final long serialVersionUID = 1L;
                        
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        };
                    };
                    jTable_Pensum.getTableHeader().setReorderingAllowed(false);
                    
                    jScrollPane1.setViewportView(jTable_Pensum);
                    
                    model.addColumn("Código");
                    model.addColumn("Materia");
                    model.addColumn("Profesor");
                    model.addColumn("Estado");
                    model.addColumn("T");
                    model.addColumn("CR");
                    model.addColumn("Cal.");
                    
                    while (rs.next()) {
                        Object[] fila = new Object[7];
                        for (int i = 0; i < 7; i++) {
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

        TableColumnModel columnModel = jTable_Pensum.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(0).setMinWidth(70);
        columnModel.getColumn(0).setMaxWidth(70);
        
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(1).setMinWidth(300);
        columnModel.getColumn(1).setMaxWidth(300);
        
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(2).setMinWidth(120);
        columnModel.getColumn(2).setMaxWidth(120);
        
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMinWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);
        
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(4).setMinWidth(40);
        columnModel.getColumn(4).setMaxWidth(40);
        
        columnModel.getColumn(5).setPreferredWidth(40);
        columnModel.getColumn(5).setMinWidth(40);
        columnModel.getColumn(5).setMaxWidth(40);
        
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(6).setMinWidth(50);
        columnModel.getColumn(6).setMaxWidth(50);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable_Pensum.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable_Pensum.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable_Pensum.getColumnModel().getColumn(6).setCellRenderer(tcr);

        jTable_Pensum.setRowHeight(30);
        
        jLabel_suma.setText(Integer.toString(suma()));
        
        ObetenerDatosTabla();

    }
    
    public int suma(){
        int contar = jTable_Pensum.getRowCount();
        int suma = 0;
        for (int i = 0; i < contar; i++) {
            
            suma = suma + Integer.parseInt(jTable_Pensum.getValueAt(i, 5).toString());
            
        }
        return suma;
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
        jLabel_Carrera = new javax.swing.JLabel();
        jLabel_footer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Pensum = new javax.swing.JTable();
        jButton_AgregarMateria = new javax.swing.JButton();
        cmb_trimestre = new javax.swing.JComboBox<>();
        jLabel_suma = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_Titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Titulo.setText("jLabel1");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_Carrera.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_Carrera.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Carrera.setText("jLabel2");
        jLabel_Carrera.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel_Carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jLabel_footer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_footer.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_footer.setText("Creado por Oscar Durán ®");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 440, -1, -1));

        jTable_Pensum.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_Pensum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_PensumMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Pensum);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 720, 210));

        jButton_AgregarMateria.setBackground(new java.awt.Color(153, 153, 255));
        jButton_AgregarMateria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_AgregarMateria.setForeground(new java.awt.Color(255, 255, 255));
        jButton_AgregarMateria.setText("Agregar Materia");
        jButton_AgregarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AgregarMateriaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_AgregarMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 170, 40));

        cmb_trimestre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cmb_trimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Trimestre 1", "Trimestre 2", "Trimestre 3", "Trimestre 4", "Trimestre 5", "Trimestre 6", "Trimestre 7", "Trimestre 8", "Trimestre 9", "Trimestre 10", "Trimestre 11", "Trimestre 12", "Trimestre 13", "Trimestre 14" }));
        cmb_trimestre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_trimestreItemStateChanged(evt);
            }
        });
        cmb_trimestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_trimestreActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_trimestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 120, -1));

        jLabel_suma.setText("jLabel1");
        getContentPane().add(jLabel_suma, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 340, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AgregarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AgregarMateriaActionPerformed
        new AgregarMateria().setVisible(true);
    }//GEN-LAST:event_jButton_AgregarMateriaActionPerformed

    private void cmb_trimestreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_trimestreItemStateChanged
        
        int trimestre = cmb_trimestre.getSelectedIndex();
        String query = "";
        
        model.setRowCount(0);
        model.setColumnCount(0);
        
        try {
            try (Connection cn = Conexion.conectar()) {
                if (trimestre == 0) {
                    query = "select codigo, materias, profesores, estado, trimestre, creditos, calificacion from pensum where id_alumno = '" + Login.ID + "' order by trimestre, codigo";
                } else {
                    query = "select codigo, materias, profesores, estado, trimestre, creditos, calificacion from pensum where trimestre = '" + trimestre + "' and id_alumno = '" + Login.ID + "' order by trimestre, codigo";
                }
                
                try (PreparedStatement pst = cn.prepareStatement(query)) {
                    ResultSet rs = pst.executeQuery();
                    
                    jTable_Pensum = new JTable(model) {
                        private static final long serialVersionUID = 1L;
                        
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        };
                    };
                    jTable_Pensum.getTableHeader().setReorderingAllowed(false);
                    
                    jScrollPane1.setViewportView(jTable_Pensum);
                    
                    model.addColumn("Código");
                    model.addColumn("Materia");
                    model.addColumn("Profesor");
                    model.addColumn("Estado");
                    model.addColumn("T");
                    model.addColumn("CR");
                    model.addColumn("Cal.");
                    
                    while (rs.next()) {
                        Object[] fila = new Object[7];
                        for (int i = 0; i < 7; i++) {
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
        
        TableColumnModel columnModel = jTable_Pensum.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(0).setMinWidth(70);
        columnModel.getColumn(0).setMaxWidth(70);
        
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(1).setMinWidth(300);
        columnModel.getColumn(1).setMaxWidth(300);
        
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(2).setMinWidth(120);
        columnModel.getColumn(2).setMaxWidth(120);
        
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(3).setMinWidth(80);
        columnModel.getColumn(3).setMaxWidth(80);
        
        columnModel.getColumn(4).setPreferredWidth(40);
        columnModel.getColumn(4).setMinWidth(40);
        columnModel.getColumn(4).setMaxWidth(40);
        
        columnModel.getColumn(5).setPreferredWidth(40);
        columnModel.getColumn(5).setMinWidth(40);
        columnModel.getColumn(5).setMaxWidth(40);
        
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(6).setMinWidth(50);
        columnModel.getColumn(6).setMaxWidth(50);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable_Pensum.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable_Pensum.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable_Pensum.getColumnModel().getColumn(6).setCellRenderer(tcr);
        
        jTable_Pensum.setRowHeight(30);
        
        jLabel_suma.setText(Integer.toString(suma()));
        
        ObetenerDatosTabla();
    }//GEN-LAST:event_cmb_trimestreItemStateChanged

    private void jTable_PensumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_PensumMouseClicked

    }//GEN-LAST:event_jTable_PensumMouseClicked

    private void cmb_trimestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_trimestreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_trimestreActionPerformed

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
            java.util.logging.Logger.getLogger(Pensum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pensum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pensum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pensum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pensum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_trimestre;
    private javax.swing.JButton jButton_AgregarMateria;
    private javax.swing.JLabel jLabel_Carrera;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JLabel jLabel_suma;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Pensum;
    // End of variables declaration//GEN-END:variables
   
    public void ObetenerDatosTabla(){
        jTable_Pensum.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int fila_point = jTable_Pensum.rowAtPoint(e.getPoint());
                int columna_point = 0;
                
                if (fila_point > -1) {
                    codigo = model.getValueAt(fila_point, columna_point).toString();
                    materia = model.getValueAt(fila_point, 1).toString();
                    profesor = model.getValueAt(fila_point, 2).toString();
                    estado = model.getValueAt(fila_point, 3).toString();
                    trimestre = (int) model.getValueAt(fila_point, 4);
                    creditos = (int) model.getValueAt(fila_point, 5);
                    calificacion = model.getValueAt(fila_point, 6).toString();
                    
                    Materia materia = new Materia();
                    materia.setVisible(true);
                }
            }
        });
    }
    
}

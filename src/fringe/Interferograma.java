/*
 * Interferograma.java
 *
 * Created on 16 de octubre de 2007, 03:20 PM
 */

package fringe;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel.*;
import java.awt.event.*;

/**
 *
 * @author  Julio Vielma
 */
public class Interferograma extends javax.swing.JFrame {
    //matriz que contendra al interferograma
    int[][] inter;
    //matriz que contendra al interferograma escalado
    int[][]franjas;
    //variables para controlar los maximos y moverse entre columnas y renglones
    int ren=0, col=0, xmax=0, ymax=0;
    //variable de objeto CANVAS para dibujar el patron de franjas
    private MiCanvas jCanvas;
    private MiCanvas jCanvas2;
    
    /** Creates new form Interferograma */
    public Interferograma() {
        //inicizalizacion de componentes
        initComponents(); 
        initComponents2();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    

    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        javax.swing.JPanel jP_terminos;

        jP_terminos = new javax.swing.JPanel();
        jTiluminacion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTamplitud = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTterm1 = new javax.swing.JTextField();
        jTterm2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTterm3 = new javax.swing.JTextField();
        jTterm4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTterm5 = new javax.swing.JTextField();
        jTterm6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTterm7 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTterm8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTterm9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTterm10 = new javax.swing.JTextField();
        jTterm11 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTterm12 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTterm13 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTterm14 = new javax.swing.JTextField();
        jTterm15 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTerror = new javax.swing.JTextField();
        jBgenerar = new javax.swing.JButton();
        jP_Caracteristicas = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTesc_x = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTesc_y = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTerror3 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTerror4 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTzoom = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jS_zoom = new javax.swing.JSlider();
        jP_Grafica = new javax.swing.JPanel();
        jP_Grafica2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interferograma...");
        jP_terminos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jP_terminos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)), "Terminos del Polinomio:"));
        jTiluminacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTiluminacion.setText("128");
        jP_terminos.add(jTiluminacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 40, -1));

        jLabel1.setText("+");
        jP_terminos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jTamplitud.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTamplitud.setText("128");
        jP_terminos.add(jTamplitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 40, -1));

        jLabel2.setText(" * ( Cos (");
        jP_terminos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        jLabel3.setText("+");
        jP_terminos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jTterm1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm1.setText("0");
        jP_terminos.add(jTterm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 50, -1));

        jTterm2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm2.setText("-0.7316");
        jP_terminos.add(jTterm2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 60, -1));

        jLabel4.setText("x+");
        jP_terminos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        jLabel5.setText("y+");
        jP_terminos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jTterm3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm3.setText("-0.2801");
        jP_terminos.add(jTterm3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, 60, -1));

        jTterm4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm4.setText("0.0065");
        jP_terminos.add(jTterm4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 60, -1));

        jLabel6.setText("x^2+");
        jP_terminos.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        jLabel7.setText("xy+");
        jP_terminos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        jTterm5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm5.setText(".00036");
        jP_terminos.add(jTterm5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 60, -1));

        jTterm6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm6.setText("-0.0372");
        jP_terminos.add(jTterm6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 60, -1));

        jLabel8.setText("y^2+");
        jP_terminos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        jTterm7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm7.setText("0.00212");
        jP_terminos.add(jTterm7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 60, -1));

        jLabel9.setText("x^3+");
        jP_terminos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jTterm8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm8.setText("0.00272");
        jP_terminos.add(jTterm8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 60, -1));

        jLabel10.setText("x^2y+");
        jP_terminos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jTterm9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm9.setText("0.001");
        jP_terminos.add(jTterm9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 60, -1));

        jLabel11.setText("xy^2+");
        jP_terminos.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel12.setText("y^3+");
        jP_terminos.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        jTterm10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm10.setText("-0.002");
        jP_terminos.add(jTterm10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 60, -1));

        jTterm11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm11.setText("1.2e-005");
        jP_terminos.add(jTterm11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 60, -1));

        jLabel13.setText("x^4+");
        jP_terminos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));

        jTterm12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm12.setText("0.00015");
        jP_terminos.add(jTterm12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, -1));

        jLabel14.setText("x^3y+");
        jP_terminos.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jTterm13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm13.setText("-0.00023");
        jP_terminos.add(jTterm13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 60, -1));

        jLabel15.setText("x^2*y^2+");
        jP_terminos.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jLabel16.setText("xy^3+");
        jP_terminos.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, -1, -1));

        jTterm14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm14.setText("0.00011");
        jP_terminos.add(jTterm14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 60, -1));

        jTterm15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTterm15.setText(".000086");
        jP_terminos.add(jTterm15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 60, -1));

        jLabel17.setText("y^4+");
        jP_terminos.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel18.setText(") )");
        jP_terminos.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        jTerror.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTerror.setText("0.5");
        jP_terminos.add(jTerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 40, -1));

        jBgenerar.setText("Generar");
        jBgenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBgenerarActionPerformed(evt);
            }
        });

        jP_terminos.add(jBgenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, -1, -1));

        jP_Caracteristicas.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuraci\u00f3n Escala:"));
        jLabel19.setText("Escalas (pixeles):");

        jTesc_x.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTesc_x.setText("30");

        jLabel20.setText("X:");

        jLabel21.setText("Y:");

        jTesc_y.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTesc_y.setText("30");

        jLabel22.setText("Rango Interferograma:");

        jTerror3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTerror3.setText("-1");

        jLabel23.setText("<= X <=");

        jTerror4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTerror4.setText("-1");

        jLabel24.setText("Zoom:");

        jTzoom.setEditable(false);
        jTzoom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTzoom.setText("1");

        jLabel25.setText("x");

        jS_zoom.setMajorTickSpacing(1);
        jS_zoom.setMaximum(10);
        jS_zoom.setMinimum(1);
        jS_zoom.setMinorTickSpacing(1);
        jS_zoom.setPaintLabels(true);
        jS_zoom.setPaintTicks(true);
        jS_zoom.setValue(1);
        jS_zoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jS_zoomStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jP_CaracteristicasLayout = new org.jdesktop.layout.GroupLayout(jP_Caracteristicas);
        jP_Caracteristicas.setLayout(jP_CaracteristicasLayout);
        jP_CaracteristicasLayout.setHorizontalGroup(
            jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jP_CaracteristicasLayout.createSequentialGroup()
                .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jP_CaracteristicasLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jP_CaracteristicasLayout.createSequentialGroup()
                                .add(jLabel19)
                                .add(19, 19, 19)
                                .add(jLabel20)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTesc_x, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(16, 16, 16)
                                .add(jLabel21)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTesc_y, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jP_CaracteristicasLayout.createSequentialGroup()
                                .add(116, 116, 116)
                                .add(jTerror3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel23)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTerror4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jP_CaracteristicasLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel24)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTzoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel25))))
                    .add(jP_CaracteristicasLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel22))
                    .add(jP_CaracteristicasLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jS_zoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jP_CaracteristicasLayout.setVerticalGroup(
            jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jP_CaracteristicasLayout.createSequentialGroup()
                .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel19)
                        .add(jLabel20)
                        .add(jTesc_x, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel21)
                        .add(jTesc_y, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jS_zoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(jTzoom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel25))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jP_CaracteristicasLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(jTerror3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel23)
                    .add(jTerror4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jP_Grafica.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafica:"));
        org.jdesktop.layout.GroupLayout jP_GraficaLayout = new org.jdesktop.layout.GroupLayout(jP_Grafica);
        jP_Grafica.setLayout(jP_GraficaLayout);
        jP_GraficaLayout.setHorizontalGroup(
            jP_GraficaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 204, Short.MAX_VALUE)
        );
        jP_GraficaLayout.setVerticalGroup(
            jP_GraficaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 170, Short.MAX_VALUE)
        );

        jP_Grafica2.setBorder(javax.swing.BorderFactory.createTitledBorder("Grafica:"));
        org.jdesktop.layout.GroupLayout jP_Grafica2Layout = new org.jdesktop.layout.GroupLayout(jP_Grafica2);
        jP_Grafica2.setLayout(jP_Grafica2Layout);
        jP_Grafica2Layout.setHorizontalGroup(
            jP_Grafica2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 204, Short.MAX_VALUE)
        );
        jP_Grafica2Layout.setVerticalGroup(
            jP_Grafica2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 170, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jP_terminos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 398, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(45, 45, 45)
                        .add(jP_Caracteristicas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jP_Grafica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jP_Grafica2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jP_Grafica, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jP_terminos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jP_Grafica2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jP_Caracteristicas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jS_zoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jS_zoomStateChanged
// TODO add your handling code here:
        jTzoom.setText(Integer.toString(jS_zoom.getValue()));
        generar_int();
        
    }//GEN-LAST:event_jS_zoomStateChanged
   
    /**
     * M�todo que inicia componentes no visuales (CANVAS)
     */
    private void initComponents2(){
        //genera la instancia de canvas
         jCanvas = new MiCanvas();
         //coloca el canvas dentro del panel jp_Grafica
         jP_Grafica.add(jCanvas, java.awt.BorderLayout.CENTER);
         //genera la instancia de canvas
         jCanvas2 = new MiCanvas();
         //coloca el canvas dentro del panel jp_Grafica
         jP_Grafica2.add(jCanvas2, java.awt.BorderLayout.CENTER);
//         jP_Grafica.setLayout(new FlowLayout(FlowLayout.CENTER));       
    }
    
    
    private void jBgenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBgenerarActionPerformed
// TODO add your handling code here:
        generar_int();
        
        
          
    }//GEN-LAST:event_jBgenerarActionPerformed
    
    public void generar_int(){
        //variables para la escala de la grafica
        int ren=0, col=0, xmax=0, ymax=0;
        //valor para controlar el nivel de franja
        double c=0;
        //variable para escalar la imagen es decir hacer el zoom
        int zoom;
        
        //obtiene los valores de la escala de la grafica (Columna o Ancho)
        xmax=(int)Double.parseDouble(jTesc_x.getText());
        //obtiene los valores de la escala de la grafica (Fila o Alto)
        ymax=(int)Double.parseDouble(jTesc_y.getText());

        c=xmax/2;
        
        //declara las variables de los terminos de polinomio asi como la luz y la amplitud
        double t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14,t15, tluz, tampli, terro;
        //obtiene los valores de entrada de los terminos, luz, amplitud y zoom
        t1=Double.parseDouble(jTterm1.getText());
        t2=Double.parseDouble(jTterm2.getText());
        t3=Double.parseDouble(jTterm3.getText());
        t4=Double.parseDouble(jTterm4.getText());
        t5=Double.parseDouble(jTterm5.getText());
        t6=Double.parseDouble(jTterm6.getText());
        t7=Double.parseDouble(jTterm7.getText());
        t8=Double.parseDouble(jTterm8.getText());
        t9=Double.parseDouble(jTterm9.getText());
        t10=Double.parseDouble(jTterm10.getText());
        t11=Double.parseDouble(jTterm11.getText());
        t12=Double.parseDouble(jTterm12.getText());
        t13=Double.parseDouble(jTterm13.getText());
        t14=Double.parseDouble(jTterm14.getText());
        t15=Double.parseDouble(jTterm15.getText());
        tluz=Double.parseDouble(jTiluminacion.getText());
        tampli=Double.parseDouble(jTamplitud.getText());
        terro=Double.parseDouble(jTerror.getText());
        zoom=Integer.parseInt(jTzoom.getText());
        
        //inicializa la matriz qe tendra al interferograma
        int inter[][]= new int[xmax][ymax]; 
        //inicializa la matriz qe tendra al interferograma escalado
        int franjas[][]=new int[xmax*zoom][ymax*zoom]; //para escalar 
        
        
        //Funcion con la que genera los pixeles del interferograma en base al polinomio
        
        for (int yi=0;yi<ymax;yi++)//recorre filas  
             for (int xi=0;xi<xmax;xi++){//recorre columnas
                inter[yi][xi]= (int)(tluz + tampli*Math.cos(
                t1 +
                (t2*(xi-c)) +
                (t3*(yi-c)) +
                (t4*Math.pow(xi-c,2))+
                (t5*(xi-c)*(yi-c))+
                (t6*Math.pow(yi-c,2))+
                (t7*Math.pow(xi-c,3))+
                (t8*Math.pow(xi-c,2)*(yi-c))+ 
                (t9*(xi-c)*Math.pow(yi-c,2))+        
                (t10*Math.pow(yi-c,3))+
                (t11*Math.pow(xi-c,4))+
                (t12*Math.pow(xi-c,3)*(yi-c))+
                (t13*Math.pow(xi-c,2)*Math.pow(yi-c,2))+
                (t14*xi*Math.pow(yi-c,3))+
                (t15*Math.pow(yi-c,4))
                +terro
                ));
                //System.out.println("el resultados es: "+"("+yi+","+xi+")"+"= " +inter[yi][xi]);
             }
        //termina de generar el interferograma
        
        //variables para escalar el interferograma
        int cont=0,cont2=0,ipixel=0;
        int salt=zoom; //para escalar
        
        //recorre filas
        for (int yi=0;yi<ymax;yi=yi+1){
            //recorre columnas
            for (int xi=0;xi<xmax;xi=xi+1){
                //obtiene el pixel en la posicion (x,y)
                ipixel=inter[yi][xi];
                //for para escalar el pixel
                for(int yy=(yi+cont);yy<(yi+cont+salt);yy++){
                    for(int xx=(xi+cont2);xx<(xi+cont2+salt);xx++){
                        franjas[yy][xx]=ipixel;
                    }
                }
                //variable que controla las ventanas de escalamiento
                cont2=cont2+salt-1;
            }
            //variable que controla las ventanas de escalamiento
            cont=cont+salt-1;
            //reinicia la variable de salto de ventanas
            cont2=0;
        }// termina de escalar la imagen de franjas
        
        //metodo para validar que se va a dibujar        
        jCanvas.setDibujaFranjas(true);
        jCanvas.setDibujaQuadree(false);
        //metodo que dibuja el interferograma, se le envia la matriz de franjas escalada, alto y ancho escalados
        jCanvas.dibuja_interferograma(franjas,xmax*zoom,ymax*zoom);
        //repinta el interferograma
        jCanvas.repaint();
        
         //metodo para validar que se va a dibujar        
        jCanvas2.setDibujaFranjas(true);
        jCanvas2.setDibujaQuadree(true);
        //metodo que dibuja el interferograma, se le envia la matriz de franjas escalada, alto y ancho escalados
        jCanvas2.dibuja_interferograma(franjas,xmax*zoom,ymax*zoom);
        //repinta el interferograma
        jCanvas2.repaint();
        
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interferograma().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBgenerar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP_Caracteristicas;
    private javax.swing.JPanel jP_Grafica;
    private javax.swing.JPanel jP_Grafica2;
    private javax.swing.JSlider jS_zoom;
    private javax.swing.JTextField jTamplitud;
    private javax.swing.JTextField jTerror;
    private javax.swing.JTextField jTerror3;
    private javax.swing.JTextField jTerror4;
    private javax.swing.JTextField jTesc_x;
    private javax.swing.JTextField jTesc_y;
    private javax.swing.JTextField jTiluminacion;
    private javax.swing.JTextField jTterm1;
    private javax.swing.JTextField jTterm10;
    private javax.swing.JTextField jTterm11;
    private javax.swing.JTextField jTterm12;
    private javax.swing.JTextField jTterm13;
    private javax.swing.JTextField jTterm14;
    private javax.swing.JTextField jTterm15;
    private javax.swing.JTextField jTterm2;
    private javax.swing.JTextField jTterm3;
    private javax.swing.JTextField jTterm4;
    private javax.swing.JTextField jTterm5;
    private javax.swing.JTextField jTterm6;
    private javax.swing.JTextField jTterm7;
    private javax.swing.JTextField jTterm8;
    private javax.swing.JTextField jTterm9;
    private javax.swing.JTextField jTzoom;
    // End of variables declaration//GEN-END:variables
  
}
/*
    class MiCanvas extends Canvas {
    private int ancho;
    private int alto;
    private int matrix[][];
    private int r=1;
    
    
    public MiCanvas(int m[][],int x, int y) {
        this.ancho = x;
        this.alto = y;
        this.matrix=m;
        System.out.println("wey soy el 1");
        reshape( 15,15,ancho,alto );
        }

    public void paint( Graphics g ) {
        System.out.println("ya entro wey");
        
        for (int yi=0;yi<alto;yi++)
             for (int xi=0;xi<ancho;xi++){
                //inter2[index]=(255<<24)|(255<<16)|255;
                g.setColor(new java.awt.Color(matrix[xi][yi],matrix[xi][yi],matrix[xi][yi]));
                g.drawOval(xi,yi,r,r);
             }
        //g.setColor( Color.blue );
        //g.fillRect( 15,15,ancho,alto );
        }
    public void borrar(){
        repaint();
    }
  
    }
*/
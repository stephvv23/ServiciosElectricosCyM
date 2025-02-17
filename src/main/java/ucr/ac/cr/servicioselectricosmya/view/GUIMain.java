/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ucr.ac.cr.servicioselectricosmya.view;

import java.awt.event.ActionListener;

/**
 *
 * @author Stephanie
 */
public class GUIMain extends javax.swing.JFrame {

    /**
     * Creates new form GUIMain
     */
    public GUIMain() {
        initComponents();
    }

    public void listen(ActionListener controller) {
        this.jmiBill.addActionListener(controller);
        this.jmiClient.addActionListener(controller);
        this.jmiReportBill.addActionListener(controller);
        this.jmiReportClient.addActionListener(controller);
        this.jmiExit.addActionListener(controller);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiClient = new javax.swing.JMenuItem();
        jmiBill = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiReportClient = new javax.swing.JMenuItem();
        jmiReportBill = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Exit");

        jmiExit.setText("Exit");
        jMenu1.add(jmiExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jmiClient.setText("Client");
        jMenu2.add(jmiClient);

        jmiBill.setText("Bill");
        jMenu2.add(jmiBill);

        jMenu3.setText("Reports");

        jmiReportClient.setText("Clients");
        jMenu3.add(jmiReportClient);

        jmiReportBill.setText("Bills");
        jMenu3.add(jmiReportBill);

        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmiBill;
    private javax.swing.JMenuItem jmiClient;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiReportBill;
    private javax.swing.JMenuItem jmiReportClient;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fileinfo.FileInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.UIManager;

/**
 *
 * @author Charlie Hard
 */
public class BigFileFinder extends javax.swing.JFrame {

    private static Map<Long, File> allFiles = new TreeMap(Collections.reverseOrder());

    /**
     * Creates new form LargeFiles
     */
    public BigFileFinder() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelOutput = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOutput = new javax.swing.JList<>();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(listOutput);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOutputLayout = new javax.swing.GroupLayout(panelOutput);
        panelOutput.setLayout(panelOutputLayout);
        panelOutputLayout.setHorizontalGroup(
            panelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart)
                .addContainerGap())
        );
        panelOutputLayout.setVerticalGroup(
            panelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOutputLayout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        Path path = Paths.get(System.getProperty("user.home"));
        while (path.getParent() != null) {
            path = path.getParent();
        }
        File file = path.toFile();
        File[] curLevelFiles = file.listFiles();
        for (File f : curLevelFiles) {
            getChildren(f);
        }

        ArrayList<File> biggestFiles = new ArrayList(allFiles.values());
        DefaultListModel listModel = new DefaultListModel();
        
        DecimalFormat df = new DecimalFormat("##0.00");

        for (int i = 0; i < 100; i++) {
            try {
                listModel.addElement(biggestFiles.get(i).getCanonicalPath() + "   [" + df.format(biggestFiles.get(i).length() / 1000000000D) + "gb]");                
            } catch (IOException ex) {}
        }

        listOutput.setModel(listModel);

    }//GEN-LAST:event_btnStartActionPerformed

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
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BigFileFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BigFileFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BigFileFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BigFileFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BigFileFinder().setVisible(true);
            }
        });
    }

    public void getChildren(File f) {
        if (f.isDirectory() && !f.isHidden() && f.listFiles().length != 0 && f.listFiles() != null) {
            for (File fc : f.listFiles()) {
                try {
                    getChildren(fc);
                } catch (NullPointerException ex) {
                    allFiles.put(fc.length(), fc.getAbsoluteFile());
                }
            }
        } else if (f.isFile() && !f.isHidden()) {
            allFiles.put(f.length(), f.getAbsoluteFile());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listOutput;
    private javax.swing.JPanel panelOutput;
    // End of variables declaration//GEN-END:variables
}

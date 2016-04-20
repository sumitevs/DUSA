/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sumit_Saseendran
 */
public class JTableSample extends javax.swing.JFrame{
    // create object of table and table model
 JTable tbl = new JTable();
 DefaultTableModel dtm = new DefaultTableModel(0, 0);

// add header of the table
String header[] = new String[] { "Prority", "Task Title", "Start",
            "Pause", "Stop", "Statulses" };

JTableSample(){  
 dtm.setColumnIdentifiers(header);
 tbl.setModel(dtm);

     // add row dynamically into the table      
for (int count = 1; count <= 30; count++) {
        dtm.addRow(new Object[] { "data", "data", "data",
                "data", "data", "data" });
 }

add(tbl);
setVisible(true);
setResizable(false);
setLocation(520,170);
setSize(1000,850);
}

public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JTableSample().setVisible(true);
                
            }
        });
    }

}

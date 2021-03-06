/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pie;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sumit_Saseendran
 */
public class Execution extends javax.swing.JPanel {

    /**
     * Creates new form Inventory
     */
    public Execution() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        timeoutField = new javax.swing.JTextField();
        startFileField = new javax.swing.JTextField();
        cmdStdField = new javax.swing.JTextField();
        cmdFileField = new javax.swing.JTextField();
        modsField = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        returnCodeMsgTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmdFStdField = new javax.swing.JTextField();
        cmdFFileField = new javax.swing.JTextField();
        sysRebootCheckBox = new javax.swing.JCheckBox();
        copyPayloadCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        cmdFileNameField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmdFFileNameField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel1.setText("Time out");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel2.setText("Start File name");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel3.setText("Command( inventory to Standard output)");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel4.setText("Command( inventory to file)");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel6.setText("Modules(separate by comma)");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel7.setText("Return codes & messages");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel8.setText("Code/ID");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel9.setText("Message");

        jButton1.setText("add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        timeoutField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        startFileField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        cmdStdField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        cmdFileField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        modsField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        modsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modsFieldActionPerformed(evt);
            }
        });

        jTextField8.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        jTextField9.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        jButton2.setText("remove");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        returnCodeMsgTable.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        returnCodeMsgTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code/ID", "Message"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        returnCodeMsgTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                returnCodeMsgTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(returnCodeMsgTable);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel10.setText("<html>Command<br>(force apply inventory to file)</html>");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel11.setText("<html>Command<br>(force apply inventory to Standard output)</html>");

        cmdFStdField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        cmdFStdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdFStdFieldActionPerformed(evt);
            }
        });

        cmdFFileField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        sysRebootCheckBox.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        sysRebootCheckBox.setText("System reboot required");

        copyPayloadCheckBox.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        copyPayloadCheckBox.setText("Copy of payload to current directory required");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel5.setText("File Name");

        cmdFileNameField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel12.setText("File Name");

        cmdFFileNameField.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(13, 13, 13)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))))
                            .addComponent(copyPayloadCheckBox)
                            .addComponent(sysRebootCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(300, 300, 300)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmdStdField, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(timeoutField)
                                    .addComponent(startFileField)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(294, 294, 294))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cmdFFileField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(81, 81, 81)
                                                .addComponent(cmdFileField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel12))
                                        .addGap(69, 69, 69)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(modsField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                    .addComponent(cmdFStdField)
                                    .addComponent(cmdFileNameField)
                                    .addComponent(cmdFFileNameField, javax.swing.GroupLayout.Alignment.LEADING))))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(timeoutField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(startFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmdStdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmdFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmdFileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdFStdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdFFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cmdFFileNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sysRebootCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(copyPayloadCheckBox))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void modsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modsFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modsFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField8.getText().isEmpty() || jTextField9.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Enter id and message.");
        } else {
            addRow(jTextField8.getText(),jTextField9.getText());
            jTextField8.setText("");
            jTextField9.setText("");
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) returnCodeMsgTable.getModel();
        if(tableModel.getRowCount()>0){
            tableModel.removeRow(returnCodeMsgTable.getSelectedRow());
            returnCodeMsgTable.setModel(tableModel);
        }
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void returnCodeMsgTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnCodeMsgTableMouseReleased
        jButton2.setEnabled(true);
    }//GEN-LAST:event_returnCodeMsgTableMouseReleased

    private void cmdFStdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdFStdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdFStdFieldActionPerformed

    public JCheckBox getjCheckBox1() {
        return sysRebootCheckBox;
    }

    public JCheckBox getjCheckBox2() {
        return copyPayloadCheckBox;
    }

    public JTable getjTable1() {
        return returnCodeMsgTable;
    }

    public JTextField getjTextField1() {
        return timeoutField;
    }

    public JTextField getjTextField2() {
        return startFileField;
    }

    public JTextField getjTextField3() {
        return cmdStdField;
    }

    public JTextField getjTextField4() {
        return cmdFileField;
    }

    public JTextField getjTextField5() {
        return cmdFStdField;
    }

    public JTextField getjTextField6() {
        return cmdFFileField;
    }

    public JTextField getjTextField7() {
        return modsField;
    }

    public JTextField getjTextField10() {
        return cmdFileNameField;
    }

    public JTextField getjTextField11() {
        return cmdFFileNameField;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cmdFFileField;
    private javax.swing.JTextField cmdFFileNameField;
    private javax.swing.JTextField cmdFStdField;
    private javax.swing.JTextField cmdFileField;
    private javax.swing.JTextField cmdFileNameField;
    private javax.swing.JTextField cmdStdField;
    private javax.swing.JCheckBox copyPayloadCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField modsField;
    private javax.swing.JTable returnCodeMsgTable;
    private javax.swing.JTextField startFileField;
    private javax.swing.JCheckBox sysRebootCheckBox;
    private javax.swing.JTextField timeoutField;
    // End of variables declaration//GEN-END:variables

    public void addRow(String id, String message) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) returnCodeMsgTable.getModel();
        tableModel.addRow(new Object[]{id,message});
    }
}

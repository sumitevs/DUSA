/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mup;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sumit_Saseendran
 */
public class InstallerBehaviour extends javax.swing.JPanel {

    /**
     * Creates new form panel3sub5
     */
    public InstallerBehaviour() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        behaviourTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        staticExtractionField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(527, 673));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabel1.setText("heading");

        behaviourTable.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        behaviourTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Switch/Delimiter", "Value", "Require Value", "Enclose"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        behaviourTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                behaviourTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(behaviourTable);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton1.setText("add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton2.setText("remove");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jRadioButton1.setText("Static Extraction");
        jRadioButton1.setVisible(false);
        jRadioButton1.setSelected(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jRadioButton2.setText("Programmatic Extraction");
        jRadioButton2.setVisible(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        staticExtractionField.setVisible(false);
        staticExtractionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                staticExtractionFieldFocusLost(evt);
            }
        });

        jLabel2.setText("Switch/Delimiter*");

        jLabel3.setText("Value");

        jLabel4.setText("Enclose");

        jCheckBox1.setText("Require Value");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(122, 122, 122)
                        .addComponent(staticExtractionField, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2))
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(jCheckBox1)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(staticExtractionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Switch/delimiter.");
        } else {
            addRow(new Object[]{jTextField2.getText(), jTextField3.getText(), jCheckBox1.isSelected(), jTextField4.getText()});
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jCheckBox1.setSelected(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) behaviourTable.getModel();
        if(tableModel.getRowCount()>0){
            tableModel.removeRow(behaviourTable.getSelectedRow());
            behaviourTable.setModel(tableModel);
        }
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        staticExtractionField.setVisible(true);
        behaviourTable.setVisible(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        jCheckBox1.setVisible(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        staticExtractionField.setText("");
        staticExtractionField.setVisible(false);
        behaviourTable.setVisible(true);
        jButton1.setEnabled(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jTextField4.setVisible(true);
        jCheckBox1.setVisible(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void behaviourTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_behaviourTableMouseReleased
        jButton2.setEnabled(true);
    }//GEN-LAST:event_behaviourTableMouseReleased

    private void staticExtractionFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staticExtractionFieldFocusLost
        // TODO add your handling code here:
        staticExtractionField.setText(staticExtractionField.getText().trim());
    }//GEN-LAST:event_staticExtractionFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable behaviourTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField staticExtractionField;
    // End of variables declaration//GEN-END:variables

    public JRadioButton getjRadioButton1() {
        return jRadioButton1;
    }

    public JRadioButton getjRadioButton2() {
        return jRadioButton2;
    }

    public JTable getjTable1() {
        return behaviourTable;
    }

    public JTextField getjTextField1() {
        return staticExtractionField;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }
  

    public void setTitle(String title) {
        jLabel1.setText(title);
        if(title.equals("Extract Drivers")){
            jRadioButton1.setVisible(true);
            jRadioButton1.setSelected(true);
            jRadioButton2.setVisible(true);
            staticExtractionField.setVisible(true);
            behaviourTable.setVisible (false);
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);    
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jTextField2.setVisible(false);
            jTextField3.setVisible(false);
            jTextField4.setVisible(false);
            jCheckBox1.setVisible(false);
        }
    }

    public void clearField() {
        staticExtractionField.setBackground(Color.white);
        staticExtractionField.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) behaviourTable.getModel();
        while(tableModel.getRowCount()>0){
            tableModel.removeRow(0);
        }
        behaviourTable.setModel(tableModel);
        jButton2.setEnabled(false);
        
    }

    public void addRow(Object[] newRow) {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) behaviourTable.getModel();
        tableModel.addRow(newRow);        
    }

    public boolean checkField(String errorNode) {
        staticExtractionField.setBackground(Color.white);
        if (staticExtractionField.getText().isEmpty()) {
            int rowCount = behaviourTable.getRowCount();
            int colCount = behaviourTable.getColumnCount();
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < colCount; col++) {
                    if (errorNode.equalsIgnoreCase(behaviourTable.getValueAt(row, col).toString())) {
                        return true;
                    }
                }
            }
        }else if(errorNode.equalsIgnoreCase(staticExtractionField.getText())) {
            staticExtractionField.setBackground(Color.yellow);
            return true;
        }

        return false;
    }
}

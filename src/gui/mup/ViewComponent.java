/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mup;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sumit_Saseendran
 */
public class ViewComponent extends javax.swing.JPanel {

    String key;
    Object[][] imageList;
    ArrayList<ImageInventoryData> imageInventoryDataList;

    /**
     * Creates new form ViewComponent
     */
    public ViewComponent() {
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel2.setText("Images");
        jLabel2.setToolTipText("");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Version", "Files", "Supported OS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton1.setText("Delete Component");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel3.setText("Constituent ID ");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jLabel1.setText("Inventory");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton2.setText("Edit Component");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int newAdd = 1;
        for (ImageInventoryData ivData : imageInventoryDataList) {
            System.out.println("image name in inventory: "+ivData.getImageName());
            System.out.println("image name in image data table: "+jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());
            if (ivData.getImageName().contains(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString()) || ivData.getImageName().isEmpty()) {
                //set header of table according to inventory method
                //DefaultTableModel tableModel = null;
                String[] colNames = null;
                if (ivData.getInventoryMethod().equalsIgnoreCase("pnpids")) {
                    //tableModel = (DefaultTableModel) new Devices().getjTable1().getModel();
                    colNames = new String[]{"Device Type", "Device/Product ID", "Vendor ID", "SubDevice ID", "SubVendor ID", "Identifier"};
                } else if (ivData.getInventoryMethod().equalsIgnoreCase("msis")) {
                    //tableModel = (DefaultTableModel) new Msis().getjTable1().getModel();
                    colNames = new String[]{"Component  ID", "Identifying Number", "Name", "Upgrade Code", "Version", "Vendor", "Caption"};
                } else if (ivData.getInventoryMethod().equalsIgnoreCase("registryKeys")) {
                    //tableModel = (DefaultTableModel) new Registrykeys().getjTable1().getModel();
                    colNames = new String[]{"Component ID", "Name", "Value", "Display Name", "Display Value"};
                } else if (ivData.getInventoryMethod().equalsIgnoreCase("softwareIdentityInstances")) {
                    // tableModel = (DefaultTableModel) new SoftwareIdentityInstance().getjTable1().getModel();
                    colNames = new String[]{"Component ID", "Name", "Version", "Value", "Type"};
                }
                //tableModel.setRowCount(0);
//                for (Object[] tableData1 : image.getTableData()) {
//                    //tableModel.addRow(tableData1);
//                    tableModel.insertRow(0, tableData1.clone());
//                    
//                }
//                for(int i = 0; i < image.getTableData().length; i++){
//                    System.out.println(image.getTableData()[i][0].toString());
//                    tableModel.addRow(image.getTableData()[i].clone());
//                }
                if(newAdd==1){
                    this.jTable1.setModel(new DefaultTableModel(ivData.getTableData(), colNames));
                    newAdd=0;
                }else{
                    DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel(); 
                    for(Object[] row:ivData.getTableData()){
                        model.addRow(row);
                    }                    
                }                
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public void setButtonsActionListener(ActionListener listener) {
        jButton1.addActionListener(listener);
        jButton2.addActionListener(listener);
    }

    public JTable getjTable2() {
        return jTable2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
    public void setjTable2(JTable jTable2) {
        this.jTable2 = jTable2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setValues(ConstituentItem item) {
        this.setKey("Constituent No."+item.getComponentId()+"_"+item.getKey());
        jLabel3.setText("component ID: " + item.getComponentId());
        imageInventoryDataList = new ArrayList<ImageInventoryData>();
        int nRow = item.getImageList().size(), nCol = 5, i = 0;
        Object[][] tableData = new Object[nRow][nCol];
        for (ImageData imageItem : item.getImageList()) {
            tableData[i][0] = imageItem.getName();
            tableData[i][1] = imageItem.getType();
            tableData[i][2] = imageItem.getVersion();
            tableData[i][3] = imageItem.getFiles();
            tableData[i][4] = imageItem.getSupportedOS();
            imageInventoryDataList.addAll(imageItem.getImageInventoryDataList());
            i++;
        }
        jTable2.setModel(new DefaultTableModel(tableData, new Object[]{"Name", "Type", "Version", "Files", "Supported OS"}));
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

}

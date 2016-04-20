/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.pie;

import PIEXmlClasses.FieldServiceEnum;
import PIEXmlClasses.FmpComparisonEnum;
import PIEXmlClasses.PayloadInfo;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Sumit_Saseendran
 */
public class PayloadMain extends javax.swing.JPanel {

    static int payload_count;
    private DefaultTreeModel treeModel;

    PayloadMain(JTree pTree) {
        this.treeModel = (DefaultTreeModel) pTree.getModel();
        //pieTree = new javax.swing.JTree();
        pieTree = pTree;
        capsule = new Capsule();
        devices = new Devices();
        rollback = new Rollback();
        payload = new Payload();
        fmpWrapper = new FMPWrapper();
        payloadList = new ArrayList<>();
        initComponents();
        setListeners();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabel1.setText("Part Information ");

        jTabbedPane1.add("Payload",payload);
        jTabbedPane1.add("Capsule",capsule);
        jTabbedPane1.add("Devices",devices);
        jTabbedPane1.add("Rollback",rollback);
        jTabbedPane1.add("FMP Wrapper",fmpWrapper);
        jTabbedPane1.setEnabledAt(4, false);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton1.setText("Add Part");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton2.setText("Remove Part");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        payload_count++;

        //adding to payloadList
        PayloadInfo payloadInfo = new PayloadInfo();
        payloadInfo.setPayloadNumber(payload_count + "");
        //saving payloadinfo
        payloadInfo.setPayloadType(payload.getjTextField1().getText());
        payloadInfo.setVersion(payload.getjTextField2().getText());
        payloadInfo.setFMPWrapperID(payload.getjTextField3().getText());
        payloadInfo.setStartFile(payload.getjTextField3().getText());
        payloadInfo.setModules(payload.getjTextField5().getText());
        //saving capsule info
        payloadInfo.setImageID(capsule.getjTextField1().getText());
        payloadInfo.setFileName(capsule.getjTextField2().getText());
        payloadInfo.setSkip(capsule.getjComboBox1().getSelectedItem().toString());
        payloadInfo.setComponentIDs(capsule.getjTextField5().getText());
        //saving fmp driver info
        payloadInfo.setFmpDrvrName(capsule.getjTextField6().getText());
        payloadInfo.setPath(capsule.getjTextField7().getText());
        payloadInfo.setFmpDrvrVerison(capsule.getjTextField8().getText());
        payloadInfo.setFmpComparison(FmpComparisonEnum.fromValue(capsule.getjComboBox2().getSelectedItem().toString()));
        //saving supported devices
        int rowCount = devices.getjTable1().getRowCount();
        for (int count = 0; count < rowCount; count++) {
            payloadInfo.getDeviceInfo().addPCIID(devices.getjTable1().getValueAt(count, 0).toString(), devices.getjTable1().getValueAt(count, 1).toString(), devices.getjTable1().getValueAt(count, 2).toString(), devices.getjTable1().getValueAt(count, 3).toString());
        }
        rowCount = devices.getjTable2().getRowCount();
        for (int count = 0; count < rowCount; count++) {
            payloadInfo.getDeviceInfo().addPlugPlay(devices.getjTable1().getValueAt(count, 0).toString(), devices.getjTable1().getValueAt(count, 1).toString());
        }
        //saving rollback info
        if (!rollback.getjTextField1().getText().isEmpty()) {
            payloadInfo.setRollbackID(rollback.getjTextField1().getText());
            payloadInfo.setRollbackVolume(rollback.getjTextField2().getText());
            payloadInfo.setTaskName(rollback.getjTextField4().getText());
            payloadInfo.setFmpWrapperVerison(rollback.getjTextField5().getText());
            payloadInfo.setFmpID(rollback.getjTextField6().getText());
            payloadInfo.setRollbackTimeout(rollback.getjTextField7().getText());
            payloadInfo.setUpdateImpact(rollback.getjComboBox1().getSelectedItem().toString());
            System.out.println(rollback.getjComboBox1().getSelectedItem().toString());
            if (rollback.getjComboBox2().getSelectedIndex() > 0) {
                payloadInfo.setFieldServiceEnum(FieldServiceEnum.fromValue(rollback.getjComboBox2().getSelectedItem().toString()));
            }
            payloadInfo.setFmpVendorCode(rollback.getjTextField8().getText());
            payloadInfo.setFmpVendorCodeType(rollback.getjTextField9().getText());
            payloadInfo.setFmpFileExtension(rollback.getjTextField10().getText());
            payloadInfo.setSwapDevice(rollback.getjComboBox3().getSelectedItem().toString());
            payloadInfo.setApplicationNme(rollback.getjTextField11().getText());
            payloadInfo.setAlternateRbID(rollback.getjTextField12().getText());
        } else {
            payloadInfo.setRollbackID("");
        }
        //saving fwp wrapper info
        if (!fmpWrapper.getjTextField1().getText().isEmpty()) {
            payloadInfo.addFmpWrapperInfo(payload.getjTextField3().getText(), fmpWrapper.getjTextField2().getText(), fmpWrapper.getjTextField3().getText(), fmpWrapper.getjComboBox1().getSelectedItem().toString(), fmpWrapper.getjTextField4().getText(), fmpWrapper.getjComboBox2().getSelectedItem().toString(), fmpWrapper.getjTextField5().getText(), fmpWrapper.getjComboBox3().getSelectedItem().toString(), fmpWrapper.getjComboBox4().getSelectedItem().toString());
        } else {
            payloadInfo.setFMPWrapperID("");
        }

        //adding payloadinfo to payload info list
        payloadList.add(payloadInfo);
        //adding to tree
        TreePath treePathParent = find((DefaultMutableTreeNode) treeModel.getRoot(), "Payload");
        DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) treePathParent.getLastPathComponent();
        treeModel.insertNodeInto(new DefaultMutableTreeNode("Part_" + payload_count), nodeParent, nodeParent.getChildCount());

        clearData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TreePath treePath = find((DefaultMutableTreeNode) treeModel.getRoot(), jLabel1.getText());
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        if (node != null) {
            treeModel.removeNodeFromParent(node);
            Iterator<PayloadInfo> it = payloadList.iterator();
            while (it.hasNext()) {
                PayloadInfo pItem = it.next();
                if (pItem.getPayloadNumber().equals(jLabel1.getText().split("_")[1])) {
                    it.remove();
                    break;
                }
            }
        }
        treePath = find((DefaultMutableTreeNode) treeModel.getRoot(), "Payload");
        pieTree.setSelectionPath(treePath);

    }//GEN-LAST:event_jButton2ActionPerformed

    private TreePath find(DefaultMutableTreeNode root, String s) {
        @SuppressWarnings("unchecked")
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = e.nextElement();
            if (node.toString().equalsIgnoreCase(s)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public List<PayloadInfo> getPayloadList() {
        return payloadList;
    }

    public int getPayload_count() {
        return ++payload_count;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    private final Capsule capsule;
    private final Devices devices;
    private final Rollback rollback;
    private final Payload payload;
    private final FMPWrapper fmpWrapper;
    private List<PayloadInfo> payloadList;
    private final javax.swing.JTree pieTree;

    public void clearData() {
        jLabel1.setText("Part Information");
        payload.clearData();
        capsule.clearData();
        devices.clearData();
        rollback.clearData();
        fmpWrapper.clearData();
    }

    void load(String selected) {
        clearData();
        Iterator<PayloadInfo> it = payloadList.iterator();
        while (it.hasNext()) {
            PayloadInfo pItem = it.next();
            if (pItem.getPayloadNumber().equals(selected.split("_")[1])) {
                jLabel1.setText("Part_" + pItem.getPayloadNumber());
                payload.load(pItem);
                capsule.load(pItem);
                devices.load(pItem);
                rollback.load(pItem);
                fmpWrapper.load(pItem);
                break;
            }
        }
    }

    private void setListeners() {
        payload.getjTextField3()
                .getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent de) {
                        updateLabel(de);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent de) {
                        updateLabel(de);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent de) {
                        updateLabel(de);
                    }

                    private void updateLabel(DocumentEvent de) {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                String fmpWrapperID = payload.getjTextField3().getText();
                                if (fmpWrapperID.isEmpty()) {
                                    jTabbedPane1.setEnabledAt(4, false);
                                } else {
                                    rollback.getjTextField3().setText(fmpWrapperID);
                                    fmpWrapper.getjTextField1().setText(fmpWrapperID);
                                    jTabbedPane1.setEnabledAt(4, true);
                                }

                            }

                        });
                    }
                }
                );
    }
}

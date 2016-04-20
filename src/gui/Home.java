/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.pie.PIETree;
import gui.mup.Installer;
import gui.mup.InstallerBehaviour;
import gui.mup.ImageInventoryData;
import gui.mup.PackageInformation;
import gui.mup.AddConstituents;
import gui.mup.ImageData;
import gui.mup.ViewComponent;
import gui.mup.Constituents;
import gui.mup.InstallerSub1;
import gui.mup.ConstituentItem;
import gui.mup.Applicability;
import Filters.CustomFilter1;
import VerifyFields.XMLValidation;
import MUPXmlClasses.CreateMupXml;
import MUPXmlClasses.MupDef;
import PIEXmlClasses.CreatePieXml;
import PIEXmlClasses.PieDef;
import XmlImport.XmlImport;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Sumit_Saseendran
 */
public class Home extends javax.swing.JPanel {

    private CardLayout cl;
    private DefaultTreeModel treeModel;
    private RootNode mup;
    private PackageInformation packageInformation;
    private Applicability applicability;
    private Installer installer;
    private InstallerSub1 installer32;
    private InstallerSub1 installer64;

    private InstallerBehaviour unattended_x86;
    private InstallerBehaviour attended_x86;
    private InstallerBehaviour extractDrivers_x86;
    private InstallerBehaviour freshInstall_x86;
    private InstallerBehaviour help_x86;
    private InstallerBehaviour driverOnly_x86;
    private InstallerBehaviour applicationOnly_x86;
    private InstallerBehaviour log_x86;
    private InstallerBehaviour force_x86;

    private InstallerBehaviour unattended_x64;
    private InstallerBehaviour attended_x64;
    private InstallerBehaviour extractDrivers_x64;
    private InstallerBehaviour freshInstall_x64;
    private InstallerBehaviour help_x64;
    private InstallerBehaviour driverOnly_x64;
    private InstallerBehaviour applicationOnly_x64;
    private InstallerBehaviour log_x64;
    private InstallerBehaviour force_x64;

    private AddConstituents addConstituents;
    private Constituents constituents;
    private ViewComponent viewComponent;
    private ConstituentItem cItem;

    private HashMap<String, InstallerBehaviour> x86Behaviour;
    private HashMap<String, InstallerBehaviour> x64Behaviour;

    private XMLValidation xmlValidation;
    private XmlImport xmlImport;

    private PIETree pieTree;

    public Home() {
        initPanels();
        initComponents();
        pieTree = new PIETree(this);
        setBehavourHashMap();
//        installerList.add(installer32);
//        installerList.add(installer64);
        setListeners();
        setBackground(new Color(220, 230, 250));
        jPanel1.setBackground(Color.white);
        mupTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                String selected = tse.getPath().getLastPathComponent().toString();
                String parent = new String();
                if (!selected.equalsIgnoreCase("mup")) {
                    parent = tse.getPath().getParentPath().getLastPathComponent().toString();
                }
                switch (selected) {
                    case "Package Information":
                        cl.show(jPanel2, "1");
                        break;
                    case "Applicability":
                        cl.show(jPanel2, "2");
                        break;
                    case "Installer":
                        cl.show(jPanel2, "3");
                        break;
                    case "32-bit Installer":
                        cl.show(jPanel2, "4");
                        break;
                    case "64-bit Installer":
                        cl.show(jPanel2, "5");
                        break;
                    case "Unattended":
                        unattended_x86.setTitle(selected);
                        unattended_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "6" : "17");
                        break;
                    case "Attended":
                        attended_x86.setTitle(selected);
                        attended_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "7" : "18");
                        break;
                    case "Extract Drivers":
                        extractDrivers_x86.setTitle(selected);
                        extractDrivers_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "8" : "19");
                        break;
                    case "Fresh Install":
                        freshInstall_x86.setTitle(selected);
                        freshInstall_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "9" : "20");
                        break;
                    case "Help":
                        help_x86.setTitle(selected);
                        help_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "10" : "21");
                        break;
                    case "Driver Only":
                        driverOnly_x86.setTitle(selected);
                        driverOnly_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "11" : "22");
                        break;
                    case "Application Only":
                        applicationOnly_x86.setTitle(selected);
                        applicationOnly_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "12" : "23");
                        break;
                    case "Log":
                        log_x86.setTitle(selected);
                        log_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "13" : "24");
                        break;
                    case "Force":
                        force_x86.setTitle(selected);
                        force_x64.setTitle(selected);
                        cl.show(jPanel2, (parent.equalsIgnoreCase("32-bit Installer")) ? "14" : "25");
                        break;
                    case "<add constituents>":
                        cl.show(jPanel2, "15");
                        break;
                    case "MUP":
                        cl.show(jPanel2, "16");
                        break;
                    case "Constituents":
                        cl.show(jPanel2, "27");
                        break;
                    default:
                        if (selected.contains("Constituent No.")) {
                            for (ConstituentItem item : constituentList) {
//                                String fileName = "";
//                                for (ImageData iData : item.getImageList()) {
//                                    if (fileName.isEmpty()) {
//                                        fileName = iData.getFiles();
//                                    } else {
//                                        fileName = fileName.concat("," + iData.getFiles());
//                                    }
//                                    
//                                }
//                                if (fileName.equalsIgnoreCase(selected.substring(15))) {
//                                    viewComponent.setValues(item);
//                                    break;
//                                }
                                if (item.getKey().equalsIgnoreCase(selected.split("_")[1])) {
                                    viewComponent.setValues(item);
                                    break;
                                }
                            }
                            cl.show(jPanel2, "26");
                            break;
                        }
                }
            }
        });
    }

    public Home(Home home) {

    }

    private void initPanels() {
        cl = new CardLayout();
        mup = new RootNode();
        addConstituents = new AddConstituents();
        packageInformation = new PackageInformation();
        applicability = new Applicability(addConstituents);
        installer = new Installer();
        installer32 = new InstallerSub1("x86");
        installer64 = new InstallerSub1("x64");

        unattended_x86 = new InstallerBehaviour();
        attended_x86 = new InstallerBehaviour();
        extractDrivers_x86 = new InstallerBehaviour();
        freshInstall_x86 = new InstallerBehaviour();
        help_x86 = new InstallerBehaviour();
        driverOnly_x86 = new InstallerBehaviour();
        applicationOnly_x86 = new InstallerBehaviour();
        log_x86 = new InstallerBehaviour();
        force_x86 = new InstallerBehaviour();

        unattended_x64 = new InstallerBehaviour();
        attended_x64 = new InstallerBehaviour();
        extractDrivers_x64 = new InstallerBehaviour();
        freshInstall_x64 = new InstallerBehaviour();
        help_x64 = new InstallerBehaviour();
        driverOnly_x64 = new InstallerBehaviour();
        applicationOnly_x64 = new InstallerBehaviour();
        log_x64 = new InstallerBehaviour();
        force_x64 = new InstallerBehaviour();

        viewComponent = new ViewComponent();
        constituents = new Constituents();

        xmlValidation = new XMLValidation();
        xmlImport = new XmlImport();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        mupTree = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jMenuItem1.setText("Settings");
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Help");
        jPopupMenu1.add(jMenuItem2);

        setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(420, 120));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Image img = new javax.swing.ImageIcon(getClass().getResource("/gui/settings.png")).getImage() ;
        Image newimg = img.getScaledInstance( 25,25,  java.awt.Image.SCALE_SMOOTH ) ;
        jButton5.setIcon(new ImageIcon( newimg ));
        jButton5.setActionCommand("back");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton5MouseReleased(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Museo Sans For Dell", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 125, 184));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/dell_icon.png"))); // NOI18N
        jLabel1.setText("Update Supplier Assistant");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabel3.setText("Select Specification");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel3MouseReleased(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MUP", "PIE" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))))
        );

        mupTree.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, new java.awt.Color(0, 0, 0)));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("MUP");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Package Information");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("<add constituents>");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Applicability");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Constituents");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Installer");
        treeNode2.add(add_installer("32-bit Installer"));
        treeNode2.add(add_installer("64-bit Installer"));
        treeNode1.add(treeNode2);
        treeModel = new DefaultTreeModel(treeNode1);
        mupTree.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        mupTree.setModel(treeModel);
        jScrollPane1.setViewportView(mupTree);

        jPanel2.setBackground(new java.awt.Color(210, 220, 240));
        jPanel2.setMaximumSize(new java.awt.Dimension(600, 700));
        jPanel2.setSize(600,700);
        jPanel2.setLayout(null);
        jPanel2.setLayout(cl);
        jPanel2.add(packageInformation,"1");
        jPanel2.add(applicability,"2");
        jPanel2.add(installer,"3");
        jPanel2.add(installer32,"4");
        jPanel2.add(installer64,"5");
        jPanel2.add(unattended_x86,"6");
        jPanel2.add(attended_x86,"7");
        jPanel2.add(extractDrivers_x86,"8");
        jPanel2.add(freshInstall_x86,"9");
        jPanel2.add(help_x86,"10");
        jPanel2.add(driverOnly_x86,"11");
        jPanel2.add(applicationOnly_x86,"12");
        jPanel2.add(log_x86,"13");
        jPanel2.add(force_x86,"14");
        jPanel2.add(unattended_x64,"17");
        jPanel2.add(attended_x64,"18");
        jPanel2.add(extractDrivers_x64,"19");
        jPanel2.add(freshInstall_x64,"20");
        jPanel2.add(help_x64,"21");
        jPanel2.add(driverOnly_x64,"22");
        jPanel2.add(applicationOnly_x64,"23");
        jPanel2.add(log_x64,"24");
        jPanel2.add(force_x64,"25");
        jPanel2.add(addConstituents,"15");
        jPanel2.add(mup,"16");
        jPanel2.add(viewComponent,"26");
        jPanel2.add(constituents,"27");
        cl.show(jPanel2,"16");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, new java.awt.Color(0, 0, 0)));

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton2.setText("Import xml");
        fileChooser = new javax.swing.JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setDialogTitle("Choose file");
        fileChooser.setFileFilter(new CustomFilter1());
        fileChooser.setFileHidingEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton6.setText("Create Package");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 15)); // NOI18N
        jLabel2.setText("File imported:");

        jButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton7.setText("Save & Validate xml");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        jButton1.setText("Clear Fields");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 457, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton6)
                    .addComponent(jLabel2)
                    .addComponent(jButton7)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFields();
        xmlImport.ImportXml(this);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseReleased

    private void jButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseReleased
        jPopupMenu1.show(this, jButton5.getX() - 10, jButton5.getY() + 30);
    }//GEN-LAST:event_jButton5MouseReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("mup")) {
            mup.getjLabel1().setText("Enter details for MUP specification");
            jScrollPane1.setViewportView(mupTree);
            cl.show(jPanel2, "1");
        } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("pie")) {
            mup.getjLabel1().setText("Enter details for PIE specification");
            jScrollPane1.setViewportView(pieTree.getjTree1());
            cl.show(jPanel2, "30");
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JFileChooser fileChooserSave = new javax.swing.JFileChooser();
        fileChooserSave.setAcceptAllFileFilterUsed(true);
        fileChooserSave.setDialogTitle("Save as");
        fileChooserSave.setFileFilter(new CustomFilter1());
        fileChooserSave.setFileHidingEnabled(false);
        int returnVal = fileChooserSave.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("mup")) {
                MupDef mupObj = new MupDef(packageInformation, applicability, installer, installer32, installer64, x86Behaviour, x64Behaviour, constituents, constituentList);
                CreateMupXml createXml = new CreateMupXml(mupObj, fileChooserSave.getSelectedFile());
                String errorNode = xmlValidation.validate(this, fileChooserSave.getSelectedFile().toString());
                String nodeName = errorNode.isEmpty() ? "" : getNodeName(errorNode);
                mupTree.setCellRenderer(new DefaultTreeCellRenderer() {
                    @Override
                    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                            boolean leaf, int row, boolean hasFocus) {
                        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                        //YourNode node = (YourNode) value;
                        if (errorNode.isEmpty()) {
                            label.setForeground(Color.BLACK);
                        } else if (label.getText().contains(errorNode) || label.getText().contains(nodeName)) {
                            label.setForeground(Color.RED);
                        }
                        if (label.getText().equalsIgnoreCase(nodeName)) {
                            label.setForeground(Color.RED);
                        }
                        return label;
                    }
                });
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("pie")) {
                PieDef pieObj = new PieDef(pieTree);
                CreatePieXml createXml = new CreatePieXml(pieObj, fileChooserSave.getSelectedFile());
                String errorNode = xmlValidation.validate(this, fileChooserSave.getSelectedFile().toString());
                String nodeName = errorNode.isEmpty() ? "" : getNodeName(errorNode);
            }

        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private String getNodeName(String errorNode) {
        if (packageInformation.checkField(errorNode)) {
            return "package information";
        } else if (installer32.checkField(errorNode)) {
            return "32-bit installer";
        } else if (installer64.checkField(errorNode)) {
            return "64-bit installer";
        } else if (constituents.checkField(errorNode)) {
            return "constituents";
        } else if (unattended_x86.checkField(errorNode)) {
            return "unattended";
        } else if (attended_x86.checkField(errorNode)) {
            return "attended";
        } else if (extractDrivers_x86.checkField(errorNode)) {
            return "extract drivers";
        } else if (freshInstall_x86.checkField(errorNode)) {
            return "fresh install";
        } else if (help_x86.checkField(errorNode)) {
            return "help";
        } else if (driverOnly_x86.checkField(errorNode)) {
            return "driver only";
        } else if (applicationOnly_x86.checkField(errorNode)) {
            return "application only";
        } else if (log_x86.checkField(errorNode)) {
            return "log";
        } else if (force_x86.checkField(errorNode)) {
            return "force";
        } else {
            return checkConstituentList(errorNode);
        }
    }

    private String checkConstituentList(String errorNode) {
        if (!constituentList.isEmpty()) {
            for (ConstituentItem cItem : constituentList) {
                for (ImageData iData : cItem.getImageList()) {
                    if (iData.getName().equalsIgnoreCase(errorNode) || iData.getFiles().equalsIgnoreCase(errorNode) || iData.getVersion().equalsIgnoreCase(errorNode)) {
                        return cItem.getComponentId();
                    }
                    for (ImageInventoryData iiData : iData.getImageInventoryDataList()) {
                        Object[][] tableData = iiData.getTableData();
                        for (int row = 0; row < tableData.length; row++) {
                            for (int col = 0; col < tableData[row].length; col++) {
                                if (tableData[row][col].toString().equalsIgnoreCase(errorNode)) {
                                    return cItem.getComponentId();
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        clearFields();
        mupTree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                    boolean leaf, int row, boolean hasFocus) {
                JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                label.setForeground(Color.BLACK);
                return label;
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    public void getObject(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true); // if you want to modify private fields
                System.out.println(field.getName()
                        + " - " + field.get(obj).toString());
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton6ActionPerformed

    private void setListeners() {
        installer.setButtonsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String node_name = e.getActionCommand();
                if (node_name.equals("32-bit Installer") || node_name.equals("64-bit Installer")) {
                    JCheckBox cb = (JCheckBox) e.getSource();
                    if (cb.isSelected()) {
                        TreePath treePathParent = find((DefaultMutableTreeNode) treeModel.getRoot(), "Installer");
                        DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) treePathParent.getLastPathComponent();
                        treeModel.insertNodeInto(add_installer(node_name), nodeParent, nodeParent.getChildCount());
//                        installerList.add((node_name.equalsIgnoreCase("32-bit Installer")) ? installer32 : installer64);
                    } else {
                        TreePath treePath = find((DefaultMutableTreeNode) treeModel.getRoot(), node_name);
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                        if (node != null) {
                            treeModel.removeNodeFromParent(node);
//                            installerList.remove((node_name.equalsIgnoreCase("32-bit Installer")) ? installer32 : installer64);
                        }
                    }
                }
            }
        });

        addConstituents.setButtonsActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if (addConstituents.getjTextField1().getText().isEmpty()) {
                            JOptionPane.showMessageDialog(Home.this, "Enter Component ID.");
                        } else {
//                            String fileName="";
//                            for(int count=0;count<addConstituents.getImage().getjTable1().getRowCount();count++){
//                                if(fileName.isEmpty()){
//                                    fileName=addConstituents.getImage().getjTable1().getValueAt(count, 3).toString();
//                                }else{
//                                    fileName=fileName.concat(","+addConstituents.getImage().getjTable1().getValueAt(count, 3).toString());
//                                }                                
//                            }

                            String node_name = "Constituent No." + addConstituents.getjTextField1().getText();
                            addNode(node_name, addConstituents.getjTextField1().getText(), addConstituents.getImage().getImageData(), addConstituents.getImageInventory().getImageInventoryDataList());
                            addConstituents.clearField();
                            cl.show(jPanel2, "15");
                        }

                    }
                }
        );

        viewComponent.setButtonsActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TreePath treePath = find((DefaultMutableTreeNode) treeModel.getRoot(), viewComponent.getKey());
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                        if (node != null) {
                            treeModel.removeNodeFromParent(node);
                            Iterator<ConstituentItem> it = constituentList.iterator();
                            while (it.hasNext()) {
                                ConstituentItem cItem = it.next();
                                if (cItem.getKey().equals(viewComponent.getKey().split("_")[1])) {
                                    if (e.getActionCommand().equalsIgnoreCase("Edit Component")) {
                                        loadAddConstituentScreen(cItem);
                                    }
                                    it.remove();
                                    break;
                                }
                            }
                        }
                        cl.show(jPanel2, "15");
                    }

                    private void loadAddConstituentScreen(ConstituentItem cItem) {
                        addConstituents.getjTextField1().setText(cItem.getComponentId());
                        DefaultTableModel tableModel = new DefaultTableModel();
                        tableModel = (DefaultTableModel) addConstituents.getImage().getjTable1().getModel();
                        for (ImageData iData : cItem.getImageList()) {
                            tableModel.addRow(new Object[]{iData.getName(), iData.getType(), iData.getVersion(), iData.getFiles(), iData.getSupportedOS()});
                            for (ImageInventoryData iiData : iData.getImageInventoryDataList()) {
                                addConstituents.getImageInventory().setImageInventoryDataList().add(iiData);
                            }
                        }
                        //addConstituents.getImageInventory().setImage(addConstituents.getImage());

                    }
                }
        );
        installer32.getjTextField3()
                .getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e
                    ) {
                        updateLabel(e);
                    }

                    @Override
                    public void insertUpdate(DocumentEvent e
                    ) {
                        updateLabel(e);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e
                    ) {
                        updateLabel(e);
                    }

                    private void updateLabel(DocumentEvent e) {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                String paraString = installer32.getjTextField3().getText();
                                String[] tokens = paraString.split(" ");
                                DefaultTableModel tableModel = new DefaultTableModel();
                                for (Map.Entry<String, InstallerBehaviour> entry : x86Behaviour.entrySet()) {
                                    String key = entry.getKey();
                                    InstallerBehaviour value = entry.getValue();
                                    tableModel = (DefaultTableModel) value.getjTable1().getModel();
                                    tableModel.setRowCount(0);
                                    for (String t : tokens) {
                                        if (t.length() == 2) {
                                            tableModel.addRow(new Object[]{t.charAt(0), t.charAt(1), false, ""});
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
                );
        installer64.getjTextField3().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel(e);
            }

            private void updateLabel(DocumentEvent e) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String paraString = installer64.getjTextField3().getText();;
                        String[] tokens = paraString.split(" ");
                        DefaultTableModel tableModel = new DefaultTableModel();
                        for (Map.Entry<String, InstallerBehaviour> entry : x64Behaviour.entrySet()) {
                            String key = entry.getKey();
                            InstallerBehaviour value = entry.getValue();
                            tableModel = (DefaultTableModel) value.getjTable1().getModel();
                            tableModel.setRowCount(0);
                            for (String t : tokens) {
                                if (t.length() == 2) {
                                    tableModel.addRow(new Object[]{t.charAt(0), t.charAt(1), false, ""});
                                }
                            }
                        }
                    }
                });
            }
        });

        packageInformation.getjComboBox2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                constituents.getjLabel4().setText(packageInformation.getjComboBox2().getSelectedItem().toString());
            }
        });
    }

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

    DefaultMutableTreeNode add_installer(String node_name) {
        DefaultMutableTreeNode parent = new javax.swing.tree.DefaultMutableTreeNode(node_name);
        DefaultMutableTreeNode child = new javax.swing.tree.DefaultMutableTreeNode("Unattended");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Attended");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Extract Drivers");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Fresh Install");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Help");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Driver Only");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Application Only");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Log");
        parent.add(child);
        child = new javax.swing.tree.DefaultMutableTreeNode("Force");
        parent.add(child);
        return parent;
    }

    public JComboBox getjComboBox1() {
        return jComboBox1;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree mupTree;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JFileChooser fileChooser;
    private ArrayList<ConstituentItem> constituentList = new ArrayList<ConstituentItem>();

    private void setBehavourHashMap() {
        x86Behaviour = new HashMap<>();
        x86Behaviour.put("unattended", unattended_x86);
        x86Behaviour.put("attended", attended_x86);
        x86Behaviour.put("extractdrivers", extractDrivers_x86);
        x86Behaviour.put("freshinstall", freshInstall_x86);
        x86Behaviour.put("help", help_x86);
        x86Behaviour.put("driveronly", driverOnly_x86);
        x86Behaviour.put("applicationonly", applicationOnly_x86);
        x86Behaviour.put("logfile", log_x86);
        x86Behaviour.put("force", force_x86);
        x64Behaviour = new HashMap<>();
        x64Behaviour.put("unattended", unattended_x64);
        x64Behaviour.put("attended", attended_x64);
        x64Behaviour.put("extractdrivers", extractDrivers_x64);
        x64Behaviour.put("freshinstall", freshInstall_x64);
        x64Behaviour.put("help", help_x64);
        x64Behaviour.put("drivernly", driverOnly_x64);
        x64Behaviour.put("applicationonly", applicationOnly_x64);
        x64Behaviour.put("logfile", log_x64);
        x64Behaviour.put("force", force_x64);
    }

    private void clearFields() {
        addConstituents.clearField();
        packageInformation.clearField();
        applicability.clearField();
        installer.clearField();
        installer32.clearField();
        installer64.clearField();

        unattended_x86.clearField();
        attended_x86.clearField();
        extractDrivers_x86.clearField();
        freshInstall_x86.clearField();
        help_x86.clearField();
        driverOnly_x86.clearField();
        applicationOnly_x86.clearField();
        log_x86.clearField();
        force_x86.clearField();

        unattended_x64.clearField();
        attended_x64.clearField();
        extractDrivers_x64.clearField();
        freshInstall_x64.clearField();
        help_x64.clearField();
        driverOnly_x64.clearField();
        applicationOnly_x64.clearField();
        log_x64.clearField();
        force_x64.clearField();

        constituents.clearField();

        TreePath treePath = find((DefaultMutableTreeNode) treeModel.getRoot(), "Constituents");
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
        if (node != null) {
            for (int i = 1; i < node.getChildCount();) {
                treeModel.removeNodeFromParent((MutableTreeNode) node.getChildAt(i));
            }
            constituentList.clear();
        }
    }

    public void addNode(String node_name, String text, Object[][] imageData, ArrayList<ImageInventoryData> imageInventoryDataList) {
        cItem = new ConstituentItem();
        cItem.setComponentId(text);
        cItem.addImage(imageData, imageInventoryDataList);
        constituentList.add(cItem);
        TreePath treePathParent = find((DefaultMutableTreeNode) treeModel.getRoot(), "Constituents");
        DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) treePathParent.getLastPathComponent();
        treeModel.insertNodeInto(new DefaultMutableTreeNode(node_name + "_" + cItem.getKey()), nodeParent, nodeParent.getChildCount());

    }

    public RootNode getMup() {
        return mup;
    }

    public PackageInformation getPackageInformation() {
        return packageInformation;
    }

    public Applicability getApplicability() {
        return applicability;
    }

    public Installer getInstaller() {
        return installer;
    }

    public InstallerSub1 getInstaller32() {
        return installer32;
    }

    public InstallerSub1 getInstaller64() {
        return installer64;
    }

    public InstallerBehaviour getUnattended_x86() {
        return unattended_x86;
    }

    public InstallerBehaviour getAttended_x86() {
        return attended_x86;
    }

    public InstallerBehaviour getExtractDrivers_x86() {
        return extractDrivers_x86;
    }

    public InstallerBehaviour getFreshInstall_x86() {
        return freshInstall_x86;
    }

    public InstallerBehaviour getHelp_x86() {
        return help_x86;
    }

    public InstallerBehaviour getDriverOnly_x86() {
        return driverOnly_x86;
    }

    public InstallerBehaviour getApplicationOnly_x86() {
        return applicationOnly_x86;
    }

    public InstallerBehaviour getLog_x86() {
        return log_x86;
    }

    public InstallerBehaviour getForce_x86() {
        return force_x86;
    }

    public InstallerBehaviour getUnattended_x64() {
        return unattended_x64;
    }

    public InstallerBehaviour getAttended_x64() {
        return attended_x64;
    }

    public InstallerBehaviour getExtractDrivers_x64() {
        return extractDrivers_x64;
    }

    public InstallerBehaviour getFreshInstall_x64() {
        return freshInstall_x64;
    }

    public InstallerBehaviour getHelp_x64() {
        return help_x64;
    }

    public InstallerBehaviour getDriverOnly_x64() {
        return driverOnly_x64;
    }

    public InstallerBehaviour getApplicationOnly_x64() {
        return applicationOnly_x64;
    }

    public InstallerBehaviour getLog_x64() {
        return log_x64;
    }

    public InstallerBehaviour getForce_x64() {
        return force_x64;
    }

    public AddConstituents getAddConstituents() {
        return addConstituents;
    }

    public Constituents getConstituents() {
        return constituents;
    }

    public ViewComponent getViewComponent() {
        return viewComponent;
    }

    public ConstituentItem getcItem() {
        return cItem;
    }

    public HashMap<String, InstallerBehaviour> getX86Behaviour() {
        return x86Behaviour;
    }

    public HashMap<String, InstallerBehaviour> getX64Behaviour() {
        return x64Behaviour;
    }

    public ArrayList<ConstituentItem> getConstituentList() {
        return constituentList;
    }

    public CardLayout getCl() {
        return cl;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public PIETree getPieTree() {
        return pieTree;
    }

}

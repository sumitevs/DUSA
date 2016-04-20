/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mup;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import org.ini4j.Profile;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 *
 * @author Sumit_Saseendran
 */
public class LoadInf {

    private static String pattern;
    private static Pattern p;

    public LoadInf() {
    }

    LoadInf(File selectedFile, Image image, ImageInventory imageInventory) {

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) image.getjTable1().getModel();
        StringBuilder osString = new StringBuilder();
        for(int i =0; i < image.getjList1().getModel().getSize();i++){
            osString.append(image.getjList1().getModel().getElementAt(i).toString());
            osString.append(",");
        }
        String version= null;
        Wini ini = null;
        try {
            ini = new Wini(selectedFile);
        } catch (IOException ex) {
            Logger.getLogger(AddConstituents.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Iterator<String> it = ini.keySet().iterator(); it.hasNext();) {
            String sectionName = it.next();
            if (sectionName.contains("Version")) {
                System.out.println("[" + sectionName + "]");
                Section section = (Section)ini.get(sectionName);
                for (Object optionKey : section.keySet()) {
                    if(optionKey.toString().contains("DriverVer")){
                        version=(String)section.get(optionKey);
                    }
                }
            }
           
            if (sectionName.contains("MTI_SSD")) {
                System.out.println("[" + sectionName + "]");
                pattern = "^.*PCI\\\\VEN_(?<vendorid>[0-9a-zA-Z]{4})&DEV_(?<devid>[0-9a-zA-Z]{4})&SUBSYS_(?<subsysid>[0-9a-zA-Z]{8})";

                p = Pattern.compile(pattern);
                Section section = (Section)ini.get(sectionName);
                for (Object optionKey : section.keySet()) {
                    //System.out.println(section.get(optionKey));
                    Matcher m = p.matcher((CharSequence)section.get(optionKey.toString()));
                    if (m.find()) {
                        System.out.println("\tvendor id:" + m.group("vendorid"));
                        System.out.println("\tdevice id:" + m.group("devid"));
                        System.out.println("\tsubystem id:" + m.group("subsysid") + "\n");
                        imageInventory.getDevices().addRow(new Object[]{"PCI",  m.group("devid"),m.group("vendorid"),m.group("subsysid"),"",""});
                    }
                }
            }
        }
        tableModel.addRow(new Object[]{selectedFile.getName(), "DRVR", version, selectedFile.getParent(), osString});
        
    }

}

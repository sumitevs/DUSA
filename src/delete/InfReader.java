/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import gui.mup.AddConstituents;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 *
 * @author Sumit_Saseendran
 */
public class InfReader {

    private static String pattern;
    private static Pattern p;

    public InfReader() {
        pattern = null;
        p = null;
    }

    public static void main(String args[]) {
        File file = new File("C:\\Users\\sumit_saseendran\\Documents\\xml\\muxp.inf");
        Wini ini = null;
        try {
            ini = new Wini(file);
        } catch (IOException ex) {
            Logger.getLogger(AddConstituents.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Number of sections: " + ini.size() + "\n");
//        for (String sectionName : ini.keySet()) {
//            if (sectionName.contains("Manufact")) {
//                System.out.println("[" + sectionName + "]");
//                Section section = ini.get(sectionName);
//                for (String optionKey : section.keySet()) {
//                    System.out.println("\t" + optionKey + "=" + section.get(optionKey));
//                }
//            }
//            if (sectionName.contains("MTI_SSD")) {
//                System.out.println("[" + sectionName + "]");
//                pattern="^.*PCI\\\\VEN_(?<vendorid>[0-9a-zA-Z]{4})&DEV_(?<devid>[0-9a-zA-Z]{4})&SUBSYS_(?<subsysid>[0-9a-zA-Z]{8})";
//
//                p = Pattern.compile(pattern);
//                Section section = ini.get(sectionName);
//                for (String optionKey : section.keySet()) {
//                    //System.out.println(section.get(optionKey));
//                    Matcher m = p.matcher(section.get(optionKey));
//                    if (m.find()) {
//                        System.out.println("\tvendor id:" + m.group("vendorid"));
//                        System.out.println("\tdevice id:" + m.group("devid"));
//                        System.out.println("\tsubystem id:" + m.group("subsysid")+"\n");
//                    }
//                }
//            }
//
//        }

    }

}

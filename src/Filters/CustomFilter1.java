/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import java.io.File;

/**
 *
 * @author Sumit_Saseendran
 */
public class CustomFilter1 extends javax.swing.filechooser.FileFilter {
    public boolean accept(File file) {
        //Allow only directories, or files with ".txt" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".xml");
    }

    @Override
    public String getDescription() {
        return "XML file (*.xml)";
    }
}

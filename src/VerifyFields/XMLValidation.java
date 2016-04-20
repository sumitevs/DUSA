/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VerifyFields;

import gui.Home;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Sumit_Saseendran
 */
public class XMLValidation {

    String errorNode;

    public XMLValidation() {

    }

    public String getErrorNode() {
        return errorNode;
    }

    public String validate(Home aThis, String fileName) {
        errorNode = "";
        String specType = aThis.getjComboBox1().getSelectedItem().toString();
        Source xmlFile = new StreamSource(fileName);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            String sourcePath = "";
            if (specType.equalsIgnoreCase("mup")) {
                sourcePath = "/resource/mupdefinition.xsd";
            } else {
                sourcePath = "/resource/PIEConfig.xsd";
            }
            URL url = getClass().getResource(sourcePath);
            schema = schemaFactory.newSchema(url);

        } catch (SAXException ex) {
            Logger.getLogger(XMLValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
        javax.xml.validation.Validator validator = schema.newValidator();
        try {
            validator.validate(xmlFile);
            JOptionPane.showMessageDialog(null, "The xml is valid as per the schema", "Valid file", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(XMLValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException e) {
            String errMsg = e.getLocalizedMessage();
            System.out.println(e.getMessage() + " is NOT valid");
            System.out.println("Reason: " + errMsg);
            JOptionPane.showMessageDialog(null, "<html><body><p style='width: 200px;'>" + errMsg + "</p></body></html>", "Error in the file", JOptionPane.ERROR_MESSAGE);
            if (specType.equalsIgnoreCase("mup")) {
                if (errMsg.contains("requiredparameters")) {
                    errorNode = "Installer";
                } else if (errMsg.contains("extract")) {
                    errorNode = "Constituents";
                } else if (errMsg.contains("T_Packageinformaation")) {
                    errorNode = "Package Information";
                } else if (errMsg.contains("[executable, zip]")) {
                    errorNode = "Package Information";
                } else if (errMsg.contains("[BIOS, DRVR, APP, FRMW, APAC, UTIL, BSD, UEFICAPSULE]")) {
                    errorNode = "Package Information";
                } else if (errMsg.contains("is not a valid value")) {
                    errorNode = errMsg.split("\'")[1];
                }
            }
        }
//        } else {
//            System.out.println("File access cancelled by user.");
//        }
        return errorNode;
    }
}

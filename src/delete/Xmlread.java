/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import java.io.InputStream;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sumit_Saseendran
 */
public class Xmlread {
    public void main(String[] args) {
        try {
            DocumentBuilderFactory domFactory
                    = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            String sourcePath = "/resource/OSXml.xml";
            InputStream is = getClass().getResourceAsStream(sourcePath);
            
            Document doc = builder.parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();
            DefaultListModel vv = new DefaultListModel();
            NodeList nl = (NodeList) xpath.evaluate("//osidentifier/@*", doc, XPathConstants.NODESET);
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                Attr attr = (Attr) nl.item(i);
                String name = attr.getName();
                String value = attr.getValue();
                vv.addElement(value);

                System.out.println(name);
                System.out.println(value);
                System.out.println("loop ended");

            }
            JList list = new JList(vv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
}

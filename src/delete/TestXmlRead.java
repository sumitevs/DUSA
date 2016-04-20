/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sumit_Saseendran
 */
public class TestXmlRead {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("src/gui/OSXml.xml");
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("osidentifier");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("name= "
                            + eElement.getAttribute("name"));
                    System.out.println("enumVal= "
                            + eElement.getAttribute("enumVal"));
                    System.out.println("architecture= "
                            + eElement.getAttribute("architecture"));
                    System.out.println("vendor= "
                            + eElement.getAttribute("vendor"));
                    System.out.println("majorversion= "
                            + eElement.getAttribute("majorversion"));
                    System.out.println("minorversion= "
                            + eElement.getAttribute("minorversion"));
                    System.out.println("spmajorversion= "
                            + eElement.getAttribute("spmajorversion"));
                    System.out.println("spminorversion= "
                            + eElement.getAttribute("spminorversion"));
                    System.out.println("locale= "
                            + eElement.getAttribute("locale"));
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

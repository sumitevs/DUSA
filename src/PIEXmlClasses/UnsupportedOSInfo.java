/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.ListOS;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sumit_Saseendran
 */
public class UnsupportedOSInfo {

    public List<OSIdentifier> getUnsupportedOSList() {
        if(unsupportedOSList==null){
            unsupportedOSList=new ArrayList<>();
        }
        return unsupportedOSList;
    }

    public void addOS(String osName){
        try {
            DocumentBuilderFactory domFactory
                    = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            String sourcePath = "/resource/operatingsystemslist.xml";
            InputStream is = getClass().getResourceAsStream(sourcePath);
            Document doc = builder.parse(is);
            NodeList nl = doc.getElementsByTagName("OperatingSystem");
            int length = nl.getLength();
            for (int i = 0; i < length; i++) {
                Node node = nl.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    if(element.getElementsByTagName("Display").item(0).getTextContent().equalsIgnoreCase(osName)){
                        getUnsupportedOSList().add(new OSIdentifier(element.getAttribute("majorVersion"),element.getAttribute("minorVersion"),element.getAttribute("osArch"),element.getAttribute("osCode"),element.getAttribute("spMajorVersion"),element.getAttribute("spMinorVersion")));
			}
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private List<OSIdentifier> unsupportedOSList;

    void load(ListOS listOS) {
        int rowCount = listOS.getListB().getSize();
        for(int count = 0;count<rowCount;count++){
            addOS(listOS.getListB().getElementAt(count).toString());
        }
    }
    
}

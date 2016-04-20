/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import gui.mup.ImageInventoryData;
import java.io.InputStream;
import java.util.ArrayList;
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
public class ImageItem {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumReleaseTypesProgrammatic getEnumDeviceType() {
        return enumDeviceType;
    }

    public void setEnumDeviceType(EnumReleaseTypesProgrammatic enumDeviceType) {
        this.enumDeviceType = enumDeviceType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<String> getFiles() {
        if (files == null) {
            files = new ArrayList<String>();
        }
        return files;
    }

    public ArrayList<OSIdentifier> getOsList() {
        if (osList == null) {
            osList = new ArrayList<OSIdentifier>();
        }
        return osList;
    }

    public ArrayList<InventoryItem> getInventoryList() {
        if (inventoryList == null) {
            inventoryList = new ArrayList<InventoryItem>();
        }
        return inventoryList;
    }

    private String name;
    private EnumReleaseTypesProgrammatic enumDeviceType;
    private String version;
    private ArrayList<String> files;
    private ArrayList<OSIdentifier> osList;
    private ArrayList<InventoryItem> inventoryList;
    private OSIdentifier osIdentifier;
    private InventoryItem inventoryItem;
    
    void setFiles(String files) {
        String[] fileNames = files.split(",");
        for (String s : fileNames) {
            this.getFiles().add(s);
        }
    }

    void setOSIdentifiers(String supportedOS) {
        String[] osNames = supportedOS.split(",");
        try {
            DocumentBuilderFactory domFactory
                    = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            String sourcePath = "/resource/OSXml.xml";
            InputStream is = getClass().getResourceAsStream(sourcePath);
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("osidentifier");
            for (String s:osNames) {
                osIdentifier = new OSIdentifier();
                for (int j = 0; j < nList.getLength(); j++) {
                    Node nNode = nList.item(j);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if (eElement.getAttribute("name").equalsIgnoreCase(s)){
                            osIdentifier.setOsName(EnumOSNames.fromValue(eElement.getAttribute("enumVal")));
                            osIdentifier.setOsArchitecture(EnumOSArchitecture.fromValue(eElement.getAttribute("architecture")));
                            osIdentifier.setVendor(eElement.getAttribute("vendor"));
                            osIdentifier.setMajorVersion(eElement.getAttribute("majorversion"));
                            osIdentifier.setMinorVersion(eElement.getAttribute("minorversion"));
                            osIdentifier.setSpMajorVersion(eElement.getAttribute("spmajorversion"));
                            osIdentifier.setSpMinorVersion(eElement.getAttribute("spminorversion"));
                            osIdentifier.setLocale(eElement.getAttribute("locale"));
                            this.getOsList().add(osIdentifier);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setInventoryList(ArrayList<ImageInventoryData> imageInventoryDataList) {
        for(ImageInventoryData iiData:imageInventoryDataList){
            inventoryItem = new InventoryItem();
            inventoryItem.addInventoryData(iiData);
            this.getInventoryList().add(inventoryItem);
        }
    }
    
    
}

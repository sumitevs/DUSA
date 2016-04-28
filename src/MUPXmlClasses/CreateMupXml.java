/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sumit_Saseendran
 */
public class CreateMupXml {

    private static MupDef mupDef;

    private static Node getPackageInfo(Document doc, MupDef mupDef, String name) {
        Element node = doc.createElement(name);
        node.appendChild(getChildElements(doc, "name", mupDef.getName()));
        node.appendChild(getChildElements(doc, "mupspecificationversion", mupDef.getMupSpecVersion()));
        node.appendChild(getChildElements(doc, "version", mupDef.getVersion()));
        node.appendChild(getChildElements(doc, "installertype", EnumInstallerTypes.msi.toString()));
        node.appendChild(getChildElements(doc, "packagegrouping", EnumPackageTypes.single_package.toString()));
        node.appendChild(getChildElements(doc, "packagingtype", EnumPackagingTypes.executable.toString()));
        node.appendChild(getChildElements(doc, "releasetype", mupDef.getReleasetype().toString()));
        node.appendChild(getSupportedOS(doc, mupDef.getApplicableOS(), "supportedoperatingsystems"));
        node.appendChild(getContent(doc, mupDef, "content"));
        return node;
    }

    private static Node getExecutables(Document doc, Installer installer, String name) {
        Element node = doc.createElement(name);
        node.setAttribute("architecture", installer.getArchitecture());
        node.setAttribute("timeout", String.valueOf(installer.getTimeOut()));
        node.appendChild(getChildElements(doc, "executablename", installer.getName()));
        if (!installer.getMandatoryParameters().isEmpty()) {
            node.appendChild(getRequiredParameters(doc, "requiredparameters", installer.getMandatoryParameters()));
        }
        return node;
    }

    private static Node getBehaviours(Document doc, List<InstallerBehavior> installerBehavior, String name) {
        Element node = doc.createElement(name);
        for (InstallerBehavior behaviour : installerBehavior) {
            if (name.equalsIgnoreCase("behaviors") && !behaviour.getEnumBehavior().toString().equalsIgnoreCase("logfile") && !behaviour.getEnumBehavior().toString().equalsIgnoreCase("force")) {
                node.appendChild(addBehaviourNode(doc, "behavior", behaviour));
            } else if (name.equalsIgnoreCase("parameters") && (behaviour.getEnumBehavior().toString().equalsIgnoreCase("logfile") || behaviour.getEnumBehavior().toString().equalsIgnoreCase("force"))) {
                node.appendChild(addBehaviourNode(doc, "parametermapping", behaviour));
            }
        }
        return node;
    }

    private static Node addBehaviourNode(Document doc, String nodeName, InstallerBehavior behaviour) {
        Element subNode = doc.createElement(nodeName);
        subNode.setAttribute("name", behaviour.getEnumBehavior().toString());
        int size = behaviour.getPgmExtraction().size();
        if (size == 1) { //if only one node, then create vendoroption
            subNode.appendChild(getVendoroptions(doc, behaviour.getPgmExtraction().get(0)));
        } else {        // for more than one, make pair of container and if odd number, one vendoroption node.
            int count = 0;
            if (size % 2 != 0) {
                subNode.appendChild(getVendoroptions(doc, behaviour.getPgmExtraction().get(0)));
                ++count;
            }
            for (; count < size; count = count + 2) {
                subNode.appendChild(getVendoroptionsContainer(doc, behaviour.getPgmExtraction().get(count), behaviour.getPgmExtraction().get(count + 1)));
            }
        }
        return subNode;
    }

    private static Node getReturnCode(Document doc, HashMap<EnumReturnCodeMap, String> returnCodeMap, String name) {
        Element node = doc.createElement(name);
        for (Map.Entry<EnumReturnCodeMap, String> entry : returnCodeMap.entrySet()) {
            Element subNode = doc.createElement("returncodemapping");
            subNode.setAttribute("name", entry.getKey().toString());
            Element subSubNode = doc.createElement("vendorreturncode");
            subSubNode.appendChild(doc.createTextNode(entry.getValue()));
            subNode.appendChild(subSubNode);
            node.appendChild(subNode);
        }
        return node;
    }

    private static Node getDriverArchive(Document doc, InstallerBehavior behaviour, String name) {
        Element node = doc.createElement(name);
        Element subNode = doc.createElement("alldriversroot");
        subNode.appendChild(doc.createTextNode(behaviour.getStaticExtraction()));
        node.appendChild(subNode);
        return node;
    }

    private static Node getInventory(Document doc, MupDef mupDef, String name) {
        Element node = doc.createElement(name);
        if (mupDef.getReleasetype().toString().equalsIgnoreCase("bios")) {
            Element subNode = doc.createElement("bios");
            subNode.appendChild(getChildElements(doc, "version", mupDef.getPackageLevelInventory().getBiosVersion()));
            node.appendChild(subNode);
        } else {
            Element subNode1 = doc.createElement("extractdriversidentifier");
            if(addDriverInfo(doc, subNode1)==1){
                node.appendChild(subNode1);
            }

            Element subNode2 = doc.createElement("fullpackageidentifier");
            if (mupDef.getPackageLevelInventory().getInventoryMethod().equalsIgnoreCase("pnpIds")) {
                addDriverInfo(doc, subNode2);
            } else if (mupDef.getPackageLevelInventory().getInventoryMethod().equalsIgnoreCase("msis")) {
                Element subNode3 = doc.createElement("msis");
                for (MsisItem mItem : mupDef.getPackageLevelInventory().getMsisList()) {                    
                    Element subNode4 = doc.createElement("msi");
                    subNode4.setAttribute("componentID", mItem.getComponentId());
                    if(!mItem.getIdentifyingNumber().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "identifyingnumber", mItem.getIdentifyingNumber()));
                    }
                    if(!mItem.getUpgradeCode().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "upgradecode", mItem.getUpgradeCode()));
                    }
                    if(!mItem.getVersion().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "version", mItem.getVersion()));
                    }
                    if(!mItem.getName().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "name", mItem.getName()));
                    }
                    if(!mItem.getVendor().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "vendor", mItem.getVendor()));
                    }
                    if(!mItem.getCaption().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "caption", mItem.getCaption()));
                    }
                    subNode3.appendChild(subNode4);                    
                }
                subNode2.appendChild(subNode3);
            } else if (mupDef.getPackageLevelInventory().getInventoryMethod().equalsIgnoreCase("registryKeys")) {
                Element subNode3 = doc.createElement("registrykeys");
                for (RegItem rItem : mupDef.getPackageLevelInventory().getRegList()) {                    
                    Element subNode4 = doc.createElement("registrykey");
                    subNode4.setAttribute("componentID", rItem.getComponentId());
                    subNode4.appendChild(getChildElements(doc, "name", rItem.getName()));
                    if(!rItem.getValue().isEmpty()){
                        subNode4.appendChild(getChildElements(doc, "value", rItem.getValue()));
                    }                    
                    subNode4.appendChild(getChildElements(doc, "displayName", rItem.getDisplayName()));
                    subNode4.appendChild(getChildElements(doc, "displayValue", rItem.getDisplayValue()));
                    subNode3.appendChild(subNode4);                    
                }
                subNode2.appendChild(subNode3);
            } else if (mupDef.getPackageLevelInventory().getInventoryMethod().equalsIgnoreCase("softwareIdentityInstances")) {
                Element subNode3 = doc.createElement("softwareidentityinstances");
                for (SIIItem sItem : mupDef.getPackageLevelInventory().getsIIList()) {                    
                    Element subNode4 = doc.createElement("softwareidentity");
                    subNode4.setAttribute("componentID", sItem.getComponentId());
                    subNode4.appendChild(getChildElements(doc, "elementname", sItem.getElementName()));
                    subNode4.appendChild(getChildElements(doc, "versionstring", sItem.getVersion()));
                    Element subNode5 = doc.createElement("identityinfo");
                    subNode5.appendChild(getChildElements(doc, "value", sItem.getValue()));
                    subNode5.appendChild(getChildElements(doc, "type", sItem.getType()));
                    subNode4.appendChild(subNode5);
                    subNode3.appendChild(subNode4);                    
                }
                subNode2.appendChild(subNode3);
            }
            node.appendChild(subNode2);
        }
        return node;
    }

    private static Node getChildElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static Node getContent(Document doc, MupDef mupDef, String name) {
        Element node = doc.createElement(name);
        for (Constituent cItem : mupDef.getConstituent()) {
            Element subNode = doc.createElement("Device");
            subNode.setAttribute("componentID", cItem.getConstituentId());

            //filling PCI,PNP and generic info into Device node
            for (ImageItem imItem : cItem.getImageItemList()) {
                for (InventoryItem ivItem : imItem.getInventoryList()) {
                    if (ivItem.getInventoryMethod().equalsIgnoreCase("pnpIds")) {
                        for (PnpItem pItem : ivItem.getPnpList()) {
                            Element subSubNode = null;
                            if (pItem.getDeviceType().equalsIgnoreCase("PCI")) {
                                subSubNode = doc.createElement("PCIInfo");
                                subSubNode.setAttribute("vendorID", pItem.getVendorId());
                                subSubNode.setAttribute("deviceID", pItem.getDeviceId());
                                subSubNode.setAttribute("subDeviceID", pItem.getSubDeviceId());
                                subSubNode.setAttribute("subVendorID", pItem.getSubVendorId());
                            } else if (pItem.getDeviceType().equalsIgnoreCase("PNP")) {
                                subSubNode = doc.createElement("PnPInfo");
                                if (pItem.getVendorId().length() == 3) {
                                    subSubNode.appendChild(getChildElements(doc, "PNPID", pItem.getVendorId()));
                                } else if (pItem.getVendorId().length() == 4) {
                                    subSubNode.appendChild(getChildElements(doc, "ACPIID", pItem.getVendorId()));
                                }
                                subSubNode.appendChild(getChildElements(doc, "PnPProductID", pItem.getDeviceId()));
                            } else if (pItem.getDeviceType().equalsIgnoreCase("Abstract")) {
                                subSubNode = doc.createElement("Generic");
                                subSubNode.appendChild(doc.createTextNode(pItem.getIdentifier()));
                            }
                            if (!alreadyAdded(subNode.getChildNodes(), subSubNode)) {
                                subNode.appendChild(subSubNode);
                            }

                        }
                    }
                }
            }

            //filing images details into Device node
            for (ImageItem imItem : cItem.getImageItemList()) {
                Element subSubNode = doc.createElement("Image");
                subSubNode.setAttribute("type", imItem.getEnumDeviceType().toString());
                subSubNode.setAttribute("version", imItem.getVersion());
                for (String files : imItem.getFiles()) {
                    subSubNode.appendChild(getChildElements(doc, "file", files));
                }
                subSubNode.appendChild(getSupportedOS(doc, imItem.getOsList(), "supportedoperatingsystems"));
                subNode.appendChild(subSubNode);
            }
            node.appendChild(subNode);
        }
        return node;
    }

    private static Node getSupportedOS(Document doc, List<OSIdentifier> listOS, String name) {
        Element node = doc.createElement(name);
        for (OSIdentifier os : listOS) {
            node.appendChild(getChildOsidentifier(doc, os, "osidentifier"));
        }
        return node;
    }

    private static Node getChildOsidentifier(Document doc, OSIdentifier osIdentifier, String name) {
        Element node = doc.createElement(name);
        node.setAttribute("name", osIdentifier.getOsName());
        node.setAttribute("architecture", osIdentifier.getOsArchitecture().toString());
        node.setAttribute("vendor", osIdentifier.getVendor());
        node.setAttribute("majorversion", osIdentifier.getMajorVersion());
        node.setAttribute("minorversion", osIdentifier.getMinorVersion());
        node.setAttribute("spmajorversion", osIdentifier.getSpMajorVersion());
        node.setAttribute("spminorversion", osIdentifier.getSpMinorVersion());
        node.setAttribute("locale", osIdentifier.getLocale());
        return node;
    }

    private static Node getRequiredParameters(Document doc, String name, String mandatoryParameters) {
        Element node = doc.createElement(name);
        String[] tokens = mandatoryParameters.split(" ");
        for (String t : tokens) {
            if (t.length() > 0) {
                Element subNode = doc.createElement("commandlineparameter");
                subNode.appendChild(doc.createTextNode(t));
                node.appendChild(subNode);
            }
        }
        return node;
    }

    private static Node getVendoroptions(Document doc, PgmExtraction pgmExt) {
        Element Node = doc.createElement("vendoroption");
        Element subNode = doc.createElement("optionvalue");
        subNode.setAttribute("switch", pgmExt.getSwitch());
        subNode.setAttribute("requiresvalue", String.valueOf(pgmExt.getRequireValue()));
        subNode.setAttribute("valuedelimiter", pgmExt.getDelimiterValue());
        subNode.setAttribute("enclose", pgmExt.getEnclose());
        subNode.appendChild(doc.createTextNode(pgmExt.getValue()));
        Node.appendChild(subNode);
        return Node;
    }

    private static Node getVendoroptionsContainer(Document doc, PgmExtraction pgmExt1, PgmExtraction pgmExt2) {
        Element Node = doc.createElement("vendoroption");
        Element subNode = doc.createElement("container");
        Element subSubNode1 = doc.createElement("containervalue");
        subSubNode1.setAttribute("switch", pgmExt1.getSwitch());
        subSubNode1.setAttribute("requiresvalue", String.valueOf(pgmExt1.getRequireValue()));
        subSubNode1.setAttribute("valuedelimiter", pgmExt1.getDelimiterValue());
        subSubNode1.setAttribute("enclose", pgmExt1.getEnclose());
        subSubNode1.appendChild(doc.createTextNode(pgmExt1.getValue()));
        Element subSubNode2 = doc.createElement("optionvalue");
        subSubNode2.setAttribute("switch", pgmExt2.getSwitch());
        subSubNode2.setAttribute("requiresvalue", String.valueOf(pgmExt2.getRequireValue()));
        subSubNode2.setAttribute("valuedelimiter", pgmExt2.getDelimiterValue());
        subSubNode2.setAttribute("enclose", pgmExt2.getEnclose());
        subSubNode2.appendChild(doc.createTextNode(pgmExt2.getValue()));
        subNode.appendChild(subSubNode1);
        subNode.appendChild(subSubNode2);
        Node.appendChild(subNode);
        return Node;
    }

    private static int addDriverInfo(Document doc, Element subNode1) {
        Element subNode2 = doc.createElement("pnpids");
        Element subNode3 = doc.createElement("pnpdevice");
        String previousCid = "";
        int flag = 2;
        int flag_driver=0;
        for (Constituent cItem : mupDef.getConstituent()) {
            if (!previousCid.isEmpty() && !previousCid.equalsIgnoreCase(cItem.getConstituentId())) { //whenever a new component id is found, a new pnpdevice node is created
                subNode2.appendChild(subNode3);
                subNode3 = doc.createElement("pnpdevice");
                flag = 1; // to make sure the final element is added, we need to check once outside the loop
            }
            for (ImageItem imItem : cItem.getImageItemList()) {
                if (previousCid.isEmpty() || flag == 1) {//this block runs only if it is a new component id or if it is the first component 
                    subNode3.setAttribute("componentID", cItem.getConstituentId());
                    subNode3.setAttribute("display", imItem.getName());
                    //************************************************
                    //there are more attributes which are not mandatory. bus, device, function and componentinstance
                    //*************************************************

                    //driver version needs to be added to the node subNode3 by appendChild
                    subNode3.appendChild(getChildElements(doc, "driverversion", imItem.getVersion()));
                    flag = 0;//indicating that this subNode3 needs to be appended to subNode2 outside the loop if it is the last constituent item
                }
                for (InventoryItem ivItem : imItem.getInventoryList()) {
                    if (ivItem.getInventoryMethod().equalsIgnoreCase("pnpIds")) {
                        flag_driver=1;
                        for (PnpItem pItem : ivItem.getPnpList()) {
                            Element subSubNode = null;
                            if (pItem.getDeviceType().equalsIgnoreCase("PCI")) {
                                subSubNode = doc.createElement("pciinfo");
                                subSubNode.setAttribute("vendorID", pItem.getVendorId());
                                subSubNode.setAttribute("deviceID", pItem.getDeviceId());
                                subSubNode.setAttribute("subDeviceID", pItem.getSubDeviceId());
                                subSubNode.setAttribute("subVendorID", pItem.getSubVendorId());
                            } else if (pItem.getDeviceType().equalsIgnoreCase("PNP")) {
                                subSubNode = doc.createElement("pnpidstring");
                                if (pItem.getVendorId().length() == 3) {
                                    subSubNode.appendChild(getChildElements(doc, "PNPID", pItem.getVendorId()));
                                } else if (pItem.getVendorId().length() == 4) {
                                    subSubNode.appendChild(getChildElements(doc, "ACPIID", pItem.getVendorId()));
                                } else {
                                    System.out.println("incorrect entery for vendor id (T_PnPInfo) ");
                                }
                                subSubNode.appendChild(getChildElements(doc, "PnPProductID", pItem.getDeviceId()));
                            } else if (pItem.getDeviceType().equalsIgnoreCase("Abstract")) {
                                subSubNode = doc.createElement("generic");
                                subSubNode.appendChild(doc.createTextNode(pItem.getIdentifier()));
                            }
                            if (!alreadyAdded(subNode3.getChildNodes(), subSubNode)) {
                                subNode3.appendChild(subSubNode);
                            }
                        }
                    }
                }
            }
            previousCid = cItem.getConstituentId();
        }
        if (flag == 0) {
            subNode2.appendChild(subNode3);
        }
        subNode1.appendChild(subNode2);
        return flag_driver;
    }

    private static boolean alreadyAdded(NodeList subNode, Element subSubNode) {
        for (int count = 0; count < subNode.getLength(); count++) {
            Node node = subNode.item(count);
            if (node.getNodeName().equalsIgnoreCase(subSubNode.getNodeName())) {
                if (node.getNodeName().equalsIgnoreCase("PCIInfo")) {
                    if (node.getAttributes().getNamedItem("vendorID").getNodeValue().equalsIgnoreCase(subSubNode.getAttribute("vendorID")) && node.getAttributes().getNamedItem("deviceID").getNodeValue().equalsIgnoreCase(subSubNode.getAttribute("deviceID")) && node.getAttributes().getNamedItem("subDeviceID").getNodeValue().equalsIgnoreCase(subSubNode.getAttribute("subDeviceID")) && node.getAttributes().getNamedItem("subVendorID").getNodeValue().equalsIgnoreCase(subSubNode.getAttribute("subVendorID"))) {
                        return true;
                    }
                } else if (node.getNodeName().equalsIgnoreCase("PnPInfo")) {
//                    NodeList nodeList = node.getChildNodes();
//                    for(int count1=0;count1 < nodeList.getLength();count1++){
//                        Node node1 = nodeList.item(count1);
//                        System.out.println("subSubNode name "+ subSubNode.getChildNodes().item(count1).getNodeName() + "value is "+ subSubNode.getChildNodes().item(count1).getTextContent());
//                    }

                    if (node.getChildNodes().item(0).getTextContent().equalsIgnoreCase(subSubNode.getChildNodes().item(0).getTextContent()) && node.getChildNodes().item(1).getTextContent().equalsIgnoreCase(subSubNode.getChildNodes().item(1).getTextContent())) {
                        return true;
                    }
                } else if (node.getNodeName().equalsIgnoreCase("Generic")) {
                    if (node.getChildNodes().item(0).getTextContent().equalsIgnoreCase(subSubNode.getChildNodes().item(0).getTextContent())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public CreateMupXml(MupDef mupObj, File fileName) {

        mupDef = mupObj;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            //Document doc = builder.newDocument();
            DOMImplementation domImpl = builder.getDOMImplementation();
            Document doc = domImpl.createDocument("http://schemas.dell.com/openmanage/cm/2/0/mupdefinition.xsd", "MUPDefinition", null);
            doc.setXmlVersion("1.0");
            doc.setXmlStandalone(true);

            Element mainRootElement = doc.getDocumentElement();
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            mainRootElement.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:schemaLocation", "http://schemas.dell.com/openmanage/cm/2/0/mupdefinition.xsd mupdefinition.xsd");

            //Adding comment line to XML
            Comment titleComment = doc.createComment("This XML is created using DUSA");
            mainRootElement.appendChild(titleComment);

            // append child element package info to root element
            mainRootElement.appendChild(getPackageInfo(doc, mupDef, "packageinformation"));

            // append child element Executables to root element
            for (Installer installer : mupDef.getInstallerList()) {
                mainRootElement.appendChild(getExecutables(doc, installer, "executable"));
            }

            // append child element Behaviours to root element
            mainRootElement.appendChild(getBehaviours(doc, mupDef.getInstallerList().get(0).getInstallerBehavior(), "behaviors"));

            // append child element parameters to root element
            mainRootElement.appendChild(getBehaviours(doc, mupDef.getInstallerList().get(0).getInstallerBehavior(), "parameters"));

            // append child element returncodes to root element
            mainRootElement.appendChild(getReturnCode(doc, mupDef.getInstallerList().get(0).getReturnCodeMap(), "returncodes"));

            // append child element driver archive to root element
            for (InstallerBehavior behaviour : mupDef.getInstallerList().get(0).getInstallerBehavior()) {
                if (behaviour.getEnumBehavior().equals(EnumBehaviors.extractdrivers) && !(behaviour.getStaticExtraction() == null)) {
                    mainRootElement.appendChild(getDriverArchive(doc, behaviour, "driverarchive"));
                    break;
                }
            }

            // append child element inventory metadata to root element
            mainRootElement.appendChild(getInventory(doc, mupDef, "inventorymetadata"));

            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fileName);
            transformer.transform(source, result);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (ParserConfigurationException | DOMException | IllegalArgumentException | TransformerException e) {
            e.printStackTrace();
        }

    }

}

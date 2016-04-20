/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlImport;

import Filters.CustomFilter1;
import PIEXmlClasses.BrandInfoItem;
import PIEXmlClasses.DisplayType;
import PIEXmlClasses.FmpComparisonEnum;
import PIEXmlClasses.Model;
import PIEXmlClasses.PayloadInfo;
import gui.mup.ConstituentItem;
import gui.Home;
import gui.mup.ImageData;
import gui.mup.ImageInventoryData;
import gui.mup.InstallerBehaviour;
import gui.pie.Dependency;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Sumit_Saseendran
 */
public class XmlImport {

    private Home home;

    public void ImportXml(Home aThis) {
        home = aThis;
        JFileChooser fileChooserSave = new javax.swing.JFileChooser();
        fileChooserSave.setAcceptAllFileFilterUsed(true);
        fileChooserSave.setDialogTitle("Open file");
        fileChooserSave.setFileFilter(new CustomFilter1());
        fileChooserSave.setFileHidingEnabled(false);
        int returnVal = fileChooserSave.showOpenDialog(aThis);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File xmlFile = fileChooserSave.getSelectedFile();
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(XmlImport.class.getName()).log(Level.SEVERE, null, ex);
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(xmlFile);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(XmlImport.class.getName()).log(Level.SEVERE, null, ex);
            }
            doc.getDocumentElement().normalize();
            if (doc.hasChildNodes()) {
                readRootNode(doc.getChildNodes());
            }

        } else {

        }
    }

    private void readRootNode(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("MUPDefinition")) {
                home.getjComboBox1().setSelectedIndex(0);
                readMupNode(node.getChildNodes());
                break;
            } else if (node.getNodeName().equalsIgnoreCase("PIEConfig")) {
                home.getjComboBox1().setSelectedIndex(1);
                readPieNode(node.getChildNodes());
                break;
            } else {
                System.out.println("invalid xml uploaded");
                break;
            }
        }
    }

    private void getDependencyInfo(Node node, Dependency dependency) {
        dependency.getjTextField1().setText(getNodeAttr(node, "releaseID"));
        dependency.getjTextField2().setText(getNodeAttr(node, "componentID"));
        dependency.getjTextField3().setText(getNodeAttr(node, "version"));
        dependency.getjTextField4().setText(getNodeAttr(node, "prerequisite"));
        dependency.getjTextField5().setText(getNodeAttr(node, "path"));
        String type = getNodeAttr(node, "componentType");
        for (int count1 = 0; count1 < dependency.getjComboBox1().getItemCount(); count1++) {
            if (type.equalsIgnoreCase(dependency.getjComboBox1().getItemAt(count1).toString())) {
                home.getPieTree().getDependency().getjComboBox1().setSelectedIndex(count1);
            }
        }
        String context = getNodeAttr(node, "context");
        for (int count1 = 0; count1 < dependency.getjComboBox2().getItemCount(); count1++) {
            if (context.equalsIgnoreCase(dependency.getjComboBox2().getItemAt(count1).toString())) {
                home.getPieTree().getDependency().getjComboBox2().setSelectedIndex(count1);
            }
        }
        for (int count = 0; count < node.getChildNodes().getLength(); count++) {
            Node subNode = node.getChildNodes().item(count);
            if (subNode.getNodeName().equalsIgnoreCase("Display")) {
                dependency.getDisplayDetail().addRow(subNode.getTextContent(), "display");
            } else if (subNode.getNodeName().equalsIgnoreCase("detail")) {
                dependency.getDisplayDetail().addRow(subNode.getTextContent(), "detail");
            } else if (subNode.getNodeName().equalsIgnoreCase("PCIInfo")) {
                dependency.getDevices().addRowPCID(new Object[]{getNodeAttr(subNode, "deviceID"), getNodeAttr(subNode, "vendorID"), getNodeAttr(subNode, "subDeviceID"), getNodeAttr(subNode, "subVendorID")});
            } else if (subNode.getNodeName().equalsIgnoreCase("PnPInfo")) {
                String devID = "";
                String prodID = "";
                for (int count4 = 0; count4 < subNode.getChildNodes().getLength(); count4++) {
                    Node pnpNode = subNode.getChildNodes().item(count4);
                    if (pnpNode.getNodeName().equalsIgnoreCase("PNPID")) {
                        devID = pnpNode.getTextContent();
                    } else if (pnpNode.getNodeName().equalsIgnoreCase("ACPIID")) {
                        devID = pnpNode.getTextContent();
                    } else if (pnpNode.getNodeName().equalsIgnoreCase("PnPProductID")) {
                        prodID = pnpNode.getTextContent();
                    }
                }
                dependency.getDevices().addRowPlug(new Object[]{devID, prodID});
            } else if (subNode.getNodeName().equalsIgnoreCase("Brand")) {
                BrandInfoItem bi = new BrandInfoItem();
                bi.setKey(getNodeAttr(subNode, "key"));
                bi.setPrefix(getNodeAttr(subNode, "prefix"));
                for (int count3 = 0; count3 < subNode.getChildNodes().getLength(); count3++) {
                    Node bNode = subNode.getChildNodes().item(count3);
                    if (bNode.getNodeName().equalsIgnoreCase("Display")) {
                        bi.getDisplayTextList().add(new DisplayType(bNode.getTextContent(), "en"));
                    } else if (bNode.getNodeName().equalsIgnoreCase("Model")) {
                        bi.getModelList().add(new Model(getNodeAttr(bNode, "systemID"), getNodeAttr(bNode, "systemIDType"), getNodeAttr(bNode, "systemContext"), getNodeInfo(bNode.getChildNodes(), "Display")));
                    }
                }
                dependency.getBrand().getBrandInfo().getBrandInfoList().add(bi);
            } else if (subNode.getNodeName().equalsIgnoreCase("OperatingSystem")) {
                getUnsupportedOs(subNode.getChildNodes(), dependency.getListOS().getListA(), dependency.getListOS().getListB());
            }
        }
    }

    private void getPayloadInfo(Node node) {
        int flag = 0;
        PayloadInfo pi = null;
        String fmpWrapperID = getNodeAttr(node, "fmpWrapperIdentifier");
        if (!fmpWrapperID.isEmpty()) {
            //checking if fmpWrapper info have been already added to payloadinfo list. If so, we should add rest into to that payloadinfo item
            for (PayloadInfo pI : home.getPieTree().getPayloadMain().getPayloadList()) {
                if (pI.getFMPWrapperID().equalsIgnoreCase(fmpWrapperID)) {
                    pi = pI;
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0) {
            pi = new PayloadInfo();
        }
        pi.setPayloadNumber(String.valueOf(home.getPieTree().getPayloadMain().getPayload_count()));
        pi.setPayloadType(getNodeAttr(node, "type"));
        pi.setVersion(getNodeAttr(node, "version"));
        pi.setFMPWrapperID(fmpWrapperID);
        for (int count1 = 0; count1 < node.getChildNodes().getLength(); count1++) {
            Node subNode = node.getChildNodes().item(count1);
            if (subNode.getNodeName().equalsIgnoreCase("Startfile")) {
                pi.setStartFile(subNode.getTextContent());
            } else if (subNode.getNodeName().equalsIgnoreCase("Modules")) {
                pi.setModules(getNodeInfo(subNode.getChildNodes(), "Module"));
            } else if (subNode.getNodeName().equalsIgnoreCase("CapsulePayloadParts")) {
                //inside CapsulePayloadParts node
                for (int count2 = 0; count2 < subNode.getChildNodes().getLength(); count2++) {
                    Node capNode = subNode.getChildNodes().item(count2);
                    if (capNode.getNodeName().equalsIgnoreCase("CapsulePayloadPart")) {
                        //inside CapsulePayloadPart node
                        for (int count3 = 0; count3 < capNode.getChildNodes().getLength(); count3++) {
                            Node subCapNode = capNode.getChildNodes().item(count3);
                            if (subCapNode.getNodeName().equalsIgnoreCase("PayloadImage")) {
                                pi.setImageID(getNodeAttr(subCapNode, "id"));
                                pi.setFileName(getNodeAttr(subCapNode, "filename"));
                                pi.setSkip(getNodeAttr(subCapNode, "skip"));
                            } else if (subCapNode.getNodeName().equalsIgnoreCase("FMPDriver")) {
                                pi.setFmpDrvrName(getNodeAttr(subCapNode, "name"));
                                pi.setPath(getNodeAttr(subCapNode, "path"));
                                pi.setFmpDrvrVerison(getNodeAttr(subCapNode, "version"));
                                pi.setFmpComparison(FmpComparisonEnum.valueOf(getNodeAttr(subCapNode, "comparisonOperator")));
                            } else if (subCapNode.getNodeName().equalsIgnoreCase("PCIInfo")) {
                                pi.getDeviceInfo().addPCIID(getNodeAttr(subCapNode, "deviceID"), getNodeAttr(subCapNode, "vendorID"), getNodeAttr(subCapNode, "subDeviceID"), getNodeAttr(subCapNode, "subVendorID"));
                            } else if (subCapNode.getNodeName().equalsIgnoreCase("PnPInfo")) {
                                String devID = "";
                                String prodID = "";
                                for (int count4 = 0; count4 < subCapNode.getChildNodes().getLength(); count4++) {
                                    Node pnpNode = subCapNode.getChildNodes().item(count4);
                                    if (pnpNode.getNodeName().equalsIgnoreCase("PNPID")) {
                                        devID = pnpNode.getTextContent();
                                    } else if (pnpNode.getNodeName().equalsIgnoreCase("ACPIID")) {
                                        devID = pnpNode.getTextContent();
                                    } else if (pnpNode.getNodeName().equalsIgnoreCase("PnPProductID")) {
                                        prodID = pnpNode.getTextContent();
                                    }
                                }
                                pi.getDeviceInfo().addPlugPlay(devID, prodID);
                            } else if (subCapNode.getNodeName().equalsIgnoreCase("ComponentID")) {
                                if (pi.getComponentIDs().isEmpty()) {
                                    pi.setComponentIDs(subCapNode.getTextContent());
                                } else {
                                    pi.setComponentIDs(pi.getComponentIDs() + "," + subCapNode.getTextContent());
                                }
                            }
                        }

                    }
                }
            }
        }
        if (flag == 0) {
            home.getPieTree().getPayloadMain().getPayloadList().add(pi);
        }
    }

    private void getFMPWrapperInfo(NodeList childNodes) {
        String name = "";
        String id = "";
        String filePathName = "";
        String digiSign = "";
        String dvrFileName = "";
        String invSupp = "";
        String invSrc = "";
        String upSupp = "";
        String upSrc = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("FMPWrapperInformation")) {
                //getting attributes
                if (node.hasAttributes()) {
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int count1 = 0; count1 < nodeMap.getLength(); count1++) {
                        Node attNode = nodeMap.item(count1);
                        if (attNode.getNodeName().equalsIgnoreCase("identifier")) {
                            id = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("name")) {
                            name = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("filePathName")) {
                            filePathName = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("digitalSignature")) {
                            digiSign = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("driverFileName")) {
                            dvrFileName = attNode.getNodeValue();
                        }
                    }
                }
                //getting attributes of from child nodes(inventory and update) of fmpwrapperinfo
                for (int count1 = 0; count1 < node.getChildNodes().getLength(); count1++) {
                    Node subNode = node.getChildNodes().item(count1);
                    if (subNode.getNodeName().equalsIgnoreCase("Inventory")) {
                        invSupp = getNodeAttr(subNode, "supported");
                        invSrc = getNodeAttr(subNode, "source");
                    } else if (subNode.getNodeName().equalsIgnoreCase("Update")) {
                        upSupp = getNodeAttr(subNode, "supported");
                        upSrc = getNodeAttr(subNode, "rollback");
                    }
                }
                //check if fmpWrapperId already in payload list
                int flag = 0;
                for (PayloadInfo pi : home.getPieTree().getPayloadMain().getPayloadList()) {
                    if (id.equalsIgnoreCase(pi.getFMPWrapperID())) {
                        pi.addFmpWrapperInfo(id, name, filePathName, digiSign, dvrFileName, invSupp, invSrc, upSupp, upSrc);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    PayloadInfo pi = new PayloadInfo();
                    pi.addFmpWrapperInfo(id, name, filePathName, digiSign, dvrFileName, invSupp, invSrc, upSupp, upSrc);
                    home.getPieTree().getPayloadMain().getPayloadList().add(pi);
                }
            }
        }
    }

    private void getUnsupportedOs(NodeList childNodes, DefaultListModel listA, DefaultListModel listB) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("OperatingSystem")) {
                String osName = getNodeInfo(node.getChildNodes(), "Display");
                listB.addElement(osName);
                listA.removeElement(osName);
            }
        }
    }

    private String getNodeAttr(Node node, String attrName) {
        String attr = "";
        if (node.hasAttributes()) {
            NamedNodeMap nodeMap = node.getAttributes();
            for (int count1 = 0; count1 < nodeMap.getLength(); count1++) {
                Node attNode = nodeMap.item(count1);
                if (attNode.getNodeName().equalsIgnoreCase(attrName)) {
                    return attNode.getNodeValue();
                }
            }
        }
        return attr;
    }

    private void getFeatureSetInfo(NodeList childNodes, String type) {
        String value = "";
        String valSep = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("Feature")) {
                String name = getNodeAttr(node, "name");
                for (int count1 = 0; count1 < childNodes.getLength(); count1++) {
                    Node subNode = node.getChildNodes().item(count1);
                    if (subNode.getNodeName().equalsIgnoreCase("Command")) {
                        value = getNodeAttr(subNode, "value");
                        valSep = getNodeAttr(subNode, "valueSeparator");
                    }
                }
                if (type.equalsIgnoreCase("0")) {
                    home.getPieTree().getInventory().getFeatureSet().addRow(name, value, valSep);
                } else if (type.equalsIgnoreCase("1")) {
                    home.getPieTree().getExecution().getFeatureSet().addRow(name, value, valSep);
                } else if (type.equalsIgnoreCase("2")) {
                    home.getPieTree().getFeatureSet().addRow(name, value, valSep);
                }

            }
        }
    }

    private String getNodeInfo(NodeList childNodes, String name) {
        String mod = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node subNode = childNodes.item(count);
            if (subNode.getNodeName().equalsIgnoreCase(name)) {
                if (mod.isEmpty()) {
                    mod = subNode.getTextContent();
                } else {
                    mod = mod + "," + subNode.getTextContent();
                }
            }
        }
        return mod;
    }

    private void getPluginInfo(Node node) {
        String type = null;
        String timeout = null;
        if (node.hasAttributes()) {
            NamedNodeMap nodeMap = node.getAttributes();
            for (int count = 0; count < nodeMap.getLength(); count++) {
                Node attNode = nodeMap.item(count);
                if (attNode.getNodeName().equalsIgnoreCase("type")) {
                    type = attNode.getNodeValue();
                } else if (attNode.getNodeName().equalsIgnoreCase("timeout")) {
                    timeout = attNode.getNodeValue();
                }
            }
        }
        NodeList childNodes = node.getChildNodes();
        if (type.equalsIgnoreCase("0")) {
            home.getPieTree().getInventory().getInventory().getjTextField1().setText(timeout);
            for (int count = 0; count < childNodes.getLength(); count++) {
                Node subNode = childNodes.item(count);
                if (subNode.getNodeName().equalsIgnoreCase("Startfile")) {
                    home.getPieTree().getInventory().getInventory().getjTextField2().setText(subNode.getTextContent());
                } else if (subNode.getNodeName().equalsIgnoreCase("CliToStdout")) {
                    home.getPieTree().getInventory().getInventory().getjTextField3().setText(getNodeInfo(subNode.getChildNodes(), "Command"));
                } else if (subNode.getNodeName().equalsIgnoreCase("CliToFile")) {
                    home.getPieTree().getInventory().getInventory().getjTextField4().setText(getNodeInfo(subNode.getChildNodes(), "Command"));
                    home.getPieTree().getInventory().getInventory().getjTextField6().setText(getNodeInfo(subNode.getChildNodes(), "Output"));
                } else if (subNode.getNodeName().equalsIgnoreCase("Modules")) {
                    home.getPieTree().getInventory().getInventory().getjTextField5().setText(getNodeInfo(subNode.getChildNodes(), "Module"));
                } else if (subNode.getNodeName().equalsIgnoreCase("Messages")) {
                    for (int count1 = 0; count1 < subNode.getChildNodes().getLength(); count1++) {
                        Node subNode1 = subNode.getChildNodes().item(count1);
                        if (subNode1.getNodeName().equalsIgnoreCase("Message")) {
                            home.getPieTree().getInventory().getInventory().addRow(getNodeAttr(subNode1,"id"), subNode1.getTextContent());
                        }
                    }
                } else if (subNode.getNodeName().equalsIgnoreCase("FeatureSet")) {
                    getFeatureSetInfo(subNode.getChildNodes(), type);
                } else if (subNode.getNodeName().equalsIgnoreCase("UnsupportedOperatingSystems")) {
                    getUnsupportedOs(subNode.getChildNodes(), home.getPieTree().getInventory().getListOS().getListA(), home.getPieTree().getInventory().getListOS().getListB());
                }
            }
        } else if (type.equalsIgnoreCase("1")) {
            home.getPieTree().getExecution().getExecution().getjTextField1().setText(timeout);
            for (int count = 0; count < childNodes.getLength(); count++) {
                Node subNode = childNodes.item(count);
                if (subNode.getNodeName().equalsIgnoreCase("Startfile")) {
                    home.getPieTree().getExecution().getExecution().getjTextField2().setText(subNode.getTextContent());
                } else if (subNode.getNodeName().equalsIgnoreCase("CliToStdout")) {
                    home.getPieTree().getExecution().getExecution().getjTextField3().setText(getNodeInfo(subNode.getChildNodes(), "Command"));
                } else if (subNode.getNodeName().equalsIgnoreCase("CliToFile")) {
                    home.getPieTree().getExecution().getExecution().getjTextField4().setText(getNodeInfo(subNode.getChildNodes(), "Command"));
                    home.getPieTree().getExecution().getExecution().getjTextField10().setText(getNodeInfo(subNode.getChildNodes(), "Output"));
                } else if (subNode.getNodeName().equalsIgnoreCase("CliforceToStdout")) {
                    home.getPieTree().getExecution().getExecution().getjTextField5().setText(getNodeInfo(subNode.getChildNodes(), "Command"));
                } else if (subNode.getNodeName().equalsIgnoreCase("CliforceToFile")) {
                    home.getPieTree().getExecution().getExecution().getjTextField6().setText(getNodeInfo(subNode.getChildNodes(), "Command"));
                    home.getPieTree().getExecution().getExecution().getjTextField11().setText(getNodeInfo(subNode.getChildNodes(), "Output"));
                } else if (subNode.getNodeName().equalsIgnoreCase("Modules")) {
                    home.getPieTree().getExecution().getExecution().getjTextField7().setText(getNodeInfo(subNode.getChildNodes(), "Module"));
                } else if (subNode.getNodeName().equalsIgnoreCase("Messages")) {
                    for (int count1 = 0; count1 < subNode.getChildNodes().getLength(); count1++) {
                        Node subNode1 = subNode.getChildNodes().item(count1);
                        if (subNode1.getNodeName().equalsIgnoreCase("Message")) {
                            home.getPieTree().getExecution().getExecution().addRow(getNodeAttr(subNode1,"id"), subNode1.getTextContent());
                        }
                    }
                } else if (subNode.getNodeName().equalsIgnoreCase("FeatureSet")) {
                    getFeatureSetInfo(subNode.getChildNodes(), type);
                } else if (subNode.getNodeName().equalsIgnoreCase("UnsupportedOperatingSystems")) {
                    getUnsupportedOs(subNode.getChildNodes(), home.getPieTree().getExecution().getListOS().getListA(), home.getPieTree().getExecution().getListOS().getListB());
                }
            }
        }

    }

    private void readPieNode(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("Plugins")) {
                readPieNode(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("Plugin")) {
                getPluginInfo(node);
            } else if (node.getNodeName().equalsIgnoreCase("Info")) {
                if (node.hasAttributes()) {
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int count1 = 0; count1 < nodeMap.getLength(); count1++) {
                        Node attNode = nodeMap.item(count1);
                        if (attNode.getNodeName().equalsIgnoreCase("identifier")) {
                            home.getPieTree().getPieRoot().getjTextField1().setText(attNode.getNodeValue());
                        } else if (attNode.getNodeName().equalsIgnoreCase("folderName")) {
                            home.getPieTree().getPieRoot().getjTextField2().setText(attNode.getNodeValue());
                        }
                    }
                }
            } else if (node.getNodeName().equalsIgnoreCase("RebootRequired")) {
                if (node.getTextContent().equalsIgnoreCase("1")) {
                    home.getPieTree().getExecution().getExecution().getjCheckBox1().setSelected(true);
                } else {
                    home.getPieTree().getExecution().getExecution().getjCheckBox1().setSelected(false);
                }
            } else if (node.getNodeName().equalsIgnoreCase("CopyRequired")) {
                if (node.getTextContent().equalsIgnoreCase("0")) {
                    home.getPieTree().getExecution().getExecution().getjCheckBox2().setSelected(false);
                } else {
                    home.getPieTree().getExecution().getExecution().getjCheckBox2().setSelected(true);
                }
            } else if (node.getNodeName().equalsIgnoreCase("FMPWrappers")) {
                getFMPWrapperInfo(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("SupportedSystems")) {
                //not used any more
            } else if (node.getNodeName().equalsIgnoreCase("Runtime")) {
                //not used any more
            } else if (node.getNodeName().equalsIgnoreCase("Payload")) {
                getPayloadInfo(node);
            } else if (node.getNodeName().equalsIgnoreCase("UnsupportedOperatingSystems")) {
                getUnsupportedOs(node.getChildNodes(), home.getPieTree().getUnsupportedOS().getListA(), home.getPieTree().getUnsupportedOS().getListB());
            } else if (node.getNodeName().equalsIgnoreCase("FeatureSet")) {
                getFeatureSetInfo(node.getChildNodes(), "2");
            } else if (node.getNodeName().equalsIgnoreCase("Dependency")) {
                getDependencyInfo(node, home.getPieTree().getDependency());
            } else if (node.getNodeName().equalsIgnoreCase("SoftDependency")) {
                getDependencyInfo(node, home.getPieTree().getSoftDependency());
            }
        }
    }

    private void readMupNode(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("packageinformation")) {
                readPackageInfo(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("executable")) {
                readExecutableInfo(node);
            } else if (node.getNodeName().equalsIgnoreCase("behaviors")) {
                readBehaviorInfo(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("parameters")) {
                readBehaviorInfo(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("returncodes")) {
                readReturnCodes(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("inventorymetadata")) {
                readMupNode(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("extractdriversidentifier")) {
                readExtractDriversInfo(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("fullpackageidentifier")) {
                readFullPackageInfo(node.getChildNodes());
            }
        }

    }

    private void readPackageInfo(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("installertype")) {
                System.out.println("installer type : " + node.getTextContent());
            } else if (node.getNodeName().equalsIgnoreCase("packagegrouping")) {
                System.out.println("packagegrouping : " + node.getTextContent());
            } else if (node.getNodeName().equalsIgnoreCase("packagingtype")) {
                System.out.println("packagingtype : " + node.getTextContent());
            } else if (node.getNodeName().equalsIgnoreCase("releasetype")) {
                for (int itemCount = 0; itemCount < home.getPackageInformation().getjComboBox2().getItemCount(); itemCount++) {
                    if (node.getTextContent().equalsIgnoreCase(home.getPackageInformation().getjComboBox2().getItemAt(itemCount).toString())) {
                        home.getPackageInformation().getjComboBox2().setSelectedIndex(itemCount);
                        home.getConstituents().getjLabel4().setText(node.getTextContent());
                        break;
                    }
                }
            } else if (node.getNodeName().equalsIgnoreCase("mupspecificationversion")) {
                for (int itemCount = 0; itemCount < home.getPackageInformation().getjComboBox1().getItemCount(); itemCount++) {
                    if (node.getTextContent().equalsIgnoreCase(home.getPackageInformation().getjComboBox1().getItemAt(itemCount).toString())) {
                        home.getPackageInformation().getjComboBox1().setSelectedIndex(itemCount);
                    }
                }
            } else if (node.getNodeName().equalsIgnoreCase("version")) {
                home.getPackageInformation().getjTextField2().setText(node.getTextContent());
            } else if (node.getNodeName().equalsIgnoreCase("name")) {
                home.getPackageInformation().getjTextField1().setText(node.getTextContent());
            } else if (node.getNodeName().equalsIgnoreCase("supportedoperatingsystems")) {
                readSupportedOs(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("content")) {
                readPackageInfo(node.getChildNodes());
            } else if (node.getNodeName().equalsIgnoreCase("Device")) {
                if (node.hasAttributes()) {
                    readDeviceInfo(node.getChildNodes(), node.getAttributes().getNamedItem("componentID").getNodeValue());
                }
            }

        }
    }

    private void readSupportedOs(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("osidentifier")) {
                String osName = node.getAttributes().getNamedItem("name").getNodeValue() + " - " + node.getAttributes().getNamedItem("architecture").getNodeValue();
                home.getApplicability().getListB().addElement(osName);
                home.getApplicability().getListA().removeElement(osName);
            }
        }
    }

    private void readExecutableInfo(Node node) {
        if (node.hasAttributes()) {
            NamedNodeMap nodeMap = node.getAttributes();
            for (int count = 0; count < nodeMap.getLength(); count++) {
                Node attNode = nodeMap.item(count);
                if (attNode.getNodeName().equalsIgnoreCase("timeout")) {
                    home.getInstaller32().getjTextField2().setText(attNode.getNodeValue());
                }
            }
        }
        //os architecture attribute not used. assuming it will be x86
        for (int count = 0; count < node.getChildNodes().getLength(); count++) {
            Node subNode = node.getChildNodes().item(count);
            if (subNode.getNodeName().equalsIgnoreCase("executablename")) {
                home.getInstaller32().getjTextField1().setText(subNode.getTextContent());
            } else if (subNode.getNodeName().equalsIgnoreCase("requiredparameters")) {
                Node subNode1 = subNode.getChildNodes().item(0);
                if (subNode1.getNodeName().equalsIgnoreCase("commandlineparameter")) {
                    home.getInstaller32().getjTextField3().setText(subNode1.getTextContent());
                }
            }
        }
    }

    private void readBehaviorInfo(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.hasChildNodes()) {
                if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("unattended")) {
                    addBehaviors(node.getChildNodes(), home.getUnattended_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("attended")) {
                    addBehaviors(node.getChildNodes(), home.getAttended_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("extractdrivers")) {
                    addBehaviors(node.getChildNodes(), home.getExtractDrivers_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("freshinstall")) {
                    addBehaviors(node.getChildNodes(), home.getFreshInstall_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("help")) {
                    addBehaviors(node.getChildNodes(), home.getHelp_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("driveronly")) {
                    addBehaviors(node.getChildNodes(), home.getDriverOnly_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("applicationonly")) {
                    addBehaviors(node.getChildNodes(), home.getApplicationOnly_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("force")) {
                    addBehaviors(node.getChildNodes(), home.getForce_x86());
                } else if (node.getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("logfile")) {
                    addBehaviors(node.getChildNodes(), home.getLog_x86());
                }
            }
        }
    }

    private void addBehaviors(NodeList childNodes, InstallerBehaviour behaviour) {
        for (int count = 0; count < childNodes.getLength(); count++) { //vender option node
            Node node = childNodes.item(count);
            for (int subCount = 0; subCount < node.getChildNodes().getLength(); subCount++) { //option value node
                Node subNode = node.getChildNodes().item(subCount);
                if (subNode.getNodeName().equalsIgnoreCase("optionvalue")) {
                    addBehaviorNode(subNode, behaviour);
                } else if (subNode.getNodeName().equalsIgnoreCase("container")) {
                    for (int subCount1 = 0; subCount1 < subNode.getChildNodes().getLength(); subCount1++) {
                        Node subNode1 = subNode.getChildNodes().item(subCount1);
                        if (subNode1.getNodeName().equalsIgnoreCase("optionvalue") || subNode1.getNodeName().equalsIgnoreCase("containervalue")) {
                            addBehaviorNode(subNode1, behaviour);
                        }
                    }
                }
            }
        }
    }

    private void addBehaviorNode(Node subNode, InstallerBehaviour behaviour) {
        String switch_delimiter = "";
        String value = "";
        Boolean reqValue = null;
        String enclose = "";
        NamedNodeMap nodeMap = subNode.getAttributes();
        for (int count2 = 0; count2 < nodeMap.getLength(); count2++) {
            Node attNode = nodeMap.item(count2);
            if (attNode.getNodeName().equalsIgnoreCase("switch")) {
                switch_delimiter = attNode.getNodeValue();
            } else if (attNode.getNodeName().equalsIgnoreCase("requiresvalue")) {
                reqValue = Boolean.valueOf(attNode.getNodeValue());
            } else if (attNode.getNodeName().equalsIgnoreCase("enclose")) {
                enclose = attNode.getNodeValue();
            }
        }

        value = subNode.getTextContent();
        if (!switch_delimiter.equalsIgnoreCase("")) {
            behaviour.addRow(new Object[]{switch_delimiter, value, reqValue, enclose});
        }
    }

    private void readReturnCodes(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.hasAttributes()) {
                String attName = node.getAttributes().getNamedItem("name").getNodeValue();
                String val = getReturnCode(node.getChildNodes());
                if (attName.equalsIgnoreCase("SUCCESS")) {
                    home.getInstaller32().getjTextField4().setText(val);
                } else if (attName.equalsIgnoreCase("ERROR")) {
                    home.getInstaller32().getjTextField5().setText(val);
                } else if (attName.equalsIgnoreCase("REBOOT_REQUIRED")) {
                    home.getInstaller32().getjTextField6().setText(val);
                } else if (attName.equalsIgnoreCase("DEP_SOFT_ERROR")) {
                    home.getInstaller32().getjTextField7().setText(val);
                } else if (attName.equalsIgnoreCase("DEP_HARD_ERROR")) {
                    home.getInstaller32().getjTextField8().setText(val);
                } else if (attName.equalsIgnoreCase("ERROR_INSTALL_PLATFORM_UNSUPPORTED")) {
                    home.getInstaller32().getjTextField9().setText(val);
                } else if (attName.equalsIgnoreCase("REBOOTING_SYSTEM")) {
                    home.getInstaller32().getjTextField10().setText(val);
                } else if (attName.equalsIgnoreCase("PASSWORD_REQUIRED")) {
                    home.getInstaller32().getjTextField11().setText(val);
                } else if (attName.equalsIgnoreCase("NO_DOWNGRADE")) {
                    home.getInstaller32().getjTextField12().setText(val);
                } else if (attName.equalsIgnoreCase("REBOOT_UPDATE_PENDING")) {
                    home.getInstaller32().getjTextField13().setText(val);
                } else if (attName.equalsIgnoreCase("UNKNOWN_OPTION")) {
                    home.getInstaller32().getjTextField14().setText(val);
                }
            }
        }
    }

    private String getReturnCode(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("vendorreturncode")) {
                return node.getTextContent();
            }
        }
        return null;
    }

    private void readExtractDriversInfo(NodeList childNodes) {// this code is only to read the display name and save it as image name
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("pnpids")) {
                for (int count1 = 0; count1 < node.getChildNodes().getLength(); count1++) {
                    Node subNode = node.getChildNodes().item(count1);
                    if (subNode.getNodeName().equalsIgnoreCase("pnpdevice") && subNode.getAttributes().getLength() > 1) {
                        for (ConstituentItem cItem : home.getConstituentList()) {
                            for (ImageData iData : cItem.getImageList()) {
                                if (subNode.getAttributes().getNamedItem("componentID").toString().contains(cItem.getComponentId())) {
                                    String name = subNode.getAttributes().getNamedItem("display").toString().split("\"")[1];
                                    iData.setName(name);
                                    for (ImageInventoryData iiData : iData.getImageInventoryDataList()) {
                                        iiData.setImageName(name);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void readFullPackageInfo(NodeList childNodes) {
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("pnpids") || node.getNodeName().equalsIgnoreCase("msis") || node.getNodeName().equalsIgnoreCase("registrykeys") || node.getNodeName().equalsIgnoreCase("softwareidentityinstances")) {
                readFullPackageInfo(node.getChildNodes());
                if (node.getNodeName().equalsIgnoreCase("pnpids")) {
                    home.getConstituents().getjComboBox1().setSelectedIndex(1);
                } else if (node.getNodeName().equalsIgnoreCase("msis")) {
                    home.getConstituents().getjComboBox1().setSelectedIndex(2);
                } else if (node.getNodeName().equalsIgnoreCase("registrykeys")) {
                    home.getConstituents().getjComboBox1().setSelectedIndex(3);
                } else if (node.getNodeName().equalsIgnoreCase("softwareidentityinstances")) {
                    home.getConstituents().getjComboBox1().setSelectedIndex(4);
                }
            } else if (node.getNodeName().equalsIgnoreCase("msi")) {
                if (node.hasAttributes()) {
                    getMsiInfo(node.getChildNodes(), node.getAttributes().getNamedItem("componentID").getNodeValue());
                }
            } else if (node.getNodeName().equalsIgnoreCase("registrykey")) {
                if (node.hasAttributes()) {
                    getRegistryInfo(node.getChildNodes(), node.getAttributes().getNamedItem("componentID").getNodeValue());
                }
            } else if (node.getNodeName().equalsIgnoreCase("softwareidentity")) {
                if (node.hasAttributes()) {
                    getSoftwareInstanceInfo(node.getChildNodes(), node.getAttributes().getNamedItem("componentID").getNodeValue());
                }
            }
        }
    }

    private void getMsiInfo(NodeList childNodes, String componentID) {
        String idNumber = "";
        String upgradeCode = "";
        String version = "";
        String vendor = "";
        String name = "";
        String caption = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("identifyingnumber")) {
                idNumber = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("upgradecode")) {
                upgradeCode = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("version")) {
                version = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("name")) {
                name = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("caption")) {
                caption = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("vendor")) {
                vendor = node.getTextContent();
            }
        }

        home.getConstituents().getMsis().addRow(new Object[]{componentID, idNumber, name, upgradeCode, version, vendor, caption});
    }

    private void getRegistryInfo(NodeList childNodes, String componentID) {
        String name = "";
        String value = "";
        String displayName = "";
        String displayValue = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("name")) {
                name = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("value")) {
                value = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("displayName")) {
                displayName = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("displayValue")) {
                displayValue = node.getTextContent();
            }
        }
        home.getConstituents().getRegistrykeys().addRow(new Object[]{componentID, name, value, displayName, displayValue});
    }

    private void getSoftwareInstanceInfo(NodeList childNodes, String componentID) {
        String elementName = "";
        String versionString = "";
        String value = "";
        String type = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("elementname")) {
                elementName = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("versionstring")) {
                versionString = node.getTextContent();
            } else if (node.getNodeName().equalsIgnoreCase("identityinfo")) {
                for (int count2 = 0; count2 < node.getChildNodes().getLength(); count2++) {
                    Node subNode = node.getChildNodes().item(count2);
                    if (subNode.getNodeName().equalsIgnoreCase("value")) {
                        value = subNode.getTextContent();
                    } else if (subNode.getNodeName().equalsIgnoreCase("type")) {
                        type = subNode.getTextContent();
                    }
                }
            }
        }
        home.getConstituents().getSoftwareIdentityInstance().addRow(new Object[]{componentID, elementName, versionString, value, type});
    }

    private void readDeviceInfo(NodeList childNodes, String componentID) {
        ArrayList<ImageInventoryData> imageInventoryDataList = new ArrayList<ImageInventoryData>();
        int imageCount = 0;
        int invenCount = 0;
        String constiFileName = "";
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("PCIInfo") || node.getNodeName().equalsIgnoreCase("PnPInfo") || node.getNodeName().equalsIgnoreCase("Generic")) {
                invenCount++;
            } else if (node.getNodeName().equalsIgnoreCase("Image")) {
                imageCount++;
            }
        }
        Object[][] tData = new Object[invenCount][];
        ImageInventoryData ivData = new ImageInventoryData();
        int cno = 0;
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("PCIInfo")) {
                //tData[cno][0]="PCI";
                if (node.hasAttributes()) {
                    NamedNodeMap nodeMap = node.getAttributes();
                    tData[cno] = new Object[nodeMap.getLength() + 1];
                    tData[cno][0] = "PCI";
                    for (int count2 = 0; count2 < nodeMap.getLength(); count2++) {
                        Node attNode = nodeMap.item(count2);
                        if (attNode.getNodeName().equalsIgnoreCase("deviceID")) {
                            tData[cno][1] = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("vendorID")) {
                            tData[cno][2] = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("subDeviceID")) {
                            tData[cno][3] = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("subVendorID")) {
                            tData[cno][4] = attNode.getNodeValue();
                        }
                    }
                    //
                }
                cno++;
            } else if (node.getNodeName().equalsIgnoreCase("PnPInfo")) {
                tData[cno] = new Object[3];
                tData[cno][0] = "PNP";
                for (int count2 = 0; count2 < node.getChildNodes().getLength(); count2++) {
                    Node subNode = node.getChildNodes().item(count2);
                    if (subNode.getNodeName().equalsIgnoreCase("PNPID") || subNode.getNodeName().equalsIgnoreCase("ACPIID")) {
                        tData[cno][2] = subNode.getTextContent();
                    } else if (subNode.getNodeName().equalsIgnoreCase("PnPProductID")) {
                        tData[cno][1] = subNode.getTextContent();
                    }
                }
                cno++;
            } else if (node.getNodeName().equalsIgnoreCase("Generic")) {
                tData[cno] = new Object[6];
                tData[cno][0] = "Abstract";
                tData[cno][5] = node.getTextContent();
                cno++;
            }
        }

        Object[][] imageData = new Object[imageCount][5];
        cno = 0;
        for (int count = 0; count < childNodes.getLength(); count++) {
            String type = "";
            String version = "";
            String file = "";
            String osList = "";
            Node node = childNodes.item(count);
            if (node.getNodeName().equalsIgnoreCase("Image")) {
                //taking attributes
                if (node.hasAttributes()) {
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int count2 = 0; count2 < nodeMap.getLength(); count2++) {
                        Node attNode = nodeMap.item(count2);
                        if (attNode.getNodeName().equalsIgnoreCase("type")) {
                            type = attNode.getNodeValue();
                        } else if (attNode.getNodeName().equalsIgnoreCase("version")) {
                            version = attNode.getNodeValue();
                        }
                    }
                }
                //taking file details
                for (int count3 = 0; count3 < node.getChildNodes().getLength(); count3++) {
                    Node subNode = node.getChildNodes().item(count3);
                    if (subNode.getNodeName().equalsIgnoreCase("file")) {
                        if (file.isEmpty()) {
                            file = file.concat(subNode.getTextContent());
                        } else {
                            file = file.concat(",").concat(subNode.getTextContent());
                        }
                    } else if (subNode.getNodeName().equalsIgnoreCase("supportedoperatingsystems")) {
                        for (int count4 = 0; count4 < subNode.getChildNodes().getLength(); count4++) {
                            Node sub2Node = subNode.getChildNodes().item(count4);
                            if (sub2Node.getNodeName().equalsIgnoreCase("osidentifier")) {
                                String osName = sub2Node.getAttributes().getNamedItem("name").getNodeValue() + " - " + sub2Node.getAttributes().getNamedItem("architecture").getNodeValue();
                                if (osList.isEmpty()) {
                                    osList = osList.concat(osName);
                                } else {
                                    osList = osList.concat(",").concat(osName);
                                }
                            }
                        }
                    }
                }
                imageData[cno][0] = file;
                imageData[cno][1] = type;
                imageData[cno][2] = version;
                imageData[cno][3] = file;
                imageData[cno][4] = osList;
                cno++;

                if (constiFileName.isEmpty()) {
                    constiFileName = file;
                } else {
                    constiFileName = constiFileName.concat("," + file);
                }
            }
        }

        ivData.setTableData(tData);
        ivData.setInventoryMethod("pnpIds");
        ivData.setImageName(constiFileName);
        imageInventoryDataList.add(ivData);

        home.addNode("Constituent No." + componentID, componentID, imageData, imageInventoryDataList);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.io.File;
import java.util.List;
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

/**
 *
 * @author Sumit_Saseendran
 */
public class CreatePieXml {
    
    private Node getModelNode(Document doc, Model mItem) {
        Element mNode = doc.createElement("Model");
        mNode.setAttribute("systemID", mItem.getSystemID());
        if(!mItem.getSystemIdType().toString().isEmpty()){
            mNode.setAttribute("systemIDType", mItem.getSystemIdType().toString());
        }
        if(!mItem.getSystemContext().toString().isEmpty()){
            mNode.setAttribute("systemContext", mItem.getSystemContext().toString());
        }
        for(DisplayType dType:mItem.getDisplayTextList()){
            mNode.appendChild(getDisplayNode(doc, dType, "Display"));
        }
        return mNode;
    }
    
    private Node getBrandNode(Document doc, BrandInfoItem bItem) {
        Element bNode = doc.createElement("Brand");
        bNode.setAttribute("key", bItem.getKey());
        bNode.setAttribute("prefix", bItem.getPrefix());
        if(!bItem.getDisplayTextList().isEmpty()){
            for(DisplayType dType:bItem.getDisplayTextList()){
                bNode.appendChild(getDisplayNode(doc, dType, "Display"));
            }
        }
        if(!bItem.getModelList().isEmpty()){
            for(Model mItem:bItem.getModelList()){
                bNode.appendChild(getModelNode(doc,mItem));
            }
        }
        return bNode;
    }
    
    private Node getDisplayNode(Document doc, DisplayType dt,String name) {
        Element dtNode = doc.createElement(name);
        dtNode.setAttribute("lang", dt.getLang());
        dtNode.appendChild(doc.createTextNode(dt.getName()));
        return dtNode;
    }
    
    private Node getDependencyNode(Document doc, DependencyInfo dInfo, String name) {
        Element depNode = doc.createElement(name);
        if(!dInfo.getComponentType().isEmpty()){
            depNode.setAttribute("componentType", dInfo.getComponentType());
        }
        if(!dInfo.getComponentID().isEmpty()){
            depNode.setAttribute("componentID", dInfo.getComponentID());
        }
        if(!dInfo.getVersion().isEmpty()){
            depNode.setAttribute("version", dInfo.getVersion());
        }
        if(!dInfo.getPrerequisite().isEmpty()){
            depNode.setAttribute("prerequisite", dInfo.getPrerequisite());
        }
        if(!dInfo.getPath().isEmpty()){
            depNode.setAttribute("path", dInfo.getPath());
        }
        depNode.setAttribute("releaseID", dInfo.getReleaseID());
        if(!dInfo.getContext().isEmpty()){
            depNode.setAttribute("context", dInfo.getContext());
        }
        //adding display nodes
        for(DisplayType dt:dInfo.getDisplayTextList()){
            depNode.appendChild(getDisplayNode(doc, dt,"Display"));
        }   
        
        //adding detail nodes
        if(name.equalsIgnoreCase("softdependency")){
            for(DisplayType dt:dInfo.getFeatureMissedList()){
            depNode.appendChild(getDisplayNode(doc, dt,"detail"));
            }   
        }
        
        //adding pci nodes
        if(!dInfo.getDeviceInfo().getPciIds().isEmpty()){
            for(PCIIDs pci:dInfo.getDeviceInfo().getPciIds()){
                depNode.appendChild(getPCIDeviceNode(doc,pci));
            }
        }
        
        //adding pnp node
        if(!dInfo.getDeviceInfo().getPlugPlayIds().isEmpty()){
            for(PlugPlayIDs pnp:dInfo.getDeviceInfo().getPlugPlayIds()){
                depNode.appendChild(getPnPDeviceNode(doc,pnp));
            }
        }
            
        //adding brand info node
        if(!dInfo.getBrandInfo().getBrandInfoList().isEmpty()){
            for(BrandInfoItem bItem:dInfo.getBrandInfo().getBrandInfoList()){
                depNode.appendChild(getBrandNode(doc,bItem));
            }
        }
        return depNode;
    }

    private Node getPCIDeviceNode(Document doc, PCIIDs pci) {
        Element pciInfo = doc.createElement("PCIInfo");
        pciInfo.setAttribute("deviceID", pci.getDeviceID());
        pciInfo.setAttribute("vendorID", pci.getVendorID());
        pciInfo.setAttribute("subDeviceID", pci.getSubDeviceID());
        pciInfo.setAttribute("subVendorID", pci.getSubVendorID());
        return pciInfo;
    }
   
    private Node getPnPDeviceNode(Document doc, PlugPlayIDs pnp) {
        Element pnpInfo = doc.createElement("PnPInfo");
        String deviceId = pnp.getDeviceID();
        if(deviceId.length()==3){
            pnpInfo.appendChild(getChildElements(doc, "PNPID", deviceId));
        }else if(deviceId.length()==4){
            pnpInfo.appendChild(getChildElements(doc, "ACPIID", deviceId));
        }
        pnpInfo.appendChild(getChildElements(doc, "PnPProductID", pnp.getProductID()));
        return pnpInfo;
    }
    
    private Node getCapsulePayloadPart(Document doc, PayloadInfo pi) {
        Element capsulePayload = doc.createElement("CapsulePayloadParts");
        Element capsulePayloadPart = doc.createElement("CapsulePayloadPart");
        //payload node
        Element payloadImage = doc.createElement("PayloadImage");
        payloadImage.setAttribute("id", pi.getImageID());
        payloadImage.setAttribute("filename", pi.getFileName());
        payloadImage.setAttribute("version", pi.getVersion());
        payloadImage.setAttribute("skip", pi.getSkip());
        if(!pi.getPayloadType().isEmpty()){
           payloadImage.setAttribute("type", pi.getPayloadType()); 
        }
        capsulePayloadPart.appendChild(payloadImage);
        //fmp driver node
        Element fmpDrvrNode = doc.createElement("FMPDriver");
        fmpDrvrNode.setAttribute("name", pi.getFmpDrvrName());
        if(!pi.getPath().isEmpty()){
            fmpDrvrNode.setAttribute("path", pi.getPath());
        }
        fmpDrvrNode.setAttribute("version", pi.getFmpDrvrVerison());
        fmpDrvrNode.setAttribute("comparisonOperator", pi.getFmpComparison().toString());
        capsulePayloadPart.appendChild(fmpDrvrNode);
        //PCI info node
        if(!pi.getDeviceInfo().getPciIds().isEmpty()){
            for(PCIIDs pci:pi.getDeviceInfo().getPciIds()){
                capsulePayloadPart.appendChild(getPCIDeviceNode(doc,pci));
            }
        }
        //PNP info node
        if(!pi.getDeviceInfo().getPlugPlayIds().isEmpty()){
            for(PlugPlayIDs pnp:pi.getDeviceInfo().getPlugPlayIds()){
                capsulePayloadPart.appendChild(getPnPDeviceNode(doc,pnp));
            }
        }
        //component id info node
        if(!pi.getComponentIDs().isEmpty()){
            String[] cIds = pi.getComponentIDs().split(",");
            for(String cId:cIds){
                capsulePayloadPart.appendChild(getChildElements(doc, "ComponentID", cId));
            }
        }
        capsulePayload.appendChild(capsulePayloadPart);
        return capsulePayload;
    }
    
    private Node getModules(Document doc, String modules) {
        String[] mods = modules.split(",");
            Element subNode = doc.createElement("Modules");
            for(String mod:mods){
                subNode.appendChild(getChildElements(doc, "Module", mod));
            }
            return subNode;
    }
    
    private Node getInfoType(Document doc, PieDef pieObj, String name) {
        Element subNode = doc.createElement(name);
        subNode.setAttribute("identifier", pieObj.getPieIdentifier());
        subNode.setAttribute("folderName", pieObj.getFolderName());
        return subNode;
    }
    
    private Node getOSNode(Document doc, List<OSIdentifier> unsupportedOSList) {
        Element subNode = doc.createElement("UnsupportedOperatingSystems");
            for(OSIdentifier osId:unsupportedOSList){
                Element osNode =doc.createElement("OperatingSystem");
                osNode.setAttribute("osCode", osId.getOsCode());
                osNode.setAttribute("osVendor", osId.getOsVendor());
                if(!osId.getOsArch().isEmpty()){
                    osNode.setAttribute("osArch", osId.getOsArch());
                }
                if(!osId.getOsType().isEmpty()){
                    osNode.setAttribute("osType", osId.getOsType());
                }
                if(!osId.getMajorVersion().isEmpty()){
                    osNode.setAttribute("majorVersion", osId.getMajorVersion());
                }
                if(!osId.getMinorVersion().isEmpty()){
                    osNode.setAttribute("minorVersion", osId.getMinorVersion());
                }
                if(!osId.getSpMajorVersion().isEmpty()){
                    osNode.setAttribute("spMajorVersion", osId.getSpMajorVersion());
                }
                if(!osId.getSpMinorVersion().isEmpty()){
                    osNode.setAttribute("spMinorVersion", osId.getSpMinorVersion());
                }
                if(osId.getPreinstallationEnvironment()!=null){
                    osNode.setAttribute("spMinorVersion", osId.getPreinstallationEnvironment().toString());
                }
                if(osId.getSuiteMask()>=0){
                    osNode.setAttribute("suiteMask", String.valueOf(osId.getSuiteMask()));
                }
                if(!osId.getDisplay().isEmpty()){
                    for(DisplayType dt:osId.getDisplay()){
                        osNode.appendChild(getDisplayNode(doc,dt,"Display"));
                    }                    
                }
                if(!osId.getSupportedLanguages().getLanguage().isEmpty()){
                    Element displayNode =doc.createElement("SupportedLanguages");
                    for(DisplayType dt:osId.getSupportedLanguages().getLanguage()){
                        Element dtNode =doc.createElement("Language");
                        dtNode.setAttribute("lang", dt.getLang());
                        dtNode.appendChild(doc.createTextNode(dt.getName()));
                        displayNode.appendChild(dtNode);
                    }
                    osNode.appendChild(displayNode);
                }
                subNode.appendChild(osNode);
            }
        return subNode;        
    }
    
    private Node getFeatureNode(Document doc, List<FeatureSetItem> featureSetList) {
        Element subNode = doc.createElement("FeatureSet");
        for (FeatureSetItem fi : featureSetList) {
            Element subSubNode = doc.createElement("Feature");
            subSubNode.setAttribute("name", fi.getFeatureName().value());
            Element commandLineNode = doc.createElement("CommandLine");
            commandLineNode.setAttribute("value", fi.getValue());
            commandLineNode.setAttribute("valueSeperator", fi.getValueSeparator());
            subSubNode.appendChild(commandLineNode);
            subNode.appendChild(subSubNode);
        }
        return subNode;
    }
    
    private Node getPluginInfo(Document doc, PluginType pluginInfo, String name) {
        Element node = doc.createElement("Plugin");
        node.setAttribute("type", name.equalsIgnoreCase("Inventory")?"0":"1");
        node.setAttribute("description", name);
        node.setAttribute("timeout", pluginInfo.getTimeout());
        node.appendChild(getChildElements(doc, "Startfile", pluginInfo.getStartFileName()));
        Element subNode = doc.createElement("CliToStdout");
        subNode.appendChild(getChildElements(doc, "Command", pluginInfo.getCommandStd()));
        node.appendChild(subNode);
        subNode = doc.createElement("CliToFile");
        subNode.appendChild(getChildElements(doc, "Command", pluginInfo.getCommandFile()));
        subNode.appendChild(getChildElements(doc, "Output", pluginInfo.getCommandFileName()));
        node.appendChild(subNode);
        if(name.equalsIgnoreCase("Execution")){
            subNode = doc.createElement("CliforceToStdout");
            subNode.appendChild(getChildElements(doc, "Command", pluginInfo.getCommandStdForce()));
            node.appendChild(subNode);
            subNode = doc.createElement("CliforceToFile");
            subNode.appendChild(getChildElements(doc, "Command", pluginInfo.getCommandFileForce()));
            subNode.appendChild(getChildElements(doc, "Output", pluginInfo.getCommandFileForceName()));
            node.appendChild(subNode);
        }
        //adding modules
        if(!pluginInfo.getModules().isEmpty()){
            node.appendChild(getModules(doc,pluginInfo.getModules()));
        }
               
        //adding Messages 
        if(!pluginInfo.getReturnCodeList().isEmpty()){
            subNode = doc.createElement("Messages");
            for(Returncodes rc:pluginInfo.getReturnCodeList()){
                Element subSubNode =doc.createElement("Message");
                subSubNode.setAttribute("id", rc.getCode());
                subSubNode.appendChild(doc.createTextNode(rc.getMessage()));
                subNode.appendChild(subSubNode);
            }
            node.appendChild(subNode);
        }
        //adding featureset
        if(!pluginInfo.getFeatureSetInfo().getFeatureSetList().isEmpty()){
            node.appendChild(getFeatureNode(doc,pluginInfo.getFeatureSetInfo().getFeatureSetList()));
        }
        //adding OS details
        if(!pluginInfo.getUnsupportedOSInfo().getUnsupportedOSList().isEmpty()){            
            node.appendChild(getOSNode(doc,pluginInfo.getUnsupportedOSInfo().getUnsupportedOSList()));
        }
        return node;
    }

    private Node getPlugins(Document doc, PieDef pieObj, String name) {
        Element node = doc.createElement(name);
        node.appendChild(getPluginInfo(doc,pieObj.getInventoryInfo(),"Inventory"));
        node.appendChild(getPluginInfo(doc,pieObj.getExecutionInfo(),"Execution"));
        return node;
    }
    
    private String getCopyRequiredValue(PieDef pieObj){
        if(pieObj.getExecutionInfo().getCopyPayload().equalsIgnoreCase("true")){
            return "1";
        }else if(pieObj.getExecutionInfo().getCopyPayload().equalsIgnoreCase("false")){
            return "0";
        }
        return null;
    }
    
    private String getRebootRequiredValue(PieDef pieObj){
        if(pieObj.getExecutionInfo().getSystemReboot().equalsIgnoreCase("true")){
            return "1";
        }else if(pieObj.getExecutionInfo().getSystemReboot().equalsIgnoreCase("false")){
            return "0";
        }
        return null;
    }
    
    private Node getChildElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        if(!value.isEmpty()){
            node.appendChild(doc.createTextNode(value));
        }
        return node;
    }

    public CreatePieXml(PieDef pieObj, File selectedFile) {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            //Document doc = builder.newDocument();
            DOMImplementation domImpl = builder.getDOMImplementation();
            Document doc = domImpl.createDocument("", "PIEConfig", null);
//            doc.setXmlVersion("1.0");
//            doc.setXmlStandalone(true);

            Element mainRootElement = doc.getDocumentElement();
            
            //Adding comment line to XML
            Comment titleComment = doc.createComment("This XML is created using DUSA");
            mainRootElement.appendChild(titleComment);
            
            //Describes the schema version used by the PIE
            mainRootElement.setAttribute("SchemaVersion", "1.4");


            // append Plugins to root element
            mainRootElement.appendChild(getPlugins(doc, pieObj, "Plugins"));
         

            // append  Info type to root element
            mainRootElement.appendChild(getInfoType(doc,pieObj,"Info"));
            
            // append child node with RebootRequired info
            mainRootElement.appendChild(getChildElements(doc,"RebootRequired",getRebootRequiredValue(pieObj)));
            
            // append CopyRequired info
            mainRootElement.appendChild(getChildElements(doc,"CopyRequired",getCopyRequiredValue(pieObj)));
            
            // append child element FMP wrapper to root element
            if(!pieObj.getPayloadInfoList().isEmpty()){
                int flag = 0;
                Element fmpNode = null;
                for(PayloadInfo pi:pieObj.getPayloadInfoList()){
                    if(!pi.getFMPWrapperID().isEmpty()){
                        if(flag == 0){
                            fmpNode=doc.createElement("FMPWrappers");
                            flag=1;
                        }
                        Element fmpWrapperInfoNode = doc.createElement("FMPWrapperInformation");
                        fmpWrapperInfoNode.setAttribute("identifier", pi.getFMPWrapperID());
                        fmpWrapperInfoNode.setAttribute("name", pi.getFmpWrapperName());
                        fmpWrapperInfoNode.setAttribute("filePathName", pi.getFileNameFmp());
                        fmpWrapperInfoNode.setAttribute("digitalSignature", pi.getFmpDigiSign());
                        if(!pi.getDvrNameFmp().isEmpty()){
                            fmpWrapperInfoNode.setAttribute("driverFileName", pi.getDvrNameFmp());
                        }
                        Element invNode = doc.createElement("Inventory");
                        invNode.setAttribute("supported", pi.getInventorySupp());
                        invNode.setAttribute("source", pi.getInvSource());
                        fmpWrapperInfoNode.appendChild(invNode);
                        Element upNode = doc.createElement("Update");
                        upNode.setAttribute("supported", pi.getUpdateSupp());
                        upNode.setAttribute("rollback", pi.getRollbackSupp());
                        fmpWrapperInfoNode.appendChild(upNode);
                        fmpNode.appendChild(fmpWrapperInfoNode);
                    }
                }
                if(flag==1){
                    mainRootElement.appendChild(fmpNode);
                }
            }            
            
            // append child element unsupported OS to root element
            if(!pieObj.getUnsupportedOSInfo().getUnsupportedOSList().isEmpty()){
                mainRootElement.appendChild(getOSNode(doc,pieObj.getUnsupportedOSInfo().getUnsupportedOSList()));
            }
                       
            
            // append payload element to root element
            if(!pieObj.getPayloadInfoList().isEmpty()){
                 for(PayloadInfo pi:pieObj.getPayloadInfoList()){
                     Element payloadNode = doc.createElement("Payload");
                     payloadNode.setAttribute("type", pi.getPayloadType());
                     if(!pi.getVersion().isEmpty()){
                         payloadNode.setAttribute("version", pi.getVersion());
                     }
                     if(!pi.getFMPWrapperID().isEmpty()){
                         payloadNode.setAttribute("fmpWrapperIdentifier", pi.getFMPWrapperID());
                     }
                     payloadNode.appendChild(getChildElements(doc, "Startfile", pi.getStartFile()));
                     if(!pi.getModules().isEmpty()){
                         payloadNode.appendChild(getModules(doc,pi.getModules()));
                     }
                     if(!pi.getImageID().isEmpty()){
                         payloadNode.appendChild(getCapsulePayloadPart(doc,pi));
                     }
                     mainRootElement.appendChild(payloadNode);
                 }
            }
            
            //append unsupported os node tp root
            if(!pieObj.getUnsupportedOSInfo().getUnsupportedOSList().isEmpty()){
                mainRootElement.appendChild(getOSNode(doc, pieObj.getUnsupportedOSInfo().getUnsupportedOSList()));
            }
            
            //append featureset node to root
            if(!pieObj.getFeatureSetInfo().getFeatureSetList().isEmpty()){
                mainRootElement.appendChild(getFeatureNode(doc, pieObj.getFeatureSetInfo().getFeatureSetList()));
            }
            
            //append dependency node to root
            if(!pieObj.getDependencyInfo().getReleaseID().isEmpty()){
                mainRootElement.appendChild(getDependencyNode(doc,pieObj.getDependencyInfo(),"Dependency"));
            }
            
            //append soft dependency node to root
            if(!pieObj.getDependencyInfo().getReleaseID().isEmpty()){
                mainRootElement.appendChild(getDependencyNode(doc,pieObj.getDependencyInfo(),"SoftDependency"));
            }
            
            // output DOM XML to console 
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(selectedFile);
            transformer.transform(source, result);

            System.out.println("\nXML DOM Created Successfully..");

        } catch (ParserConfigurationException | DOMException | IllegalArgumentException | TransformerException e) {
            e.printStackTrace();
        }

    }   

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import gui.mup.Applicability;
import gui.mup.ConstituentItem;
import gui.mup.Constituents;
import gui.mup.ImageData;
import gui.mup.InstallerBehaviour;
import gui.mup.InstallerSub1;
import gui.mup.PackageInformation;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
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
public final class MupDef {
    
    public MupDef(PackageInformation packageInformation, Applicability applicability, gui.mup.Installer inst, InstallerSub1 installer32, InstallerSub1 installer64, HashMap<String, InstallerBehaviour> x86Behaviour, HashMap<String, InstallerBehaviour> x64Behaviour, Constituents constituents, ArrayList<ConstituentItem> constituentList) {

        this.setMupSpecVersion(packageInformation.getjComboBox1().getSelectedItem().toString());
        this.setName(packageInformation.getjTextField1().getText());
        this.setReleasetype(EnumReleaseTypesProgrammatic.valueOf(packageInformation.getjComboBox2().getSelectedItem().toString()));
        this.setVersion(packageInformation.getjTextField2().getText());

        this.addOsIdentifiers(applicability);

        this.addInstaller(inst, installer32, installer64, x86Behaviour, x64Behaviour);

        this.addConstituents(constituentList);
        
        this.addPackageLevelInventory(packageInformation,constituents,constituentList);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMupSpecVersion() {
        return mupSpecVersion;
    }

    public void setMupSpecVersion(String mupSpecVersion) {
        this.mupSpecVersion = mupSpecVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public EnumReleaseTypesProgrammatic getReleasetype() {
        return releasetype;
    }

    public void setReleasetype(EnumReleaseTypesProgrammatic releasetype) {
        this.releasetype = releasetype;
    }

    public List<OSIdentifier> getApplicableOS() {
        if (this.applicableOS == null) {
            this.applicableOS = new ArrayList<OSIdentifier>();
        }
        return this.applicableOS;
    }

    public List<Installer> getInstallerList() {
        if (installerList == null) {
            installerList = new ArrayList<Installer>();
        }
        return this.installerList;
    }

    public List<Constituent> getConstituent() {
        if (constituentList == null) {
            constituentList = new ArrayList<Constituent>();
        }
        return this.constituentList;
    }

    void addOsIdentifiers(Applicability applicability) {
        DefaultListModel osList = applicability.getListB();
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
            for (int i = 0; i < osList.getSize(); i++) {
                osIdentifier = new OSIdentifier();
                for (int j = 0; j < nList.getLength(); j++) {
                    Node nNode = nList.item(j);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if (eElement.getAttribute("name").equalsIgnoreCase(osList.getElementAt(i).toString())){
                            osIdentifier.setOsName(EnumOSNames.fromValue(eElement.getAttribute("enumVal")));
                            osIdentifier.setOsArchitecture(EnumOSArchitecture.fromValue(eElement.getAttribute("architecture")));
                            osIdentifier.setVendor(eElement.getAttribute("vendor"));
                            osIdentifier.setMajorVersion(eElement.getAttribute("majorversion"));
                            osIdentifier.setMinorVersion(eElement.getAttribute("minorversion"));
                            osIdentifier.setSpMajorVersion(eElement.getAttribute("spmajorversion"));
                            osIdentifier.setSpMinorVersion(eElement.getAttribute("spminorversion"));
                            osIdentifier.setLocale(eElement.getAttribute("locale"));
                            this.getApplicableOS().add(osIdentifier);
                            break;
                        }
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addInstaller(gui.mup.Installer inst, InstallerSub1 installer32, InstallerSub1 installer64, HashMap<String, InstallerBehaviour> x86Behaviour, HashMap<String, InstallerBehaviour> x64Behaviour) {
        if (inst.getjCheckBox1().isSelected()) {
            addInstallerNode(installer32, x86Behaviour);
        }
        if (inst.getjCheckBox2().isSelected()) {
            //not supported in present schema
            addInstallerNode(installer64, x64Behaviour);
        }
    }

    void addConstituents(ArrayList<ConstituentItem> constituentList) {
        for(ConstituentItem cItem :constituentList){
            constituent = new Constituent();
            constituent.setConstituentId(cItem.getComponentId());
            for(ImageData iData : cItem.getImageList()){
                constituent.addImage(iData);
            }
//            constituent.setImageItemList((List<ImageItem>) cItem.getImageList().clone());
            this.getConstituent().add(constituent);
        }
    }

    private void addInstallerNode(InstallerSub1 inst,HashMap<String, InstallerBehaviour> behaviour) {
        this.installer = new Installer();
            for (Map.Entry<String, InstallerBehaviour> entry : behaviour.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(EnumBehaviors.extractdrivers.toString()) && !entry.getValue().getjTextField1().getText().isEmpty()) {
                    installerBehavior = new InstallerBehavior();
                    installerBehavior.setStaticExtraction(entry.getValue().getjTextField1().getText());
                    installer.getInstallerBehavior().add(installerBehavior);
                } else {
                    if (entry.getValue().getjTable1().getRowCount() > 0) {
                        installerBehavior = new InstallerBehavior();
                        installerBehavior.setEnumBehavior(EnumBehaviors.fromValue(entry.getKey()));
                        DefaultTableModel dtm = (DefaultTableModel) entry.getValue().getjTable1().getModel();
                        int nRow = dtm.getRowCount();
                        for (int i = 0; i < nRow; i++) {
                            pgmExtraction = new PgmExtraction();
                            pgmExtraction.setDelimiterValue(dtm.getValueAt(i, 0).toString());
                            pgmExtraction.setEnclose(dtm.getValueAt(i, 3).toString());
                            pgmExtraction.setRequireValue((Boolean)dtm.getValueAt(i,2));
                            pgmExtraction.setSwitch(dtm.getValueAt(i, 0).toString());
                            pgmExtraction.setValue(dtm.getValueAt(i, 1).toString());
                            installerBehavior.getPgmExtraction().add(pgmExtraction);                           
                        }
                        installer.getInstallerBehavior().add(installerBehavior);
                    }
                }

            }
            installer.setName(inst.getjTextField1().getText());
            installer.setMandatoryParameters(inst.getjTextField3().getText());
            installer.setArchitecture(EnumOSArchitecture.x86.toString());
            installer.setTimeOut(Integer.parseInt(inst.getjTextField2().getText()));
            if(!inst.getjTextField4().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.SUCCESS, inst.getjTextField4().getText());
            }
            if(!inst.getjTextField5().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.ERROR, inst.getjTextField5().getText());
            }
            if(!inst.getjTextField6().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.REBOOT_REQUIRED, inst.getjTextField6().getText());
            }
            if(!inst.getjTextField7().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.DEP_SOFT_ERROR, inst.getjTextField7().getText());
            }
            if(!inst.getjTextField8().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.DEP_HARD_ERROR, inst.getjTextField8().getText());
            }
            if(!inst.getjTextField9().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.ERROR_INSTALL_PLATFORM_UNSUPPORTED, inst.getjTextField9().getText());
            }
            if(!inst.getjTextField10().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.REBOOTING_SYSTEM, inst.getjTextField10().getText());
            }
            if(!inst.getjTextField11().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.PASSWORD_REQUIRED, inst.getjTextField11().getText());
            }
            if(!inst.getjTextField12().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.NO_DOWNGRADE, inst.getjTextField12().getText());
            }
            if(!inst.getjTextField13().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.REBOOT_UPDATE_PENDING, inst.getjTextField13().getText());
            }
            if(!inst.getjTextField14().getText().isEmpty()){
                installer.getReturnCodeMap().put(EnumReturnCodeMap.UNKNOWN_OPTION, inst.getjTextField14().getText());
            }
            this.getInstallerList().add(installer);
    }

    public InventoryItem getPackageLevelInventory() {
        return packageLevelInventory;
    }
   
    
    
    private String name;
    private String mupSpecVersion;
    private String version;
    private EnumReleaseTypesProgrammatic releasetype;
    private List<OSIdentifier> applicableOS;
    private List<Installer> installerList;
    private List<Constituent> constituentList;
    private InventoryItem packageLevelInventory;
    
    private OSIdentifier osIdentifier;
    private Installer installer;
    private InstallerBehavior installerBehavior;
    private PgmExtraction pgmExtraction;
    private Constituent constituent;

    private void addPackageLevelInventory(PackageInformation packageInformation, Constituents constituents,ArrayList<ConstituentItem> constituentList) {
        packageLevelInventory = new InventoryItem();
        if (!packageInformation.getjComboBox2().getSelectedItem().toString().equalsIgnoreCase("bios")) {
            packageLevelInventory.setInventoryMethod(constituents.getjComboBox1().getSelectedItem().toString());
//            if (packageLevelInventory.getInventoryMethod().equalsIgnoreCase("pnpIds")) {
//                for (ConstituentItem cItem : constituentList) {
//                    for (ImageData iData : cItem.getImageList()) {
//                        for(ImageInventoryData iiData : iData.getImageInventoryDataList()){
//                            if(iiData.getInventoryMethod().equalsIgnoreCase("pnpIds")){
//                                for(Object[] row : iiData.getTableData()){
//                                    packageLevelInventory.addPnpItem(row);
//                                }
//                            }
//                        }
//                        constituent.addImage(iData);
//                    }
//                    this.getConstituent().add(constituent);
//                }
//
//            } else 
            
            if (packageLevelInventory.getInventoryMethod().equalsIgnoreCase("msis")) {
                Object[][] data = constituents.getMsis().getTableData();
                for (Object[] row : data) {
                    packageLevelInventory.addMsisItem(row);
                }
            } else if (packageLevelInventory.getInventoryMethod().equalsIgnoreCase("registryKeys")) {
                Object[][] data = constituents.getRegistrykeys().getTableData();
                for (Object[] row : data) {
                    packageLevelInventory.addRegItem(row);
                }
            } else if (packageLevelInventory.getInventoryMethod().equalsIgnoreCase("softwareIdentityInstances")) {
                Object[][] data = constituents.getSoftwareIdentityInstance().getTableData();
                for (Object[] row : data) {
                    packageLevelInventory.addSIIItem(row);
                }
            }
        } else {
            packageLevelInventory.setBiosVersion(constituents.getjTextField2().getText());
        }
        
    }
    
}

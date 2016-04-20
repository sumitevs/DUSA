/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.Dependency;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class DependencyInfo {

    public DependencyInfo() {
       deviceInfo = new DeviceInfo();
       displayTextList = new ArrayList<>();
       featureMissedList = new ArrayList<>();
        osInfo = new UnsupportedOSInfo();
        brandInfo = new BrandInfo();
    }

    public List<DisplayType> getDisplayTextList() {
        return displayTextList;
    }

    public List<DisplayType> getFeatureMissedList() {
        return featureMissedList;
    }

    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    
    public String getReleaseID() {
        return releaseID;
    }

    public void setReleaseID(String releaseID) {
        this.releaseID = releaseID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public DeviceInfo getDeviceInfo() {
       return deviceInfo;
    }
   

    public UnsupportedOSInfo getOsInfo() {
        return osInfo;
    }

    public BrandInfo getBrandInfoList() {
        return brandInfo;
    }

    private String releaseID;
    private String version;
    private String path;
    private String componentID;
    private String prerequisite;
    private String componentType;
    private String context;
    
    //device info
    private DeviceInfo deviceInfo;
    
    //display details
    private List<DisplayType> displayTextList;
    
    //features missed
    private List<DisplayType> featureMissedList;
    
    // list of os
    private UnsupportedOSInfo osInfo;
    
    //list of brands
    private BrandInfo brandInfo;

    void load(Dependency dependency) {
        
        this.releaseID = dependency.getjTextField2().getText();
        this.version = dependency.getjTextField1().getText();
        this.path = dependency.getjTextField4().getText();
        this.componentID = dependency.getjTextField3().getText();
        this.prerequisite = dependency.getjTextField5().getText();
        if(dependency.getjComboBox1().getSelectedIndex()>0){
            this.componentType = dependency.getjComboBox1().getSelectedItem().toString();
        }
        if(dependency.getjComboBox2().getSelectedIndex()>0){
            this.context = dependency.getjComboBox2().getSelectedItem().toString();
        }
       
        
        //device info
        this.deviceInfo.load(dependency.getDevices());
        
        //display details
        int rowCount = dependency.getDisplayDetail().getjTable1().getRowCount();
        for(int count=0;count<rowCount;count++){
            this.displayTextList.add(new DisplayType(dependency.getDisplayDetail().getjTable1().getValueAt(count,0).toString(),"en"));
        }
        
        //features missed
        rowCount = dependency.getDisplayDetail().getjTable2().getRowCount();
        for(int count=0;count<rowCount;count++){
            this.featureMissedList.add(new DisplayType(dependency.getDisplayDetail().getjTable2().getValueAt(count,0).toString(),"en"));
        }
        
        // list of os
        rowCount = dependency.getListOS().getListB().getSize();
        for(int count=0;count<rowCount;count++){
            this.osInfo.addOS(dependency.getListOS().getListB().getElementAt(count).toString());
        }
        
        //list of brands
        this.brandInfo = dependency.getBrand().getBrandInfo();
        
    }
    
}

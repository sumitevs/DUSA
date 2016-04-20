/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.InventoryMain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
class InventoryInfo implements PluginType {

    @Override
    public String getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    @Override
    public String getStartFileName() {
        return startFileName;
    }

    public void setName(String name) {
        this.startFileName = name;
    }

    @Override
    public String getCommandStd() {
        return commandStd;
    }

    @Override
    public void setCommandStd(String commandStd) {
        this.commandStd = commandStd;
    }

    @Override
    public String getCommandFile() {
        return commandFile;
    }

    @Override
    public void setCommandFile(String commandFile) {
        this.commandFile = commandFile;
    }

    @Override
    public String getModules() {
        return modules;
    }

    @Override
    public void setModules(String modules) {
        this.modules = modules;
    }

    @Override
    public List<Returncodes> getReturnCodeList() {
        if(returnCodeList==null){
            returnCodeList = new ArrayList<>();
        }
        return returnCodeList;
    }

    @Override
    public FeatureSetInfo getFeatureSetInfo() {
        if(featureSetInfo==null){
            featureSetInfo = new FeatureSetInfo();
        }
        return featureSetInfo;
    }

    @Override
    public UnsupportedOSInfo getUnsupportedOSInfo() {
        if(unsupportedOSInfo==null){
            unsupportedOSInfo = new UnsupportedOSInfo();
        }
        return unsupportedOSInfo;
    }
    
    @Override
    public String getCommandFileName() {
        return this.commandFileName;
    }

    @Override
    public String getCommandFileForceName() {
       return null;
    }

    //inventory info
    private String timeout;
    private String startFileName;
    private String commandStd;
    private String commandFile;
    private String commandFileName;
    private String modules;
    private List<Returncodes> returnCodeList;
    
    //feature set info
    private FeatureSetInfo featureSetInfo;
    
    //unsupported info
    private UnsupportedOSInfo unsupportedOSInfo;

    void load(InventoryMain inventory) {
        //load inventory info
        this.timeout = inventory.getInventory().getjTextField1().getText();
        this.startFileName = inventory.getInventory().getjTextField2().getText();
        this.commandStd = inventory.getInventory().getjTextField3().getText();
        this.commandFile = inventory.getInventory().getjTextField4().getText();
        this.commandFileName = inventory.getInventory().getjTextField6().getText();
        this.modules = inventory.getInventory().getjTextField5().getText();
        int rowCount = inventory.getInventory().getjTable1().getRowCount();
        for(int count=0;count<rowCount;count++){
            getReturnCodeList().add(new Returncodes(inventory.getInventory().getjTable1().getValueAt(count,0).toString(),inventory.getInventory().getjTable1().getValueAt(count,1).toString()));
        }
        
        //load feature set
        getFeatureSetInfo().load(inventory.getFeatureSet());
        
        //load os list
        getUnsupportedOSInfo().load(inventory.getListOS());       
    }

    @Override
    public String getCommandFileForce() {
        return null;
    }

    @Override
    public String getCommandStdForce() {
        return null;
    }

    @Override
    public String getCopyPayload() {
        return null;
    }

     @Override
    public String getSystemReboot() {
        return null;
    }

    @Override
    public void setCommandFileForce(String commandFileForce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCommandStdForce(String commandStdForce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCopyPayload(String copyPayload) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStartFileName(String startFileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSystemReboot(String systemReboot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        
}

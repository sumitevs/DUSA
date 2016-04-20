/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.ExecutionMain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
class ExecutionInfo implements PluginType{

    @Override
    public String getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(String timeout) {
        this.timeout = timeout;
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
    public String getStartFileName() {
        return startFileName;
    }

    @Override
    public void setStartFileName(String startFileName) {
        this.startFileName = startFileName;
    }

    @Override
    public String getCommandStdForce() {
        return commandStdForce;
    }

    @Override
    public void setCommandStdForce(String commandStdForce) {
        this.commandStdForce = commandStdForce;
    }

    @Override
    public String getCommandFileForce() {
        return commandFileForce;
    }

    @Override
    public void setCommandFileForce(String commandFileForce) {
        this.commandFileForce = commandFileForce;
    }

    @Override
    public String getSystemReboot() {
        return systemReboot;
    }

    @Override
    public void setSystemReboot(String systemReboot) {
        this.systemReboot = systemReboot;
    }

    @Override
    public String getCopyPayload() {
        return copyPayload;
    }

    @Override
    public void setCopyPayload(String copyPayload) {
        this.copyPayload = copyPayload;
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
        return commandFileName;
    }

    @Override
    public String getCommandFileForceName() {
        return commandFileForceName;
    }
    
    

    //inventory info
    private String timeout;
    private String startFileName;
    private String commandStd;
    private String commandFile;
    private String commandFileName;
    private String commandStdForce;
    private String commandFileForce;
    private String commandFileForceName;
    private String modules;
    private List<Returncodes> returnCodeList;
    private String systemReboot;
    private String copyPayload;
    
    //feature set info
    private FeatureSetInfo featureSetInfo;
    
    //unsupported info
    private UnsupportedOSInfo unsupportedOSInfo;

    void load(ExecutionMain execution) {
        //load inventory info
        this.timeout = execution.getExecution().getjTextField1().getText();
        this.startFileName = execution.getExecution().getjTextField2().getText();
        this.commandStd = execution.getExecution().getjTextField3().getText();
        this.commandFile = execution.getExecution().getjTextField4().getText();
        this.commandFileName = execution.getExecution().getjTextField10().getText();
        this.commandStdForce = execution.getExecution().getjTextField5().getText();
        this.commandFileForce = execution.getExecution().getjTextField6().getText();
        this.commandFileForceName = execution.getExecution().getjTextField11().getText();
        this.modules = execution.getExecution().getjTextField7().getText();
        int rowCount = execution.getExecution().getjTable1().getRowCount();
        for(int count=0;count<rowCount;count++){
            getReturnCodeList().add(new Returncodes(execution.getExecution().getjTable1().getValueAt(count,0).toString(),execution.getExecution().getjTable1().getValueAt(count,1).toString()));
        }
        this.systemReboot = execution.getExecution().getjCheckBox1().isSelected()?"true":"false";
        this.copyPayload = execution.getExecution().getjCheckBox1().isSelected()?"true":"false";
        //load feature set
        getFeatureSetInfo().load(execution.getFeatureSet());
        
        //load os list
        getUnsupportedOSInfo().load(execution.getListOS());       
    }
        
}

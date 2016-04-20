/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class Installer {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMandatoryParameters() {
        return mandatoryParameters;
    }

    public void setMandatoryParameters(String mandatoryParameters) {
        this.mandatoryParameters = mandatoryParameters;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public List<InstallerBehavior> getInstallerBehavior() {
        if (installerBehavior == null) {
                installerBehavior = new ArrayList<InstallerBehavior>();
            }
        return this.installerBehavior;
    }

    public HashMap<EnumReturnCodeMap, String> getReturnCodeMap() {
        if (returnCodeMap == null) {
                returnCodeMap = new HashMap<EnumReturnCodeMap,String>();
            }
        return returnCodeMap;
    }

    public void setReturnCodeMap(HashMap<EnumReturnCodeMap, String> returnCodeMap) {
        this.returnCodeMap = returnCodeMap;
    }
   
    
   
    protected String name;
    protected String mandatoryParameters;
    protected String architecture;    
    protected int timeOut;
    protected List<InstallerBehavior> installerBehavior;
    protected HashMap<EnumReturnCodeMap,String> returnCodeMap;

}

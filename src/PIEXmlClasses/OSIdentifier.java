/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class OSIdentifier {

    public OSIdentifier() {

    }

    OSIdentifier(String majorV, String minorV, String osArch, String osCode, String spMajorV, String spMinorV) {
        this.majorVersion = majorV;
        this.minorVersion = minorV;
        this.osArch = osArch;
        this.osCode = osCode;
        this.spMajorVersion = spMajorV;
        this.spMinorVersion = spMinorV;
        this.suiteMask = -1;
        
        this.osVendor="";
        this.osType="";
        this.preinstallationEnvironment=null;
    
    }

    public List<DisplayType> getDisplay() {
        if (display == null) {
            display = new ArrayList<>();
        }
        return display;
    }

    public SupportedLanguagesType getSupportedLanguages() {
        if(supportedLanguages==null){
            supportedLanguages = new SupportedLanguagesType();
        }
                
        return supportedLanguages;
    }

    public void setSupportedLanguages(SupportedLanguagesType supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public String getOsCode() {
        return osCode;
    }

    public void setOsCode(String osCode) {
        this.osCode = osCode;
    }

    public String getOsVendor() {
        return osVendor;
    }

    public void setOsVendor(String osVendor) {
        this.osVendor = osVendor;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }

    public String getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(String minorVersion) {
        this.minorVersion = minorVersion;
    }

    public String getSpMajorVersion() {
        return spMajorVersion;
    }

    public void setSpMajorVersion(String spMajorVersion) {
        this.spMajorVersion = spMajorVersion;
    }

    public String getSpMinorVersion() {
        return spMinorVersion;
    }

    public void setSpMinorVersion(String spMinorVersion) {
        this.spMinorVersion = spMinorVersion;
    }

    public Boolean getPreinstallationEnvironment() {
        return preinstallationEnvironment;
    }

    public void setPreinstallationEnvironment(Boolean preinstallationEnvironment) {
        this.preinstallationEnvironment = preinstallationEnvironment;
    }

    public int getSuiteMask() {
        return suiteMask;
    }

    public void setSuiteMask(int suiteMask) {
        this.suiteMask = suiteMask;
    }

    private List<DisplayType> display;
    private SupportedLanguagesType supportedLanguages;
    private String osCode;
    private String osVendor;
    private String osArch;
    private String osType;
    private String majorVersion;
    private String minorVersion;
    private String spMajorVersion;
    private String spMinorVersion;
    private Boolean preinstallationEnvironment;
    private int suiteMask;

}

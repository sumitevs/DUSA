/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

/**
 *
 * @author Sumit_Saseendran
 */
public class OSIdentifier {

    public String getOsName() {
        return osName.value();
    }

    public void setOsName(EnumOSNames osName) {
        this.osName = osName;
    }

    public EnumOSArchitecture getOsArchitecture() {
        return osArchitecture;
    }

    public void setOsArchitecture(EnumOSArchitecture osArchitecture) {
        this.osArchitecture = osArchitecture;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String Vendor) {
        this.Vendor = Vendor;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
    private EnumOSNames osName;
    private EnumOSArchitecture osArchitecture;
    private String Vendor;
    private String majorVersion;
    private String minorVersion;
    private String spMajorVersion;
    private String spMinorVersion;
    private String locale;
    
}

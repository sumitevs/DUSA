/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

/**
 *
 * @author Sumit_Saseendran
 */
public class PCIIDs {
    private String deviceID;
    private String vendorID;
    private String subDeviceID;
    private String subVendorID;

    PCIIDs(String devId, String venId, String subDevId, String subVenId) {
        this.deviceID = devId;
        this.vendorID = venId;
        this.subDeviceID = subDevId;
        this.subVendorID = subVenId;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public String getSubDeviceID() {
        return subDeviceID;
    }

    public String getSubVendorID() {
        return subVendorID;
    }
    
    
    
}

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
public class PlugPlayIDs {
    private String deviceID;
    private String productID;

    PlugPlayIDs(String devId, String prodId) {
        this.deviceID = devId;
        this.productID = prodId;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getProductID() {
        return productID;
    }
    
    
}

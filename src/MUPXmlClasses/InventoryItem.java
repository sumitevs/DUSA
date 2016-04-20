/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import gui.mup.ImageInventoryData;
import java.util.ArrayList;

/**
 *
 * @author Sumit_Saseendran
 */
public class InventoryItem {
    
    private String biosVersion;
    private String inventoryMethod;
    private ArrayList<PnpItem> pnpList;
    private ArrayList<MsisItem> msisList;
    private ArrayList<RegItem> regList;
    private ArrayList<SIIItem> sIIList;
    private PnpItem pnpItem;
    private MsisItem msisItem;
    private RegItem regItem;
    private SIIItem sIIItem;

    public String getBiosVersion() {
        return biosVersion;
    }

    public void setBiosVersion(String biosVersion) {
        this.biosVersion = biosVersion;
    }

    
    public String getInventoryMethod() {
        return inventoryMethod;
    }

    public void setInventoryMethod(String inventoryMethod) {
        this.inventoryMethod = inventoryMethod;
    }

    public ArrayList<PnpItem> getPnpList() {
        if(pnpList==null){
            pnpList = new ArrayList<PnpItem>();
        }
        return pnpList;
    }

    public ArrayList<MsisItem> getMsisList() {
        if(msisList==null){
            msisList = new ArrayList<MsisItem>();
        }
        return msisList;
    }

    public ArrayList<RegItem> getRegList() {
        if(regList==null){
            regList = new ArrayList<RegItem>();
        }
        return regList;
    }
   
    public ArrayList<SIIItem> getsIIList() {
        if(sIIList==null){
            sIIList = new ArrayList<SIIItem>();
        }
        return sIIList;
    }

    void addInventoryData(ImageInventoryData iiData) {
        this.inventoryMethod = iiData.getInventoryMethod();
        Object[][] data = iiData.getTableData();
        for (int i = 0; i < data.length; i++) {
            if (this.inventoryMethod.equalsIgnoreCase("pnpIds")) {
                addPnpItem(data[i]);
            } else if (this.inventoryMethod.equalsIgnoreCase("msis")) {
                addMsisItem(data[i]);
            } else if (this.inventoryMethod.equalsIgnoreCase("registryKeys")) {
                addRegItem(data[i]);
            } else if (this.inventoryMethod.equalsIgnoreCase("softwareIdentityInstances")) {
                addSIIItem(data[i]);
            }
        }
    }

    void addMsisItem(Object[] data) {
        int size = data.length;
        msisItem = new MsisItem();
        msisItem.setComponentId(size>0?data[0].toString():"");
        msisItem.setIdentifyingNumber(size>1?data[1].toString():"");
        msisItem.setName(size>2?data[2].toString():"");
        msisItem.setUpgradeCode(size>3?data[3].toString():"");
        msisItem.setVersion(size>4?data[4].toString():"");
        msisItem.setVendor(size>5?data[5].toString():"");
        msisItem.setCaption(size>6?data[6].toString():"");
        this.getMsisList().add(msisItem);
    }

    void addRegItem(Object[] data) {
        int size = data.length;
        regItem = new RegItem();
        regItem.setComponentId(size>0?data[0].toString():"");
        regItem.setName(size>1?data[1].toString():"");
        regItem.setValue(size>2?data[2].toString():"");
        regItem.setDisplayName(size>3?data[3].toString():"");
        regItem.setDisplayValue(size>4?data[4].toString():"");
        this.getRegList().add(regItem);
    }

    void addSIIItem(Object[] data) {
        int size = data.length;
        sIIItem = new SIIItem();
        sIIItem.setComponentId(size>0?data[0].toString():"");
        sIIItem.setElementName(size>1?data[1].toString():"");
        sIIItem.setVersion(size>2?data[2].toString():"");
        sIIItem.setValue(size>3?data[3].toString():"");
        sIIItem.setType(size>4?data[4].toString():"");
        this.getsIIList().add(sIIItem);
    }

    void addPnpItem(Object[] data) {
        int size = data.length;
        pnpItem = new PnpItem();
        pnpItem.setDeviceType(size>0? data[0].toString():"");
        pnpItem.setDeviceId(size>1? data[1].toString():"");
        pnpItem.setVendorId(size>2? data[2].toString():"");
        pnpItem.setSubDeviceId(size>3? data[3].toString():"");
        pnpItem.setSubVendorId(size>4? data[4].toString():"");
        pnpItem.setIdentifier(size>5? data[5].toString():"");
        this.getPnpList().add(pnpItem);
    }

}

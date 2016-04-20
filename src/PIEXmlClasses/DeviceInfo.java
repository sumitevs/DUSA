/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.Devices;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class DeviceInfo {
     public List<PCIIDs> getPciIds() {
        if(pciIds==null){
            pciIds = new ArrayList<>();
        }
        return pciIds;
    }

    public List<PlugPlayIDs> getPlugPlayIds() {
        if(plugPlayIds==null){
            plugPlayIds = new ArrayList<>();
        }
        return plugPlayIds;
    }
    
    public void addPCIID(String devId,String venId,String subDevId,String subVenId){
        getPciIds().add(new PCIIDs(devId,venId,subDevId,subVenId));
    }
    
    public void addPlugPlay(String devId,String prodId){
        getPlugPlayIds().add(new PlugPlayIDs(devId,prodId));
    }
    private List<PCIIDs> pciIds;
    private List<PlugPlayIDs> plugPlayIds;

    void load(Devices devices) {
        int rowCount=devices.getjTable1().getRowCount();
        for(int count=0;count<rowCount;count++){
            addPCIID(devices.getjTable1().getValueAt(count,0).toString(),devices.getjTable1().getValueAt(count,1).toString(),devices.getjTable1().getValueAt(count,2).toString(),devices.getjTable1().getValueAt(count,3).toString());
        }
        rowCount=devices.getjTable2().getRowCount();
        for(int count=0;count<rowCount;count++){
            addPlugPlay(devices.getjTable2().getValueAt(count,0).toString(),devices.getjTable2().getValueAt(count,1).toString());
        }
    }
}

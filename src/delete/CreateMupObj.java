/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import MUPXmlClasses.Constituent;
import MUPXmlClasses.Installer;
import MUPXmlClasses.InstallerBehavior;
import MUPXmlClasses.InventoryItem;
import MUPXmlClasses.MupDef;
import MUPXmlClasses.OSIdentifier;
import MUPXmlClasses.PgmExtraction;
import gui.mup.Applicability;
import gui.mup.ConstituentItem;
import gui.mup.Constituents;
import gui.mup.InstallerBehaviour;
import gui.mup.InstallerSub1;
import gui.mup.PackageInformation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sumit_Saseendran
 */
public class CreateMupObj {
    
    protected MupDef mupDef;
    protected OSIdentifier osIdentifier;
    protected Installer installer;
    protected Constituent constituent;
    protected InstallerBehavior installerBehavior;
    protected PgmExtraction pgmExtraction;
    protected InventoryItem fullPackageInventory;
//    HashMap<String, String> osEnumMap = new HashMap<String, String>();

    public CreateMupObj(PackageInformation packageInformation, Applicability applicability, gui.mup.Installer inst, InstallerSub1 installer32, InstallerSub1 installer64, HashMap<String, InstallerBehaviour> x86Behaviour, HashMap<String, InstallerBehaviour> x64Behaviour, Constituents constituents, ArrayList<ConstituentItem> constituentList) {
        
       
        
                  
        
    }

    
    
    
    public MupDef getMupDef() {
        return mupDef;
    }

//    private void createOsEnumMap() {
//        osEnumMap.put("Microsoft Windows Vista - 32 bit", null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        osEnumMap.put(null, null);
//        
//        
//    }
}

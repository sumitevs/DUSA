/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.PIETree;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class PieDef {

    public PieDef() {
        init();
    }

    public PieDef(PIETree pieTree) {
        init();
        this.pieIdentifier = pieTree.getPieRoot().getjTextField1().getText();
        this.folderName = pieTree.getPieRoot().getjTextField2().getText();
        this.payloadCapsule = pieTree.getPieRoot().getjCheckBox3().isSelected()?"true":"false";
        this.payloadInfoList = pieTree.getPayloadMain().getPayloadList();
        this.inventoryInfo.load(pieTree.getInventory());
        this.executionInfo.load(pieTree.getExecution());
        this.unsupportedOSInfo.load(pieTree.getUnsupportedOS());
        this.featureSetInfo.load(pieTree.getFeatureSet());
        this.dependencyInfo.load(pieTree.getDependency());
        this.softDependencyInfo.load(pieTree.getSoftDependency());
    }

    public String getPieIdentifier() {
        return pieIdentifier;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getPayloadCapsule() {
        return payloadCapsule;
    }

    public List<PayloadInfo> getPayloadInfoList() {
        return payloadInfoList;
    }

    public InventoryInfo getInventoryInfo() {
        return inventoryInfo;
    }

    public ExecutionInfo getExecutionInfo() {
        return executionInfo;
    }

    public UnsupportedOSInfo getUnsupportedOSInfo() {
        return unsupportedOSInfo;
    }

    public FeatureSetInfo getFeatureSetInfo() {
       return featureSetInfo;
    }

    public DependencyInfo getDependencyInfo() {
        return dependencyInfo;
    }

    public DependencyInfo getSoftDependencyInfo() {
        return softDependencyInfo;
    }
    
    
    public void init(){
        payloadInfoList= new ArrayList<>();
        inventoryInfo = new InventoryInfo();
        executionInfo = new ExecutionInfo();
        unsupportedOSInfo = new UnsupportedOSInfo();
        featureSetInfo = new FeatureSetInfo();
        dependencyInfo = new DependencyInfo();
        softDependencyInfo = new DependencyInfo();
    }
    
    private String pieIdentifier;
    private String folderName;
    private String payloadCapsule;
    private List<PayloadInfo> payloadInfoList;
    private InventoryInfo inventoryInfo;
    private ExecutionInfo executionInfo;
    private UnsupportedOSInfo unsupportedOSInfo;
    private FeatureSetInfo featureSetInfo;
    private DependencyInfo dependencyInfo;
    private DependencyInfo softDependencyInfo;
}

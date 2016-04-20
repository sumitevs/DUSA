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
public class PayloadInfo {

    public PayloadInfo() {
    }

    public String getPayloadType() {
        return payloadType;
    }

    public String getVersion() {
        return version;
    }

    public String getFMPWrapperID() {
        return FMPWrapperID;
    }

    public String getStartFile() {
        return startFile;
    }

    public String getModules() {
        return modules;
    }

    public String getImageID() {
        return imageID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSkip() {
        return skip;
    }

    public String getComponentIDs() {
        return componentIDs;
    }

    public String getFmpDrvrName() {
        return fmpDrvrName;
    }

    public String getPath() {
        return path;
    }

    public String getFmpDrvrVerison() {
        return fmpDrvrVerison;
    }

    public FmpComparisonEnum getFmpComparison() {
        return fmpComparison;
    }

    public DeviceInfo getDeviceInfo() {
        if(deviceInfo==null){
            deviceInfo = new DeviceInfo();
        }
        return deviceInfo;
    }   

    public String getRollbackID() {
        return rollbackID;
    }

    public String getRollbackVolume() {
        return rollbackVolume;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getFmpWrapperVerison() {
        return fmpWrapperVerison;
    }

    public String getFmpID() {
        return fmpID;
    }

    public String getRollbackTimeout() {
        return rollbackTimeout;
    }

    public String getUpdateImpact() {
        return updateImpact;
    }

    public FieldServiceEnum getFieldServiceEnum() {
        return fieldServiceEnum;
    }

    public String getFmpVendorCode() {
        return fmpVendorCode;
    }

    public String getFmpVendorCodeType() {
        return fmpVendorCodeType;
    }

    public String getFmpFileExtension() {
        return fmpFileExtension;
    }

    public String getSwapDevice() {
        return swapDevice;
    }

    public String getApplicationNme() {
        return applicationNme;
    }

    public String getAlternateRbID() {
        return alternateRbID;
    }

    public String getFmpWrapperName() {
        return fmpWrapperName;
    }

    public String getDvrNameFmp() {
        return dvrNameFmp;
    }

    public String getInvSource() {
        return invSource;
    }

       
    public String getFileNameFmp() {
        return fileNameFmp;
    }

    public String getInventorySupp() {
        return inventorySupp;
    }

    public String getUpdateSupp() {
        return updateSupp;
    }

    public String getRollbackSupp() {
        return rollbackSupp;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setFMPWrapperID(String FMPWrapperID) {
        this.FMPWrapperID = FMPWrapperID;
    }

    public void setStartFile(String startFile) {
        this.startFile = startFile;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public void setComponentIDs(String componentIDs) {
        this.componentIDs = componentIDs;
    }

    public void setFmpDrvrName(String fmpDrvrName) {
        this.fmpDrvrName = fmpDrvrName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFmpDrvrVerison(String fmpDrvrVerison) {
        this.fmpDrvrVerison = fmpDrvrVerison;
    }

    public void setFmpComparison(FmpComparisonEnum fmpComparison) {
        this.fmpComparison = fmpComparison;
    }

    public void setRollbackID(String rollbackID) {
        this.rollbackID = rollbackID;
    }

    public void setRollbackVolume(String rollbackVolume) {
        this.rollbackVolume = rollbackVolume;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setFmpWrapperVerison(String fmpWrapperVerison) {
        this.fmpWrapperVerison = fmpWrapperVerison;
    }

    public void setFmpID(String fmpID) {
        this.fmpID = fmpID;
    }

    public void setRollbackTimeout(String rollbackTimeout) {
        this.rollbackTimeout = rollbackTimeout;
    }

    public void setUpdateImpact(String updateImpact) {
        this.updateImpact = updateImpact;
    }

    public void setFieldServiceEnum(FieldServiceEnum fieldServiceEnum) {
        this.fieldServiceEnum = fieldServiceEnum;
    }

    public void setDvrNameFmp(String dvrNameFmp) {
        this.dvrNameFmp = dvrNameFmp;
    }

    public void setInvSource(String invSource) {
        this.invSource = invSource;
    }

    
    public void setFmpVendorCode(String fmpVendorCode) {
        this.fmpVendorCode = fmpVendorCode;
    }

    public void setFmpVendorCodeType(String fmpVendorCodeType) {
        this.fmpVendorCodeType = fmpVendorCodeType;
    }

    public void setFmpFileExtension(String fmpFileExtension) {
        this.fmpFileExtension = fmpFileExtension;
    }

    public void setSwapDevice(String swapDevice) {
        this.swapDevice = swapDevice;
    }

    public void setApplicationNme(String applicationNme) {
        this.applicationNme = applicationNme;
    }

    public void setAlternateRbID(String alternateRbID) {
        this.alternateRbID = alternateRbID;
    }

    public void setFmpWrapperName(String fmpWrapperName) {
        this.fmpWrapperName = fmpWrapperName;
    }


    public void setFileNameFmp(String fileNameFmp) {
        this.fileNameFmp = fileNameFmp;
    }

    public void setInventorySupp(String inventorySupp) {
        this.inventorySupp = inventorySupp;
    }

    public void setUpdateSupp(String updateSupp) {
        this.updateSupp = updateSupp;
    }

    public void setRollbackSupp(String rollbackSupp) {
        this.rollbackSupp = rollbackSupp;
    }

    public String getFmpDigiSign() {
        return fmpDigiSign;
    }

    public void setFmpDigiSign(String fmpDigiSign) {
        this.fmpDigiSign = fmpDigiSign;
    }

    public String getPayloadNumber() {
        return payloadNumber;
    }

    public void setPayloadNumber(String payloadNumber) {
        this.payloadNumber = payloadNumber;
    }
    
    
    
   private String payloadNumber;
    
    //payload info
   private String payloadType;
   private String version;
   private String FMPWrapperID;
   private String startFile;
   private String modules;
   
   //capsule  info
   private String imageID;
   private String fileName;
   private String skip;
   private String componentIDs;
   
   //fmp driver info
   private String fmpDrvrName;
   private String path;
   private String fmpDrvrVerison;
   private FmpComparisonEnum fmpComparison;
   
   //supported device info
   private DeviceInfo deviceInfo;
   
   //rollback info
   private String rollbackID;
   private String rollbackVolume;
   private String taskName;
   private String fmpWrapperVerison;
   private String fmpID;
   private String rollbackTimeout;
   private String updateImpact;
   private FieldServiceEnum fieldServiceEnum;
   private String fmpVendorCode;
   private String fmpVendorCodeType;
   private String fmpFileExtension;
   private String swapDevice;
   private String applicationNme;
   private String alternateRbID;
   
   //fwp wrapper info
   //FMPWrapperID in payload
   private String fmpWrapperName;
   private String fileNameFmp;
   private String dvrNameFmp;
   private String fmpDigiSign;
   private String inventorySupp;
   private String invSource;
   private String updateSupp;
   private String rollbackSupp;

    public void addFmpWrapperInfo(String fmpWrapperId, String fmpWrapperName, String fileName, String digiSign, String dvrName, String invSupp, String invSrc, String updateSupp, String rollbkSupp) {
        this.setFMPWrapperID(fmpWrapperId);
        this.setFmpWrapperName(fmpWrapperName);
        this.setFileNameFmp(fileName);
        this.setFmpDigiSign(digiSign);
        this.setDvrNameFmp(dvrName);
        this.setInventorySupp(invSupp);
        this.setInvSource(invSrc);
        this.setUpdateSupp(updateSupp);
        this.setRollbackSupp(rollbkSupp);
    }
}

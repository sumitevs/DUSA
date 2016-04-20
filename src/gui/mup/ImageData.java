/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mup;

import java.util.ArrayList;

/**
 *
 * @author Sumit_Saseendran
 */
public class ImageData {
    private String name;
    private String type;
    private String version;
    private String files;
    private String supportedOS;
    protected ArrayList<ImageInventoryData> imageInventoryDataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getSupportedOS() {
        return supportedOS;
    }

    public void setSupportedOS(String supportedOS) {
        this.supportedOS = supportedOS;
    }

    public ArrayList<ImageInventoryData> getImageInventoryDataList() {
        if(imageInventoryDataList==null){
            imageInventoryDataList = new ArrayList<ImageInventoryData>();
        }
        return imageInventoryDataList;
    }
}

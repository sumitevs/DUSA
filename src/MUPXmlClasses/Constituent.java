/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

import gui.mup.ImageData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class Constituent {

    private String constituentId;
    private List<ImageItem> imageItemList;
    
    public List<ImageItem> getImageItemList() {
        if (imageItemList == null) {
                imageItemList = new ArrayList<ImageItem>();
            }
        return imageItemList;
    }

    public String getConstituentId() {
        return constituentId;
    }

    public void setConstituentId(String constituentId) {
        this.constituentId = constituentId;
    }

//    public void setImageItemList(List<ImageItem> imageItemList) {
//        if (this.imageItemList == null) {
//                this.imageItemList = new ArrayList<ImageItem>();
//            }
//        this.imageItemList = imageItemList;
//    }

    void addImage(ImageData iData) {
        if (this.imageItemList == null) {
                this.imageItemList = new ArrayList<ImageItem>();
            }
        ImageItem imageItem=new ImageItem();
        imageItem.setName(iData.getName());
        imageItem.setVersion(iData.getVersion());
        imageItem.setEnumDeviceType(EnumReleaseTypesProgrammatic.fromValue(iData.getType()));
        imageItem.setFiles(iData.getFiles());
        imageItem.setOSIdentifiers(iData.getSupportedOS());
        imageItem.setInventoryList(iData.getImageInventoryDataList());
        this.imageItemList.add(imageItem);
    }
    
    
}

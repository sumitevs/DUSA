/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mup;

import java.util.ArrayList;

public class ConstituentItem {

    public ConstituentItem() {
        count++;
        key=String.valueOf(count);
    }

    
    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ConstituentItem.count = count;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

        
    public ArrayList<ImageData> getImageList() {
        if (imageDataList == null) {
            imageDataList = new ArrayList<ImageData>();
        }
        return imageDataList;
    }
    public void addImage(Object[][] imageData, ArrayList<ImageInventoryData> imageInventoryDataList) {
        for (Object[] item : imageData) {
            ImageData imageDataItem = new ImageData();
            imageDataItem.setName(item[0].toString());
            imageDataItem.setType(item[1].toString());
            imageDataItem.setVersion(item[2].toString());
            imageDataItem.setFiles(item[3].toString());
            imageDataItem.setSupportedOS(item[4].toString());
            for(ImageInventoryData iiData : imageInventoryDataList){
                imageDataItem.getImageInventoryDataList().add(iiData);            
            }
            this.getImageList().add(imageDataItem);
        }
    }

    private String componentId;
    private static int count = 0;
    private String key;
    private ArrayList<ImageData> imageDataList;
}

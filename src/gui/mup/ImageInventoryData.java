/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.mup;

/**
 *
 * @author Sumit_Saseendran
 */
public class ImageInventoryData {
    private String imageName;
    private String inventoryMethod;
    private Object[][] tableData;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getInventoryMethod() {
        return inventoryMethod;
    }

    public void setInventoryMethod(String inventoryMethod) {
        this.inventoryMethod = inventoryMethod;
    }

    public Object[][] getTableData() {
        return tableData;
    }

    public void setTableData(Object[][] tData) {
        
        this.tableData = tData.clone();
    }
    
  
}

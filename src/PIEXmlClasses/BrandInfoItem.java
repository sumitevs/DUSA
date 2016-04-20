/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JTable;

/**
 *
 * @author Sumit_Saseendran
 */
public class BrandInfoItem {

    public BrandInfoItem(String key, String prefix, DefaultListModel displayList, JTable jTable2) {
        this.key = key;
        this.prefix = prefix;
        int size = displayList.getSize();
        for (int count = 0; count < size; count++) {
            getDisplayTextList().add(new DisplayType(displayList.getElementAt(count).toString(), "en"));
        }
        int rowCount = jTable2.getRowCount();
        for (int count = 0; count < rowCount; count++) {
            getModelList().add(new Model(jTable2.getValueAt(count, 0).toString(), jTable2.getValueAt(count, 1).toString(), jTable2.getValueAt(count, 2).toString(), jTable2.getValueAt(count, 3).toString()));
        }
    }

    public BrandInfoItem() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Model> getModelList() {
        if(modelList==null){
            modelList= new ArrayList<>();
        }
        return modelList;
    }

    public void addModel(String sysId,String sysIdtype,String sysContext,String display){
        getModelList().add(new Model(sysId,sysIdtype,sysContext,display));
    }
    
    public List<DisplayType> getDisplayTextList() {
        if(displayTextList==null){
            displayTextList = new ArrayList<>();
        }
        return displayTextList;
    }
    
    //line of bussiness
    private String key;
    private String prefix;
    
    private List<Model> modelList;
    private List<DisplayType> displayTextList;
}

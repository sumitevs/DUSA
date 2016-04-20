/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIEXmlClasses;

import gui.pie.FeatureSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit_Saseendran
 */
public class FeatureSetInfo {

    public FeatureSetInfo() {
    }

    public List<FeatureSetItem> getFeatureSetList() {
        if(featureSetList==null){
            featureSetList = new ArrayList<>();
        }
        return featureSetList;
    }

    public void addFeature(String name,String value,String valSep){
        getFeatureSetList().add(new FeatureSetItem(name,value,valSep));
    }
       
    

    void load(FeatureSet featureSet) {
        int rowCount = featureSet.getjTable1().getRowCount();
        for(int count=0;count<rowCount;count++){
            addFeature(featureSet.getjTable1().getValueAt(count,0).toString(),featureSet.getjTable1().getValueAt(count,1).toString(),featureSet.getjTable1().getValueAt(count,2).toString());
        }
    }
    
    private List<FeatureSetItem> featureSetList;
}



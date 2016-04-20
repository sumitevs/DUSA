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
public class FeatureSetItem {
    
    private FeatureNameEnum featureName;
    private String value;
    private String valueSeparator;

    FeatureSetItem(String name, String value, String valSep) {
        this.featureName = FeatureNameEnum.fromValue(name);
        this.value = value;
        this.valueSeparator = valSep;
    }

    public FeatureNameEnum getFeatureName() {
        return featureName;
    }

    public String getValue() {
        return value;
    }

    public String getValueSeparator() {
        return valueSeparator;
    }

    public void setFeatureName(FeatureNameEnum featureName) {
        this.featureName = featureName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValueSeparator(String valueSeparator) {
        this.valueSeparator = valueSeparator;
    }
    
    
}

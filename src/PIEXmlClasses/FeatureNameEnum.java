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
public enum FeatureNameEnum {
    SelectiveUpdate("SelectiveUpdate"),
    DisableForceUpdate("DisableForceUpdate");

    private final String value;

    FeatureNameEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

   public static FeatureNameEnum fromValue(String v) {
        for (FeatureNameEnum c : FeatureNameEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
}

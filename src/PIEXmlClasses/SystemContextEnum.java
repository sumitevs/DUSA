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
public enum SystemContextEnum {

    Independent("Independent"),
    Chassis("Chassis"),
    SubChassis("SubChassis"),
    Rack("Rack");

    private final String value;

    SystemContextEnum(String v) {
        value = v;
    }
    public String value() {
        return value;
    }

    public static SystemContextEnum fromValue(String v) {
        for (SystemContextEnum c : SystemContextEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MUPXmlClasses;

/**
 *
 * @author Sumit_Saseendran
 */
public enum EnumDeviceType {
    PCI("PCI"),
    PNP("PNP"),
    Abstract("Abstract");
    private final String value;
    
    EnumDeviceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDeviceType fromValue(String v) {
        for (EnumDeviceType c: EnumDeviceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

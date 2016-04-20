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
public enum ComponentTypeEnum {

    APP("APP"),
    BIOS("BIOS"),
    DRVR("DRVR"),
    FRMW("FRMW");

    private final String value;

    ComponentTypeEnum(String v) {
        value = v;
    }
    public String value() {
        return value;
    }

    public static ComponentTypeEnum fromValue(String v) {
        for (ComponentTypeEnum c : ComponentTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
}

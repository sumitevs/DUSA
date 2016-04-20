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
public enum FmpWrapperEnum {

    APP("APP"),
    BIOS("BIOS"),
    DRVR("DRVR"),
    FRMW("FRMW");

    private final String value;

    FmpWrapperEnum(String v) {
        value = v;
    }
    public String value() {
        return value;
    }

    public static FmpWrapperEnum fromValue(String v) {
        for (FmpWrapperEnum c : FmpWrapperEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }

}

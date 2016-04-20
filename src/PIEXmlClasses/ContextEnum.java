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
public enum ContextEnum {

    OS("OS"),
    LC("LC"),
    iDRAC("iDRAC"),
    Console("Console"),
    All("All");

    private final String value;

    ContextEnum(String v) {
        value = v;
    }
    public String value() {
        return value;
    }

    public static ContextEnum fromValue(String v) {
        for (ContextEnum c : ContextEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
}

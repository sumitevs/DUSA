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
public enum InvSourceEnum {
    client("client"),
    server("server"),
    controller("controller"),
    Switch("switch");

    private final String value;

    InvSourceEnum(String v) {
        value = v;
    }
    public String value() {
        return value;
    }

    public static InvSourceEnum fromValue(String v) {
        for (InvSourceEnum c : InvSourceEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return null;
    }
    
}
